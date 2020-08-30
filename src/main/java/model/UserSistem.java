package model;


import javax.persistence.*;
import java.util.List;

/** Modelo da entidade do usuário do sitema, o qual contem dados para login e dados gerais do cadastro
 * - Robson Murilo: 29/08/2020 -
 * */

@Entity
public class UserSistem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String login;
    private String passwordUser;
    private String cep;
    private String bairro;
    private String localidade;
    private String uf;
    @OneToMany(mappedBy = "userSistem",fetch = FetchType.EAGER)
    private List<PhoneUser> phoneUserList;
    public UserSistem() {
    }
    public void setPhoneUserList(List<PhoneUser> phoneUserList) {
        this.phoneUserList = phoneUserList;
    }

    public List<PhoneUser> getPhoneUserList() {
        return phoneUserList;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String sobreNome) {
        this.lastName = sobreNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String senha) {
        this.passwordUser = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "UserSistem{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", sobreNome='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + passwordUser + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSistem that = (UserSistem) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
