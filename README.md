## ChatLobby – Real-Time Chat Application
A simple real-time chat application built with Spring Boot, WebSockets, and PostgreSQL.

## Features
- User registration & login with Spring Security.
- Real-time messaging with WebSockets.
- Persistent chat history stored in PostgreSQL.
- Chat rooms for group conversations.

## Technologies Used
- Backend: Spring Boot
- Database: PostgreSQL
- ORM: Spring Data JPA / Hibernate
- Real-time: WebSocket + STOMP
- Security: Spring Security
- Frontend: HTML, CSS, and JavaScript

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
- Register a new user at `http://localhost:8080/register.html`
- Login at `http://localhost:8080/login.html`
- Access the chat interface at `http://localhost:8080/chat.html`