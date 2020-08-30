package model;

import javax.persistence.*;

/** Modelo da entidade telefone, ligado a um usuário o qual pode ter varios telefones */

@Entity
public class PhoneUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String typeNumber;
    @Column(nullable = false)
    private String numberFone;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private UserSistem userSistem;
    private int dd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(String tipo) {
        this.typeNumber = tipo;
    }

    public String getNumberFone() {
        return numberFone;
    }

    public void setNumberFone(String numero) {
        this.numberFone = numero;
    }

    public UserSistem getUserSistem() {
        return userSistem;
    }

    public void setUserSistem(UserSistem userSistem) {
        this.userSistem = userSistem;
    }

    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }
}
