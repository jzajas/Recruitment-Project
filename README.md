# Campaign Management Platform

This project is a full-stack web application that allows users to create and manage campaigns, track balances in a simple and transparent way. It was built as a learning-focused project to practice backend API design, frontend integration, and deployment using modern technologies.

---

## 🚀 Functionality Overview

### 👤 Users

* User creation and management
* Each user has an associated **balance**
* Users can:

  * Create campaigns
  * Contribute funds to campaigns
  * Receive remaining funds when campaigns are deleted

### 📢 Campaigns

* Create, view, and delete campaigns
* Each campaign has a creator and a balance
* When a campaign is deleted, remaining funds are transferred back to the creator

### 💰 Balance Handling

* Automatic balance updates on contributions and refunds
* Backend enforces balance checks to ensure consistency

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Web (REST API)
* Spring Data JPA
* H2 Database
* Maven

### Frontend

* React
* JavaScript
* Vite
* CSS
* Axios

---

## ▶️ How to Interact With the Application

There are two supported ways to use this application: via the hosted deployment or by running it locally using Docker.

### 1. Using the Hosted Version

The application is deployed and accessible through a [public hosted URL](https://frontend-production-e47a.up.railway.app). In this mode, the frontend is already configured to communicate with the hosted backend API, so no local setup is required. Users can simply open the application in a browser and start interacting with campaigns and user balances immediately.

This approach is intended for quick evaluation, demonstrations, and general usage without any installation steps.

### 2. Running Locally with Docker

The application can also be run locally after cloning the repository. Using Docker and Docker Compose, both the backend and frontend services can be started together with a single command. Environment variables are used to configure service communication, database access, and CORS settings for local execution.

This approach is intended for development, debugging, and exploring the codebase in a local environment.

#### How to install
Copy Repository
 ```
git clone https://github.com/jzajas/Recruitment-Project.git
```
Change directory
 ```
cd Recruitment-Project
```
Run Docker
 ```
docker compose up --build
```

---

## 📈 Areas for Improvement

This section is intentionally left open for future enhancements.

Examples:

* Authentication and authorization
* Improved validation and error handling
* Unit and integration tests

---

## 🧠 Purpose of This Project

The main goals of this project are:

* Performing CRUD operations using RestAPI
* Practicing full-stack development
* Designing transactional backend logic
* Working with Dockerized applications
