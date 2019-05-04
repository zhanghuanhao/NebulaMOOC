/*
 * @author Zhanghh
 * @date 2019-05-04
 */
package com.nebula.mooc.core.util;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * 一个简单计时器实现
 */
@NotThreadSafe
public class StopWatch {

    private long startTimeMillis;

    private boolean isRunning = false;

    private long totalTimeMillis;

    public static StopWatch newInstance() {
        return new StopWatch();
    }

    /**
     * 开始计时器，自动重置开始时间
     *
     * @throws IllegalStateException 计时器已经在运行
     */
    public void start() throws IllegalStateException {
        if (isRunning()) {
            throw new IllegalStateException("Can't start StopWatch: it's already running");
        }
        this.isRunning = true;
        this.startTimeMillis = System.currentTimeMillis();
    }

    /**
     * 停止计时器，保存时间
     *
     * @throws IllegalStateException 计时器未在运行
     */
    public void stop() throws IllegalStateException {
        if (!isRunning()) {
            throw new IllegalStateException("Can't stop StopWatch: it's not running");
        }
        this.totalTimeMillis = System.currentTimeMillis() - this.startTimeMillis;
        this.isRunning = false;
    }

    /**
     * 返回此计时器是否在运行
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * 获取运行毫秒
     *
     * @return 返回时间，毫秒为单位
     * @throws IllegalStateException 已经在运行或未重新开始
     */
    public long getTotalTimeMillis() throws IllegalStateException {
        if (isRunning() || this.totalTimeMillis > this.startTimeMillis) {
            throw new IllegalStateException("No tasks run: can't get the time");
        }
        return this.totalTimeMillis;
    }

    /**
     * 获取运行秒数
     *
     * @return 返回时间，秒为单位
     * @throws IllegalStateException 已经在运行或未重新开始
     */
    public double getTotalTimeSeconds() throws IllegalStateException {
        if (isRunning() || this.totalTimeMillis > this.startTimeMillis) {
            throw new IllegalStateException("No tasks run: can't get the time");
        }
        return this.totalTimeMillis / 1000.0;
    }

}
