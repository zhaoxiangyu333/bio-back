# Bio

> Bio后端微服务项目，也将作为毕业设计，[个人博客](www.blzcat.cn)

**SpringCloud的微服务全栈项目后端**

<u>Docker部署</u>

<u>Spark商品推荐</u>

### 目录结构（model为单位）

```
.
├── bio-base 基础包
│   ├── bio-base-eureka 注册中心
│   └── bio-config 配置中心
├── bio-common 通用工具
├── bio-gateway 网关
├── bio-gateway-swagger2 网关(swagger2)
├── bio-recommend 商品推荐
├── bio-search 搜索Elasticsearch
└── bio-service 服务
    ├── bio-service-address 收货地址
    ├── bio-service-brand 品牌
    ├── bio-service-category 失效
    ├── bio-service-order 订单
    ├── bio-service-product 商品
    ├── bio-service-specificate 属性
    └── bio-service-user 用户
```

### 端口设计

 模块名称         | 端口                   | 说明                                               |
 ---------------- | ---------------------- | -------------------------------------------------- |
 基础组件         |                        |                                                    |
 docker           | 2375                   | 远程访问docker                                     |
 mysql            | 3306、3307、3308、3309 | 3306属于ambari<br>3307、3308、3309 PXC分布式数据库 |
 registry         | 5000                   | 本地bio项目的仓库                                  |
 redis            | 6379                   | 缓存数据库                                         |
 elasticsearch    | 9200、9300             | 9200：客户端、9300：集群通信                       |
 rancher          | 20000                  | docker-web-ui                                      |
 bio后端项目端口  |                        |                                                    |
 eureka           | 20101                  | 注册中心                                           |
 config           | 20102                  | 配置中心                                           |
 gateway          | 20103                  | 网关zuul                                           |
 gateway-swagger2 | 20104                  | 网关zuul+swagger2(api)                             |
 common           | 20105                  | 通用模块                                           |
 search           | 20106                  | elasticsearch搜索                                  |
 category         | 30000                  | 分类                                               |
 product          | 30001                  | 商品                                               |
 specificate      | 30002                  | 商品规格                                           |
 brand            | 30003                  | 品牌                                               |
 user             | 30004                  | 用户                                               |
 order            | 30005                  | 订单                                               |
 address          | 30006                  | 地址                                               |

### 数据库设计

### 项目结构图

![](https://blzcatblogsimg-1255544391.cos.ap-shanghai.myqcloud.com/bio-back.png)

### 未完待续
