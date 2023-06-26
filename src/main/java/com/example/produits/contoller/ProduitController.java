package com.example.produits.contoller;

import com.example.produits.dao.ProduitDao;
import com.example.produits.model.Produit;
import com.example.produits.security.JwtUtils;
import com.example.produits.services.FichierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin
public class ProduitController {

    @Autowired
    private ProduitDao produitDao;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    FichierService fichierService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/produits")
    public List<Produit> getProduits() {
        return produitDao.findAll();
    }

    @GetMapping("/produit/{id}")
    public ResponseEntity<Produit> getProduit(@PathVariable Long id) {
        
        Optional<Produit> optional = produitDao.findById(id);

        if(optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/produit")
    public ResponseEntity<Produit> ajoutProduit(
            @RequestBody Produit nouveauProduit
    ) {

        //si le produit fournit possède un id
        if(nouveauProduit.getIdProduit() != null) {

            Optional<Produit> optional = produitDao.findById(nouveauProduit.getIdProduit());

            //si c'est un update
            if(optional.isPresent()) {
                Produit userToUpdate = optional.get();
                userToUpdate.setNomProduit(nouveauProduit.getNomProduit());
                userToUpdate.setPrixProduit(nouveauProduit.getPrixProduit());
                userToUpdate.setDateCreation(nouveauProduit.getDateCreation());
                userToUpdate.setCategorie(nouveauProduit.getCategorie());

                produitDao.save(userToUpdate);

                return new ResponseEntity<>(nouveauProduit,HttpStatus.OK);
            }

            //si il y a eu une tentative d'insertion d'un Produit avec un id qui n'existait pas
            return new ResponseEntity<>(nouveauProduit,HttpStatus.BAD_REQUEST);

        }

        produitDao.save(nouveauProduit);

        return new ResponseEntity<>(nouveauProduit,HttpStatus.CREATED);

    }

    @DeleteMapping("/admin/produit/{id}")
    public ResponseEntity<Produit> supprimeProduit(@PathVariable Long id) {

        Optional<Produit> ProduitAsupprimer = produitDao.findById(id);

        if(ProduitAsupprimer.isPresent()) {
            produitDao.deleteById(id);

            return new ResponseEntity<>(null,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}