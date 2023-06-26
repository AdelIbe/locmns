package com.example.produits.dao;

import com.example.produits.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    @Query("FROM Utilisateur U JOIN FETCH U.roles WHERE U.email = :email")
    Optional<Utilisateur> findByEmail(@Param("email") String email);
}
