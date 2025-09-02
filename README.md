# MDD – Monde de Dev

Application full-stack (back-end **Spring Boot** + front-end **Angular**) permettant la mise en relation des développeurs à travers des articles, des abonnements et un fil d’actualité.
Ce projet constitue un **MVP (Minimum Viable Product)** conforme aux contraintes et spécifications fournies.

---

## Installation et Setup commun

### Cloner le repository

```bash
git clone https://github.com/steevelefort/Developpez-une-application-full-stack-complete.git
cd Developpez-une-application-full-stack-complete
```

---

## Back-End API (Spring Boot)

### Informations techniques

* **Langage** : Java 21
* **Framework** : Spring Boot 3.5.5
* **Modules utilisés** : Spring Web, Spring Data JPA, Spring Security (OAuth2 Resource Server), Validation
* **Base de données** : MySQL 8.0+
* **Librairies supplémentaires** : MapStruct 1.6.3, Lombok 1.18.38
* **Build tool** : Maven 3.6+

### Database Setup

**Créer la base MySQL (dans un client MySQL) **

```sql
CREATE DATABASE mdd;
```

Les tables sont créées automatiquement au démarrage de l’application (`ddl-auto=update`).

### Lancement du back-end

1. **Se placer dans le dossier back**

   ```bash
   cd back
   ```

2. **Générer les clés JWT**

   ```bash
   mkdir keys
   openssl genrsa -out keys/private.pem 2048
   openssl rsa -in keys/private.pem -pubout -out keys/public.pem
   ```

3. **Lancer l’application**

   ```bash
   DB_USERNAME=your_mysql_user \
   DB_PASSWORD=your_mysql_password \
   DB_HOST=jdbc:mysql://localhost:3306/mdd \
   JWT_PRIVATE_KEY="`cat ./keys/private.pem`" \
   JWT_PUBLIC_KEY="`cat ./keys/public.pem`" \
   FRONTEND_URL="http://localhost:4200" \
   mvn spring-boot:run
   ```

👉 Si vous rencontrez des erreurs de compilation, exécutez d’abord :

```bash
mvn clean compile
```

L’API sera accessible sur :
[http://localhost:8080](http://localhost:8080)

---

### Variables d’environnement

| Variable          | Description                                                          | Exemple                           |
| ----------------- | -------------------------------------------------------------------- | --------------------------------- |
| `DB_HOST`         | URL JDBC de la base MySQL                                            | `jdbc:mysql://localhost:3306/mdd` |
| `DB_USERNAME`     | Nom d’utilisateur MySQL                                              | `root`                            |
| `DB_PASSWORD`     | Mot de passe MySQL                                                   | `root`                            |
| `JWT_PRIVATE_KEY` | Clé privée RSA (contenu PEM) utilisée pour signer les tokens JWT     | `cat ./keys/private.pem`          |
| `JWT_PUBLIC_KEY`  | Clé publique RSA (contenu PEM) utilisée pour vérifier les tokens JWT | `cat ./keys/public.pem`           |
| `FRONTEND_URL`    | URL du front-end Angular autorisée pour les requêtes CORS            | `http://localhost:4200`           |
| `SERVER_PORT`     | Port de l’API (optionnel, par défaut `8080`)                         | `3001`                            |


---

## Front-End (Angular)

### Informations techniques

* **Angular** : 20.2.x (CLI / build / compiler)
* **TypeScript** : \~5.8.3
* **RxJS** : \~7.5
* **Tailwind CSS** : 4.x
* **Node.js recommandé** : 20 LTS

### Prérequis

* Node.js 20+ (LTS recommandé)
* npm 9+

### Installation et lancement

À partir de la racine du projet, exécuter :

```bash
cd front
npm install
npm start
```

L’application sera disponible sur :
[http://localhost:4200](http://localhost:4200)

### Build de production

```bash
npm run build
```

Les fichiers de build seront générés dans `dist/front`.
