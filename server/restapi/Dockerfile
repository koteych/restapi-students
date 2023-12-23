# Use the official Ubuntu 20.04 image as the base image
FROM ubuntu:20.04

# Update the package lists and install necessary tools
RUN apt-get update && apt-get install -y \
    openjdk-11-jdk \
    maven \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Set the default Java and Maven environment variables
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG /root/.m2

# Add the /usr/share/maven/bin directory to the PATH environment variable
ENV PATH $MAVEN_HOME/bin:$PATH

# Set the working directory in the container
WORKDIR /app

# Copy the project source code to the container
COPY . /app

# Set the default command to run when the container starts
CMD ["bash", "run_from_docker.sh"]
