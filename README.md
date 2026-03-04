# Java EE – Practical Works (TPs)

A collection of Java EE (Jakarta EE) lab exercises covering Servlets, JSP, MVC architecture, and database integration.

---

## Project Structure

```
src/main/java/
├── cours/
│   ├── chaptire2/          # Chapter 2 – HTTP Request Info
│   ├── chaptire3/
│   │   ├── Exemple1/       # Chapter 3 Ex1 – Servlet + JSP (GET param)
│   │   └── Exemple2/       # Chapter 3 Ex2 – Servlet + JSP (Form/POST)
│   └── tp/
│       └── tp7/
│          ├── metier/      # DAO, User model, DB connection
│          └── web/         # Servlets (Login, Logout, UserList, UserForm)
│
└── tp/tp2/                 # TP2 – Loan Calculator (MVC)
    ├── metier/             # Business layer
    └── web/                # Controller + Model



src/main/webapp/
├── cours/chapitre3/        # JSP views for chapter 3 examples
├── cours/tp/tp7/           # JSP views for TP7 (login, user-list, user-form)
└── tp/tp2/                 # JSP view for loan calculator
```

---

## Labs Overview

### Chapter 2 – HTTP Request Information
**Servlet:** `Application.java` → `/info`

Displays basic HTTP request metadata: protocol, scheme, server name, port, and HTTP method.

---

### Chapter 3 – Servlet + JSP (MVC Pattern)

#### Example 1 – URL Parameter Forwarding
**Servlet:** `Servlet.java` → `/fs`

Reads an `age` parameter from the URL query string and forwards it to `View.jsp`, which displays a greeting and age category.

#### Example 2 – HTML Form Processing
**Servlet:** `Controleur.java` → `/controleur`

- `GET` → displays `formulaire.jsp` (title, first name, last name, age)
- `POST` → processes form data using `PersonneService`, then forwards to `resultat.jsp`

**Age categories (via `PersonneService`):**

| Age Range | Category |
|-----------|----------|
| 1 – 11    | Child (enfant) |
| 12 – 17   | Teenager (adolescent) |
| 18 – 59   | Adult (adulte) |
| 60+       | Senior (personne du troisième âge) |

---

### TP2 – Loan Monthly Payment Calculator

**Servlet:** `ControleurServlet.java` → `/calcul`

A classic MVC loan calculator.

- `GET` → displays the empty form (`VueCredit.jsp`)
- `POST` → reads loan parameters and computes the monthly payment

**Formula:**

```
mensualité = C × (t/12) / (1 − (1 + t/12)^−n)
```

Where `C` = capital, `t` = monthly rate (annual rate / 100), `n` = duration in months.

**Layers:**
- `ICreditMetier` / `CreditMetierImp` → business logic
- `CreditModel` → data transfer object
- `ControleurServlet` → MVC controller
- `VueCredit.jsp` → view

**Test:** `TestCalcul.java` — verifies that a €200,000 loan at 4.5% over 240 months yields ~€1265.30/month.

---

### TP7 – User Management CRUD with Session Authentication

A full CRUD application with login/logout session management backed by a MySQL database.

#### Setup

1. Create a MySQL database named `crud_app`
2. Create a `users` table:

```sql
CREATE TABLE users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
```

3. Update credentials in `ConnectionManager.java` if needed (default: `root` / no password).

#### URL Mapping

| URL | Servlet | Description |
|-----|---------|-------------|
| `/login` | `LoginServlet` | Login form (GET) / Authenticate (POST) |
| `/logout` | `LogoutServlet` | Destroy session and redirect to login |
| `/user-list` | `UserListServlet` | List all users (session required) |
| `/user-form` | `UserFormServlet` | Add (GET `?action=add`) or Edit (GET `?action=edit&id=X`) |
| `/user-delete` | *(link in JSP)* | Delete a user by ID |

#### Architecture

```
LoginServlet / LogoutServlet
        ↓
UserListServlet  ←→  UserDAO  ←→  ConnectionManager (MySQL)
        ↓                ↑
UserFormServlet  ←→  User (model)
```

---

## Technologies

- **Jakarta EE (Servlet / JSP)**
- **JSTL** (core taglib) for JSP views
- **MySQL** + JDBC (TP7)
- **JUnit 5** for unit testing (TP2)
- **Apache Tomcat** (recommended server)

---

## Prerequisites

- JDK 11+
- Apache Tomcat 10+
- Maven
- MySQL (for TP7)

---

## Notes

- TP2 exists in two packages (`tp.tp2` and `test.tp2`) — both are equivalent implementations of the same exercise.
