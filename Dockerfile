FROM fabric8/java-alpine-openjdk8-jdk
MAINTAINER Christophe Furmaniak (christophe.furmaniak@gmail.com)

COPY target/fluffy-tanuki-*.jar /app
