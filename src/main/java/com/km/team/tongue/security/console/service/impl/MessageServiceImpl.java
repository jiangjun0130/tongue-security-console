package com.km.team.tongue.security.console.service.impl;

import com.km.team.tongue.security.console.dao.mybatis.custom.PushMessageMapperCustom;
import com.km.team.tongue.security.console.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: jiangjun
 * Date: 2019/9/14
 * Time: 12:06 上午
 * Description: 告警信息
 */
@Service("messageService")
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private PushMessageMapperCustom mapperCustom;

    private List<Integer> map2List(HashMap<String, Object> map) {
        List<Integer> hatList = new ArrayList<>();
        hatList.add(Integer.valueOf(map.get("four").toString()));
        hatList.add(Integer.valueOf(map.get("eight").toString()));
        hatList.add(Integer.valueOf(map.get("twelve").toString()));
        hatList.add(Integer.valueOf(map.get("sixteen").toString()));
        hatList.add(Integer.valueOf(map.get("twenty").toString()));
        hatList.add(Integer.valueOf(map.get("twentyFour").toString()));
        return hatList;
    }

    @Override
    public Map<String, List<Integer>> getCookWarn() {
        Map<String, List<Integer>> map = new HashMap<>(3);
        // 统计当天厨师 白色厨师帽 告警
        HashMap<String, Object> hatMap = mapperCustom.getTotal("422000007");
        map.put("hatMap", map2List(hatMap));
        // 统计当天厨师 白色口罩 告警
        HashMap<String, Object> maskMap = mapperCustom.getTotal("422000005");
        map.put("maskMap", map2List(maskMap));
        // 统计当天厨师 未穿白色厨师服 告警
        HashMap<String, Object> clothesMap = mapperCustom.getTotal("422000003");
        map.put("clothesMap", map2List(clothesMap));
        return map;
    }

    @Override
    public Map<String, List<Integer>> getDegreeWarn() {
        Map<String, List<Integer>> map = new HashMap<>(3);
        // 统计当天 湿度 告警
        HashMap<String, Object> humidityMap = mapperCustom.getTotal("131627");
        map.put("humidityMap", map2List(humidityMap));
        // 统计当天 温度 告警
        HashMap<String, Object> temperatureMap = mapperCustom.getTotal("131625");
        map.put("temperatureMap", map2List(temperatureMap));
        // 统计当天 持证上岗
        List<String> listId = new ArrayList<>(3);
        listId.add("131668");
        listId.add("131669");
        listId.add("131672");
        HashMap<String, Object> certificateMap = mapperCustom.getTotalByListKey(listId);
        map.put("certificateMap", map2List(certificateMap));
        return map;
    }

    @Override
    public Map<String, List<Integer>> getCabinetWarn() {
        //TODO:补齐一下3中类型id
        Map<String, List<Integer>> map = new HashMap<>(3);
        // 消毒柜
        HashMap<String, Object> disinfectionCabinetMap = mapperCustom.getTotal("131627");
        map.put("disinfectionCabinetMap", map2List(disinfectionCabinetMap));
        // 留样柜
        HashMap<String, Object> sampleCabinetMap = mapperCustom.getTotal("131625");
        map.put("sampleCabinetMap", map2List(sampleCabinetMap));
        // 紫外灯
        HashMap<String, Object> lampMap = mapperCustom.getTotal("131625");
        map.put("lampMap",map2List(lampMap));
        return map;
    }
}
