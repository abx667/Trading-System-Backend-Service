# Trading System Backend Service

## Overview

Trading System Backend Service is a microservices-based stock trading platform developed using Spring Boot and Spring Cloud. The system simulates stock trading operations where traders can manage assets, place orders, interact with trading companies, and process settlements.

## Architecture

The application follows a microservices architecture consisting of:

- Eureka Discovery Server
- API Gateway
- Asset Service
- Trader Service
- Trade Order Service
- Settlement Service
- Trading Company Service

## Technology Stack

- Java
- Spring Boot
- Spring Cloud
- Eureka Service Discovery
- Spring Cloud Gateway
- Maven
- REST APIs

## Features

- Microservices Architecture
- Service Discovery and Registration
- API Gateway Routing
- Asset Management
- Trade Order Processing
- Trader Management
- Settlement Processing
- Distributed System Design

## Project Structure

```text
Trading-System-Backend-Service
├── asset-service
├── cloudapigateway
├── eurekadiscoveryserver
├── gatewayapp
├── settlement-service
├── trade-order-service
├── trader-service
└── trading-company-service
```

## Getting Started

1. Start Eureka Discovery Server
2. Start API Gateway
3. Start all business services
4. Access APIs through the Gateway

## Learning Outcomes

This project demonstrates:

- Spring Boot Development
- Microservices Architecture
- Service Discovery Pattern
- API Gateway Pattern
- REST API Development
- Distributed System Concepts

## Author

Abir Talukder
