FROM tomcat:9-jdk17
RUN rm -rf /usr/local/tomcat/webapps/*
COPY build/libs/BitlyCloneApplication1.0.war /usr/local/tomcat/webapps/bitlycloneapp
EXPOSE 8080
CMD ["catalina.sh", "run"]

# docker exec -it  bash ls /usr/local/tomcat/webapps