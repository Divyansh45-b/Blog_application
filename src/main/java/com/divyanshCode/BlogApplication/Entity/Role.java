package com.divyanshCode.BlogApplication.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;


    private String roleName;


    @ManyToMany(mappedBy = "roles")
    private List<User> user;

}
