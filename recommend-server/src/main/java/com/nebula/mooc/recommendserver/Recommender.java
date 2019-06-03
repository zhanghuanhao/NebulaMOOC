package com.nebula.mooc.recommendserver;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Recommender {

    private static Connection conn = null;
    private static ReloadFromJDBCDataModel model = null;
    final static int RECOMMENDER_NUM = 5;
    private static int userNum = 0;
    /*
        利用RecommenderResult传递推荐结果
     */
    private static long [][] RecommenderResult;
    public static void main(String[] args) throws TasteException, IOException {

        MysqlDataSource dataSource = new MysqlDataSource();

        try {
            dataSource.setServerName("119.23.63.134");
            dataSource.setUser("root");
            dataSource.setPassword("Mooc123456");
            dataSource.setDatabaseName("mooc");

            JDBCDataModel jdbcDataModel=new MySQLJDBCDataModel(dataSource,"course_score","USER_ID","COURSE_ID","SCORE", "CREATED_TIME");
            //利用ReloadFromJDBCDataModel包裹jdbcDataModel,可以把输入加入内存计算，加快计算速度。
            model = new ReloadFromJDBCDataModel(jdbcDataModel);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        //获取用户个数
        userNum = model.getNumUsers();
        RecommenderResult = new long[userNum][RECOMMENDER_NUM];
        //用SVD模型计算推荐结果
//        svd(model);
        itemCF(model);

    }

    //SVD模型
    public static void svd(DataModel dataModel) throws TasteException {
        RecommenderBuilder recommenderBuilder = RecommendFactory.svdRecommender(new ALSWRFactorizer(dataModel, 10, 0.05, 10));

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);

        LongPrimitiveIterator iter = dataModel.getUserIDs();
        while (iter.hasNext()) {
            int uid = (int)iter.nextLong();
            List list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM);
//            WriteResult(uid, list);
            RecommendFactory.showItems(uid, list, true);

        }
    }

    public static void itemCF(DataModel dataModel) throws TasteException {
        ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);

        LongPrimitiveIterator iter = dataModel.getUserIDs();
        while (iter.hasNext()) {
            int uid = (int)iter.nextLong();
            List list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM);
            RecommendFactory.showItems(uid, list, true);
        }
    }

    //将推荐结果写进二维数据传给前端
    public static void WriteResult(int uid, List<RecommendedItem> recommendations){
        for (RecommendedItem recommendation : recommendations) {
            for(int i=0;i<RECOMMENDER_NUM;i++){
                RecommenderResult[uid][i] = recommendation.getItemID();
            }
        }
    }


}