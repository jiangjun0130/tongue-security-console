package com.km.team.tongue.security.console.controller;

import com.km.team.tongue.security.console.beans.vo.ResponseData;
import com.km.team.tongue.security.console.controller.base.BaseController;
import com.km.team.tongue.security.console.utils.ResponseDataUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

/**
 * Author: jiangjun
 * Date: 2019/9/14
 * Time: 12:03 上午
 * Description: 告警信息
 */
@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("warning")
public class WarningController extends BaseController<WarningController> {

    /**
     * 获取厨师穿戴衣帽告警信息
     * @return
     */
//    @GetMapping("cook")
//    public ResponseData getCookWarn() {
//        return ResponseDataUtil.buildSuccess(messageService.getCookWarn());
//    }

    /**
     * 白色厨师帽
     * @return
     */
    @GetMapping("cookHat")
    public ResponseData getCookHatWarn() {
        return ResponseDataUtil.buildSuccess(messageService.getCookHatWarn());
    }

    /**
     * 白色口罩
     * @return
     */
    @GetMapping("cookMask")
    public ResponseData getCookMaskWarn() {
        return ResponseDataUtil.buildSuccess(messageService.getCookMaskWarn());
    }

    /**
     * 未穿白色厨师服
     * @return
     */
    @GetMapping("cookClothes")
    public ResponseData getCookClothesWarn() {
        return ResponseDataUtil.buildSuccess(messageService.getCookClothesWarn());
    }

    /**
     * 温度、湿度、持证上岗统计
     * @return
     */
//    @GetMapping("degree")
//    public ResponseData getDegreeWarn() {
//        return ResponseDataUtil.buildSuccess(messageService.getDegreeWarn());
//    }

    /**
     * 统计当天 湿度 告警
     * @return
     */
    @GetMapping("degreeHumidity")
    public ResponseData getDegreeHumidityWarn() {
        return ResponseDataUtil.buildSuccess(messageService.getDegreeHumidityWarn());
    }

    /**
     * 统计当天 温度 告警
     * @return
     */
    @GetMapping("degreeTemperature")
    public ResponseData getDegreeTemperatureWarn() {
        return ResponseDataUtil.buildSuccess(messageService.getDegreeTemperatureWarn());
    }

    /**
     * 统计当天 持证上岗 告警
     * @return
     */
    @GetMapping("degreeCertificate")
    public ResponseData getDegreeCertificateWarn() {
        return ResponseDataUtil.buildSuccess(messageService.getDegreeCertificateWarn());
    }

    /**
     * 消毒柜、留样柜、紫外灯
     * @return
     */
    @GetMapping("cabinet")
    public ResponseData getCabinetWarn() {
        return ResponseDataUtil.buildSuccess(messageService.getCabinetWarn());
    }
}
