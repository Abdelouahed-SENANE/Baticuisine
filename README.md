# BatiCuisine - Aperçu du projet d'application d'estimation de coûts de construction de cuisines

BatiCuisine est une application Java conçue pour les professionnels de la construction et de la rénovation de cuisines. Elle propose une interface intuitive pour estimer le coût total d'un projet, incluant les coûts des matériaux et les frais de main-d'œuvre. Cette application simplifie la gestion de projets en prenant en charge les détails des clients, en générant des estimations de coûts, et en offrant une vue d'ensemble financière des projets de rénovation.

### Fonctionnalités

**Gestion de projet** :
   - Ajouter et gérer les clients et les matériaux.
   - Associer des estimations de coûts à chaque projet.
   - Suivre l'état du projet : En cours, Terminé ou Annulé.

**Gestion des composants** :
   - **Matériaux** : Spécifier les coûts des matériaux, les quantités et les frais de transport associés.
   - **Main-d'œuvre** : Calculer les coûts de main-d'œuvre en fonction des taux horaires et de la productivité.

**Gestion des clients** :
   - Stocker les informations des clients (nom, adresse, numéro de téléphone).
   - Différencier les clients professionnels et particuliers pour offrir des remises personnalisées.

**Génération de devis** :
   - Générer des estimations avant de commencer le projet, incluant les matériaux, la main-d'œuvre, et les taxes.
   - Indiquer l'acceptation du devis par le client.

**Calcul des coûts** :
   - Calculer le coût total du projet en tenant compte des matériaux, de la main-d'œuvre, et des taxes.
   - Inclure les marges bénéficiaires pour obtenir le coût final du projet.

**Résumé détaillé et résultats** :
   - Afficher les détails du projet, y compris les informations du client, les matériaux utilisés, et les coûts finaux.
   - Générer un récapitulatif détaillé des coûts, incluant la TVA et les marges bénéficiaires.

### Exigences techniques

- Java 8 pour la logique de base.
- PostgreSQL comme base de données.
- Utilisation de modèles de conception tels que Singleton et Repository.
- Validation des données (dates, TVA, etc.).
- Architecture en couches : Service, Repository et Présentation.
- Git et JIRA pour le contrôle de version et la gestion du projet.

### Structure du projet

**DESIGN DRIVEN DOMAIN AVEC LES COUCHES SUIVANTES** :
- **Couche Service** : Gère la logique métier, y compris les calculs de coûts et la gestion des clients.
- **Couche Repository** : Gère les opérations de base de données pour stocker et récupérer les données des projets, clients et composants.
- **Couche UI** : Interface en ligne de commande pour interagir avec l'application.

### Dépendances

- **Java Time API** pour la gestion des dates.
- **JDBC** pour la connexion PostgreSQL.
- **Enum** pour gérer les statuts de projet et les types de composants.
- **Streams** et **Optionals** pour une gestion efficace des données.

### Instructions d'utilisation

1. **Cloner le dépôt** : Cloner le projet à partir du dépôt Git.
2. **Construire l'application** : Utiliser le fichier `build.gradle` ou `pom.xml` fourni pour compiler le code source Java.
3. **Configuration de la base de données** : Exécuter les scripts SQL fournis pour créer et initialiser la base de données PostgreSQL.
4. **Lancer l'application** : Démarrer le fichier JAR pour initier le processus d'estimation des coûts.

### Commandes d'exemple

- **Créer un nouveau projet** : Suivre les invites interactives pour ajouter un client, des matériaux et de la main-d'œuvre, puis générer une estimation détaillée des coûts.
- **Générer un devis** : Calculer le coût total en fonction des entrées et appliquer la TVA ainsi que les marges bénéficiaires.
- **Gérer les clients et projets** : Visualiser ou mettre à jour les projets et clients existants via l'interface en ligne de commande.
