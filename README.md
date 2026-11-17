# Library Accounting System

Application de bureau JavaFX pour gérer une petite librairie : catalogue de livres, ventes, encaissement et export Excel.

## Prérequis

- Java 24 ou plus
- Maven
- MySQL (serveur local, utilisateur `root` sans mot de passe)

Sur macOS avec Homebrew :

```bash
brew install maven mysql
brew services start mysql
```

## Installation de la base

```bash
mysql -u root < sql/library.sql
```

Le script crée la base `library`, ses 4 tables et des données de démonstration.
⚠️ Il supprime la base `library` si elle existe déjà.

## Lancement

```bash
mvn javafx:run
```

Identifiants de démonstration : **erwan** / **erwan**

## Fonctionnalités

- **Dashboard** : nombre de livres, chiffre d'affaires total, nombre d'utilisateurs, date et heure
- **Add Books** : ajouter, modifier, supprimer et rechercher des livres ; export du catalogue en `BooksDetails.xls`
- **Purchase** : composer une commande, calculer la monnaie à rendre (saisir le montant reçu puis Entrée), valider le paiement ; export des ventes en `sales.xls`

## Structure

```
src/main/java/.../          Code Java (contrôleurs, modèles, connexion BDD)
src/main/resources/.../     Écrans FXML et feuille de style CSS
sql/library.sql             Schéma et données de démonstration
```
