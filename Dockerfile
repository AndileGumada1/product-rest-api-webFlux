FROM openjdk:11
MAINTAINER andile.com
EXPOSE 8050
ADD target/product-app.jar product-app.jar
ENTRYPOINT ["java","-jar","/product-app.jar"]