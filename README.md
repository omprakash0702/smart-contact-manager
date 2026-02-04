# 📒 Smart Contact Manager

A secure, full-stack **Smart Contact Management System** built using **Java 21**, **Spring Boot**, **Spring Security**, **JPA**, and **Thymeleaf**.  
The application allows users to manage personal contacts with authentication, search, pagination, and profile management.

---

## 🚀 Features

### 🔐 Authentication & Security
- Email & password login
- Google OAuth2 login
- Role-based access control
- Secure session handling using Spring Security

### 👤 User Management
- User registration with email verification
- User dashboard with statistics
- Profile view & safe profile editing  
  *(only allowed fields: about, phone, profile picture)*

### 📇 Contact Management
- Add, edit, delete contacts
- Search contacts by:
  - Name
  - Email
  - Phone number
- Pagination & sorting
- Export contacts to Excel
- Secure access (users can only access their own contacts)

### 🧠 Smart Design Choices
- Backend-first architecture
- Clean separation of concerns (Controller / Service / Repository)
- Security checks at controller & service level
- Minimal JS, server-driven UI

---

## 🛠 Tech Stack

**Backend**
- Java **21**
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)

**Frontend**
- Thymeleaf
- HTML / Tailwind CSS
- Minimal JavaScript

**Database**
- MySQL / PostgreSQL (configurable)

**Build Tool**
- Maven

---

## 📁 Project Structure
```
src/main/java
 ├── config        → Security & OAuth config
 ├── controllers   → Web controllers
 ├── entities      → JPA entities
 ├── repositories  → JPA repositories
 ├── services
 │    ├── impl     → Business logic
 ├── helpers       → Utility & constants

src/main/resources
 ├── templates     → Thymeleaf views
 ├── static        → CSS / JS / images
```

## ⚙️ Setup & Run Locally

### 2️⃣ Configure Database

Create `application.properties` (not committed to GitHub):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/scm
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 3️⃣ Run the application
mvn spring-boot:run
Open in browser:
```http://localhost:8081```

## 🔒 Security Highlights

- CSRF protection handled via Spring Security

- Passwords encrypted using BCrypt

- OAuth users handled safely without password exposure

- Controller-level ownership checks to prevent data leaks

## 📌 Design Decisions (Interview-Friendly)

- No password change feature → intentionally excluded for simplicity & safety

- Email & username immutable → avoids identity inconsistency

- Backend validation preferred over JS

- No heavy frontend frameworks → focus on core backend engineering

## 🧪 What This Project Demonstrates

- Real-world Spring Boot application design

- Secure authentication & authorization

- Clean JPA entity relationships

- Pagination, searching, and filtering

- Production-style backend structure

## 👨‍💻 Author
Omprakash
Backend Developer (Java | Spring Boot)

_________________________________________________________________________________________________________________________________________________________________________________________________
MIT License

Copyright (c) 2026 Your Name

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
