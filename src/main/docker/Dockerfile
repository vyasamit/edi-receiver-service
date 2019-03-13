FROM openjdk:8u151-jre-stretch
VOLUME /tmp
ARG jarVersion
RUN apt-get update -y \
&& apt-get install -y vim \
&& apt-get install -y dos2unix \
&& apt-get clean

RUN mkdir /opt/edi-receiver-service
RUN mkdir /opt/edi-receiver-service/conf
RUN mkdir -p /app/bin

ADD edi-receiver-service-$jarVersion.jar /app/bin/edi-receiver-service.jar
ADD application.yml /opt/edi-receiver-service/conf/application.yml
ADD invokeServices.sh /app/bin/invokeServices.sh

RUN dos2unix /app/bin/invokeServices.sh
RUN chmod +x /app/bin/invokeServices.sh
EXPOSE 8089
ENTRYPOINT ["/app/bin/invokeServices.sh"]