package by.it.milosh.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by USER on 26.06.2017.
 */
public enum RoleEnum implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN");

    String type;

    private RoleEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.name();
    }

}
