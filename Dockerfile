#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#COPY /target/demo.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#https://blog.csdn.net/qq_19520877/article/details/110391939?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242


FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]