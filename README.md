# Java EE – Practical Works (TPs)

A collection of Java EE (Jakarta EE) lab exercises covering Servlets, JSP, MVC architecture, and database integration.

---

## Folder Organization

| Folder | Purpose |
|--------|---------|
| `cours/` | **Class sessions** — code written during lectures |
| `tp/` | **Lab sessions** — exercises done during supervised TP sessions |
| `test/` | **Home exercises** — personal practice done outside of class |

---

## Project Structure

```
src/main/java/
├── cours/                  # 📚 Class session code
│   ├── chaptire2/          # Chapter 2 – HTTP Request Info
│   ├── chaptire3/
│   │   ├── Exemple1/       # Chapter 3 Ex1 – Servlet + JSP (GET param)
│   │   └── Exemple2/       # Chapter 3 Ex2 – Servlet + JSP (Form/POST)
│   └── tp/
│       ├── tp5/            # TP5 – Session management (Login/Logout, no DB)
│       └── tp7/            # TP7 – User CRUD with session
│          ├── metier/      # DAO, User model, DB connection
│          └── web/         # Servlets (Login, Logout, UserList, UserForm)
│
├── tp/                     # 🔬 Lab session code
│   ├── tp2/                # TP2 – Loan Calculator (MVC)
│   │   ├── metier/         # Business layer
│   │   └── web/            # Controller + Model
│   └── tp3/                # TP3 – Product Catalogue (MVC + DB)
│       ├── metier/         # DAO, Produit model, Singleton connection
│       └── web/            # Controller + Model
│
└── test/                   # 🏠 Home exercise code
    ├── tp2/                # Personal re-implementation of TP2 + JUnit tests
    └── tp3/                # Extended re-implementation of TP3 (full CRUD)
        ├── metier/         # DAO, Produit model, Singleton connection
        └── web/            # Controller + Model


src/main/webapp/
├── cours/
│   ├── chapitre3/                  # JSP views for chapter 3 examples
│   └── tp/
│       ├── tp5/                    # JSP views for TP5 (login, welcome)
│       └── tp7/                    # JSP views for TP7 (login, user-list, user-form)
├── tp/
│   ├── tp2/                        # JSP view for lab TP2
│   └── tp3/                        # JSP view for lab TP3
└── test/
    ├── tp2/                        # JSP view + CSS for home TP2
    └── tp3/                        # JSP view for home TP3 (full CRUD)
```

---

## Course Sessions (`cours/`)

### Chapter 2 – HTTP Request Information
**Servlet:** `Application.java` → `/info`

Displays basic HTTP request metadata: protocol, scheme, server name, port, and HTTP method.

---

### Chapter 3 – Servlet + JSP (MVC Pattern)

#### Example 1 – URL Parameter Forwarding
**Servlet:** `Servlet.java` → `/fs`

Reads an `age` URL parameter and forwards it to `View.jsp`, which displays a greeting and age category.

#### Example 2 – HTML Form Processing
**Servlet:** `Controleur.java` → `/controleur`

- `GET` → displays `formulaire.jsp` (title, first name, last name, age)
- `POST` → processes form data via `PersonneService`, then forwards to `resultat.jsp`

**Age categories (`PersonneService`):**

| Age Range | Category |
|-----------|----------|
| 1 – 11 | Child (enfant) |
| 12 – 17 | Teenager (adolescent) |
| 18 – 59 | Adult (adulte) |
| 60+ | Senior (personne du troisième âge) |

---

### TP5 – HTTP Session Management
**Package:** `cours.tp.tp5`

An introduction to session handling in Jakarta Servlets with a simple login/logout flow — no database involved.

- `POST /login_tp5` (`LoginServlet`) → reads a username from the form, stores it in the session (`HttpSession`), then redirects to `welcome.jsp`
- `GET /logout_tp5` (`LogoutServlet`) → invalidates the session and redirects back to `login.jsp`

**JSP pages:**

| Page | Description |
|------|-------------|
| `login.jsp` | Login form — displays an error message if the username is empty, shows the current session ID |
| `welcome.jsp` | Welcome page — shows the logged-in username, session ID, creation time, and a logout link using `response.encodeURL()` for cookie-less support |

**Key concepts covered:** `HttpSession`, `session.setAttribute()`, `session.invalidate()`, `session.getId()`, `getSession(false)` vs `getSession(true)`, URL rewriting with `encodeURL()`.

---

### TP7 – User CRUD with Session Authentication
**Package:** `cours.tp.tp7`

A full CRUD application with login/logout session management backed by a MySQL database.

#### Database Setup

```sql
CREATE DATABASE crud_app;

USE crud_app;

CREATE TABLE users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);
```

Update credentials in `ConnectionManager.java` if needed (default: `root` / no password).

#### URL Mapping

| URL | Servlet | Description |
|-----|---------|-------------|
| `/login` | `LoginServlet` | Login form (GET) / Authenticate (POST) |
| `/logout` | `LogoutServlet` | Destroy session → redirect to login |
| `/user-list` | `UserListServlet` | List all users (session required) |
| `/user-form` | `UserFormServlet` | Add (`?action=add`) or Edit (`?action=edit&id=X`) |
| `/user-delete` | `UserDeleteServlet` | Delete a user by ID |

#### Architecture

