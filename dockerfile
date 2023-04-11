# Use an official OpenJDK runtime as a parent image
FROM openjdk:18.0.1.1

VOLUME /tmp

ENV MYSQL_ROOT_PASSWORD=20021223
ENV MYSQL_DATABASE=beverageshop
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=20021223
# Set the working directory to /app
RUN mkdir /app
WORKDIR /app

# Copy the executable JAR file from the target directory into the container at /app
COPY out/artifacts/API_Beverage_Shop_jar/API_Beverage_Shop.jar /app/API_Beverage_Shop.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the executable JAR when the container starts
CMD ["java", "-jar", "API_Beverage_Shop.jar"]
