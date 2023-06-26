package com.example.produits.security;

import com.example.produits.model.Utilisateur;
import com.example.produits.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MonUserDetails implements UserDetails {

    private Utilisateur utilisateur;

    public MonUserDetails(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        List<GrantedAuthority> roles = new ArrayList<>();
//
//        if(utilisateur.isAdmin()) {
//            roles.add(new SimpleGrantedAuthority("ROLE_ADMINISTRATEUR"));
//        } else {
//            roles.add(new SimpleGrantedAuthority("ROLE_UTILISATEUR"));
//        }
//        return roles;

//        return utilisateur.isAdmin()
//                ? List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRATEUR"))
//                : List.of(new SimpleGrantedAuthority("ROLE_UTILISATEUR"));
//
//        return List.of(new SimpleGrantedAuthority(utilisateur.isAdmin() ? "ROLE_ADMINISTRATEUR" : "ROLE_UTILISATEUR"));

//        if(utilisateur.getRole() != null) {
//            return List.of(new SimpleGrantedAuthority(utilisateur.getRole().getNom()));
//        }

//        return List.of(new SimpleGrantedAuthority("ROLE_UTILISATEUR"));

        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Role role : utilisateur.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getNom()));
        }

        return authorities;

    }

    @Override
    public String getPassword() {
        return utilisateur.getPassword();
    }

    @Override
    public String getUsername() {
        return utilisateur.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