```
LoginServlet / LogoutServlet
        ↓
UserListServlet  ←→  UserDAO  ←→  ConnectionManager (MySQL)
        ↓                ↑
UserFormServlet  ←→  User (model)
        ↓
UserDeleteServlet
```

> **Note:** `ConnectionManager.getConnection()` opens a **fresh connection on every call**. This is required because `UserDAO` uses try-with-resources, which closes the connection after each query. A shared static connection would be closed after the first use, silently breaking all subsequent DB calls.

---

## Lab Sessions (`tp/`)

### TP2 – Loan Monthly Payment Calculator
**Package:** `tp.tp2` | **Servlet:** `ControleurServlet.java` → `/calcul`

- `GET` → displays the empty form (`VueCredit.jsp`)
- `POST` → reads loan parameters, calls the business layer, displays the monthly payment

**Formula:**

```
mensualité = C × (t/12) / (1 − (1 + t/12)^−n)
```

Where `C` = capital, `t` = monthly interest rate (annual rate ÷ 100), `n` = duration in months.

**Layers:**
- `ICreditMetier` / `CreditMetierImp` → business logic
- `CreditModel` → data transfer object
- `ControleurServlet` → MVC controller
- `VueCredit.jsp` → view

---

### TP3 – Product Catalogue (Search + Add)
**Package:** `tp.tp3` | **Servlet:** `ControleurServlet.java` → `/tp3`

A basic product catalogue backed by a MySQL database with keyword search and product creation.

- `GET /tp3` → loads all products (empty keyword) and displays `ProduitsView.jsp`
- `POST /tp3` → processes a keyword search and re-renders the product list

**Layers:**
- `ImetierCatalogue` / `MetierImpl` → business layer (search + add)
- `SingletonConnection` → shared JDBC connection (Singleton pattern)
- `Produit` → entity model
- `ProduitModele` → MVC model (keyword + product list)
- `ControleurServlet` → MVC controller
- `ProduitsView.jsp` → view (search form + add form + product table)

#### Database Setup

```sql
CREATE DATABASE DB_MVC_CAT;

USE DB_MVC_CAT;

CREATE TABLE PRODUITS (
    ID_PRODUIT  BIGINT AUTO_INCREMENT PRIMARY KEY,
    NOM_PRODUIT VARCHAR(200) NOT NULL,
    PRIX        DOUBLE       NOT NULL
);
```

> **Note:** `SingletonConnection` holds a **single shared static connection** opened at class load time. This is simpler than TP7's approach but less robust — if the connection drops, the application must be restarted.

---

## Home Exercises (`test/`)

### TP2 – Loan Calculator (Home Re-implementation)
**Package:** `test.tp2` | **Servlet:** `ControleurServlet.java` → `/calcul_tp2`

A personal re-implementation of the TP2 loan calculator done at home to reinforce understanding. Uses `double` instead of `float` for higher precision, and includes a styled view (`style.css`) and a JUnit 5 unit test.

**Test (`TestCalcul.java`):** Verifies that a €200,000 loan at 4.5% over 240 months yields ~€1,265.30/month.

---

### TP3 – Product Catalogue (Home Re-implementation — Full CRUD)
**Package:** `test.tp3` | **Servlet:** `ControleurServlet.java` → `/test_tp3`

An extended re-implementation of TP3 with a **complete CRUD** (Create, Read, Update, Delete) instead of the lab's search-and-add-only version.

- `GET /test_tp3` → default action `liste`: loads all products
- `GET /test_tp3?action=edit&id=X` → loads a product into the edit form
- `GET /test_tp3?action=supprimer&id=X` → deletes a product, then redirects to list
- `POST /test_tp3` with `action=search` → keyword filter
- `POST /test_tp3` with `action=ajouter` → inserts a new product, then redirects
- `POST /test_tp3` with `action=update` → updates an existing product, then redirects

**Extended interface `ImetierCatalogue`** adds `deleteProduit(Long id)`, `updateProduit(Produit p)`, and `getProduit(Long id)` on top of the lab version.

**Extended model `ProduitModele`** adds `idProduit` and `action` fields to support the edit workflow.

**View (`ProduitsView.jsp`):** Bootstrap 4 layout with a search bar, an add form, a conditional edit form (shown when `modele.idProduit != null`), and a product table with Edit / Delete action buttons.

> Uses the same `SingletonConnection` and `DB_MVC_CAT` database as the lab TP3.

---

## Technologies

- **Jakarta EE** — Servlet / JSP
- **JSTL** — core taglib for JSP views
- **Bootstrap 4** — UI styling (TP3 home views)
- **Bootstrap 5** — UI styling (TP7 JSPs)
- **MySQL + JDBC** — database (TP3, TP7)
- **JUnit 5** — unit testing (TP2 home)
- **Apache Tomcat 10+** — application server

---

## Prerequisites

- JDK 11+
- Apache Tomcat 10+
- Maven
- MySQL (for TP3 and TP7)

---

## Notes

- Passwords are stored in **plain text** in TP7 — intentional for learning purposes only. Never do this in production.
- The `[DEBUG]` log statements in `UserDAO` are for development only and should be removed before any real deployment.
- `SingletonConnection` (TP3) uses a **single shared connection** opened once at startup. It is simpler than TP7's per-call approach but not suitable for concurrent or long-running applications.
- The lab TP3 (`tp.tp3`) only implements search and add. The full CRUD (edit, update, delete) is in the home re-implementation (`test.tp3`).