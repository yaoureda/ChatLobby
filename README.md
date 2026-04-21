## ChatLobby – Real-Time Chat Application
A simple real-time chat application built with Spring Boot, WebSockets, and PostgreSQL.

## Features
- User registration & login with Spring Security.
- Real-time messaging with WebSockets.
- Persistent chat history stored in PostgreSQL.
- Chat rooms for group conversations.

## Interface
<p align="center">
  <img src="./assets/interface.png" height="450" style="object-fit: contain;">
</p>

## Technologies Used
- Backend: Spring Boot
- Database: PostgreSQL
- ORM: Spring Data JPA / Hibernate
- Real-time: WebSocket + STOMP
- Security: Spring Security
- Frontend: HTML, CSS, JavaScript, Thymeleaf

## Database Setup and Running the Application
1. Install PostgreSQL and then run in terminal:
```bash
psql postgres
```
2. Create a user and a database:
```sql
CREATE USER chatuser WITH PASSWORD 'chatpass';
CREATE DATABASE chatlobbydb OWNER chatuser;
```
3. Type `\q` to exit the PostgreSQL terminal.
4. Start the application with the following command:
```bash
./mvnw spring-boot:run
```
5. Open in browser:
- Register a new user at `http://localhost:8080/register`
- Login at `http://localhost:8080/login`
- Access the chat interface at `http://localhost:8080/chat`

## Project Structure
```
ChatLobby/
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── personal
    │   │           └── chatlobby
    │   │               ├── ChatLobbyApplication.java
    │   │               ├── config
    │   │               │   ├── SecurityBeansConfig.java
    │   │               │   ├── SecurityConfig.java
    │   │               │   └── WebSocketConfig.java
    │   │               ├── controller
    │   │               │   ├── AuthController.java
    │   │               │   ├── ChatRoomController.java
    │   │               │   ├── ChatWebSocketController.java
    │   │               │   ├── MessageController.java
    │   │               │   ├── OnlineCountController.java
    │   │               │   └── PageController.java
    │   │               ├── dto
    │   │               │   ├── ChatMessage.java
    │   │               │   └── RegisterRequest.java
    │   │               ├── entity
    │   │               │   ├── ChatRoom.java
    │   │               │   ├── Message.java
    │   │               │   └── User.java
    │   │               ├── event
    │   │               │   └── WebSocketEventListener.java
    │   │               ├── exception
    │   │               │   └── GlobalExceptionHandler.java
    │   │               ├── repository
    │   │               │   ├── MessageRepository.java
    │   │               │   └── UserRepository.java
    │   │               └── service
    │   │                   ├── CustomUserDetailsService.java
    │   │                   ├── MessageService.java
    │   │                   ├── OnlineUserService.java
    │   │                   └── UserService.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       │   ├── css
    │       │   │   ├── auth.css
    │       │   │   └── chat.css
    │       │   └── js
    │       │       ├── chat.js
    │       │       ├── login.js
    │       │       └── register.js
    │       └── templates
    │           ├── fragments
    │           │   ├── footer.html
    │           │   └── header.html
    │           └── pages
    │               ├── chat.html
    │               ├── login.html
    │               └── register.html
    └── test
```