## 第八周作业

* 作业2 - 完成

  * 分库分表规则：

    * 分库

      | 逻辑(订单ID % 2) | 库名   |
      | ---------------- | ------ |
      | 0                | mydb_0 |
      | 1                | mydb_1 |

    * 分表

      | 逻辑(订单ID%16) | 表名              |
      | --------------- | ----------------- |
      | 0               | t_order_master_0  |
      | 1               | t_order_master_1  |
      | 2               | t_order_master_2  |
      | 3               | t_order_master_3  |
      | 4               | t_order_master_4  |
      | 5               | t_order_master_5  |
      | 6               | t_order_master_6  |
      | 7               | t_order_master_7  |
      | 8               | t_order_master_8  |
      | 9               | t_order_master_9  |
      | 10              | t_order_master_10 |
      | 11              | t_order_master_11 |
      | 12              | t_order_master_12 |
      | 13              | t_order_master_13 |
      | 14              | t_order_master_14 |
      | 15              | t_order_master_15 |

* 作业6 - 完成

  

