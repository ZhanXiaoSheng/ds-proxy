
## 平台简介
该项目是一个ds的专门用于调用第三方API的一个中转服务平台。

## 项目结构

parent-project/
├── pom.xml                   # 父级 POM
└── proxy-service/
├── pom.xml               # 中转服务模块 POM
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── dscomm
│   │   │           └── proxy
│   │   │               ├── DemoApplication.java
│   │   │               ├── config
│   │   │               │   └── WebConfig.java
│   │   │               └── controller
│   │   │                   └── ProxyController.java
│   │   └── resources
│   │       └── application.yml
└── ...
# ds-proxy
