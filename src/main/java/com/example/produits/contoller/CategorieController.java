package com.example.produits.contoller;

import com.example.produits.dao.CategorieDao;
import com.example.produits.model.Categorie;
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
public class CategorieController {

    @Autowired
    private CategorieDao categorieDao;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    FichierService fichierService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/categories")
    public List<Categorie> getCategories() {
        return categorieDao.findAll();
    }

    @GetMapping("/categorie/{id}/produits")
    public ResponseEntity<List<Produit>> getCategorie(@PathVariable Long id) {
        
        Optional<Categorie> optional = categorieDao.findById(id);

        if(optional.isPresent()) {
            return new ResponseEntity<>(optional.get().getProduits(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/categorie")
    public ResponseEntity<Categorie> ajoutCategorie(
            @RequestBody Categorie nouveauCategorie
    ) {

        //si le categorie fournit possède un id
        if(nouveauCategorie.getIdCat() != 0 && nouveauCategorie.getIdCat() != null) {

            Optional<Categorie> optional = categorieDao.findById(nouveauCategorie.getIdCat());

            //si c'est un update
            if(optional.isPresent()) {
                Categorie userToUpdate = optional.get();
                userToUpdate.setNomCat(nouveauCategorie.getNomCat());
                userToUpdate.setDescriptionCat(nouveauCategorie.getDescriptionCat());

                categorieDao.save(userToUpdate);

                return new ResponseEntity<>(nouveauCategorie,HttpStatus.OK);
            }

            //si il y a eu une tentative d'insertion d'un Categorie avec un id qui n'existait pas
            return new ResponseEntity<>(nouveauCategorie,HttpStatus.BAD_REQUEST);

        }

        categorieDao.save(nouveauCategorie);

        return new ResponseEntity<>(nouveauCategorie,HttpStatus.CREATED);

    }

    @DeleteMapping("/admin/categorie/{id}")
    public ResponseEntity<Categorie> supprimeCategorie(@PathVariable Long id) {

        Optional<Categorie> CategorieAsupprimer = categorieDao.findById(id);

        if(CategorieAsupprimer.isPresent()) {
            categorieDao.deleteById(id);

            return new ResponseEntity<>(null,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}