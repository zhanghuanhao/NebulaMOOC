package com.nebula.mooc.webserver.util;

import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.Factorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * 给用户推荐课程或帖子
 */
@Component
public class RecommendUtil {

    private static final int RECOMMEND_NUM = 5;

    @Autowired
    private DataSource dataSource;

    private ReloadFromJDBCDataModel courseModel;
    private ReloadFromJDBCDataModel postModel;
    private RecommenderBuilder recommenderBuilder;

    @PostConstruct
    private void init() throws Exception {
        JDBCDataModel courseJdbcDataModel = new MySQLJDBCDataModel(dataSource, "course_score", "USER_ID", "COURSE_ID", "SCORE", "CREATED_TIME");
        JDBCDataModel postJdbcDataModel = new MySQLJDBCDataModel(dataSource, "post_score", "USER_ID", "POST_ID", "SCORE", "CREATED_TIME");
        //利用ReloadFromJDBCDataModel包裹jdbcDataModel,可以把输入加入内存计算，加快计算速度。
        courseModel = new ReloadFromJDBCDataModel(courseJdbcDataModel);
        postModel = new ReloadFromJDBCDataModel(postJdbcDataModel);
        recommenderBuilder = dataModel -> {
            Factorizer factorizer = new ALSWRFactorizer(dataModel, 10, 0.05, 10);
            return new SVDRecommender(dataModel, factorizer);
        };
    }

    /**
     * 使用SVD模型推荐课程
     *
     * @param userId 用户ID
     * @return 返回前5的推荐数组
     */
    public Object[] recommendCourse(long userId) {
        List<RecommendedItem> list;
        try {
            courseModel.refresh(null);
            list = recommenderBuilder.buildRecommender(courseModel).recommend(userId, RECOMMEND_NUM);
        } catch (Exception e) {
            return null;
        }
        return list.stream().map(RecommendedItem::getItemID).toArray();
    }

    /**
     * 使用SVD模型推荐帖子
     *
     * @param userId 用户ID
     * @return 返回前5的推荐数组
     */
    public Object[] recommendPost(long userId) {
        List<RecommendedItem> list;
        try {
            postModel.refresh(null);
            list = recommenderBuilder.buildRecommender(postModel).recommend(userId, RECOMMEND_NUM);
        } catch (Exception e) {
            return null;
        }
        return list.stream().map(RecommendedItem::getItemID).toArray();
    }

}