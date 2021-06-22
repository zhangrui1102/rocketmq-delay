添加RocketMQ延时队列,支持任意延时,RocketMQ版本4.7.1
****
一.安装  
(1)下载本项目源码：git clone https://github.com/zhangrui1102/rocketmq-delay.git  
(2)编译：  
cd rocketmq-delay/  
mvn -Prelease-all -DskipTests clean install -U  
cd distribution/target/rocketmq-4.7.1/rocketmq-4.7.1  
(3)启动：  
nohup sh bin/mqnamesrv &  
nohup sh bin/mqbroker -n localhost:9876 autoCreateTopicEnable=true &  
(4)项目依赖：   
```xml
<dependency>  
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-client-ext</artifactId>
    <version>4.7.1</version>
</dependency>
```


二.配置  
在rocketmq broker配置基础上添加延时队列文件根路径delayPathDir,不配置此参数,默认工作空间/data文件下存储,在delayPathDir文件目录下需存在delay.properties文件,若不配置或者delay.properties文件不存在,则全为默认参数,delay.properties参数如：  
store.root=文件存储目录,默认工作空间/data目录下  
log.expired.delete.enable=是否删除存储文件,true 删除 false不删除 默认false  
log.retention.check.interval.seconds=多少秒执行一次删除操作,默认60秒  
dispatch.log.keep.hour=保留多少个小时存储的文件,默认72个小时  
  
三.延时代码  
可参考example中的simple中的DelayProducer类：  
msg.setDelayTimeAfter(3000L);表示延后多久执行,必须是计算好的毫秒时间,是long整形,比如1600850679423,
若此参数小于服务器当前时间,则会立即执行。

****
[Apache RocketMQ](https://rocketmq.apache.org) 的地址



