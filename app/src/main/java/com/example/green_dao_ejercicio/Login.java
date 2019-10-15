package com.example.green_dao_ejercicio;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "LOGIN".
 */
@Entity
public class Login {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String usuario;

    @NotNull
    private String password;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Login() {
    }

    public Login(Long id) {
        this.id = id;
    }

    @Generated
    public Login(Long id, String usuario, String password) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getUsuario() {
        return usuario;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUsuario(@NotNull String usuario) {
        this.usuario = usuario;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
