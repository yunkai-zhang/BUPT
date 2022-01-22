# 基于java8镜像
FROM java:8

# 如果想导入当前文件夹的所有jar包的话，可以使用"*.jar"。“/app.jar”表示打包最后生成的一个应用。
COPY *.jar /app.jar

# 这个CMD信息会附加到ENTRYPOINT后，指定容器server使用的端口。
CMD ["--server.port=8080"]

# 镜像暴露8080端口
EXPOSE 8080

# build docker的时候执行命令
ENTRYPOINT ["java","-jar","/app.jar"]