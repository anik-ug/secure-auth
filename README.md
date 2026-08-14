# 🔐 SecureAuth

> A production-ready Authentication & Authorization REST API built using **Java, Spring Boot, Spring Security, JWT, MySQL, Docker**, and **Swagger/OpenAPI**.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-green)
![Spring Security](https://img.shields.io/badge/Spring_Security-6-brightgreen)
![JWT](https://img.shields.io/badge/JWT-Authentication-blue)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 📌 Overview

**SecureAuth** is a backend authentication and authorization system built with Spring Boot and Spring Security.

The project demonstrates how a modern authentication system can be designed with **JWT-based stateless authentication, refresh tokens, email verification, password reset, role-based authorization, secure password hashing, and Dockerized deployment.**

The application follows a layered architecture with RESTful APIs, DTOs, service/repository separation, validation, and centralized exception handling.

---

## 🚀 Features

### 🔐 Authentication

- ✅ User Registration
- ✅ Secure Login
- ✅ BCrypt Password Hashing
- ✅ JWT Access Token Authentication
- ✅ Refresh Token Support
- ✅ Logout with Refresh Token Revocation
- ✅ Protected User Profile API

### 📧 Email Verification

- ✅ Verification Token Generation
- ✅ Verification Email
- ✅ Token Expiration
- ✅ Account Activation after Email Verification
- ✅ Verification Token Cleanup

### 🔑 Password Reset

- ✅ Forgot Password
- ✅ Password Reset Token Generation
- ✅ Password Reset Email
- ✅ 15-Minute Token Expiration
- ✅ Secure Password Update using BCrypt
- ✅ Reset Token Invalidation after Password Change
- ✅ Previous Reset Token Replacement

### 👑 Authorization

- ✅ Role-Based Access Control (USER / ADMIN)
- ✅ Protected Admin APIs
- ✅ Admin User Management
- ✅ Role Update Support

### 🛡️ Security & Backend

- ✅ Spring Security 6
- ✅ Stateless Authentication
- ✅ JWT Authentication Filter
- ✅ Input Validation
- ✅ Global Exception Handling
- ✅ RESTful API Design
- ✅ JPA / Hibernate
- ✅ MySQL Database
- ✅ Docker & Docker Compose
- ✅ Swagger / OpenAPI Documentation

---

# 🛠 Tech Stack

| Category | Technology |
|----------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3.5 |
| Security | Spring Security 6 |
| Authentication | JWT |
| Password Hashing | BCrypt |
| Database | MySQL 8 |
| ORM | Spring Data JPA / Hibernate |
| API Documentation | Swagger / OpenAPI |
| Build Tool | Maven |
| Containerization | Docker & Docker Compose |

---

# 🏗️ Architecture

```text
                    ┌─────────────────────┐
                    │      Client         │
                    │ Swagger / Frontend  │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    REST Controllers │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Service Layer   │
                    │ Auth / Admin / Email│
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Repository Layer  │
                    │    Spring Data JPA  │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       MySQL         │
                    └─────────────────────┘

---

# 📂 Project Structure

```text
secure-auth
│
├── src
│   ├── controller
│   ├── service
│   │   └── impl
│   ├── repository
│   ├── security
│   ├── entity
│   ├── dto
│   │   ├── request
│   │   └── response
│   ├── exception
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
Verification Email Sent
        ↓
Email Verification
        ↓
Account Activated
        ↓
Login
        ↓
Access Token + Refresh Token
        ↓
Access Protected APIs
        ↓
Access Token Expires
        ↓
Refresh Token
        ↓
New Access Token
        ↓
Continue Access
        ↓
Logout
        ↓
Refresh Token Revoked
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

-  BCrypt password hashing
- JWT-based authentication
- Stateless authentication
- Refresh token management
- Refresh token revocation on logout
- Role-Based Access Control
- JWT authentication filter
- Email verification
- Expiring verification tokens
- Expiring password reset tokens
- One-time password reset token usage
- Input validation
- Global exception handling

---

# 📈 Future Improvements

- OAuth2 Login (Google/GitHub)
- Redis Token Blacklisting
- CI/CD using GitHub Actions
- Kubernetes Deployment

---

<p align="center">
<img src="docs/screenshots/docker-running.png" width="950"/>
</p>


# 👨‍💻 Author

**Anik Kumar**

B.Tech (ECE) • IIIT Ranchi

GitHub: https://github.com/anik-ug

LinkedIn: https://www.linkedin.com/in/anik-kumar-6a8397287/

---

⭐ If you found this project useful, consider giving it a star.
