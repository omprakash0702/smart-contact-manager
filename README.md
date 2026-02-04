📒 Smart Contact Manager (SCM 2.0)

A secure, full-stack Smart Contact Management System built using Java 21, Spring Boot, Spring Security, JPA, and Thymeleaf.
The application allows users to manage personal contacts with authentication, search, pagination, and profile management.

🚀 Features
🔐 Authentication & Security

Email & password login

Google OAuth2 login

Role-based access control

Secure session handling using Spring Security

👤 User Management

User registration with email verification

User dashboard with statistics

Profile view & safe profile editing
(only allowed fields: about, phone, profile picture)

📇 Contact Management

Add, edit, delete contacts

Search contacts by:

Name

Email

Phone number

Pagination & sorting

Export contacts to Excel

Secure access (users can only access their own contacts)

🧠 Smart Design Choices

Backend-first architecture

Clean separation of concerns (Controller / Service / Repository)

Security checks at controller & service level

Minimal JS, server-driven UI

#🛠 Tech Stack

Backend

Java 21

Spring Boot

Spring Security

Spring Data JPA (Hibernate)

Frontend

Thymeleaf

HTML / Tailwind CSS

Minimal JavaScript

Database

MySQL / PostgreSQL (configurable)

Build Tool

Maven
