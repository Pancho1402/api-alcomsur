package com.example.AlcomsurProyect.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuario")
@ToString
@EqualsAndHashCode
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id_usuario")
    private int id;

    @Getter @Setter @Column(name = "email_usuario")
    private String email;

    @Getter @Setter @Column(name = "password_usuario")
    private String password;

    @Getter @Setter @Column(name = "admin_usuario")
    private boolean admin;
}
