package br.com.vitor.usercrud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("P")
public abstract class Contabil{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable=false, updatable=false)
    private String tipo;
    private Double valor;
    private String descricao;
    private LocalDateTime dataPagamento;
    private Boolean fixo;
    @ManyToOne
    private Categoria categoria;

    public Contabil(Double valor, String descricao, Boolean fixo, Categoria categoria) {
        this.valor = valor;
        this.descricao = descricao;
        this.dataPagamento = LocalDateTime.now();
        this.fixo = fixo;
        this.categoria = categoria;
    }

    public Contabil() {
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Boolean getFixo() {
        return fixo;
    }

    public void setFixo(Boolean fixo) {
        this.fixo = fixo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
