package br.com.wesleyeduardo.controle_biblioteca.builder;
import br.com.wesleyeduardo.controle_biblioteca.dominio.Livro;

public class LivroBuilder {

    private Livro livro;

    private LivroBuilder(){}

    public static LivroBuilder umLivro(){

        LivroBuilder builder = new LivroBuilder();
        builder.livro = new Livro();
        builder.livro.setAutor("Wesley Eduardo");
        builder.livro.setTitulo("Java");

        return builder;
    }

    public LivroBuilder comAutor(String autor){
        this.livro.setAutor(autor);
        return this;
    }

    public LivroBuilder comTitulo(String titulo){
        this.livro.setTitulo(titulo);
        return this;
    }

    public LivroBuilder reservado(){
        this.livro.setReservado(true);
        return this;
    }

    public LivroBuilder semReserva(){
        this.livro.setReservado(false);
        return this;
    }

    public Livro constroi(){
        return this.livro;
    }
}
