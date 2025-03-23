部署说明：

1. 将deploy目录中的所有文件上传到服务器：
   scp -r * root@116.198.250.133:/opt/interview

2. 登录到服务器：
   ssh root@116.198.250.133

3. 在服务器上执行以下命令：
   # 进入部署目录
   cd /opt/interview

   # 停止现有容器（如果有）
   docker-compose down

   # 重新构建并启动容器
   docker-compose up -d --build

部署完成后，应用将在以下地址可访问：
- 前端: http://116.198.250.133:8081
- 后端API: http://116.198.250.133:8080/api

如果遇到问题需要查看日志：
   # 查看前端容器日志
   docker logs interview-frontend

   # 查看后端容器日志
   docker logs interview-backend

如果需要重新部署：
   # 停止并移除现有容器
   docker-compose down

   # 删除旧的镜像
   docker rmi interview-frontend interview-backend

   # 重新构建并启动容器
   docker-compose up -d --build
