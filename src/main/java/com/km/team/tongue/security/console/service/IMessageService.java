package com.km.team.tongue.security.console.service;

import java.util.List;
import java.util.Map;

/**
 * Author: jiangjun
 * Date: 2019/9/14
 * Time: 12:06 上午
 * Description:
 */
public interface IMessageService {

    /**
     * 厨师警告统计
     * @return
     */
//    Map<String, List<Integer>> getCookWarn();

    /**
     * 湿度、温度、持证上岗
     * @return
     */
    Map<String, List<Integer>> getDegreeWarn();

    /**
     * 消毒柜、留样柜、紫外灯
     * @return
     */
    Map<String, List<Integer>> getCabinetWarn();

    List<Integer> getCookHatWarn();

    List<Integer> getCookMaskWarn();

    List<Integer> getCookClothesWarn();
}
