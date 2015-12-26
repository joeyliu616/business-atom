FROM maven:3.3.3

MAINTAINER joeyliu616 joeyliu616@live.cn
ADD ./ /tmp/build/

RUN cd /tmp/build && mvn dependency:resolve

RUN cd /tmp/build && mvn -DskipTests=true package \
        #拷贝编译结果到指定目录
        && mv base-integration-test/target/*.jar /app.jar \
        #清理编译痕迹
        && cd / && rm -rf /tmp/build

VOLUME /tmp

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
