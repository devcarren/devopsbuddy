package com.devopsbuddy.backend.persistence.domain.backend;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Carren.Dsouza on 20/04/2017.
 */
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "role" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> userRoleS = new HashSet<UserRole>();

    public Set<UserRole> getUserRoleS() {
        return userRoleS;
    }

    public void setUserRoleS(Set<UserRole> userRoleS) {
        this.userRoleS = userRoleS;
    }

    public Role(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
