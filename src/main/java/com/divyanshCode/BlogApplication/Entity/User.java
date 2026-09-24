package com.divyanshCode.BlogApplication.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String name;

    private String email;

    private String password;

    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();


    /// eager means -> at that time only data will be Fetch
    /// lazy means ->at that time data will not be Fetch
    @ManyToMany(fetch = FetchType.EAGER)   /// fetch-> is how data is loaded from the DB and when.
    @JoinTable( name = "user_role",
            joinColumns = @JoinColumn(name="userId"),
            inverseJoinColumns = @JoinColumn(name="roleId"))
    private List<Role> roles;
}
