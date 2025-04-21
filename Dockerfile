FROM maven:3.9.8-eclipse-temurin-17 as build

RUN mkdir /opt/app

COPY . /opt/app

WORKDIR /opt/app

RUN mvn clean package

FROM eclipse-temurin:17-jre-alpine

RUN mkdir /opt/app

COPY --from=build /opt/app/target/coletas-0.0.1-SNAPSHOT.jar /opt/app/app.jar

WORKDIR /opt/app

#COPY target/coletas-0.0.1-SNAPSHOT.jar /app/app.jar

ENV PROFILE=prd

EXPOSE 8090

ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]

#docker build -t coletas . -> para gerar a image
#docker run --name coletas-container -p 8090:8090 coletas -> cria o container com o nome coletas-container e executa a apliacação em um container docker na porta 8090