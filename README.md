# aggregate-search
基于SpringBoot3的聚合搜索项目模块，使用Elastic Stack技术栈进行索引优化查询，支持根据数据类型进行区分查找，同时也支持综合查询数据。用户只需要输入一个关键词即可，无需关心字段名称，并且前端使用了动态路由，搜索页刷新后依旧请求刷新前的数据。



本模块可以灵活运用于任何需要搜索功能的项目中，也可以单独部署，搭建一个聚合搜索平台。



## 前端

### 技术栈

- 使用vite构建工具📦
- 使用vue3作为项目脚手架🔧
- 使用Ant Design Vue组件库布局🎁
- 使用Vue-Router搭建路由🧭
- 使用Typescript规范js代码👣
- 使用yarn管理依赖💾

### 启动和部署

- 安装所需依赖

```shell
yarn
```

- 启动开发模式

```shell
yarn dev
```

- 打包

```shell
yarn build
```



## 后端

### 技术栈

- 使用Maven构建工具📦
- 使用SpringBoot搭建web服务🔧
- 使用SpringDataJPA进行SQL操作⤴️
- 使用SpringDataElasticsearch进行es操作⤴️
- 使用PostgreSQL 17作为数据库📊
- 使用Elasticsearch 8.13.4 作为搜索引擎🔍
- 使用SpringBoot定时任务进行数据同步💼

### 启动和部署

- 使用IDEA添加为Maven项目，运行AggregateSearchBackendApplication类的main方法即可
- 使用`mvn package`进行打包（默认为jar包）
- 使用`java -jar [JAR_PACKAGE_NAME]`运行jar包
- 运行时使用参数`--spring.profiles.active=[prod|dev|test|...]`指定运行环境
