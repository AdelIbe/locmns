#!/bin/bash

# Mettre à jour le code source
git pull

# Construire le projet avec Maven
bash mvnw package -P prod,sysadmin  --settings /home/debian/.m2/settings.xml

# Construire l'image Docker
docker build --no-cache -t locmns-back .

# Arreter le conteneur existant
docker stop locmns

# Supprimer le conteneur existant
docker rm locmns

# Lancer un nouveau conteneur
docker run -d --net backend --ip 172.18.0.5 --name=locmns -p 8181:8080 -v uploaded_files:/uploads locmns-back