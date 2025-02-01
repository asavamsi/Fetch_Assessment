# Fetch_Assessment
Receipt Processor - Spring Boot API

Overview

This project is a Receipt Processor API built using Spring Boot and Docker. It allows users to submit receipts and calculates points based on predefined rules.

Features

POST /receipts/process: Submits a receipt and returns a unique ID.

GET /receipts/{id}/points: Returns the points earned for a receipt.

Dockerized for easy deployment.

Technologies Used

Java 17

Spring Boot

Maven

Docker

PostgreSQL (Optional, for persistence)

Setup Instructions

1️⃣ Prerequisites

Ensure you have installed:

JDK 17 (Download Here)

Apache Maven (Download Here)

Docker (Download Here)

2️⃣ Clone the Repository

git clone https://github.com/your-repo/receipt-processor.git
cd receipt-processor

3️⃣ Build the JAR File

Run the following command to package the Spring Boot application:

mvn clean package -DskipTests

This generates a JAR file inside the target/ directory.

4️⃣ Run Locally (Without Docker)

java -jar target/receipt-processor.jar

The API will be available at:

Swagger UI: http://localhost:8080/swagger-ui.html

Docker Instructions

5️⃣ Build the Docker Image

Ensure your Dockerfile is correctly set up, then build the image:

docker build -t receipt-processor .

6️⃣ Run the Docker Container

docker run -p 8080:8080 receipt-processor

The API will be accessible at:

Swagger UI: http://localhost:8080/swagger-ui.html

7️⃣ Verify Running Containers

To check if the container is running, use:

docker ps

To stop and remove the container:

docker stop <container_id>
docker rm <container_id>

API Endpoints

Process Receipt

Request:

POST /receipts/process

Example JSON Input:

{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "total": "35.35",
  "items": [
    { "shortDescription": "Pepsi - 12-oz", "price": "1.25" }
  ]
}

Response:

{ "id": "7fb1377b-b223-49d9-a31a-5a02701dd310" }

Get Receipt Points

Request:

GET /receipts/{id}/points

Response:
{ "points": 28 }
