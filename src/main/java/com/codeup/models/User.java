package com.codeup.models;

import javax.persistence.*;

/**
 * Created by violet on 6/22/17.
 */

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column()
    private String email;

    @Column()
    private String password;
}
