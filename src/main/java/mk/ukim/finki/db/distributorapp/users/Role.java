package mk.ukim.finki.db.distributorapp.users;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CUSTOMER, ROLE_MANAGER, ROLE_DRIVER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return "";
    }
}
