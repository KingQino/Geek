## 第六周作业

* 作业6 - 基于电商交易场景（用户、商品、订单），设计一套简单的表结构

  * 用户表 - user

    | 字段名  | 类型         | 限制                    | 说明     |
    | ------- | ------------ | ----------------------- | -------- |
    | id      | bigint(11)   | NOT NULL AUTO_INCREMENT | 用户ID   |
    | name    | varchar(30)  |                         | 用户姓名 |
    | age     | tinyint(3)   | UNSIGNED                | 用户年龄 |
    | email   | varchar(50)  |                         | 邮件     |
    | address | varchar(100) |                         | 地址     |

    * Primary Key：id

  * 商品 - product

    | 字段名      | 类型          | 限制                               | 说明     |
    | ----------- | ------------- | ---------------------------------- | -------- |
    | id          | bigint(11)    | NOT NULL AUTO_INCREMENT            | 商品ID   |
    | name        | varchar(16)   |                                    | 名称     |
    | description | varchar(100)  |                                    | 描述     |
    | price       | decimal(10,2) |                                    | 价格     |
    | count       | int(8)        | UNSIGNED NOT NULL DEFAULT 0        | 库存量   |
    | create_time | timestamp     | NOT NULL DEFAULT CURRENT_TIMESTAMP | 入库时间 |
    | update_time | timestamp     | NOT NULL DEFAULT CURRENT_TIMESTAMP | 更新时间 |

    * Primary key: id

  * 订单 - order

    | 字段名      | 类型          | 限制                               | 说明     |
    | ----------- | ------------- | ---------------------------------- | -------- |
    | id          | bigint(11)    | NOT NULL AUTO_INCREMENT            | 订单ID   |
    | user_id     | bigint(11)    | NOT NULL                           | 用户ID   |
    | product_id  | bigint(11)    | NOT NULL                           | 商品ID   |
    | number      | int(8)        | UNSIGNED NOT NULL                  | 数量     |
    | total_price | decimal(10,2) |                                    | 总价     |
    | create_time | timestamp     | NOT NULL DEFAULT CURRENT_TIMESTAMP | 创建时间 |

    * Primary Key：id




  * 建库建表语句 - `initialize.sql`

    ```sql
    CREATE DATABASE if not exists `Lab_Geek`;
    
    USE `Lab_Geek`;
    
    CREATE TABLE IF NOT EXISTS `user` (
      `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
      `name` varchar(30) DEFAULT NULL COMMENT '姓名',
      `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
      `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
      `address` varchar(100) DEFAULT NULL COMMENT '地址',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    CREATE TABLE IF NOT EXISTS `product` (
        `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
        `name` varchar(16) DEFAULT NULL COMMENT '商品名称',
        `description` varchar(100) DEFAULT NULL COMMENT '商品描述',
        `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
        `count` int(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '库存量',
        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    CREATE TABLE IF NOT EXISTS `order` (
        `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
        `user_id` bigint(11) NOT NULL COMMENT '用户ID',
        `product_id` bigint(11)NOT NULL COMMENT '商品ID',
        `number` int(8) UNSIGNED NOT NULL COMMENT '数量',
        `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        PRIMARY KEY (`id`),
        FOREIGN KEY (`user_id`) REFERENCES user(id),
        FOREIGN KEY (`product_id`) REFERENCES product(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    ```

    

  * 







