package com.mutool.commonweb.domain.settings.service;

import com.mutool.commonweb.domain.settings.entity.SysConfig;
import com.mutool.commonweb.domain.settings.entity.SysConfigGroup;

import java.util.List;

/**
 * 描述：<br>
 * 作者：les<br>
 * 日期：2021/7/22 09:47<br>
 */
public interface ISysConfigService {

    List<SysConfigGroup> queryAllConfigGroup();

    List<SysConfig> getConfigByGroupId(Integer groupId);

    SysConfig getConfigByKey(String key);

    String getValueByKey(String key);

    Integer addConfigGroup(SysConfigGroup sysConfigGroup);

    Integer addConfig(SysConfig config);

    Integer deleteConfigGroup(Integer groupId);

    Integer deleteConfig(Integer configId);

    Integer batchDeleteConfigGroup(List<Integer> groupIdList);

    Integer batchDeleteConfig(List<Integer> configIdList);

    Integer updateConfig(SysConfig sysConfig);

    Integer updateConfigGroup(SysConfigGroup configGroup);
}
