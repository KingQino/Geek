package io.github.kingqino.week07.assignment2.compare_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Comparison_02 {
    public static final int RECORD_NUM = 100_000;

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/db?rewriteBatchedStatements=true";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 1805ms
        addBatchSql();

        // 45s
//        normalSql();

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒，即" + (end - start) / 1000 + "秒");
    }


    public static void addBatchSql() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement =  connection.prepareStatement("INSERT INTO `order`(`user_id`,`product_id`) VALUES(?,?)");


            for (int i = 0; i < RECORD_NUM; i++){
                preparedStatement.setLong(1, (long) (Math.random() * 1_000_000_000));
                preparedStatement.setLong(2, (long) (Math.random() * 1_000_000_000));
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public static void normalSql() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement =  connection.prepareStatement("INSERT INTO `order`(`user_id`,`product_id`) VALUES(?,?);");

            for (int i = 0; i < RECORD_NUM; i++){
                preparedStatement.setLong(1, (long) (Math.random() * 1_000_000_000));
                preparedStatement.setLong(2, (long) (Math.random() * 1_000_000_000));
                preparedStatement.executeUpdate();
            }

            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
