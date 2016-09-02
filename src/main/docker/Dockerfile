FROM tomcat:8-jre8
VOLUME /tmp
ADD dah-1.0-SNAPSHOT.war app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]