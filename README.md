# Basic ElasticSearch with Spring Web

This repository contains a backend application built with Spring Boot that integrates with Elasticsearch for user management.

#Important
You can find the angular frontend source code in this repository:

[Angular Frontend](https://github.com/csabamarton/angular-frontend)


# Features
1. User creation: Create new users with their first name, last name, and email.
2. User search: Search for users by their first name and last name.

# Technologies Used
- Spring Boot
- Elasticsearch
- Java
- Maven
- Docker

# Getting Started
1. Clone the repository: git clone https://github.com/csabamarton/spring-elasticsearch.git
2. Navigate to the project directory: cd spring-elasticsearch
3. Build the project: mvn clean install
4. Start the application: mvn spring-boot:run
5. The backend application will be running at http://localhost:8080.

# Docker Setup for Elasticsearch and Kibana
You can use Docker to set up Elasticsearch and Kibana. Docker Compose configuration files are provided in the docker-compose folder.

1. Navigate to the docker-compose folder: cd docker-compose
2. Start Elasticsearch and Kibana containers: docker-compose up -d
3. Elasticsearch will be running at http://localhost:9200 
4. and Kibana at http://localhost:5601

# Testing
- You can import and use postman collection from the /postman-collection folder
- Use Swagger UI: http://localhost:8080/swagger-ui/index.html

# API Endpoints
- POST /api/elasticsearch/create: Create a new user.
- POST /api/elasticsearch/search: Search for users by first name and last name.