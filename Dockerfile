FROM eclipse-temurin:26-jdk-ubi10-minimal AS build

WORKDIR /app

RUN microdnf install -y tar gzip findutils curl \
    && curl -fsSL https://archive.apache.org/dist/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz \
    | tar -xz -C /opt \
    && ln -s /opt/apache-maven-3.9.9/bin/mvn /usr/bin/mvn

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:26-jdk-ubi10-minimal

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]