package com.example.produits.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    private String nomProduit;
    private Double prixProduit;
    private Date dateCreation;
    @ManyToOne
    private Categorie categorie;


}

