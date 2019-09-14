package com.km.team.tongue.security.console.controller;

import com.km.team.tongue.security.console.beans.vo.ResponseData;
import com.km.team.tongue.security.console.controller.base.BaseController;
import com.km.team.tongue.security.console.utils.ResponseDataUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: jiangjun
 * Date: 2019/9/14
 * Time: 12:03 上午
 * Description: 告警信息
 */
@RestController
@RequestMapping("warning")
public class WarningController extends BaseController<WarningController> {

    /**
     * 获取厨师穿戴衣帽告警信息
     * @return
     */
    @GetMapping("cook")
    public ResponseData getCookWarn() {
        return ResponseDataUtil.buildSuccess(messageService.getCookWarn());
    }

    /**
     * 温度、湿度、持证上岗统计
     * @return
     */
    @GetMapping("degree")
    public ResponseData getDegreeWarn() {
        return ResponseDataUtil.buildSuccess(messageService.getDegreeWarn());
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
