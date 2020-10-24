package br.com.wesleyeduardo.controle_biblioteca.dominio;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @SequenceGenerator(name = "id_usuario_seq", sequenceName = "usuario_id_usuario_seq",allocationSize=0,initialValue=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_usuario_seq")
    @Column(name="id_usuario")
    private Long id;

    @Column(name="nome")
    private String nome;

    @Column(name="matricula")
    private String matricula;

    @OneToMany
    private List<Emprestimo> historicoDeEmprestimos = new ArrayList<>();

    public Usuario(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public List<Emprestimo> getHistoricoDeEmprestimos() {
        return historicoDeEmprestimos;
    }

    public void setHistoricoDeEmprestimos(List<Emprestimo> historicoDeEmprestimos) {
        this.historicoDeEmprestimos = historicoDeEmprestimos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
