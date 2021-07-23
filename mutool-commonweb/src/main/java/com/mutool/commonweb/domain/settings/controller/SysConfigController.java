package com.mutool.commonweb.domain.settings.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.mutool.commonweb.domain.settings.entity.SysConfig;
import com.mutool.commonweb.domain.settings.entity.SysConfigGroup;
import com.mutool.commonweb.domain.settings.service.ISysConfigService;
import com.mutool.core.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述：<br>
 * 作者：les<br>
 * 日期：2021/7/22 09:45<br>
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController {

    @Autowired
    private ISysConfigService sysConfigService;

    @RequestMapping("addConfig")
    public Integer addConfig(SysConfig sysConfig){
        return sysConfigService.addConfig(sysConfig);
    }

    @RequestMapping("deleteConfig")
    public Integer deleteConfig(Integer configId){
        return sysConfigService.deleteConfig(configId);
    }

    @RequestMapping("updateConfig")
    public Integer updateConfig(SysConfig sysConfig){
        return sysConfigService.updateConfig(sysConfig);
    }

    @RequestMapping("batchDeleteConfig")
    public Integer batchDeleteConfig(String delIds){
        if(StrUtil.isBlank(delIds)){
            throw new BizException("参数不能为空");
        }
        List<Integer> configIdList = ListUtil.toList(delIds.split(",")).stream()
                .map(i -> Integer.parseInt(i)).collect(Collectors.toList());
        return sysConfigService.batchDeleteConfig(configIdList);
    }

    @RequestMapping("addConfigGroup")
    public Integer addConfigGroup(SysConfigGroup configGroup){
        return sysConfigService.addConfigGroup(configGroup);
    }

    @RequestMapping("updateConfigGroup")
    public Integer updateConfigGroup(SysConfigGroup configGroup){
        return sysConfigService.updateConfigGroup(configGroup);
    }

    @RequestMapping("deleteConfigGroup")
    public Integer deleteConfigGroup(Integer groupId){
        return sysConfigService.deleteConfigGroup(groupId);
    }

    @RequestMapping("batchDeleteConfigGroup")
    public Integer batchDeleteConfigGroup(String delIds){
        if(StrUtil.isBlank(delIds)){
            throw new BizException("参数不能为空");
        }
        List<Integer> groupIdList = ListUtil.toList(delIds.split(",")).stream()
                .map(i -> Integer.parseInt(i)).collect(Collectors.toList());
        return sysConfigService.batchDeleteConfigGroup(groupIdList);
    }

    @RequestMapping("getConfigByGroupId")
    public List<SysConfig> getConfigByGroupId(Integer groupId){
        return sysConfigService.getConfigByGroupId(groupId);
    }

    @RequestMapping("queryAllConfigGroup")
    public List<SysConfigGroup> queryAllConfigGroup(){
        return sysConfigService.queryAllConfigGroup();
    }


}
