package com.nebula.mooc.recommend.model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;

import javax.sql.ConnectionPoolDataSource;

//从数据库中摘取数据建立数据模型
public class PostScoreModel {
    public static JDBCDataModel myDataModel() {
        MysqlDataSource dataSource = new MysqlDataSource();
        JDBCDataModel dataModel = null;
        try {
            dataSource.setServerName("localhost");
            dataSource.setUser("root");
            dataSource.setPassword("123456");
            dataSource.setDatabaseName("movie");


            ConnectionPoolDataSource connectionPool=ConnectionPoolDataSource;
            // use JNDI
            dataModel = new MySQLJDBCDataModel(connectionPool,"movie_preferences", "userID", "movieID","preference");


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dataModel;
    }
}
