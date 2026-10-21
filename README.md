# Spring Students API

A small REST API built with **Spring Boot** and **Lombok** to practise the
controller → service → repository layering. Data is stored in memory.

## Endpoints (`/api/v1/students`)
| Method | Path | Description |
|---|---|---|
| GET | `/api/v1/students` | List all students |
| POST | `/api/v1/students/save_student` | Add a student (JSON body) |
| GET | `/api/v1/students/{email}` | Find a student by email |
| PUT | `/api/v1/students/update_student` | Update a student (JSON body) |
| DELETE | `/api/v1/students/delete_student/{email}` | Delete a student by email |

Example body:
```json
{ "firstName": "Jan", "secondName": "Kowalski", "dateOfBirth": "2003-05-14", "email": "jan@example.com", "age": 22 }
```

## Structure
`controller/` → `service/` (interface) → `impl/` (in-memory service) → `repository/` (in-memory DAO), `model/` (Lombok `@Data @Builder`).

## Run
```sh
./mvnw spring-boot:run
```
The API starts on http://localhost:8080.
