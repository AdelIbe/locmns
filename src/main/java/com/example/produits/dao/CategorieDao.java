package com.example.produits.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.produits.model.Categorie;

@Repository
public interface CategorieDao extends JpaRepository<Categorie , Long> {


}
