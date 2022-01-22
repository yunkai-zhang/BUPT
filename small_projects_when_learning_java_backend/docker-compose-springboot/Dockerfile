# 基于java8编写镜像
FROM java:8

# Dockerfile和jar包后续都会放进宿主机的项目目录；这里是把Dockerfile所在的项目目录的所有jar包拷贝进容器，命名为app.jar;app.jar会被ENTRYPOINT指定的命令运行；
COPY *.jar /app.jar

# 设置build镜像时默认执行的命令：配置serverport。这个serverport已经通过application.properties在微服务中指定了，这里在镜像中得再指定一下。
CMD ["--server.port=8080"]

# 暴露容器的端口
EXPOSE 8080

# 设置build镜像时默认执行的命令，CMD的命令会被附加在ENTRYPOINT后面
ENTRYPOINT ["java","-jar","/app.jar"]