# 🔐 SecureAuth

> A production-ready Authentication & Authorization System built using **Java, Spring Boot, Spring Security, JWT, MySQL, Docker**, and **Swagger UI**.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-green)
![Spring Security](https://img.shields.io/badge/Spring_Security-6-brightgreen)
![JWT](https://img.shields.io/badge/JWT-Authentication-blue)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

# 📌 Overview

SecureAuth is a secure authentication backend built with Spring Boot that demonstrates industry-standard authentication and authorization practices.

The application provides:

- User Registration
- Secure Login
- JWT Authentication
- Refresh Token Mechanism
- Role-Based Access Control (RBAC)
- Dockerized Deployment
- Swagger API Documentation
- MySQL Database Integration

This project follows clean architecture and REST API best practices.

---

# 🚀 Features

- ✅ User Registration
- ✅ Secure Password Hashing (BCrypt)
- ✅ JWT Access Token Authentication
- ✅ Refresh Token Support
- ✅ Logout with Token Revocation
- ✅ Spring Security 6
- ✅ Role Based Authorization (USER / ADMIN)
- ✅ Global Exception Handling
- ✅ Bean Validation
- ✅ Swagger UI Documentation
- ✅ Docker & Docker Compose
- ✅ MySQL Database
- ✅ RESTful API Design

---

# 🛠 Tech Stack

| Category | Technology |
|----------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3.5 |
| Security | Spring Security 6 |
| Authentication | JWT |
| Database | MySQL 8 |
| ORM | Spring Data JPA (Hibernate) |
| API Docs | Swagger / OpenAPI |
| Build Tool | Maven |
| Containerization | Docker & Docker Compose |

---

# 📂 Project Structure

```text
secure-auth
│
├── src
│   ├── controller
│   ├── service
│   ├── repository
│   ├── security
│   ├── entity
│   ├── dto
│   └── config
│
├── docs
│   └── screenshots
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# 📸 API Screenshots

## 📖 Swagger UI

<p align="center">
<img src="docs/screenshots/swagger-home.png" width="950"/>
</p>

---

## 👤 User Registration

<p align="center">
<img src="docs/screenshots/register-api.png" width="950"/>
</p>

---

## 🔐 User Login

<p align="center">
<img src="docs/screenshots/login-api.png" width="950"/>
</p>

---

## 🙋 Authenticated User Profile

<p align="center">
<img src="docs/screenshots/user-profile.png" width="950"/>
</p>

---

## 👑 Admin Endpoint

<p align="center">
<img src="docs/screenshots/admin-api.png" width="950"/>
</p>

---

# 🐳 Docker Deployment

<p align="center">
<img src="docs/screenshots/docker-running.png" width="950"/>
</p>

The application is fully containerized using Docker Compose.

Containers:

- secure-auth-app
- secure-auth-mysql

---

# 🗄 Database

<p align="center">
<img src="docs/screenshots/database1.png" width="950"/>
</p>
<p align="center">
<img src="docs/screenshots/database2.png" width="950"/>
</p>

Database tables:

- users
- refresh_tokens

---

# 🔑 Authentication Flow

User Registration

↓

Login

↓

Access Token + Refresh Token

↓

Access Protected APIs

↓

Access Token Expires

↓

Refresh Token Generates New Access Token

↓

Continue Access

↓

Logout → Refresh Token Revoked

---

# 🚀 Getting Started

## Clone Repository

```bash
git clone https://github.com/anik-ug/secure-auth.git
cd secure-auth
```

## Run with Docker

```bash
docker compose up --build
```

Application

```
http://localhost:8080
```

Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

# 🔒 Security Features

- BCrypt Password Encryption
- JWT Authentication
- Refresh Token Rotation
- Stateless Authentication
- Role-Based Access Control
- Spring Security Filter Chain
- Input Validation
- Global Exception Handling

---

# 📈 Future Improvements

- Email Verification
- Password Reset
- OAuth2 Login (Google/GitHub)
- Redis Token Blacklisting
- CI/CD using GitHub Actions
- Kubernetes Deployment

---

# 👨‍💻 Author

**Anik Kumar**

B.Tech (ECE) • IIIT Ranchi

GitHub: https://github.com/anik-ug

LinkedIn: https://www.linkedin.com/in/anik-kumar-6a8397287/

---

⭐ If you found this project useful, consider giving it a star.