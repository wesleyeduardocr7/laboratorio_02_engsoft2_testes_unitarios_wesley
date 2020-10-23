package br.com.wesleyeduardo.controle_biblioteca.dominio;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="livro")
public class Livro {

    @Id
    @SequenceGenerator(name = "id_livro_seq", sequenceName = "livro_id_livro_seq",allocationSize=0,initialValue=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_livro_seq")
    @Column(name="id_livro")
    private Long id;

    @Column(name="autor", nullable = false)
    private String autor;

    @Column(name="titulo", nullable = false)
    private String titulo;

    @Column(name="emprestado")
    private boolean emprestado;

    @Column(name="reversado")
    private boolean reservado;

    @OneToMany(mappedBy = "livro")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Emprestimo> historicoEmprestimos;

    public Livro(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public List<Emprestimo> getHistoricoEmprestimos() {
        return historicoEmprestimos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id.equals(livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
