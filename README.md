# RBAC (Role-Based Access Control) Project

This project implements a Role-Based Access Control (RBAC) system using Spring Boot. It includes various services and APIs to manage users, roles, privileges, and their associations.

## Prerequisites

- Java 21
- Maven
- PostgreSQL

## Setup

1. **Clone the repository:**

    ```sh
    git clone https://github.com/yourusername/rbac.git
    cd rbac
    ```

2. **Configure the database:**

    Update the `src/main/resources/application.properties` file with your PostgreSQL database credentials:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/rbac
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    ```

3. **Build the project:**

    ```sh
    ./mvnw clean install
    ```

4. **Run the application:**

    ```sh
    ./mvnw spring-boot:run
    ```

## Initial Setup APIs

Before testing the actual RBAC functionalities, you need to initialize some data using the following APIs:

1. **Create initial users and map roles:**

    Endpoint: `GET /api/test/user`

    This API will create three users (`dev@mail.in`, `admin@mail.in`, `super@mail.in`) and assign them the roles `AGENT`, `ADMIN`, and `SUPER_ADMIN` respectively.

    ```sh
    curl -X GET http://localhost:9000/api/test/user
    ```

2. **Test API:**

    Endpoint: `GET /api/test/{id}`

    This API can be used to test if the setup is successful.

    ```sh
    curl -X GET http://localhost:9000/api/test/1
    ```

## Running Tests

To run the tests, use the following command:

```sh
./mvnw test
