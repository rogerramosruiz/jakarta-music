FROM maven as build

WORKDIR /app

COPY . .

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
