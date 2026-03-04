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
│       └── tp7/
│          ├── metier/      # DAO, User model, DB connection
│          └── web/         # Servlets (Login, Logout, UserList, UserForm)
│
├── tp/                     # 🔬 Lab session code
│   └── tp2/                # TP2 – Loan Calculator (MVC)
│       ├── metier/         # Business layer
│       └── web/            # Controller + Model
│    
└── test/                   # 🏠 Home exercise code
    └── tp2/                #   Personal re-implementation of TP2 + JUnit tests



src/main/webapp/
├── cours/chapitre3/        # JSP views for chapter 3 examples
├── cours/tp/tp7/           # JSP views for TP7 (login, user-list, user-form)
└── tp/tp2/                 # JSP view for loan calculator
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

## Lab Session (`tp/`)

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

## Home Exercises (`test/`)

### TP2 – Loan Calculator (Home Re-implementation)
**Package:** `test.tp2` | **Servlet:** `ControleurServlet.java` → `/calcul_tp2`

A personal re-implementation of the TP2 loan calculator done at home to reinforce understanding. Uses `double` instead of `float` for higher precision, and includes a styled view (`style.css`) and a JUnit 5 unit test.

**Test (`TestCalcul.java`):** Verifies that a €200,000 loan at 4.5% over 240 months yields ~€1,265.30/month.

---

## Technologies

- **Jakarta EE** — Servlet / JSP
- **JSTL** — core taglib for JSP views
- **Bootstrap 5** — UI styling (TP7 JSPs)
- **MySQL + JDBC** — database (TP7)
- **JUnit 5** — unit testing (TP2 home)
- **Apache Tomcat 10+** — application server

---

## Prerequisites

- JDK 11+
- Apache Tomcat 10+
- Maven
- MySQL (for TP7 only)

---

## Notes

- Passwords are stored in **plain text** in TP7 — intentional for learning purposes only. Never do this in production.
- The `[DEBUG]` log statements in `UserDAO` are for development only and should be removed before any real deployment.






