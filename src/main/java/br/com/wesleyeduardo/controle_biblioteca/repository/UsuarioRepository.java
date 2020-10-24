package br.com.wesleyeduardo.controle_biblioteca.repository;
import br.com.wesleyeduardo.controle_biblioteca.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNome(String nome);
}
