package br.com.wesleyeduardo.controle_biblioteca.repository;
import br.com.wesleyeduardo.controle_biblioteca.dominio.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    Livro findByNome(String nome);
}
