FROM openjdk:17-alpine3.14

RUN apk add tzdata
RUN mkdir /etc/localtime
RUN cp /usr/share/zoneinfo/Europe/Moscow /etc/localtime
RUN echo "Europe/Moscow" >  /etc/timezone

ENV APPLICATION_PORT=8040
ENV TOKEN=TOKEN

COPY target/telegram-bot-everyone-*.jar /opt/app/app.jar

EXPOSE $APPLICATION_PORT

CMD ["java", "-Xmx128m", "-jar", "/opt/app/app.jar", "--server.port=${APPLICATION_PORT}", "--telegram.token=${TOKEN}"]