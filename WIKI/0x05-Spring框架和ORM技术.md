* 0x05-01 Spring

  ## 1. Spring技术发展

  * 背景
  
    - 2002 年 10 月，Rod Johnson 撰写了一本名为 Expert One-on-One J2EE 设计和开发的 书。Rod，Juergen 和 Yann 于 2003 年 2 月左右开始合作开发Spring项目。
    - ……
    - Pivotal 公司成立之后，于 2014 年发布了 Spring Boot，2015 年发布了 Spring Cloud，2018 年 Pivotal 公司在纽约上市。公司的开源产品有:Spring 以及 Spring 衍生产品、Web 服务器 Tomcat、 缓存中间件 Redis、消息中间件 RabbitMQ、平台即服务的 Cloud Foundry、Greenplum 数据引擎、 GemFire(12306 系统解决方案组件之一)。
    ---
    __官网提到Spring出现的意义即是使Java更简单。__
    
    > Spring makes Java simple.
  
    -
    <img src="/pictures/47.png" alt="Spring技术发展" style="zoom:80%;" />
    
  
  ## 2. Spring框架设计<sup>*</sup>
  
  * __框架是基于一组类库或工具__，在特定领域里根据一定的规则组合成的、开放性的骨架。
  
    框架具有如下特性:
  
    1. 支撑性+扩展性: 框架不解决具体的业务功能问题，我们可以在框架的基础上添加各种具体的业务功能、定制特性，从而形成具体的业务应用系统。
  
    2. 聚合性+约束性: 框架是多种技术点的按照一定规则的聚合体。我们采用了某种框架也就意味着做出了技术选型的取舍。在很多种可能的技术组合里确定了一种具体的实现方式，后续的其他工作都会从这些技术出发，也需要遵循这些规则，所以框架本身影响到研发 过程里的方方面面。
  
  * Spring framework 6大模块
  
    -
    <img src="/pictures/48.png" alt="Spring framework6大模块" style="zoom:60%;" />
  
  * 引入 Spring 意味着引入了一种研发协作模式
  
    -
    <img src="/pictures/49.png" alt="Spring框架设计" style="zoom:75%;" />
  
  
  
  ## 3. Spring AOP详解<sup>*</sup>
  
  * AOP-面向切面编程
  
    * Aspect Oriented Programming
  
    * Spring早期版本的核心功能: 管理对象生命周期与对象装配。
  
    * 为了实现管理和装配，一个自然而然的想法就是，加一个中间层代理(字节码增强)来实现所有对象的托管。
  
  * IoC-控制反转
  
    * Inverse of Control 控制反转
  
    * 它是一种思想，指所有的对象均由Spring容器统一创建并管理。控制：控制对象的创建，属性的赋值，依赖关系的管理，即对象从创建到销毁的整个生命周期。反转：把本来由开发人员管理对象的权限移交给容器统一管理。
    
    * 在Spring框架中，IoC的实现是通过依赖注入（Dependency Injection）这种方法完成。依赖注入即给Bean的属性赋值，其底层是通过反射机制实现的。
  
  * AOP-面向切面编程
  
    -
    <img src="/pictures/50.png" alt="AOP" style="zoom:67%;" />
  
  
  
  ## 4. Spring Bean 核心原理<sup>*</sup>
  
  * Spring Bean 生命周期
  
    Bean的加载过程
  
    -
    <img src="/pictures/51.png" alt="Bean的加载过程" style="zoom:67%;" />
  
    <img src="/pictures/52.png" alt="Bean的加载过程" style="zoom:67%;" />
  
    <img src="/pictures/53.png" alt="Bean的加载过程" style="zoom:67%;" />
  
  * Bean的加载过程演示
  
    * 创建对象
    * 属性赋值
    * 初始化
    * 注销接口注册
  
    -
    <img src="/pictures/54.png" alt="Bean的加载过程演示" style="zoom:67%;" />
  
    * 检查Aware装配
    * 前置处理、After处理
    * 调用init method
    * 后置处理
    * 返回包装类
  
    -
    <img src="/pictures/55.png" alt="Bean的加载过程演示" style="zoom:67%;" />
  
    -
    <img src="/pictures/56.png" alt="Bean的加载过程" style="zoom:67%;" />
  
  
  
  ## 5. Spring XML 配置原理<sup>*</sup>
  
  * XML配置原理
  
    -
    <img src="/pictures/57.png" alt="XML配置原理" style="zoom:67%;" />
  
  * 自动化XML配置工具：XmlBeans -> Spring-xbean
  
    原理：
  
    1. 根据Bean的字段结构，自动生成XSD
    2. 根据Bean的字段结构，加载XML文件
  
    思考：
  
    1. 解析XML的工具有哪些，都有什么特点?
    2. XML <-> Bean相互转换的工具，除了xbean，还有什么?
  
  * Spring Bean 配置方式演化
  
    -
    <img src="/pictures/58.png" alt="Spring Bean 配置方式演化" style="zoom:67%;" />
  
    
  
  ## 6. Spring Messaging等技术
  
  * 介绍 Messaging 与 JMS (Java Messaging Service)
  
    -
    <img src="/pictures/59.png" alt="Message_01" style="zoom:67%;" />
  
  * Messaging的两种方式“生产者-消费者”模式和“发布者-订阅者”模式
    
    * 异同？
    
    -
    <img src="/pictures/60.png" alt="Message_02" style="zoom:67%;" />
  
  * 实验演示
    
    * [演示代码](https://github.com/JavaCourse00/JavaCourseCodes/tree/main/04fx/spring01/src/main/java/io/kimmking/springjms)
    
    * 前置准备 - 需要在本地启动activeMQ,笔者下载的版本为[ActiveMQ](https://activemq.apache.org/components/classic/download/)。下载解压后，进入文件目录，运行如下命令
    
    ```shell
    bin/activemq start
    ```
    此时可以通过 `localhost:8161`访问ActiveMQ的前端页面
    
  
   # 0x05-02 SpringBoot
  
   ## 1. 从Spring到Spring Boot
  
  * Spring变得越来越复杂
  
    -
    <img src="/pictures/61.png" alt="Spring变得越来越复杂" style="zoom:80%;" />
  
  * Spring Boot的出发点
  
    * Spring臃肿以后的必然选择。一切都是为了简化。
  
      - 让开发变简单
      - 让配置变简单
      - 让运行变简单
  
    * 怎么变简单？
  
      关键词：整合
  
    * 限定性框架和非限定性框架？
  
  * Spring Boot 如何做到简化
  
    * 为什么能做到简化？
      1. Spring 本身技术的成熟与完善，各方面第三方组件的成熟集成
      2. Spring 团队在去 web 容器化等方面的努力
      3. 基于 MAVEN 与 POM 的 Java 生态体系，整合 POM 模板成为可能
      4. 避免大量 maven 导入和各种版本冲突
  
    * Spring Boot 是 Spring 的一套快速配置脚手架，关注于自动配置，配置驱动。
  
    * 什么是脚手架？
  
  * 什么是Spring Boot
  
    * Spring Boot 使创建独立运行、生产级别的 Spring 应用变得容易，你可以直接运行它。我们对 Spring 平台和第三方库采用限定性视角，以此让大家能在最小的成本下上手。大部分 Spring Boot 应用仅仅需要最少量的配置。
    * 功能特性：
      1. 创建独立运行的 Spring 应用
      2. 直接嵌入 Tomcat 或 Jetty，Undertow，无需部署 WAR 包
      3. 提供限定性的 starter 依赖简化配置(就是脚手架)
      4. 在必要时自动化配置 Spring 和其他三方依赖库
      5. 提供生产 production-ready 特性，例如指标度量，健康检查，外部配置等
      6. 完全零代码生产和不需要 XML 配置
  
  
  
  ## 2. Spring Boot 核心原理<sup>*</sup>
  
  * 两大核心原理
  
    1. 自动化配置：简化配置核心
  
       基于Configuration，EableXX，Condition
  
    2. Spring-boot-starter：脚手架核心整合各种第三方类库，协同工具
  
    -
  
    <img src="/pictures/62.png" alt="Spring Boot两大核心原理" style="zoom:70%;" />
  
  * 为什么要约定大于配置
  
    * 为什么要约定大于配置?
  
      举例来说，JVM 有1000多个参数，但是我们不需要一个参数，就能 java Hello。
  
    * 优势在于，开箱即用：
  
      1. Maven 的目录结构：默认有 resources 文件夹存放配置文件。默认打包方式为 jar。
      2. 默认的配置为准：application.properties 或 application.yml 文件
      3. 默认通过 spring.profiles.active 属性来决定运行环境时的配置文件。
      4. EnableAutoConfiguration 默认对于依赖的 starter 进行自动装载。
      5. spring-boot-start-web 中默认包含 spring-mvc 相关依赖以及内置的 web 容器，使得构建 一个 web 应用更加简单。
  
  * 自动化配置原理
  
    -
    
    <img src="/pictures/63.png" alt="自动化配置原理" style="zoom:70%;" />
  
  * Spring Boot 自动配置注解
  
    * @SpringBootApplication
  
      SpringBoot 应用标注在某个类上说明这个类是 SpringBoot 的主配置类，SpringBoot 就会运行
  
      这个类的 main 方法来启动 SpringBoot 项目。
  
      * @SpringBootConfiguration
      * @EnableAutoConfiguration
      * @AutoConfigurationPackage
      * @Import({AutoConfigurationImportSelector.class})
  
      加载所有 META-INF/spring.factories 中存在的配置类(类似 SpringMVC 中加载所有 converter)
  
    * 条件化自动配置
  
      * @ConditionalOnBean
      * @ConditionalOnClass
      * @ConditionalOnMissingBean
      * @ConditionalOnProperty
      * @ConditionalOnResource
      * @ConditionalOnSingleCandidate
      * @ConditionalOnWebApplication
  
  
  
  ## 3. Spring Boot Starter 详解<sup>* </sup>
  
  * 以实际项目讲解Starter
  
    > `shardingsphere-jdbc-core-spring-boot-starter`
    
    -
    <img src="/pictures/appendix-01.png" alt="shardingsphere源码" style="zoom:100%;" />
    
    1. spring.provides
    2. spring.factories
    3. additional -- metadata
    4. 自定义Configuration类
  
  
  # 0x05-03 ORM
  
  ## 1. JDBC与数据库连接池
  
  * JDBC
  
    -
    
    <img src="/pictures/64.png" alt="JDBC" style="zoom:67%;" />
  
  * JDBC是Java里操作数据库的核心
  
    * Java 操作数据库的各种类库，都可以看做是在 JDBC 上做的增强实现
    * 为什么可以这么做?
      *  加上 XA 事务--XAConnection
      *  从连接池获取--PooledConnection
      *  MySQL 驱动 JDBC 接口--Connection
  
  * 数据库连接池
  
    * C3P0, DBCP -- Apache CommonPool, Druid, Hikari
  
  
  
  ## 2. ORM - Hibernate/MyBatis
  
  * Hibernate
  
    * ORM(Object-Relational Mapping) 表示对象关系映射。
  
    * Hibernate 是一个开源的对象关系映射框架，它对 JDBC 进行了非常轻量级的对象封装，它将 POJO 与数据库表建立映射关系，是一个全自动的 orm 框架， hibernate 可以自动生成 SQL 语句，自动执行，使得 Java 程序员可以使用面向对象的思维来操纵数据库。
  
    * Hibernate 里需要定义实体类和 hbm 映射关系文件 (IDE 一般有工具生成)。Hibernate 里可以使用 HQL、Criteria、Native SQL 三种方式操作数据库。也可以作为JPA适配实现，使用JPA接口操作。
  
    -
    
    <img src="/pictures/65.png" alt="Hibernate" style="zoom:67%;" />
  
  * MyBatis
  
    * MyBatis是一款优秀的持久层框架，它支 持定制化 SQL、存储过程以及高级映射。 MyBatis 避免了几乎所有的 JDBC 代码和 手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映 射原生信息，将接口和 Java 的 POJOs (Plain Old Java Objects，普通的 Java 对象)映射成数据库中的记录。
  
    -
    
    <img src="/pictures/66.png" alt="MyBatis" style="zoom:60%;" />
    
  * MyBatis - 半自动化 ORM
  
    1. 需要使用映射文件mapper.xml 定义map规则和SQL
    2. 需要定义mapper/DAO，基于xml规则，操作数据库
  
    >  可以使用工具生成基础的mapper.xml 和 mapper/DAO
    >  一个经验就是，继承生成的mapper，而不是覆盖掉,也可以直接在mapper上用注解方式配置SQL
    >  {.is-info}
  
  
  * MyBatis 与 Hinerbate 比较
  
    * Mybatis 优点:原生SQL(XML 语法)，直观，对 DBA 友好
    * Hibernate 优点:简单场景不用写 SQL(HQL、Cretiria、SQL)
    * Mybatis 缺点:繁琐，可以用 MyBatis-generator、MyBatis-Plus 之类的插件
    * Hibernate 缺点:对DBA 不友好
  
    > 考虑为什么国内的大公司都用 MyBatis?
  
  
  
  ## 3. Spring/Spring Boot 集成ORM/JPA<sup>*</sup>
  
  * JPA
  
    * JPA的全称是 Java persistence API，即Java持久化API，是一套基于ORM的规范，内部是由一系列的接口和抽象类构成。
  
    * JPA通过 JDK 5.0 注解描述对象-关系表映射关系，并将运行期的实体对象持久化到数据库中。
    * 核心 EntityManager
  
    -
  
    <img src="/pictures/67.png" alt="JPA" style="zoom:67%;" />
  
  * Spring JDBC 与 ORM
  
    -
    
    <img src="/pictures/68.png" alt="Spring JDBC 与 ORM" style="zoom:67%;" />
  
  * Spring 管理事务
  
    -
    
    <img src="/pictures/69.png" alt="Spring管理事务" style="zoom:65%;" />
  
    * JDBC 层，数据库访问层，怎么操作事务？编程式事务管理
    * Spring 怎么做到无侵入实现事务？声明式事务管理：事务管理器 + AOP
  
    
  
    * Spring 声明式事务配置参考
  
      * 事务的传播性：
  
        @Transactional(propagation=Propagation.REQUIRED)
  
      * 事务的隔离级别：
  
        @Transactional(isolation = Isolation.READ_UNCOMMITTED)
  
        读取未提交数据(会出现脏读，不可重复读) 基本不使用
  
      * 只读：
  
        @Transactional(readOnly=true)
  
        该属性用于设置当前事务是否为只读事务，设置为 true 表示只读，false 则表示可读写，默认值为 false。
  
      * 事务的超时性：
  
        @Transactional(timeout=30)
  
      * 回滚：
  
        指定单一异常类:@Transactional(rollbackFor=RuntimeException.class)
  
        指定多个异常类:@Transactional(rollbackFor={RuntimeException.class, Exception.class})
