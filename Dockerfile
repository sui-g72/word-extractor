# JDK イメージ
FROM eclipse-temurin:17-jdk

# 作業ディレクトリ
WORKDIR /app

# Maven wrapper がある場合
COPY mvnw* pom.xml ./
COPY .mvn ./.mvn

# 依存関係ダウンロード
RUN ./mvnw -q dependency:resolve

# ソースコピーしてビルド
COPY src ./src
RUN ./mvnw -q package -DskipTests

# jar を起動
CMD ["java", "-jar", "target/*.jar"]

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN ./mvnw -q package -DskipTests || mvn -q package -DskipTests

CMD ["java", "-jar", "target/*.jar"]