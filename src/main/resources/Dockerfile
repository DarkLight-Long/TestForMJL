# 直接拉镜像就行
FROM openjdk:8-jdk-alpine
# 开放端口号
EXPOSE 8080
# 工作目录
WORKDIR /tmp/ehrbeckend
ENV LANG en_US.UTF-8
# openjdk会出现验证码问题，此处安装font
RUN apk add --update ttf-dejavu fontconfig && rm -rf /var/cache/apk/*
COPY ./jeecg-boot-module-system-2.4.6.jar /tmp/ehrbeckend/jeecg-boot-module-system-2.4.6.jar
CMD java -jar /tmp/ehrbeckend/jeecg-boot-module-system-2.4.6.jar >catalina.out 2>&1