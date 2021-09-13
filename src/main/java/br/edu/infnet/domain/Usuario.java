package br.edu.infnet.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String cep;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idEndereco")
    private Endereco endereco;

    public Usuario() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}

//--------bACKUP---------------\\
//import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
//
//@Entity
//@Table(name = "usuarios", catalog = "ads_m_2021", schema = "", uniqueConstraints = {
//    @UniqueConstraint(columnNames = {"cpf"}),
//    @UniqueConstraint(columnNames = {"email"}),
//    @UniqueConstraint(columnNames = {"cnpj"})})
//@NamedQueries({
//    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
//    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
//    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
//    @NamedQuery(name = "Usuario.findByEndereco", query = "SELECT u FROM Usuario u WHERE u.endereco = :endereco"),
//    @NamedQuery(name = "Usuario.findByTelefone", query = "SELECT u FROM Usuario u WHERE u.telefone = :telefone"),
//    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
//    @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
//    @NamedQuery(name = "Usuario.findByCpf", query = "SELECT u FROM Usuario u WHERE u.cpf = :cpf"),
//    @NamedQuery(name = "Usuario.findByRazaoSocial", query = "SELECT u FROM Usuario u WHERE u.razaoSocial = :razaoSocial"),
//    @NamedQuery(name = "Usuario.findByCnpj", query = "SELECT u FROM Usuario u WHERE u.cnpj = :cnpj"),
//    @NamedQuery(name = "Usuario.findByTipo", query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo")})
//public class Usuario implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(nullable = false)
//    private Integer id;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
//    @Column(nullable = false, length = 50)
//    private String nome;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 100)
//    @Column(nullable = false, length = 100)    
//    private String endereco;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 20)
//    @Column(nullable = false, length = 20)
//    private String telefone;
//    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 30)
//    @Column(nullable = false, length = 30)
//    private String email;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 32)
//    @Column(nullable = false, length = 32)
//    private String senha;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 11)
//    @Column(nullable = false, length = 11)
//    private String cpf;
//    @Size(max = 50)
//    @Column(name = "razao_social", length = 50)
//    private String razaoSocial;
//    @Size(max = 14)
//    @Column(length = 14)
//    private String cnpj;
//    @Basic(optional = false)
//    @NotNull
//    @Column(nullable = false)
//    private Character tipo;
//    
//    @OneToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "idEndereco", unique = true)
//   private Endereco viaCepEnd;
//
//    public Usuario() {
//    }
//
//    public Usuario(Integer id) {
//        this.id = id;
//    }
//
////    public Usuario(Integer id, String nome, Endereco endereco, String telefone, String email, String senha, String cpf, Character tipo) {
////        this.id = id;
////        this.nome = nome;
////        this.endereco = endereco;
////        this.telefone = telefone;
////        this.email = email;
////        this.senha = senha;
////        this.cpf = cpf;
////        this.tipo = tipo;
////    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//     public String getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(String endereco) {
//        this.endereco = endereco;
//    }
//
//    public String getTelefone() {
//        return telefone;
//    }
//
//    public void setTelefone(String telefone) {
//        this.telefone = telefone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getSenha() {
//        return senha;
//    }
//
//    public void setSenha(String senha) {
//        this.senha = senha;
//    }
//
//    public String getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }
//
//    public String getRazaoSocial() {
//        return razaoSocial;
//    }
//
//    public void setRazaoSocial(String razaoSocial) {
//        this.razaoSocial = razaoSocial;
//    }
//
//    public String getCnpj() {
//        return cnpj;
//    }
//
//    public void setCnpj(String cnpj) {
//        this.cnpj = cnpj;
//    }
//
//    public Character getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(Character tipo) {
//        this.tipo = tipo;
//    }
//    
//        public Endereco getViaCepEnd() {
//        return viaCepEnd;
//    }
//
//    public void setViaCepEnd(Endereco viaCepEnd) {
//        this.viaCepEnd = viaCepEnd;
//    }
//
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Usuario)) {
//            return false;
//        }
//        Usuario other = (Usuario) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "Usuario{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", tipo=" + tipo + '}';
//    }
//
//
//   
//
//    
//    
//}
