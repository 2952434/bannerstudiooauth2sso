#!/bin/bash

echo "停止容器"
docker stop oauth2

echo "删除容器"
docker rm oauth2

echo "删除镜像"
docker rmi oauth2sso:v1.1

echo "执行dockerfile"
docker build -t oauth2sso:v1.1 .

echo "启动容器"
docker run -it -d -p 8090:8090 --name oauth2 oauth2sso:v1.1