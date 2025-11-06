FROM eclipse-temurin:17-jdk

WORKDIR /app

# Maven wrapper & POM
COPY mvnw* pom.xml ./
COPY .mvn ./.mvn

# 依存関係だけ先にダウンロード
RUN ./mvnw -q dependency:resolve

# ソースをコピーしてビルド
COPY src ./src
RUN ./mvnw -q package -DskipTests

# jar ファイルを app.jar にリネームして統一
RUN mv target/vocabapp-1.0.0.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
