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

# 在idea可以直接运行构建镜像
# # 直接拉镜像就行
# FROM openjdk:8-jdk-alpine
# # 开放端口号
# EXPOSE 8080
# # 工作目录
# WORKDIR /tmp/ehrbeckend
# COPY ./jeecg-boot-module-system-2.4.6.jar /tmp/ehrbeckend/app.jar
# CMD java -jar /tmp/ehrbeckend/app.jar >catalina.out 2>&1
# #CMD nohup java -jar /tmp/ehrbeckend/app.jar >catalina.out 2>&1 & 不可启动


# #http://www.dockerinfo.net/dockerfile%e4%bb%8b%e7%bb%8d
# # 官方
# # 基础镜像
# FROM openjdk:8-jdk-alpine
# # 端口
# EXPOSE 5900
# # 环境变量
# ENV port 5900
# # 复制文件到配容器内，此处以本文件为根目录(可以为url)，目的目录为镜像内目录， 与copy功能相同
# ADD /target/demo.jar /app.jar
# # 复制文件(本地文件)到配容器内，此处以本文件为根目录，目的目录为镜像内目录  用于复制本地文件(比add好)
# COPY /target/demo.jar /app.jar
# # 创建一个可以从本地主机或其他容器挂载的挂载点，一般用来存放数据库和需要保持的数据  即，文件保存位置
# VOLUME /data
# # 指定运行容器的用户名或uid
# USER daemon
# # 指令工作目录
# WORKDIR /a

