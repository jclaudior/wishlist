FROM openjdk:11-jdk-alpine
VOLUME /tmp
COPY target/hr-wishlist-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]