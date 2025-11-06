FROM eclipse-temurin:17-jdk

WORKDIR /app

# Maven wrapper & POM
COPY mvnw mvnw.cmd pom.xml ./
COPY .mvn/ .mvn/

# ここを追加（mvnw を実行可能にする）
RUN chmod +x mvnw

# 依存関係をダウンロード
RUN ./mvnw -q dependency:resolve

# ソースコードコピーしてビルド
COPY src ./src
RUN ./mvnw -q package -DskipTests

# jar を統一名称に変更
RUN mv target/vocabapp-1.0.0.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
