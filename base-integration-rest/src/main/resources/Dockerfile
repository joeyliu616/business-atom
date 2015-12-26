FROM java:openjdk-7-jdk

MAINTAINER joeyliu616 joeyliu616@live.cn

VOLUME /tmp

ADD base-integration-rest-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'

#设置环境变量. Spring-boot 启动时加载
ENV server_port=45852

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Xmx500m", "-Xms100m", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/", "/app.jar"]