package com.km.team.tongue.security.console.service.impl;

import com.km.team.tongue.security.console.beans.pojo.Tcamera;
import com.km.team.tongue.security.console.dao.mybatis.TcameraMapper;
import com.km.team.tongue.security.console.dao.mybatis.custom.TcameraMapperCustom;
import com.km.team.tongue.security.console.service.ICameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: jiangjun
 * Date: 2019/9/8
 * Time: 9:17 下午
 * Description:
 */
@Service("cameraService")
public class CameraSreviceImpl implements ICameraService {

    @Autowired
    private TcameraMapper mapper;

    @Autowired
    private TcameraMapperCustom mapperCustom;

    @Override
    public Tcamera getDataById(String camId) {
        return mapper.selectByPrimaryKey(camId);
    }

    @Override
    public Map<String, Object> realTimeTotal() {
        int offlineTotal = mapperCustom.getOfflineTotal();
        int onlineTotal = mapperCustom.getOnlineTotal();
        Map<String, Object> map = new HashMap<>(2);
        map.put("online", onlineTotal);
        map.put("offline", offlineTotal);
        map.put("total", onlineTotal + offlineTotal);
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String onlineRatio = numberFormat.format((float)onlineTotal/(float)(offlineTotal + onlineTotal)*100);
        map.put("onlineRatio", onlineRatio);
        return map;
    }

    @Override
    public List<Map<String, Map<String, Object>>> categoryJoinTotal() {
        List<String> keyList = new ArrayList<>();
        keyList.add("社会餐饮");
        keyList.add("职工食堂");
        keyList.add("学校食堂");
        keyList.add("阳光车间");
        List<Map<String, Map<String, Object>>> list = new ArrayList<>();
        keyList.forEach(key -> {
            Map<String, Object> map = calc(key);
            Map<String, Map<String, Object>> keyMap = new HashMap<>();
            keyMap.put(key, map);
            list.add(keyMap);
        });

        // 农村聚餐、集体聚餐
        List<String> unionList = new ArrayList<>();
        unionList.add("集体聚餐");
        unionList.add("农村聚餐");
        Map<String, Object> unionMap = calc2(unionList);
        Map<String, Map<String, Object>> unionMaps = new HashMap<>();
        unionMaps.put("集体聚餐", unionMap);
        list.add(unionMaps);

        Map<String, Map<String, Object>> allKeyMap = new HashMap<>();
        Map<String, Object> allMap = calc(null);
        allKeyMap.put("all", allMap);
        list.add(allKeyMap);
        return list;
    }

    @Override
    public int getLastWeekTotal() {
        return mapperCustom.getLastWeekTotal(null);
    }

    @Override
    public Integer getWeekIncrTotal() {
        return mapperCustom.getWeekIncrTotal();
    }

    private Map<String, Object> calc(String key) {
        Map<String, Object> map = new HashMap<>();
        int currentWeekTotal = mapperCustom.getCurrentWeekTotal(key);
        int lastWeekTotal = mapperCustom.getLastWeekTotal(key);
        int onlineTotal = mapperCustom.getCategoryOnlineTotal(key);
        int offlineTotal = mapperCustom.getCategoryOfflineTotal(key);
        int categoryTotal = onlineTotal + offlineTotal;
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String onlineRatio = onlineTotal == 0 ? "0" : numberFormat.format((float)onlineTotal/(float)(offlineTotal+onlineTotal)*100);
        map.put("total", categoryTotal);
        map.put("onlineRatio", onlineRatio);
        map.put("currentWeekTotal", currentWeekTotal);
        int diff = currentWeekTotal - lastWeekTotal;
        diff = diff > 0? diff : 0;
        String incrRatio = lastWeekTotal == 0 ? "0" : numberFormat.format(((float)(diff) / (float) lastWeekTotal) * 100);
        map.put("incrRatio", incrRatio);
        return map;
    }

    private Map<String, Object> calc2(List<String> keys) {
        Map<String, Object> map = new HashMap<>();
        int currentWeekTotal = mapperCustom.getCurrentWeekTotal2(keys);
        int lastWeekTotal = mapperCustom.getLastWeekTotal2(keys);
        int onlineTotal = mapperCustom.getCategoryOnlineTotal2(keys);
        int offlineTotal = mapperCustom.getCategoryOfflineTotal2(keys);
        int categoryTotal = onlineTotal + offlineTotal;
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String onlineRatio = onlineTotal == 0 ? "0" : numberFormat.format((float)onlineTotal/(float)(offlineTotal+onlineTotal)*100);
        map.put("total", categoryTotal);
        map.put("onlineRatio", onlineRatio);
        map.put("currentWeekTotal", currentWeekTotal);
        int diff = currentWeekTotal - lastWeekTotal;
        diff = diff > 0? diff : 0;
        String incrRatio = lastWeekTotal == 0 ? "0" : numberFormat.format(((float)(diff) / (float) lastWeekTotal) * 100);
        map.put("incrRatio", incrRatio);
        return map;
    }

}
