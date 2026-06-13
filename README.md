# 🛒 E-Commerce Backend Application

<p align="center">
  <b>Scalable • Event-Driven • Distributed • Production-Grade</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white" />
  <img src="https://img.shields.io/badge/Apache_Kafka-231F20?style=flat&logo=apache-kafka&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Redis-DC382D?style=flat&logo=redis&logoColor=white" />
  <img src="https://img.shields.io/badge/Status-Active-brightgreen?style=flat" />
  <img src="https://img.shields.io/badge/Kubernetes-326CE5?style=flat&logo=kubernetes&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white" />
</p>

## Overview

A cloud-native e-commerce platform built using Spring Boot microservices, Apache Kafka, AI-powered services, Docker, and Kubernetes.

The platform demonstrates how modern distributed systems are designed, containerized, deployed, and orchestrated in production environments.

The system includes:

- Microservices Architecture
- Event-Driven Communication
- Distributed Transactions (Saga Pattern)
- AI-Powered Product Search & Recommendations
- Service Discovery
- API Gateway
- Centralized Configuration
- Docker Containerization
- Kubernetes Orchestration
- High Availability & Scalability
---

##  Architecture Overview

> This architecture reflects how large-scale distributed systems operate in production.

##  Core Architectural Principles

- **Microservices Architecture** (database-per-service pattern)  
- **Event-Driven Communication** via Apache Kafka  
- **Distributed Transactions** using Saga (Choreography)  
- **Service Discovery** with Netflix Eureka  
- **API Gateway** for centralized routing & security  
- **JWT-based Authentication & Authorization**  
- **Search Service** powered by Elasticsearch with Redis support
- **AI Integration** for intelligent product search and recommendations   
- **Hybrid Communication** (Async via Kafka + Sync REST where required)
- **Docker Containerization**
- **Kubernetes Orchestration**
- **Self-Healing Infrastructure**


---

##  AI-Powered Features

The platform includes a dedicated AI Service that enhances product discovery and user experience through intelligent search and recommendations.

### Capabilities

- Semantic Product Search
- AI-Powered Product Recommendations
- Retrieval-Augmented Generation (RAG)
- MCP-Based Tool Integration
- Natural Language Product Discovery

### AI Stack

- Spring AI
- Large Language Models (LLMs)
- Elasticsearch
- RAG (Retrieval-Augmented Generation)
- MCP (Model Context Protocol)

---

##  Architecture & Tech Stack
This project is a distributed, event-driven e-commerce backend system built using:

###  Architectural Style
- Microservices Architecture  
- Event-Driven Architecture  

###  Core Technologies

- Spring Boot  
- Spring Cloud  
- Apache Kafka  
- MySQL  
- Redis  
- Elasticsearch  
- JPA / Hibernate  
- OpenFeign  
- JWT Authentication  
- RAG (Retrieval-Augmented Generation)  
- MCP (Model Context Protocol)  
- AI / LLM Integration  

###  Distributed System Patterns
- Saga Pattern (Orchestration)  
- Outbox Pattern  
- Inbox Pattern  
- Idempotent Processing

### Containerization & Orchestration

- Docker
- Kubernetes
- Deployments
- Services
- ConfigMaps
- Secrets
- Namespace Management

---

## Kubernetes Deployment Architecture

All microservices are containerized using Docker and deployed to Kubernetes.

Kubernetes manages:

- Pod Lifecycle
- Service Discovery
- Internal Networking
- Load Balancing
- Configuration Management
- Secret Management
- Scaling
- Self-Healing

### Kubernetes Resources Used

- Namespace
- Deployment
- Service
- ConfigMap
- Secret

### Benefits

- Automatic Restart of Failed Pods
- Zero-Downtime Deployments
- Horizontal Scaling
- Centralized Configuration
- Better Resource Utilization

---


```mermaid
flowchart TB
    Client(["Client / Browser"])

    subgraph Infrastructure ["Infrastructure Layer"]
        GW["API Gateway\n:8080"]
        CFG["Config Server\n:8888"]
        EUR["Eureka Server\n:8761"]
    end

    subgraph Services ["Microservices"]
        AUTH["Auth Service\n:9001"]
        PROD["Product Service\n:9002"]
        CART["Cart Service\n:9003"]
        INV["Inventory Service\n:9004"]
        PAY["Payment Service\n:9006"]
        PROF["Profile Service\n:9007"]
        SEARCH["Search Service\n:9008"]
        ORDER["Order Service\n:9096"]
    end

    subgraph Messaging ["Event Bus"]
        KAFKA[["Apache Kafka\n:9092"]]
    end

    subgraph Storage ["Data Layer"]
        MYSQL[("MySQL")]
        REDIS[("Redis")]
        ES[("Elasticsearch")]
    end

    Client --> GW
    GW --> AUTH
    GW --> PROD
    GW --> CART
    GW --> ORDER
    GW --> SEARCH
    GW --> PROF

    ORDER -->|OrderCreatedEvent| KAFKA
    KAFKA -->|OrderCreatedEvent| PAY
    KAFKA -->|OrderCreatedEvent| INV
    PAY -->|PaymentCompletedEvent| KAFKA
    INV -->|InventoryReservedEvent| KAFKA
    KAFKA -->|OrderConfirmedEvent| ORDER

    AUTH --> MYSQL
    PROD --> MYSQL
    ORDER --> MYSQL
    PAY --> MYSQL
    INV --> MYSQL
    PROF --> MYSQL
    CART --> REDIS
    SEARCH --> ES

    CFG -.->|config| Services
    EUR -.->|discovery| Services
```

