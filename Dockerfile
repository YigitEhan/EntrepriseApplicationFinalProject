# ---------- Build stage ----------
    FROM maven:3.9-eclipse-temurin-17 AS build
    WORKDIR /app
    COPY . .
    RUN mvn -DskipTests clean package
    
    # ---------- Run stage ----------
    FROM eclipse-temurin:17-jre
    WORKDIR /app
    COPY --from=build /app/target/equipment-rental-1.0.0.jar app.jar
    
    
    ENV PORT=8080
    EXPOSE 8080
    CMD ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]
    