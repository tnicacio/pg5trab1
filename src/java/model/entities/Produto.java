package model.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author tnica
 */
public class Produto implements Serializable {
    
    private Integer idproduto;
    private String descricao;
    private String observacao;
    private Float preco;
    private Float estoque;
    private Integer idcategoria;

    public Produto() {
    }

    public Produto(Integer idproduto, String descricao, String observacao, Float preco, Float estoque, Integer idcategoria) {
        this.idproduto = idproduto;
        this.descricao = descricao;
        this.observacao = observacao;
        this.preco = preco;
        this.estoque = estoque;
        this.idcategoria = idcategoria;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Float getEstoque() {
        return estoque;
    }

    public void setEstoque(Float estoque) {
        this.estoque = estoque;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.idproduto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.idproduto, other.idproduto)) {
            return false;
        }
        return true;
    }
    
}
