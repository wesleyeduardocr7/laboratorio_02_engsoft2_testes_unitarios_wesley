package br.com.wesleyeduardo.controle_biblioteca.dominio;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
    private Set<Emprestimo> historicoDeEmprestimos = new LinkedHashSet<>();

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

    public Set<Emprestimo> getHistoricoDeEmprestimos() {
        return historicoDeEmprestimos;
    }

    public void setHistoricoDeEmprestimos(Set<Emprestimo> historicoDeEmprestimos) {
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
