/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.core.entity.Score;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreDao {

    int insertScore(Score score);

    int updateScore(Score score);

}
