package com.km.team.tongue.security.console.dao.mybatis.custom;

public interface TcameraMapperCustom {

    int getOnlineTotal();

    int getOfflineTotal();

    /**
     * 本周摄像头分类接入统计
     * @param key
     * @return
     */
    int getCurrentWeekTotal(String key);

    /**
     * 上周摄像头分类接入统计
     * @param key
     * @return
     */
    int getLastWeekTotal(String key);

    /**
     * 摄像头分类接入在线数量统计
     * @param key
     * @return
     */
    int getCategoryOnlineTotal(String key);

    /**
     * 摄像头分类接入离线数量统计
     * @param key
     * @return
     */
    int getCategoryOfflineTotal(String key);
}