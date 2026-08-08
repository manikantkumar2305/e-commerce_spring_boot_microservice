# 🛒 E-Commerce Microservices — AWS DevOps Pipeline

<p align="center">
  <b>Cloud-Native • Kubernetes • AWS • CI/CD • Monitoring • DevOps</b>
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
  <img src="https://img.shields.io/badge/Prometheus-E6522C?style=flat&logo=prometheus&logoColor=white" />
  <img src="https://img.shields.io/badge/Grafana-F46800?style=flat&logo=grafana&logoColor=white" />
</p>

---

## 📑 Table of Contents

- [🚀 DevOps Overview](#-devops-overview)
- [🏗️ DevOps Architecture](#️-devops-architecture)
- [🔄 CI Pipeline](#-ci-pipeline)
- [🔐 Trivy Security Scanning](#-trivy-security-scanning)
- [📦 Amazon ECR](#-amazon-ecr)
- [🔁 Build Once → Deploy Same Artifact](#-build-once--deploy-same-artifact)
- [🚀 CD Pipeline](#-cd-pipeline)
- [🖥️ Self-Hosted GitHub Actions Runner](#️-self-hosted-github-actions-runner)
- [☸️ Amazon EKS](#️-amazon-eks)
- [⎈ Helm Deployment](#-helm-deployment)
- [🌐 Kubernetes LoadBalancer](#-Kubernetes-LoadBalancer)
- [📊 Monitoring with Prometheus & Grafana](#-monitoring-with-prometheus--grafana)
- [💾 Persistent Storage](#-persistent-storage)
- [🎯 DevOps Work Implemented](#-devops-work-implemented)

---

## 🚀 DevOps Overview

This project implements the complete DevOps lifecycle for a Spring Boot e-commerce microservices application.

The application is containerized using Docker and deployed to **Amazon EKS** using **Helm**, with automated **GitHub Actions CI/CD**, Kubernetes ingress, persistent storage, and monitoring using **Prometheus and Grafana**.

The DevOps implementation includes:

- Docker containerization
- Amazon ECR image management
- GitHub Actions CI/CD
- Trivy container security scanning
- Git commit SHA-based image tagging
- Automated Docker image publishing
- Self-hosted GitHub Actions runner on EC2
- Amazon EKS deployment
- Helm umbrella chart
- Kubernetes workload management
- Kubernetes LoadBalancer
- Prometheus monitoring
- Grafana dashboards
- Persistent storage using AWS EBS gp3
- AWS EBS CSI Driver
- Automated rollout and deployment verification

---

## 🏗️ DevOps Architecture


<img width="1774" height="887" alt="ChatGPT Image Aug 8, 2026, 09_08_16 PM" src="https://github.com/user-attachments/assets/2f276189-784f-4a09-9d4c-a5eb094672cb" />



---

## 🔄 CI Pipeline

The CI pipeline is implemented using **GitHub Actions**.

Each microservice is built independently using a matrix-based workflow.

```text
GitHub
   │
   ▼
Checkout Repository
   │
   ▼
Java 17
   │
   ▼
Maven Build
   │
   ▼
Docker Build
   │
   ▼
Trivy Security Scan
   │
   ▼
Git Commit SHA Tag
   │
   ▼
Amazon ECR
```

**Services Built and Published:**

- API Gateway (`api-gatway`)
- Auth Service
- Cart Service
- Config Server
- Inventory Service
- Order Service
- Payment Service
- Product Service
- Profile Service
- Search Service
- Server Registry / Eureka

---

## 🔐 Trivy Security Scanning

Trivy is integrated directly into the CI pipeline.

Docker images are scanned for:

- HIGH vulnerabilities
- CRITICAL vulnerabilities
- Unfixed vulnerabilities

The image is scanned **before** being pushed to Amazon ECR.

```text
Docker Build
     │
     ▼
   Trivy
     │
     ▼
Security Scan
     │
     ▼
Amazon ECR
```

---

## 📦 Amazon ECR

All microservice images are stored in a single Amazon ECR repository:

```
e-commerce
```

Images are tagged using the Git commit SHA.

**Example:**

```
api-gateway-d00151e524e902a16c2b9a7aef79cb5f25f1a43e
auth-service-d00151e524e902a16c2b9a7aef79cb5f25f1a43e
product-service-d00151e524e902a16c2b9a7aef79cb5f25f1a43e
```

This allows every Docker image to be associated with the exact source-code commit that produced it.

---

## 🔁 Build Once → Deploy Same Artifact

The CD pipeline does **not** rebuild Docker images.

It retrieves the exact Git commit SHA from the successful CI workflow using:

```
github.event.workflow_run.head_sha
```

The same SHA-based images pushed by CI to Amazon ECR are deployed to Amazon EKS.

```text
CI
 │
 ├── Build
 ├── Scan
 ├── Tag with SHA
 └── Push to ECR
          │
          ▼
        ECR
          │
          ▼
         CD
          │
          └── Deploy SAME SHA image
```

---

## 🚀 CD Pipeline

The deployment pipeline is triggered using GitHub Actions `workflow_run`.

```text
CI Workflow
     │
     │ Success
     ▼
workflow_run
     │
     ▼
CD Workflow
     │
     ▼
Self-Hosted EC2 Runner
     │
     ├── AWS CLI
     ├── kubectl
     └── Helm
     │
     ▼
Amazon EKS
```

**The CD pipeline performs:**

- Repository checkout
- EKS kubeconfig configuration
- Cluster connectivity verification
- Helm dependency update
- Helm lint
- SHA-based image tag generation
- Helm deployment
- Kubernetes rollout verification
- Deployment verification

---

## 🖥️ Self-Hosted GitHub Actions Runner

The CD pipeline runs on a self-hosted GitHub Actions runner hosted on an EC2 instance.

The runner is configured with:

- AWS CLI
- kubectl
- Helm
- Git

The runner connects GitHub Actions with the Amazon EKS cluster.

---

## ☸️ Amazon EKS

The application is deployed to:

- **Cluster:** `e-commerce-prod`
- **Region:** `ap-south-1`

The cluster uses 3 worker nodes distributed across Availability Zones.

```text
AWS VPC
   │
   ▼
Amazon EKS
e-commerce-prod
   │
   ├── Worker Node
   ├── Worker Node
   └── Worker Node
         │
         ▼
  Kubernetes Pods
```

---

## ⎈ Helm Deployment

The complete Kubernetes stack is managed using an umbrella Helm chart:

```
ecommerce
```

The umbrella chart manages the application and infrastructure charts together.

```text
                 ecommerce
              Umbrella Chart
                    │
                    ▼
             Kubernetes Pods
                    │
       ┌────────────┴────────────┐
       │                         │
       ▼                         ▼
  Application               Infrastructure
       │                         │
       ├── API Gateway           ├── MySQL
       ├── Auth Service          ├── MariaDB
       ├── Cart Service          ├── Redis
       ├── Config Server         ├── Kafka
       ├── Inventory Service     └── Elasticsearch
       ├── Order Service
       ├── Payment Service
       ├── Product Service
       ├── Profile Service
       ├── Search Service
       └── Eureka Server
```

---

## 🌐 Kubernetes LoadBalancer

The ELB routes external traffic directly to the API Gateway service.

```text
Internet
   │
   ▼
Elastic Load Balancer (ELB)
   │
   ▼
API Gateway
   │
   ▼
Microservices
```

A Kubernetes Service of type LoadBalancer is used as the entry point for external application traffic, provisioning an AWS Elastic Load Balancer (ELB).

---

## 📊 Monitoring with Prometheus & Grafana

Monitoring is implemented using Prometheus and Grafana.

```text
Kubernetes Cluster
       │
       ▼
   Prometheus
       │
       │ Metrics
       ▼
    Grafana
       │
       ▼
Monitoring Dashboards
```

Prometheus collects Kubernetes and application metrics, while Grafana provides dashboards for monitoring the cluster and workloads.

**Monitoring helps track:**

- Kubernetes nodes
- Pods
- CPU usage
- Memory usage
- Application workloads
- Cluster health
- Resource utilization

---

## 💾 Persistent Storage

Stateful workloads use Kubernetes PersistentVolumeClaims backed by AWS EBS gp3 volumes.

```text
StatefulSet
     │
     ▼
PersistentVolumeClaim
     │
     ▼
AWS EBS gp3
     │
     ▼
EBS CSI Driver
```

**Persistent storage is used for:**

- MySQL
- MariaDB
- Redis
- Kafka
- Elasticsearch

---

## 🎯 DevOps Work Implemented

### Containerization
- Containerized all application microservices using Docker.
- Created independent Docker images for each service.

### CI/CD
- Built a GitHub Actions CI pipeline.
- Automated Maven builds.
- Automated Docker image builds.
- Integrated Trivy security scanning.
- Added Git commit SHA-based image tagging.
- Automated Docker image publishing to Amazon ECR.
- Implemented CD using GitHub Actions `workflow_run`.
- Implemented Build Once → Deploy Same Artifact strategy.

### AWS
- Deployed the application to Amazon EKS.
- Configured a self-hosted GitHub Actions runner on EC2.
- Integrated Amazon ECR.
- Configured AWS EBS gp3 persistent storage.
- Integrated the AWS EBS CSI Driver.
- Configured Elastic Load Balancer (ELB).

### Kubernetes
- Created Kubernetes deployments and services.
- Created Helm charts for application services.
- Created Helm charts for infrastructure components.
- Created an umbrella Helm chart named `ecommerce`.
- Deployed the complete stack to Amazon EKS.
- Configured persistent storage for stateful workloads.
- Added Kubernetes rollout and deployment verification.

### Monitoring
- Deployed Prometheus for metrics collection.
- Deployed Grafana for monitoring dashboards.
- Added Kubernetes resource and workload monitoring.
