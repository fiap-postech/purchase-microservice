FROM --platform=linux/amd64 openjdk:17-alpine

WORKDIR /service

COPY ./purchase-service.jar ./purchase-service.jar

RUN /bin/sh -c 'touch /service/purchase-service.jar'

CMD ["java", "-jar", "purchase-service.jar"]