---

```mermaid
flowchart TB

    Client --> GatewaySvc

    subgraph Kubernetes Cluster

        GatewaySvc["Gateway Service"]

        Auth["Auth Pod"]
        Product["Product Pod"]
        Order["Order Pod"]
        Inventory["Inventory Pod"]
        Payment["Payment Pod"]
        Search["Search Pod"]
        AI["AI Pod"]

        Kafka["Kafka"]
        Redis["Redis"]
        ConfigMap["ConfigMap"]
        Secret["Secret"]

        GatewaySvc --> Auth
        GatewaySvc --> Product
        GatewaySvc --> Order
        GatewaySvc --> Search
        GatewaySvc --> AI

        Auth --> ConfigMap
        Product --> ConfigMap
        Order --> Secret

        Order --> Kafka
        Kafka --> Payment
        Kafka --> Inventory
    end
```

---


##  What This Project Actually Solves

This is **not just an order system** — it addresses real-world distributed system challenges:

-  Distributed transaction management  
-  Event-driven consistency  
-  Idempotent processing  
-  Reliable event publishing  
-  Duplicate message handling  
-  Order orchestration
-  AI-powered semantic product search and recommendations  
-  Data integrity across services  
-  Eventually consistent architecture
-  Fault tolerance with Resilience4j (Circuit Breaker, Retry, Rate Limiter)

---

##  Services & Responsibilities

- **API Gateway** → Centralized request routing
- **Auth Service** → JWT authentication management
- **AI Service** → Semantic search and product recommendations  
- **Cart Service** → User cart management  
- **Config Server** → Centralized configuration management  
- **Inventory Service** → Stock reservation handling  
- **Order Service** → Order lifecycle orchestration  
- **Payment Service** → Payment transaction processing  
- **Product Service** → Product catalog management  
- **Profile Service** → User profile management  
- **Search Service** → Product search indexing and MCP Server
- **Server Registry** → Service discovery management  

---

#  Service Ports

| Service | Port |
|----------|------|
| API Gateway | 8080 |
| Eureka Server | 8761 |
| Config Server | 8888 |
| Auth Service | 9001 |
| Product Service | 9002 |
| Cart Service | 9003 |
| Inventory Service | 9004 |
| Payment Service | 9006 |
| Profile Service | 9007 |
| Search Service | 9008 |
| AI Service | 7070 |
| Order Service | 9096 |
| Kafka | 9092 |
---

#  Getting Started


Before running the system, make sure you complete the following setup.

#  Config Server Repository Setup (IMPORTANT)

This project uses a centralized configuration repository.

The Config Server fetches configuration from this Git repository:

https://github.com/rajni2209/e-commerce_config_server

##  What You Need To Do

1. Fork or clone the repository:

```bash
git clone https://github.com/rajni2209/e-commerce_config_server.git
```

2. Create your own GitHub repository.
3. Push this config project to your own repository.
4. Update the `spring.cloud.config.server.git.uri` property
   inside your Config Server `application.yml` to point to your repo.

Example:

```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-username/your-config-repo
```
---

###  MySQL
The following databases must already exist:

- e_commerce                → Auth Service
- e_commerce_productservice → Product Service
- e_commerce_orderservice   → Order Service
- e_commerce_paymentservice → Payment Service
- e_commerce_inventoryservice → Inventory Service
- e_commerce_profile        → Profile Service

If needed, create them manually:

```sql
CREATE DATABASE e_commerce;
CREATE DATABASE e_commerce_productservice;
CREATE DATABASE e_commerce_orderservice;
CREATE DATABASE e_commerce_paymentservice;
CREATE DATABASE e_commerce_inventoryservice;
CREATE DATABASE e_commerce_profile;
```

Default MySQL Port:
```
3306
```

Update your credentials according to your local MySQL setup.

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/<your_database_name>?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: <your_mysql_username>
    password: <your_mysql_password>
    driver-class-name: com.mysql.cj.jdbc.Driver
