package br.com.wesleyeduardo.controle_biblioteca.dominio;
import br.com.wesleyeduardo.controle_biblioteca.builder.EmprestimoBuilder;
import br.com.wesleyeduardo.controle_biblioteca.builder.LivroBuilder;
import br.com.wesleyeduardo.controle_biblioteca.builder.UsuarioBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class EmprestimoTest {

    @Test
    void deveRealizarEmprestimoDeUmLivroNaoReservado(){

        Usuario usuario = UsuarioBuilder.umUsuario().constroi();
        Livro livro = LivroBuilder.umLivro().semReserva().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        LocalDate dataDevolucao = LocalDate.of(2013, 10, 30);

        emprestimo.setDataDeDevolucao(dataDevolucao);
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        Assertions.assertDoesNotThrow(() -> emprestimo.setLivro(livro));
    }

    @Test
    void naoDeveRealizarEmprestimoDeUmLivroReservado(){

        Usuario usuario = UsuarioBuilder.umUsuario().constroi();
        Livro livro = LivroBuilder.umLivro().reservado().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        emprestimo.setUsuario(usuario);

        Assertions.assertThrows( IllegalArgumentException.class,
                () -> emprestimo.setLivro(livro),
                "Não é possível realizar empréstimo de um livro já reservado" );
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
    void devePermitirEmprestimoComUsuarioSemEmprestimos(){

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

    @Test
    void deveCriarEmprestimoComUsuarioComDoisEmprestimos(){

        Usuario usuario = UsuarioBuilder.umUsuario().comDoisEmprestimos().constroi();

        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertDoesNotThrow(() -> emprestimo.setUsuario(usuario));
    }


    @Test// problema nesse teste
    void naoDeveCriarEmprestimoComUsuarioComTresEmprestimos(){

        Usuario usuario = UsuarioBuilder.umUsuario().comTresEmprestimos().constroi();

        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertThrows( IllegalArgumentException.class,
                () -> emprestimo.setUsuario(usuario),
                "Não pode realizar empréstimo para um usuário com dois livros já reservados" );

    }

    @Test
    void deveRealizarDevolucaoAntesDaDataPrevista(){

        Usuario usuario = UsuarioBuilder.umUsuario().constroi();
        Livro livro = LivroBuilder.umLivro().semReserva().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        LocalDate dataDeEmprestimo = LocalDate.of(2015, 11, 23);
        LocalDate dataDeDevolucao = LocalDate.of(2015,11,26);

        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(dataDeEmprestimo);
        emprestimo.setDataDeDevolucao(dataDeDevolucao);

        Assertions.assertEquals(5, emprestimo.getValorAluguel().doubleValue(), 0.001);
    }




}
