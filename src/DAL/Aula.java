/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pedro Ferreira
 */
@Entity
@Table(name = "AULA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aula.findAll", query = "SELECT a FROM Aula a order by a.idaula")
    , @NamedQuery(name = "Aula.findByIdaula", query = "SELECT a FROM Aula a WHERE a.idaula = :idaula")
    , @NamedQuery(name = "Aula.findByNome", query = "SELECT a FROM Aula a WHERE a.nome = :nome")
    , @NamedQuery(name = "Aula.findByDiasemana", query = "SELECT a FROM Aula a WHERE a.diasemana = :diasemana")
    , @NamedQuery(name = "Aula.findByHorarioinicial", query = "SELECT a FROM Aula a WHERE a.horarioinicial = :horarioinicial")
    , @NamedQuery(name = "Aula.findByHorariofinal", query = "SELECT a FROM Aula a WHERE a.horariofinal = :horariofinal")})
public class Aula implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDAULA")
    private BigInteger idaula;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DIASEMANA")
    private String diasemana;
    @Column(name = "HORARIOINICIAL")
    private String horarioinicial;
    @Column(name = "HORARIOFINAL")
    private String horariofinal;
    @JoinColumn(name = "IDFUNCIONARIO", referencedColumnName = "IDFUNCIONARIO")
    @ManyToOne
    private Funcionario idfuncionario;
    @OneToMany(mappedBy = "idaula")
    private List<ClienteAula> clienteAulaList;

    public Aula() {
    }

    public Aula(BigInteger idaula) {
        this.idaula = idaula;
    }
    
    public Aula(BigInteger idaula, String nome, String diasemana, String horarioinicial, String horariofinal, Funcionario idfuncionario ) {
        this.idaula = idaula;
        this.nome = nome;
        this.diasemana = diasemana;
        this.horarioinicial = horarioinicial;
        this.horariofinal = horariofinal;
        this.idfuncionario = idfuncionario;
    }
    
    

    public BigInteger getIdaula() {
        return idaula;
    }

    public void setIdaula(BigInteger idaula) {
        this.idaula = idaula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(String diasemana) {
        this.diasemana = diasemana;
    }

    public String getHorarioinicial() {
        return horarioinicial;
    }

    public void setHorarioinicial(String horarioinicial) {
        this.horarioinicial = horarioinicial;
    }

    public String getHorariofinal() {
        return horariofinal;
    }

    public void setHorariofinal(String horariofinal) {
        this.horariofinal = horariofinal;
    }

    public Funcionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Funcionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    @XmlTransient
    public List<ClienteAula> getClienteAulaList() {
        return clienteAulaList;
    }

    public void setClienteAulaList(List<ClienteAula> clienteAulaList) {
        this.clienteAulaList = clienteAulaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaula != null ? idaula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aula)) {
            return false;
        }
        Aula other = (Aula) object;
        if ((this.idaula == null && other.idaula != null) || (this.idaula != null && !this.idaula.equals(other.idaula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Aula[ idaula=" + idaula + " ]";
    }
    
}
