package com.km.team.tongue.security.console.controller;

import com.km.team.tongue.security.console.beans.vo.ResponseData;
import com.km.team.tongue.security.console.controller.base.BaseController;
import com.km.team.tongue.security.console.utils.ResponseDataUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: jiangjun
 * Date: 2019/9/9
 * Time: 11:40 下午
 * Description:
 */
@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("org")
public class OrgController extends BaseController<CameraController> {

    /**
     * 共接入单位统计
     * @return
     */
    @GetMapping("total")
    public ResponseData getTotalStatistics() {
        return ResponseDataUtil.buildSuccess(orgService.getTotal());
    }

    /**
     * 实时单位接入统计（按单位的create_time来计算）
     * @return
     */
    @GetMapping("realTimeOrgJoin")
    public ResponseData getRealTimeOrgJoin() {
        return ResponseDataUtil.buildSuccess(orgService.getRealTimeOrgJoin());
    }

    /**
     * 各地市接入单位数量统计排行
     */
    @GetMapping("region")
    public ResponseData getRegionStatistics() {
        return ResponseDataUtil.buildSuccess(orgService.getRegionStatistics());
    }

    /**
     * 各类餐饮企业占比统计
     * @return
     */
    @GetMapping("companyRatio")
    public ResponseData getCompanyRatio() {
        List<HashMap<String, Object>> companyRatio = orgService.getCompanyRatio();
        Map<String, Object> map = new HashMap<>(2);
        map.put("companyTotal", companyRatio);
        Map<String, Object> ratioMap = new HashMap<>();
        companyRatio.forEach(item -> {
            String name = item.get("controlUnitName").toString();
            Integer total = Integer.valueOf(item.get("count").toString());
            Integer unitTotal = orgService.getTotalByUnitName(name);
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String ratio = numberFormat.format((float)unitTotal/(float)(total)*100);
            ratioMap.put(name, ratio);
        });
        map.put("companyRatio", ratioMap);
        return ResponseDataUtil.buildSuccess(map);
    }

    /**
     * 近一周新增单位、摄像头
     * @return
     */
    @GetMapping("weekIncrTotal")
    public ResponseData getWeekIncrTotal() {
        Integer orgIncrTotal = orgService.getWeekIncrTotal();
        Integer cameraIncrTotal = cameraService.getWeekIncrTotal();
        Map<String, Object> map = new HashMap<>(2);
        map.put("orgIncrTotal", orgIncrTotal);
        map.put("cameraIncrTotal", cameraIncrTotal);
        return ResponseDataUtil.buildSuccess(map);
    }
}
