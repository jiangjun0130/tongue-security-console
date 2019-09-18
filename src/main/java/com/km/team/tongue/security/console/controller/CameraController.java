package com.km.team.tongue.security.console.controller;

import com.km.team.tongue.security.console.beans.pojo.Tcamera;
import com.km.team.tongue.security.console.beans.vo.ResponseData;
import com.km.team.tongue.security.console.controller.base.BaseController;
import com.km.team.tongue.security.console.utils.ResponseDataUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: jiangjun
 * Date: 2019/9/8
 * Time: 9:20 下午
 * Description:
 */
@RestController
@RequestMapping("camera")
public class CameraController extends BaseController<CameraController> {

    @GetMapping("get/{id}")
    private ResponseData getCameraById(@PathVariable("id") String id) {
        Tcamera camera = cameraService.getDataById(id);
        return ResponseDataUtil.buildSuccess(camera);
    }

    /**
     * 实时摄像头统计
     * @return
     */
    @GetMapping("realTimeTotal")
    public ResponseData realTimeTotal() {
        return ResponseDataUtil.buildSuccess(cameraService.realTimeTotal());
    }

    /**
     * 摄像头分类接入统计
     * @return
     */
    @GetMapping("categoryJoinTotal")
    public ResponseData categoryJoinTotal() {
        List<Map<String, Map<String, Object>>> maps = cameraService.categoryJoinTotal();
        int lastWeekOrgTotal = orgService.getLastWeekTotal();
        int lastWeekCameraTotal = cameraService.getLastWeekTotal();
        Map<String, Object> map = new HashMap<>();
        // 分类统计信息
        map.put("categoryTotal", maps);
        // 近一周新增单位
        map.put("lastWeekOrgTotal", lastWeekOrgTotal);
        map.put("lastWeekCameraTotal", lastWeekCameraTotal);
        return ResponseDataUtil.buildSuccess(map);
    }

    /**
     * 摄像头分类接入统计--社会餐饮
     * @return
     */
    @GetMapping("socialCaterJoinTotal")
    public ResponseData socialCaterJoinTotal() {
        Map<String, Object> map = cameraService.socialCaterJoinTotal();
        return ResponseDataUtil.buildSuccess(map);
    }

    /**
     * 摄像头分类接入统计--职工食堂
     * @return
     */
    @GetMapping("staffDiningHallCaterJoinTotal")
    public ResponseData staffDiningHallCaterJoinTotal() {
        Map<String, Object> map = cameraService.staffDiningHallCaterJoinTotal();
        return ResponseDataUtil.buildSuccess(map);
    }

    /**
     * 摄像头分类接入统计--学校食堂
     * @return
     */
    @GetMapping("schoolDiningHallCaterJoinTotal")
    public ResponseData schoolDiningHallCaterJoinTotal() {
        Map<String, Object> map = cameraService.schoolDiningHallCaterJoinTotal();
        return ResponseDataUtil.buildSuccess(map);
    }

    /**
     * 摄像头分类接入统计--阳光车间
     * @return
     */
    @GetMapping("shiningWorkshopCaterJoinTotal")
    public ResponseData shiningWorkshopCaterJoinTotal() {
        Map<String, Object> map = cameraService.shiningWorkshopCaterJoinTotal();
        return ResponseDataUtil.buildSuccess(map);
    }

    /**
     * 摄像头分类接入统计--集体聚餐
     * @return
     */
    @GetMapping("unionDinnerCaterJoinTotal")
    public ResponseData unionDinnerCaterJoinTotal() {
        Map<String, Object> map = cameraService.unionDinnerCaterJoinTotal();
        return ResponseDataUtil.buildSuccess(map);
    }

    /**
     * 摄像头分类接入统计--全部
     * @return
     */
    @GetMapping("allJoinTotal")
    public ResponseData allJoinTotal() {
        Map<String, Object> map = cameraService.allJoinTotal();
        return ResponseDataUtil.buildSuccess(map);
    }

    /**
     * 摄像头分类接入统计--近一周新增单位
     * @return
     */
    @GetMapping("lastWeekIncr")
    public ResponseData lastWeekIncr() {
        int lastWeekOrgTotal = orgService.getLastWeekTotal();
        int lastWeekCameraTotal = cameraService.getLastWeekTotal();
        Map<String, Object> map = new HashMap<>();
        // 分类统计信息
        // 近一周新增单位
        map.put("lastWeekOrgTotal", lastWeekOrgTotal);
        map.put("lastWeekCameraTotal", lastWeekCameraTotal);
        return ResponseDataUtil.buildSuccess(map);
    }

}
