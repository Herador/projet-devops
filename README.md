# Projet DevOps - Plateforme de location de voitures

Application en architecture micro-services respectant les regles du DevOps :
depot Git, pipeline CI, architecture en couches, tests sur toutes les couches,
couverture de code et analyse de qualite.

## Architecture

Deux services back independants, chacun en trois couches (data / services / controller),
integres avec Docker et communiquant en HTTP.

```
                  docker-compose
   +-----------------------+        +--------------------------+
   |     CarService        |        |     RentalService        |
   |     (port 8080)       |        |     (port 8081)          |
   |                       |        |                          |
   | controller : REST     |        | controller : REST        |
   | services   : metier   |        | services   : metier      |
   | data       : Car      |        | data       : Rental      |
   |                       | <----- | client HTTP : CarClient  |
   +-----------------------+        +--------------------------+
        catalogue de                  gestion des locations ;
        voitures                      appelle CarService pour
                                      recuperer le prix d'une voiture
```

Le RentalService, quand il enregistre une location, interroge le CarService
pour connaitre le prix de la voiture et calcule le prix total. Les deux services
communiquent donc reellement entre eux.

## Lancer les deux services

```bash
docker compose up --build
```

- CarService    : http://localhost:8080
- RentalService : http://localhost:8081

Exemple d'utilisation :

```bash
# Ajouter une voiture dans le catalogue
curl -X POST http://localhost:8080/cars -H "Content-Type: application/json" \
  -d '{"plateNumber":"ABC123","brand":"Toyota","price":150.0}'

# Creer une location de 3 jours pour cette voiture
curl -X POST http://localhost:8081/rentals -H "Content-Type: application/json" \
  -d '{"bookingId":"B001","plateNumber":"ABC123","customerName":"Alice","days":3}'
# -> totalPrice = 150 x 3 = 450
```

## Tests et couverture

Pour chaque service :

```bash
cd MyService        # ou RentalService
./gradlew test jacocoTestReport
```

Rapport de couverture HTML : `build/reports/jacoco/test/html/index.html`

## Integration continue

A chaque push ou pull request, GitHub Actions :
- execute les tests des deux services (JUnit + MockMvc)
- genere les rapports de couverture JaCoCo et les envoie a Codecov
- lance une analyse SonarCloud couvrant les deux services
- construit les images Docker des deux services

Voir le fichier INSTALLATION.md pour la mise en place du depot et des secrets.
