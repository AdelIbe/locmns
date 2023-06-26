#!/bin/bash

# Mettre à jour le code source
git pull

# Construire le projet avec Maven
bash mvnw package -P prod,sysadmin  --settings /home/debian/.m2/settings.xml

# Construire l'image Docker
docker build --no-cache -t spring-demo-franck .

# Arreter le conteneur existant
docker stop spring-demo-franck

# Supprimer le conteneur existant
docker rm spring-demo-franck

# Lancer un nouveau conteneur
docker run -d --net backend --ip 172.18.0.5 --name=spring-demo-franck -p 8181:8080 -v upload_spring_mns_23:/uploads spring-demo-franck