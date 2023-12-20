FROM gradle:8.2.1-jdk17
WORKDIR /opt/app
COPY ./build/libs/RivaConceptProject-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar RivaConceptProject-0.0.1-SNAPSHOT.jar"]