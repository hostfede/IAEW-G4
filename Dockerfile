FROM amazoncorretto:17

# Instala curl y tar (si no están instalados)
RUN yum -y install tar gzip

# Directorio de instalación de Maven
ENV MAVEN_HOME /usr/local/apache-maven-3.9.5

# Descarga y descomprime Apache Maven
RUN mkdir -p $MAVEN_HOME \
    && curl -L https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz -o /tmp/apache-maven.tar.gz \
    && tar xzvf /tmp/apache-maven.tar.gz -C /usr/local/ \
    && rm /tmp/apache-maven.tar.gz

# Agrega Maven al PATH
ENV PATH $MAVEN_HOME/bin:$PATH

# Verifica la instalación de Maven
RUN mvn --version
WORKDIR /code
COPY . /code

EXPOSE 8080

RUN mvn clean package
CMD ["java", "-jar", "target/apiClientes-0.0.1-SNAPSHOT.jar"]