```
 Replace:

- `<your_database_name>` with the appropriate service database
- `<your_mysql_username>` with your MySQL username
- `<your_mysql_password>` with your MySQL password

---

# ⚠️ Prerequisites for Running Microservices Locally

Before starting any microservices locally, ensure the required infrastructure services are installed and running on your local machine.

> **Note:** This project does **not** use Docker. All infrastructure components must be started manually.

---

## Start Infrastructure Services

### Kafka

Start the Kafka broker:

```bash
kafka-server-start.sh config/server.properties
```

**Default broker address:**

```text
localhost:9092
```

---

### Redis

Start the Redis server:

```bash
redis-server
```

Verify that Redis is running:

```bash
redis-cli ping
```

Expected output:

```text
PONG
```

**Default Redis address:**

```text
localhost:6379
```

---

### Elasticsearch

Start Elasticsearch:

```bash
elasticsearch
```

Verify that Elasticsearch is running:

```bash
curl http://localhost:9200
```

Expected response:

```json
{
  "name": "...",
  "cluster_name": "...",
  "cluster_uuid": "...",
  "version": { ... },
  "tagline": "You Know, for Search"
}
```

**Default Elasticsearch address:**

```text
localhost:9200
```

---

### Ollama

Start the Ollama service:

```bash
ollama serve
```

Verify that Ollama is running:

```bash
curl http://localhost:11434/api/tags
```

**Default Ollama address:**

```text
localhost:11434
```

---

## Verification Checklist

Before launching any microservice, confirm that:

* [ ] Kafka is running on `localhost:9092`
* [ ] Redis is running on `localhost:6379`
* [ ] `redis-cli ping` returns `PONG`
* [ ] Elasticsearch is running on `localhost:9200`
* [ ] Ollama is running on `localhost:11434`

Once these services are available, you can start the microservices locally.


---

# For Kubernetes Deployment

This project is fully containerized using Docker and deployed on Kubernetes.

All microservices run as independent containers and are managed through Kubernetes Deployments and Services.

Apply all Kubernetes manifests:

```bash
kubectl apply -f k8s/
```

---

## Start Services (Order Matters)

1. Config Server (8888)
2. Eureka Server (8761)
3. API Gateway (8080)
4. All Microservices

```bash
mvn clean install
mvn spring-boot:run
```

---


#  Complete System Flow

```mermaid
flowchart TB

    A[User: Register → Login] --> B[Browse / Search]
    B --> C[Add to Cart]
    C --> D[Place Order]

    D --> E[OrderCreatedEvent]

    E --> F[Payment Service]
    E --> G[Inventory Service]

    F --> H[Payment Completed]
    G --> I[Inventory Reserved]

    H --> J[Order Confirmed]
    I --> J

    J --> K[Cart Cleared]
```


---

#  Distributed Saga Flow

## Order Service

- Creates order
- Sets:
  - paymentCompleted = false
  - inventoryReserved = false
  - status = CREATED
- Saves to database
- Stores event in Outbox
- Publishes OrderCreatedEvent

---

## Kafka Event Example

Topic: order-events

```json
{
  "eventId": "uuid",
  "eventType": "OrderCreatedEvent",
  "aggregatedId": 12,
  "payload": {
    "orderId": 12,
    "items": [...]
  }
}
```

---

#  Payment Service

Consumes OrderCreatedEvent

- Idempotency check
- Creates payment record
- Publishes PaymentCompletedEvent

Topic: payment-events

---

#  Inventory Service

Consumes OrderCreatedEvent

- Checks stock
- Deducts quantity
- Publishes InventoryReservedEvent

Topic: inventory-reserved-events

---

#  Reliability Layer

| Feature | Implemented In |
|----------|----------------|
| Saga Pattern | Order Service |
| Outbox Pattern | Order, Payment, Inventory |
| Inbox Pattern | Order, Payment, Inventory |
| Idempotency | Payment |
| Kafka acks=all | Producer |
| enable-idempotence=true | Producer |
| Resilience4j | Circuit Breaker, Retry |
| Redis | Cart Storage |

---

##  AI Configuration

The AI Service is designed to work with both local and cloud-based LLM providers.

- By default, the project uses **Ollama** for running AI models locally, enabling private and cost-free development.
- If Ollama is not available, you can configure any compatible **GPT API key** (such as OpenAI or other supported providers) through the application configuration.
- The AI layer is provider-agnostic, allowing seamless switching between local and hosted models without changing business logic.

This flexibility makes the project suitable for both local development environments and production deployments.

## 🙋‍♂️ Author
- **Rajnikant**  
  [GitHub Profile](https://github.com/rajni2209)<br>
  [Linkedin Profile](https://www.linkedin.com/in/rajnikant-kumar-27bb22354/)


---
