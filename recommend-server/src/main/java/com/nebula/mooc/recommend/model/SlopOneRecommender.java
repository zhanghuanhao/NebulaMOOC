package com.nebula.mooc.recommend.model;

import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import java.util.List;

public class SlopOneRecommender {

    /*
        size：需要推荐课程的数目
     */
    public List<RecommendedItem> mySlopeOneRecommender(long userID,int size){
        List<RecommendedItem> recommendations = null;
        try {
            DataModel model = CourseScoreModel.myDataModel();//构造数据模型
            Recommender recommender = new CachingRecommender(new SlopeOneRecommender(model));//构造推荐引擎
            recommendations = recommender.recommend(userID, size);//得到推荐的结果，size是推荐结果的数目
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return recommendations;
    }

}
