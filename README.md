# 🛒 E-Commerce Microservices — AWS DevOps

<p align="center">
  <b>Cloud-Native • Kubernetes • AWS • CI/CD • Production-Ready DevOps</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/AWS-232F3E?style=flat&logo=amazon-aws&logoColor=white" />
  <img src="https://img.shields.io/badge/Amazon_EKS-FF9900?style=flat&logo=amazon-aws&logoColor=white" />
  <img src="https://img.shields.io/badge/Amazon_ECR-FF9900?style=flat&logo=amazon-aws&logoColor=white" />
  <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=flat&logo=github-actions&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white" />
  <img src="https://img.shields.io/badge/Kubernetes-326CE5?style=flat&logo=kubernetes&logoColor=white" />
  <img src="https://img.shields.io/badge/Helm-0F1689?style=flat&logo=helm&logoColor=white" />
  <img src="https://img.shields.io/badge/Trivy-1904DA?style=flat&logo=trivy&logoColor=white" />
  <img src="https://img.shields.io/badge/Status-Active-brightgreen?style=flat" />
</p>

---

## 🚀 DevOps Overview

This project implements the complete DevOps lifecycle for a Spring Boot e-commerce microservices application.

The application is containerized using Docker and deployed to **Amazon EKS** using **Helm**, with an automated **GitHub Actions CI/CD pipeline**.

The DevOps implementation provides:

- Docker containerization
- Amazon ECR image management
- GitHub Actions CI pipeline
- Trivy container security scanning
- Git commit SHA-based image tagging
- Automated image publishing to ECR
- GitHub Actions CD using `workflow_run`
- Self-hosted GitHub Actions runner on EC2
- Amazon EKS deployment
- Helm umbrella chart deployment
- Kubernetes workload management
- Persistent storage using AWS EBS gp3
- AWS EBS CSI Driver
- Automated rollout and deployment verification

---

# 🏗️ DevOps Architecture

```text
Developer
    │
    ▼
GitHub Repository
    │
    ▼
GitHub Actions CI
    │
    ├── Checkout
    ├── Java 17
    ├── Maven Build
    ├── Docker Build
    ├── Trivy Security Scan
    ├── Git SHA Tag
    └── Push Image
            │
            ▼
     Amazon ECR
     e-commerce
            │
            ▼
GitHub Actions CD
   workflow_run
            │
            ▼
Self-Hosted EC2 Runner
   ├── AWS CLI
   ├── kubectl
   └── Helm
            │
            ▼
Amazon EKS
e-commerce-prod
            │
            ▼
      Helm Chart
       ecommerce
            │
            ▼
     Kubernetes Pods
            │
     ┌──────┴─────────────────────────────┐
     │                                    │
     ▼                                    ▼
Microservices                      Infrastructure
     │                                    │
     ├── API Gateway                      ├── MySQL
     ├── Auth Service                     ├── MariaDB
     ├── Cart Service                     ├── Redis
     ├── Config Server                    ├── Kafka
     ├── Inventory Service                └── Elasticsearch
     ├── Order Service
     ├── Payment Service
     ├── Product Service
     ├── Profile Service
     ├── Search Service
     └── Eureka Server
            │
            ▼
     Persistent Storage
            │
            ▼
       PVC → EBS gp3
            │
            ▼
      EBS CSI Driver
