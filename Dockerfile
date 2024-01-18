FROM --platform=linux/amd64 gradle:8.1.1-jdk17 AS builder

WORKDIR /build


COPY common-libraries/domain-common/build.gradle ./common-libraries/domain-common/build.gradle
COPY common-libraries/rest-common/build.gradle ./common-libraries/rest-common/build.gradle
COPY enterprise/build.gradle ./enterprise/build.gradle
COPY application/build.gradle ./application/build.gradle
COPY adapter/build.gradle ./adapter/build.gradle
COPY drivers/mysql/build.gradle ./drivers/mysql/build.gradle
COPY drivers/payment-gateway/build.gradle ./drivers/payment-gateway/build.gradle
COPY drivers/redis/build.gradle ./drivers/redis/build.gradle
COPY drivers/rest/build.gradle ./drivers/rest/build.gradle
COPY launcher/build.gradle ./launcher/build.gradle
COPY build.gradle .
COPY settings.gradle .

RUN gradle clean build --no-daemon > /dev/null 2>&1 || true

COPY . .

RUN gradle clean build --no-daemon




FROM --platform=linux/amd64 openjdk:17-alpine

WORKDIR /app

COPY --from=builder /build/launcher/build/libs/launcher-1.0.0.jar ./tech-challenge.jar

RUN /bin/sh -c 'touch /app/tech-challenge.jar'

CMD ["java", "-jar", "tech-challenge.jar"]