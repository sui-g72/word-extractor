FROM eclipse-temurin:17-jdk

WORKDIR /app

# JARファイルをコピー
COPY target/vocabapp-1.0.0.jar app.jar

# ポート公開
EXPOSE 8080

# Spring Boot 起動
CMD ["java", "-jar", "app.jar"]
