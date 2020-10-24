package br.com.wesleyeduardo.controle_biblioteca.repository;
import br.com.wesleyeduardo.controle_biblioteca.dominio.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
}
