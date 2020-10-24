package br.com.wesleyeduardo.controle_biblioteca.dominio;
import br.com.wesleyeduardo.controle_biblioteca.builder.EmprestimoBuilder;
import br.com.wesleyeduardo.controle_biblioteca.builder.LivroBuilder;
import br.com.wesleyeduardo.controle_biblioteca.builder.UsuarioBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmprestimoTest {

    @Test
    void deveRealizarEmprestimoDeUmLivroNaoReservado(){

        Usuario usuario = UsuarioBuilder.umUsuario().constroi();

        Livro livro = LivroBuilder.umLivro().semReserva().constroi();

        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        System.out.println(emprestimo.toString());

        Assertions.assertDoesNotThrow(() -> emprestimo.setLivro(livro));
    }

    @Test
    void naoDeveRealizarEmprestimoDeUmLivroReservado(){


        Livro livro = LivroBuilder.umLivro().reservado().constroi();

        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();


        Assertions.assertThrows( IllegalArgumentException.class,
                () -> emprestimo.setLivro(livro),
                "Não pode realizar empréstimo de um livro já reservado" );
    }

    @Test
    void deveGarantirQueDataPrevistaEstejaCorretaAposLocacaoDoLivro(){

        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        LocalDate dataDeEmprestimo = LocalDate.of(2020, 10, 23);

        LocalDate dataDeDevolucaoPrevista = LocalDate.of(2020,10,30);

        emprestimo.setDataEmprestimo(dataDeEmprestimo);

        Assertions.assertEquals(emprestimo.getDataDevolucaoPrevista(), dataDeDevolucaoPrevista);
    }

    @Test
    void deveCriarEmprestimoComUsuarioSemEmprestimos(){

        Usuario usuario = UsuarioBuilder.umUsuario().constroi();

        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertDoesNotThrow(() -> emprestimo.setUsuario(usuario));
    }

    @Test
    void deveCriarEmprestimoComUsuarioComUmEmprestimo(){

        Usuario usuario = UsuarioBuilder.umUsuario().comUmEmprestimo().constroi();

        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertDoesNotThrow(() -> emprestimo.setUsuario(usuario));
    }

    @Test // teste com erro
    void deveCriarEmprestimoComUsuarioComDoisEmprestimos(){

        Usuario usuario = UsuarioBuilder.umUsuario().comDoisEmprestimos().constroi();

        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertDoesNotThrow(() -> emprestimo.setUsuario(usuario));
    }


    @Test
    void deveRealizarDevolucaoAntesDaDataPrevista(){

        //Usuario usuario = UsuarioBuilder.umUsuario().comUmEmprestimo().constroi();

        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

       // LocalDate dataDeEmprestimo = LocalDate.of(2020, 10, 23);

        LocalDate dataDeDevolucao = LocalDate.of(2020,10,25);

        emprestimo.setDataDeDevolucao(dataDeDevolucao);

        Assertions.assertEquals(new BigDecimal(5), emprestimo.getValorAluguel());
    }



}
