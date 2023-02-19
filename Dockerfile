FROM openjdk:17-alpine

COPY target/izicap_test_younes_elhakimi-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "izicap_test_younes_elhakimi-0.0.1-SNAPSHOT.jar"]