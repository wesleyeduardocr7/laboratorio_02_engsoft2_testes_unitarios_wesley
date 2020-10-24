package br.com.wesleyeduardo.controle_biblioteca.builder;
import br.com.wesleyeduardo.controle_biblioteca.dominio.Emprestimo;
import br.com.wesleyeduardo.controle_biblioteca.dominio.Livro;
import br.com.wesleyeduardo.controle_biblioteca.dominio.Usuario;

import java.time.LocalDate;

public class EmprestimoBuilder {

    private Emprestimo emprestimo;

    private EmprestimoBuilder(){}

    public static EmprestimoBuilder umEmprestimo(){

        LocalDate dataDoEmprestimo = LocalDate.of(2013, 10, 10);

        EmprestimoBuilder emprestimoBuilder = new EmprestimoBuilder();

        emprestimoBuilder.emprestimo = new Emprestimo();
        emprestimoBuilder.emprestimo.setDataEmprestimo(dataDoEmprestimo);

        return emprestimoBuilder;
    }

    public EmprestimoBuilder comLivro(Livro livro){
        this.emprestimo.setLivro(livro);
        return this;
    }

    public EmprestimoBuilder comUsuario(Usuario usuario){
        this.emprestimo.setUsuario(usuario);
        return this;
    }

    public EmprestimoBuilder comDataDeEmprestimo(LocalDate dataDeEmprestimo){
        this.emprestimo.setDataEmprestimo(dataDeEmprestimo);
        return this;
    }

    public EmprestimoBuilder comDataDeDevolucao(LocalDate dataDeDevolucao){
        this.emprestimo.setDataDeDevolucao(dataDeDevolucao);
        return this;
    }

    public Emprestimo constroi(){
        return this.emprestimo;
    }

}
