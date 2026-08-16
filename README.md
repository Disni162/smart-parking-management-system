# 🚗 Smart Parking Management System (SPMS)

A **cloud-native, microservice-based Smart Parking Management System** designed to improve parking space management, availability tracking, reservations, vehicle operations, and payment processing.

---

## 📌 Project Overview

The **Smart Parking Management System (SPMS)** is a distributed application developed using a **microservices architecture**.

The system provides functionality for:

* 👤 User and owner management
* 🅿️ Parking space management
* 📍 Parking availability monitoring
* 🚘 Vehicle registration and management
* 📅 Parking space reservations
* 🚦 Vehicle entry and exit simulation
* 💳 Mock payment processing
* 🧾 Digital receipt generation
* 🔍 Service discovery
* ⚙️ Centralized configuration
* 🌐 API Gateway communication

---

## 🏗️ System Architecture

```text
                         ┌─────────────────┐
                         │     Client      │
                         │     Postman     │
                         └────────┬────────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │  API Gateway    │
                         │     :8080       │
                         └────────┬────────┘
                                  │
              ┌───────────────────┼───────────────────┐
              │                   │                   │
              ▼                   ▼                   ▼
       ┌─────────────┐     ┌──────────────┐    ┌─────────────┐
       │User Service │     │Parking Space │    │   Vehicle   │
       │   :8081     │     │   :8083      │    │   :8082     │
       └─────────────┘     └──────┬───────┘    └─────────────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │ Payment Service │
                         │     :8084       │
                         └─────────────────┘


              ┌─────────────────────────────┐
              │       Eureka Server         │
              │           :8761             │
              └─────────────────────────────┘

              ┌─────────────────────────────┐
              │       Config Server         │
              │           :8888             │
              └─────────────────────────────┘
```

---

## 🧩 Microservices & Components

| Component                 | Technology           |   Port | Responsibilities                     |
| ------------------------- | -------------------- | -----: | ------------------------------------ |
| **API Gateway**           | Spring Cloud Gateway | `8080` | Routing and single entry point       |
| **Eureka Server**         | Spring Cloud Eureka  | `8761` | Service registration and discovery   |
| **Config Server**         | Spring Cloud Config  | `8888` | Centralized configuration            |
| **User Service**          | Spring Boot          | `8081` | User registration and authentication |
| **Vehicle Service**       | Spring Boot          | `8082` | Vehicle management and entry/exit    |
| **Parking Space Service** | Spring Boot          | `8083` | Parking spaces and reservations      |
| **Payment Service**       | Spring Boot          | `8084` | Payment processing and receipts      |

---

## 🛠️ Technology Stack

### 💻 Backend

* Java
* Spring Boot
* Spring Cloud
* Spring Cloud Gateway
* Spring Cloud Eureka
* Spring Cloud Config
* Spring Data JPA
* Hibernate
* Maven

### 🗄️ Database

* MySQL

### 🐳 Tools

* Docker
* Docker Compose
* Git
* GitHub
* Postman
* IntelliJ IDEA

---

## 🚀 Running the Project

### Using Docker Compose

```bash
docker compose up --build
```

To run in background:

```bash
docker compose up --build -d
```

To stop:

```bash
docker compose down
```

---

## 🌐 Service URLs

| Service               | URL                     |
| --------------------- | ----------------------- |
| API Gateway           | `http://localhost:8080` |
| Eureka Dashboard      | `http://localhost:8761` |
| Config Server         | `http://localhost:8888` |
| User Service          | `http://localhost:8081` |
| Vehicle Service       | `http://localhost:8082` |
| Parking Space Service | `http://localhost:8083` |
| Payment Service       | `http://localhost:8084` |

---



## 📄 Project Resources

* 📮 [Postman Collection](./docs/AD2.postman_collection.json)
* 📸 [Eureka Dashboard Screenshot](./docs/eureka-dashboard.png)




## 🎓 Academic Information

**Programme:** Graduate Diploma in Software Engineering

**Module:** ITS 1018 – Software Architectures & Design Patterns II

**Assignment:** Final Examination Assignment

**Project:** Smart Parking Management System – Microservice-Based Application
