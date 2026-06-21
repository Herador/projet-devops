# Mise en place dans un depot GitHub neuf (binome)

## 1. Creer le depot

Sur GitHub, cree un depot vide nomme par exemple `projet-devops` (Public, sans README).
Puis ajoute ton binome :
Settings > Collaborators > Add people.

## 2. Pousser le code

Decompresse ce dossier, ouvre un terminal dedans et execute :

```bash
git init
git add .
git commit -m "projet devops : deux services, CI, tests, couverture, qualite"
git branch -M main
git remote add origin https://github.com/TON-COMPTE/projet-devops.git
git push -u origin main
```

## 3. Connecter Codecov

1. Va sur https://app.codecov.io et connecte-toi avec GitHub.
2. Selectionne le depot `projet-devops`.
3. Copie le token affiche.
4. Sur GitHub : Settings > Secrets and variables > Actions > New repository secret
   - Name : CODECOV_TOKEN
   - Value : le token copie

## 4. Connecter SonarCloud

1. Va sur https://sonarcloud.io et connecte-toi avec GitHub.
2. Importe le depot `projet-devops` (cela cree un projet et une organisation).
3. Note deux valeurs visibles dans le projet (onglet Information / Administration) :
   - la cle du projet (Project Key), du style `ton-org_projet-devops`
   - la cle de l'organisation (Organization Key)
4. Genere un token : ton profil > My Account > Security > Generate Token.
5. Sur GitHub : Settings > Secrets and variables > Actions > New repository secret
   - Name : SONAR_TOKEN
   - Value : le token genere

## 5. Renseigner les deux valeurs Sonar (IMPORTANT)

Ouvre le fichier `MyService/build.gradle` et remplace les deux placeholders :

```
property "sonar.projectKey", "VOTRE_CLE_PROJET"            <- ta cle de projet
property "sonar.organization", "VOTRE_ORGANISATION_SONAR"  <- ta cle d'organisation
```

Puis pousse la modification :

```bash
git add MyService/build.gradle
git commit -m "config sonarcloud du nouveau projet"
git push origin main
```

## 6. Verifier

Onglet Actions de ton depot : le workflow doit passer au vert.
- les tests des deux services s'executent
- les couvertures partent sur Codecov
- l'analyse SonarCloud couvre les deux services

Si l'etape SonarCloud bloque le jour J, tu peux commenter cette etape dans
`.github/workflows/action.yml` : les tests + la couverture JaCoCo + Codecov
des deux services suffisent deja a demontrer la demarche. Mais normalement
elle passe une fois les deux valeurs de l'etape 5 renseignees.

## Recapitulatif des secrets a creer sur GitHub
- CODECOV_TOKEN
- SONAR_TOKEN
