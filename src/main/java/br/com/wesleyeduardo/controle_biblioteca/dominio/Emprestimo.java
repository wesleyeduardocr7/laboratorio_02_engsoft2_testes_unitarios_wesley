package br.com.wesleyeduardo.controle_biblioteca.dominio;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="emprestimo")
public class Emprestimo {

    @Id
    @SequenceGenerator(name = "id_emprestimo_seq", sequenceName = "emprestimo_id_emprestimo_seq",allocationSize=0,initialValue=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_emprestimo_seq")
    @Column(name="id_emprestimo")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name="data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name="data_devolucao_prevista", nullable = false)
    private LocalDate dataDevolucaoPrevista;

    @Column(name="data_devolucao")
    private LocalDate dataDeDevolucao;

    @ManyToOne
    @JoinColumn(name="id_livro", nullable = false)
    private Livro livro;

    @Column(name="valor_aluguel")
    private BigDecimal valorAluguel;

    public Emprestimo(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataEmprestimo.plusDays(7);
    }


    public LocalDate getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public BigDecimal getValorAluguel() {

        if(isEmAtraso()) {

            BigDecimal valorMulta = calculaValorDaMulta(getDataDeDevolucao(), getDataDevolucaoPrevista());

            if (valorMulta.compareTo(new BigDecimal(0)) <= sessentaPorCentoValorFixoDoAlugue().scale()) {
                this.valorAluguel = getValorFixoAluguel().add(valorMulta);
            } else {
                this.valorAluguel = getValorFixoAluguel().add(sessentaPorCentoValorFixoDoAlugue());
            }
        }

        return valorAluguel;
    }

    private BigDecimal getValorFixoAluguel(){
        return new BigDecimal(5);
    }

    private boolean isEmAtraso(){
        return dataDeDevolucao.isAfter(dataDevolucaoPrevista);
    }

    private BigDecimal calculaValorDaMulta(LocalDate dataDeDevolucao, LocalDate dataDevolucaoPrevista){
        Integer quantidadeDeDiasEmAtraso = getQuantidadeDeDiasEmAtraso(dataDeDevolucao,dataDevolucaoPrevista);
        return  new BigDecimal(quantidadeDeDiasEmAtraso * 0.4);
    }

    private BigDecimal sessentaPorCentoValorFixoDoAlugue(){
        return new BigDecimal(5).multiply(new BigDecimal(0.6));
    }

    private Integer getQuantidadeDeDiasEmAtraso(LocalDate dataDeDevolucao, LocalDate dataDevolucaoPrevista){
        Integer diaDevolucao = dataDeDevolucao.getDayOfMonth();
        Integer diaDevolucaoPrevista = dataDevolucaoPrevista.getDayOfMonth();
        return diaDevolucao-diaDevolucaoPrevista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}