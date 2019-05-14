/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.service;

import com.nebula.mooc.core.entity.Score;

public interface ScoreService {

    void insertScore(Score score);

    void updateScore(Score score);

}
