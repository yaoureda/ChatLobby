## ChatLobby

# Database Setup
1. Install PostgreSQL and then run in terminal:
```bash
psql -U postgres
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