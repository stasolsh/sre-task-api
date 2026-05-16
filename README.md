# sre-task-api

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED)
![Kubernetes](https://img.shields.io/badge/Kubernetes-Orchestrated-326CE5)
![Prometheus](https://img.shields.io/badge/Prometheus-Monitoring-E6522C)
![Grafana](https://img.shields.io/badge/Grafana-Dashboards-F46800)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-CI/CD-2088FF)
![Micrometer](https://img.shields.io/badge/Micrometer-Metrics-6DB33F)
![Actuator](https://img.shields.io/badge/Spring_Actuator-Enabled-success)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-green)

Production-style Spring Boot Task API built for practicing modern SRE, DevOps, Platform Engineering, and Cloud-Native concepts.

---

# Features

* Spring Boot 3 + Java 21
* PostgreSQL integration
* Dockerized application
* Kubernetes deployment
* Prometheus metrics
* Grafana dashboards
* Structured JSON logging
* Spring Boot Actuator
* Health probes
* Prometheus alert rules
* GitHub Actions CI/CD
* RESTful CRUD API

---

# Architecture

```text
Client
   |
   v
Spring Boot REST API
   |
   v
PostgreSQL

Monitoring Stack:
Prometheus --> Grafana
```

---

# Tech Stack

| Technology      | Purpose                 |
| --------------- | ----------------------- |
| Java 21         | Backend                 |
| Spring Boot     | REST API                |
| PostgreSQL      | Database                |
| Docker          | Containerization        |
| Kubernetes      | Orchestration           |
| Prometheus      | Metrics                 |
| Grafana         | Visualization           |
| Micrometer      | Metrics instrumentation |
| Spring Actuator | Health & monitoring     |
| GitHub Actions  | CI/CD                   |

---

# REST API

| Method | Endpoint          |
| ------ | ----------------- |
| POST   | `/api/tasks`      |
| GET    | `/api/tasks`      |
| GET    | `/api/tasks/{id}` |
| PUT    | `/api/tasks/{id}` |
| DELETE | `/api/tasks/{id}` |

---

# Task Model

```json
{
  "id": 1,
  "title": "Learn SRE",
  "description": "Practice monitoring",
  "status": "TODO",
  "createdAt": "2026-05-15T10:15:00Z",
  "updatedAt": "2026-05-15T10:15:00Z"
}
```

Statuses:

* TODO
* IN_PROGRESS
* DONE

---

# Project Structure

```text
sre-task-api/
├── src/
├── k8s/
│   ├── monitoring/
│   └── *.yaml
├── docker-compose.yml
├── Dockerfile
├── build.gradle
├── .github/workflows/
└── README.md
```

---

# Local Development

## Requirements

* Java 21
* Docker Desktop
* Kubernetes enabled in Docker Desktop
* kubectl

---

# Run Application Locally

## Start PostgreSQL + Application

```bash
docker compose up --build
```

Application URL:

```text
http://localhost:8080
```

---

# Build Application

```bash
gradlew clean build
```

Build Docker image:

```bash
docker build -t sre-task-api-app:latest .
```

---

# API Examples

## Create Task

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Learn SRE\",\"description\":\"Practice observability\"}"
```

---

## Get Tasks

```bash
curl http://localhost:8080/api/tasks
```

---

# Health & Metrics

## Health Endpoint

```text
/actuator/health
```

Example:

```bash
curl http://localhost:8080/actuator/health
```

---

## Prometheus Metrics

```text
/actuator/prometheus
```

Example:

```bash
curl http://localhost:8080/actuator/prometheus
```

---

# Structured Logging

Structured JSON logs are enabled using:

* Logstash Logback Encoder

Example log:

```json
{
  "timestamp":"2026-05-15T10:15:00.000Z",
  "level":"INFO",
  "service":"sre-task-api",
  "message":"Task created"
}
```

---

# Kubernetes Deployment

## Deploy Resources

```bash
kubectl apply -f k8s/
kubectl apply -f k8s/monitoring/
```

---

# Check Resources

```bash
kubectl get pods -n sre-task-api
kubectl get svc -n sre-task-api
```

---

# Kubernetes Services

| Service     | URL                    |
| ----------- | ---------------------- |
| Application | http://localhost:30080 |
| Prometheus  | http://localhost:30090 |
| Grafana     | http://localhost:30300 |

---

# Prometheus

Open:

```text
http://localhost:30090
```

Useful queries:

## Application Availability

```promql
up
```

## Request Rate

```promql
rate(http_server_requests_seconds_count[1m])
```

## Error Rate

```promql
rate(http_server_requests_seconds_count{status=~"5.."}[1m])
```

## JVM Memory

```promql
jvm_memory_used_bytes
```

## CPU Usage

```promql
process_cpu_usage
```

---

# Grafana

Open:

```text
http://localhost:30300
```

Default credentials:

```text
admin / admin
```

## Configure Prometheus Data Source

```text
http://prometheus:9090
```

---

# Alerts

Configured Prometheus alerts:

| Alert              | Description             |
| ------------------ | ----------------------- |
| SreTaskApiDown     | Application unavailable |
| HighHttpErrorRate  | HTTP 5xx errors > 5%    |
| HighP95Latency     | p95 latency > 500ms     |
| HighJvmMemoryUsage | JVM heap usage > 80%    |

Open alerts page:

```text
http://localhost:30090/alerts
```

---

# CI/CD

GitHub Actions pipeline performs:

* checkout
* Java setup
* tests
* Gradle build
* Docker image build

Workflow:

```text
.github/workflows/ci.yml
```

---

# Useful Kubernetes Commands

## Get Pods

```bash
kubectl get pods -n sre-task-api
```

## View Logs

```bash
kubectl logs deployment/sre-task-api -n sre-task-api
```

## Describe Pod

```bash
kubectl describe pod POD_NAME -n sre-task-api
```

## Restart Deployment

```bash
kubectl rollout restart deployment/sre-task-api -n sre-task-api
```

---

# Observability Stack

| Component       | Responsibility          |
| --------------- | ----------------------- |
| Spring Actuator | Health & metrics        |
| Micrometer      | Metrics instrumentation |
| Prometheus      | Metrics collection      |
| Grafana         | Visualization           |
| Alert Rules     | Reliability monitoring  |

---
