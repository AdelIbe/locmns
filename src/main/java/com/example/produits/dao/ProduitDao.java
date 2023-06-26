package com.example.produits.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.produits.model.Categorie;
import com.example.produits.model.Produit;

import java.util.List;

@Repository

public interface ProduitDao extends JpaRepository<Produit,Long> {
    List<Produit> findByNomProduit(String nom);
    List<Produit> findByNomProduitContains(String nom);

//    @Query("select p from Produit p where p.nomProduit like %?1 and p.produit > ?2")
//    List<Produit>findByNomPrix (String nom,Double prix);

    @Query("select p from Produit p where p.nomProduit like %:nom and p.prixProduit >:prix")
    List<Produit> findByNomPrix (@Param("nom")String nom, @Param("prix")Double prix);

    @Query("select p from Produit p where p.categorie = ?1")
    List<Produit>findByCategorie (Categorie categorie);

    List<Produit> findByCategorieIdCat(Long id);
    List<Produit> findByOrderByNomProduitAsc();

    @Query("select p from Produit p order by p.nomProduit ASC, p.prixProduit ASC")
    List<Produit>trierProduitNomsPrix();



}
