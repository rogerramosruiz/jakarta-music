FROM maven as build

ARG DB_HOST
ARG DB_NAME
ARG DB_USER
ARG DB_PASSWORD

RUN apt-get update
RUN apt-get install -y gettext-base

WORKDIR /app

COPY . .

RUN envsubst < /app/src/main/resources/META-INF/persistence.temp.xml > /app/src/main/resources/META-INF/persistence.xml
RUN rm /app/src/main/resources/META-INF/persistence.temp.xml

RUN mvn clean install


FROM quay.io/wildfly/wildfly
RUN yum makecache

USER root

RUN yum update -y
RUN yum install -y epel-release

RUN yum install -y curl xorg-x11-font-utils fontconfig cabextract

RUN rpm -i https://downloads.sourceforge.net/project/mscorefonts2/rpms/msttcore-fonts-installer-2.6-1.noarch.rpm


USER jboss

COPY --from=build /app/target/music.war /opt/jboss/wildfly/standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-c","standalone-full.xml"]
