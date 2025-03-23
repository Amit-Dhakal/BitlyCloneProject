# Step 1: Use Gradle image to build the WAR
FROM gradle:8.0.2-jdk17 AS build

WORKDIR /app

# Copy build files first for dependency caching
COPY build.gradle settings.gradle ./
RUN gradle build -x test --no-daemon || true  # Run once to download dependencies

# Copy source code and build the WAR
COPY src ./src
RUN gradle build -x test --no-daemon

# Step 2: Use Tomcat to Deploy the WAR
FROM tomcat:10.1-jdk17

WORKDIR /usr/local/tomcat/webapps/

# Copy the generated WAR from the build step
COPY --from=build /app/build/libs/*.war ROOT.war
# Expose Tomcat's default port (8080)
EXPOSE 8080
# Start Tomcat
CMD ["catalina.sh","run"]

#ENTRYPOINT ["usr/local/tomcat/bin/startup.sh","run"]
#ENTRYPOINT ["java","-jar","BitlyCloneApplication-0.0.1-SNAPSHOT.war"]
