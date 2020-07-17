#### Zookeeper代替Eureka

```
Zookeeper是一个分布式协调工具，可以实现注册中心的功能
需要关闭Linux服务器防火墙后启动zookeeper服务器
zookeepe服务器取代Eureka服务器，zookeeper作为服务注册中心
```

zookeeper操作

```
# 进入zookee安装目录
cd /usr/local/zookeeper

# 停止防火墙
systemctl stop firewwalld

# 查看防火墙状态
systemctl status firewwalld

# 查看本机ip(zookeeperip)
ifconfig

# 客户端测试与zookeeper是否连通
ping ip

# 进入zookeeper程序目录 
cd bin

# 启动zookeeper
./zkServer.sh start

# 进入zookeeper配置目录
# cd conf

# 从配置信息zookeeper查看端口号
cat zoo.cfg
# clientPort=2181

# 进入zookeeper客户端
./zkCli.sh 

# 查看根下的节点
ls /

# 查看zookeeper节点信息
get zookeeper

# 查看zookeeper子节点信息
ls /zookeeper

# 连接成功后
ls /services
ls /services/cloud-provider-payment
ls /services/cloud-provider-payment/3c5bab6f-f2cf-4076-9758-b652b5b3e56f

get /services/cloud-provider-payment/3c5bab6f-f2cf-4076-9758-b652b5b3e56f
# 将返回节点信息(json)

```

- 客户端测试
http://localhost:8004/payment/zk