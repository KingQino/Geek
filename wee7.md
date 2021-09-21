# 0x07-01 系统性能优化



> 工欲善其事，必先利其器！ 学会使用工具可以帮助我们变得优秀。



## 1. MySQL 事务与锁<sup>*</sup>

* 事务可靠性ACID：

  * Atomicity: 原子性，一次事务中的操作要么全部成功，要么全部失败。

  * Consistency: 一致性，保证事务只能把数据库从一个有效（正确）的状态“转移”到另一个有效（正确）的状态。什么是数据库的有效(正确）的状态？

    > Consistency ensures that a transaction can **only bring the database from one valid state to another**, maintaining database [invariants](https://link.zhihu.com/?target=https%3A//en.wikipedia.org/wiki/Invariant_(computer_science)): **any data written to the database must be valid according to all defined rules, including [constraints](https://link.zhihu.com/?target=https%3A//en.wikipedia.org/wiki/Integrity_constraints), [cascades](https://link.zhihu.com/?target=https%3A//en.wikipedia.org/wiki/Cascading_rollback),[triggers](https://link.zhihu.com/?target=https%3A//en.wikipedia.org/wiki/Database_trigger), and any combination thereof.** This prevents database corruption by an illegal transaction, but does **not guarantee that a transaction is *correct*.**

  * Isolation：隔离性，可见性，保护事务不会互相干扰，包含4种隔离级别。

  * Durability： 持久性，事务提交成功后，不会丢数据。如电源故障，系统崩溃。

  > 关于一致性的理解：应用系统从一个正确的状态到另一个正确的状态，而ACID就是说事务能够通过AID来保证这个C的过程。C是目的，AID都是手段。

  * InnoDB：
    - 双写缓冲区、故障恢复、操作系统、fsync() 、磁盘存储、缓存、UPS、网络、备份策略 ......

* InnoDB Locking

  > 关于锁， [官网](https://dev.mysql.com/doc/refman/5.7/en/innodb-locking.html) 给出了详细的说明

  -

  <img src="pictures/80.png" alt="锁名" style="zoom:80%;" />

  * Shared and Exclusive Locks - row-level locks
    * A [shared (`S`) lock](https://dev.mysql.com/doc/refman/5.7/en/glossary.html#glos_shared_lock) permits the transaction that holds the lock to read a row.
    * An [exclusive (`X`) lock](https://dev.mysql.com/doc/refman/5.7/en/glossary.html#glos_exclusive_lock) permits the transaction that holds the lock to update or delete a row.
  * Intention Locks - *multiple granularity locking*

  * 表级锁

    * 意向锁: 表明事务稍后要进行哪种类型的锁定
      * 共享意向锁(IS): 打算在某些行上设置共享锁
      * 排他意向锁(IX): 打算对某些行设置排他锁
      * Insert 意向锁: Insert 操作设置的间隙锁
    * 其他
      * 自增锁(AUTO-IN)
      * LOCK TABLES/DDL

    

    -

    <img src="pictures/81.png" alt="Mysql事务" style="zoom:100%;" />

    ```sql
    SHOW ENGINE INNODB STATUS;
    ```

  * 行级锁

    * 记录锁(Record): 始终锁定索引记录，注意隐藏的聚簇索引
    * 间隙锁(Gap): 锁住一个范围
    * 临键锁(Next-Key): 记录锁+间隙锁的组合;可“锁定”表中不存在记录
    * 谓词锁(Predicat): 空间索引

  * 死锁

    * 阻塞与互相等待
    * 增删改、锁定读
    * 死锁检测与自动回滚
    * 锁粒度与程序设计

* 事务的隔离级别 - Transaction Isolation Level

  * 并发事务带来的问题：
    1. 脏读
    2. 不可重复读
    3. 幻读

  * 《SQL:1992标准》规定了四种事务隔离级别(Isolation):

    1. 读未提交: READ UNCOMMITTED

       * 很少使用

       * 不能保证一致性

       * 脏读(dirty read) : 使用到从未被确认的数据(例如: 早期版本、回滚)

         

       * 锁：

         * 以非锁定方式执行
         * 可能的问题: 脏读、幻读、不可重复读

    2. 读已提交: READ COMMITTED

       * 每次查询都会设置和读取自己的新快照。

       * 仅支持基于行的 bin-log

       * UPDATE 优化: 半一致读(semi-consistent read)

       * 不可重复读: 不加锁的情况下, 其他事务 UPDATE 或 DELETE 会对查询结果有影响

       * 幻读(Phantom): 加锁后, 不锁定间隙，其他事务可以 INSERT

         

       * 锁：

         * 锁定索引记录, 而不锁定记录之间的间隙
         * 可能的问题: 幻读、不可重复读

    3. 可重复读: REPEATABLE READ

       * InnoDB 的默认隔离级别

       * 使用事务第一次读取时创建的快照

       * 多版本技术

         

       * 锁：

         * 使用唯一索引的唯一查询条件时，只锁定查找到的索引记录, 不锁定间隙。

         * 其他查询条件，会锁定扫描到的索引范围，通过间隙锁或临键锁来阻止其他会话在这

           个范围中插入值。

         * 可能的问题: InnoDB 不能保证没有幻读，需要加锁

    4. 可串行化: SERIALIZABLE

       * 最严格的级别，事务串行执行，资源消耗最大

  * [图解 MySQL 事务隔离级别](https://zhuanlan.zhihu.com/p/102883333)

  * 其他：

    * 事务隔离是数据库的基础特征。

    * 可以设置全局的默认隔离级别
    * 可以单独设置会话的隔离级别
    * InnoDB 实现与标准之间的差异

  * -

    <img src="pictures/82.png" alt="事务隔离级别" style="zoom:100%;" />
    
  * 问题回顾:

    * 脏读(dirty read) : 使用到从未被确认的数据(例如: 早期版本、回滚)
    * 不可重复读(unrepeated read): 不加锁的情况下，其他事务 update 会对结果集有影响
    * 幻读(phantom read): 相同的查询语句，在不同的时间点执行时，产生不同的结果集不同

* 日志 (`undo log`和`redo log`)

  * `undo log`: 撤销日志
    * 保证事务的原子性
    * 用处：事务回滚，一致性读、崩溃恢复
    * 记录事务回滚时所需的撤消操作
    * 一条 INSERT 语句，对应一条 DELETE 的 undo log
    * 每个 UPDATE 语句，对应一条相反 UPDATE 的 undo log
    * 保存位置：
      * system tablespace(MySQL 5.7 默认)
      * undo tablespaces(MySQL 8.0 默认)
    * 回滚段（rollback segment）
  * `redo log`: 重做日志
    * 确保事务的持久性，防止事务提交后数据未刷新到磁盘就掉线或崩溃
    * 事务执行过程中写入 redo log，记录事务对数据页做了哪些修改。
    * 提升性能: WAL(Write-Ahead Logging) 技术，先写日志，再写磁盘
    * 日志文件: ib_logfile0, ib_logfile1
    * 日志缓冲: innodb_log_buffer_size
    * 强刷: fsync()

  -

  <img src="pictures/83.png" alt="重做日志" style="zoom:80%;" />

* MVCC：Multi-Version Concurrency Control （多版本并发控制）

  * 使 InnoDB 支持一致性读: READ COMMITTED 和 REPEATABLE READ
  * 让查询不被阻塞、无需等待被其他事务持有的锁，这种技术手段可以增加并发性能
  * InnoDB 保留被修改行的旧版本
  * 查询正在被其他事务更新的数据时，会读取更新之前的版本
  * 每行数据都存在一个版本号, 每次更新时都更新该版本
  * 这种技术在数据库领域的使用并不普遍。 某些数据库, 以及某些 MySQL 存储引擎都不支持

  > 聚簇索引的更新 = 替换更新
  >
  > 二级索引的更新 = 删除+新建

* MVCC 实现机制

  * 隐藏列

  * 事务链表， 保存还未提交的事务，事务提交则会从链表中摘除

  * Read view: 每个 SQL 一个, 包括 rw_trx_ids, low_limit_id, up_limit_id, low_limit_no 等

  * 回滚段: 通过 undo log 动态构建旧版本数据

    | 隐藏列 | DB_TRX_ID                       | DB_ROLL_PTR                               | DB_ROW_ID              |
    | ------ | ------------------------------- | ----------------------------------------- | ---------------------- |
    | 长度   | 6-byte                          | 7-byte                                    | 6-byte                 |
    | 说明   | 指示最后插入或更新该行的事务 ID | 回滚指针。指向回滚段中写入的undo log 记录 | 聚簇 row ID/ 聚簇 索引 |

* 演示事务与锁

  - 这里仅展示实验的部分截图，相关实验可以自行 Baidu 或 Google 复现。

  - 一些可能会用到的命令：

    ```sql
    SELECT CONNECTION_ID();
    SHOW ENGINE INNODB STATUS;
    SELECT * FROM performance_schema.data_locks;
    SHOW PROCESSLIST
    ```

    -

    ![demo1](pictures/84.png)

    -

    ![demo2](pictures/85.png)























