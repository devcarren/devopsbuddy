package com.devopsbuddy.enums;

/**
 * Created by Carren.Dsouza on 20/04/2017.
 */
public enum RolesEnum {
    Basic(1,"ROLE_BASIC"),
    PRO(1,"ROLE_PRO"),
    ADMIN(1,"ROLE_ADMIN");

    private final int id;
    private final String roleName;

    RolesEnum(int id, String roleName){
        this.id= id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}
