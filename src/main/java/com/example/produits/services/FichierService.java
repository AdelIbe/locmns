package com.example.produits.services;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FichierService {

    @Value("${dossier.upload}")
    private String dossierUpload;

    public void transfertVersDossierUpload(MultipartFile fichier, String nomFichier) throws IOException {
        System.out.println(nomFichier);
        Path cheminDossierUpload = Paths.get(dossierUpload);
        System.out.println(dossierUpload);
        System.out.println(!Files.exists(cheminDossierUpload));
        if(!Files.exists(cheminDossierUpload)) {
            Files.createDirectories(cheminDossierUpload);
            System.out.println("yep");
        }
        System.out.println(dossierUpload + "/" + nomFichier);
        Path destination = Paths.get(dossierUpload + "/" + nomFichier);
        System.out.println(destination.toString());

        Files.copy(fichier.getInputStream(),destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("DONE");
    }

    public byte[] getImageByName(String nomImage) throws FileNotFoundException {

        Path destination = Paths.get(dossierUpload+"/"+nomImage);// retrieve the image by its name

        try {
            return IOUtils.toByteArray(destination.toUri());
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }

    }


}
