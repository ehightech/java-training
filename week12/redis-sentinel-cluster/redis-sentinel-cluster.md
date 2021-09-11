# 配置redis的主从复制，sentinel高可用，Cluster集群

## 1. 下载安装Redis
redis下载地址：https://github.com/tporadowski/redis/releases
### 1.1 下载Redis-x64-5.0.10.zip
### 1.2 解压文件，复制两份重命名，形成三个文件夹：master_6380、slave_6381、slave_6382
## 2. 配置文件
### 2.1 主从配置
#### 2.1.1 master_6380文件夹中redis.windows.conf文件配置
port 6380
#### 2.1.2 slave_6381文件夹中redis.windows.conf文件配置
port 6381  
slaveof 127.0.0.1 6380
#### 2.1.3 slave_6382文件夹中redis.windows.conf文件配置
port 6382  
slaveof 127.0.0.1 6380
### 2.2 哨兵配置
每个redis目录中都创建一个sentinel.conf文件
#### 2.2.1 master_6380的sentinel.conf文件配置如下
port 26380  
sentinel monitor mymaster 127.0.0.1 6380 1  
sentinel down-after-milliseconds mymaster 5000  
sentinel config-epoch mymaster 12  
sentinel leader-epoch mymaster 13  
#### 2.2.2 slave_6381的sentinel.conf文件配置如下
port 26381  
sentinel monitor mymaster 127.0.0.1 6380 1  
sentinel down-after-milliseconds mymaster 5000  
sentinel config-epoch mymaster 12  
sentinel leader-epoch mymaster 13  
#### 2.2.3 slave_6382的sentinel.conf文件配置如下
port 26382  
sentinel monitor mymaster 127.0.0.1 6380 1  
sentinel down-after-milliseconds mymaster 5000  
sentinel config-epoch mymaster 12  
sentinel leader-epoch mymaster 13  
## 3. 启动服务
### 3.1 编写启动redis脚本
#### 3.1.1 master_6380文件夹中建立startup.bat，内容如下：
title master_6380  
redis-server.exe redis.windows.conf  
#### 3.1.2 slave_6381文件夹中建立startup.bat，内容如下：
title slave_6381  
redis-server.exe redis.windows.conf  
#### 3.1.3 slave_6382文件夹中建立startup.bat，内容如下：
title slave_6382  
redis-server.exe redis.windows.conf  
### 3.2 编写启动sentinel脚本
#### 3.2.1 master_6380文件夹中建立startup_sentinel.bat，内容如下：
title master_6380  
redis-server.exe sentinel.conf --sentinel
#### 3.2.2 slave_6381文件夹中建立startup_sentinel.bat，内容如下：
title slave_6381  
redis-server.exe sentinel.conf --sentinel
#### 3.2.3 slave_6382文件夹中建立startup_sentinel.bat，内容如下：
title slave_6382  
redis-server.exe sentinel.conf --sentinel  