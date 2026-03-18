FROM eclipse-temurin:11-jre
WORKDIR /app
COPY target/shopping-cart.jar /app/shopping-cart.jar
ENTRYPOINT ["java", "-jar", "shopping-cart.jar"]