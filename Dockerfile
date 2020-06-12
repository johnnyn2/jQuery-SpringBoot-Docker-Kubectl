FROM openjdk
EXPOSE 8888
ADD target/spring-boot-docker-mysql.war spring-boot-docker-mysql.war
ENTRYPOINT ["java", "-jar", "/spring-boot-docker-mysql.war"]