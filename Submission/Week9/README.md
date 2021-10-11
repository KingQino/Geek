# 第九周作业 

> 参考 https://github.com/ykthree/JAVA-000/tree/main/Week_09

- 第三题
  - 完成
  - `learn-rpc-custom`


- 第七题
  - 完成s
  - `learn-transaction-tcc`

  

  1. 正常交易具体流程如下：
     1. 交易服务中，用户 1 欲使用其美元账户下的 1 美元换取用户 2 人民币账户下的 7 块钱。
     2. 美元账户服务将用户 1 美元余额减 1，将用户 2 美元余额加 1，人名币账户服务将用户 2 的人名币余额减 7， 将用户 1 人名币余额加 7。

  2. 服务设计：
     * 外汇交易服务（exchange-dubbo）：接受用户发起的外汇交易。
     * 美元账户服务（dollar-account-dubbo）：处理美元的增删改查。
     * 人名币账户服务（cny-account-dubbo）：处理人名币的增删改查。

  3. 表设计：

     * 美元账户表（dollar_account.sql）：

         ```sql
         CREATE TABLE IF NOT EXISTS `dollar_account`.`dollar_account` (
         `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
         `user_id` INT UNSIGNED NOT NULL COMMENT '用户 ID',
         `balance` DECIMAL(10,0) NOT NULL COMMENT '美元余额',
         `decrease_freeze` DECIMAL(10,0) NOT NULL COMMENT '冻结金额，扣款暂存余额',
         `increase_freeze` DECIMAL(10,0) NOT NULL COMMENT '冻结金额，转账暂存余额',
         `create_time` DATETIME NOT NULL COMMENT '创建时间',
         `update_time` DATETIME NULL COMMENT '更新时间',
         PRIMARY KEY (`id`))
         ENGINE = InnoDB
         COMMENT = '美元账户表';
         ```

     * 人民币账户表（cny_account.sql）：

       ```sql
       CREATE TABLE IF NOT EXISTS `cny_account`.`cny_account` (
       `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
       `user_id` INT UNSIGNED NOT NULL COMMENT '用户 ID',
       `balance` DECIMAL(10,0) NOT NULL COMMENT '人名币余额',
       `decrease_freeze` DECIMAL(10,0) NOT NULL COMMENT '冻结金额，扣款暂存余额',
       `increase_freeze` DECIMAL(10,0) NOT NULL COMMENT '冻结金额，转账暂存余额',
       `create_time` DATETIME NOT NULL COMMENT '创建时间',
       `update_time` DATETIME NULL COMMENT '更新时间',
       PRIMARY KEY (`id`))
       ENGINE = InnoDB
       COMMENT = '人名币账户表';
       ```
