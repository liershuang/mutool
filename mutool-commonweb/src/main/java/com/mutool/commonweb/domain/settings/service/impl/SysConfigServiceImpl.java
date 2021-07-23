package com.mutool.commonweb.domain.settings.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mutool.commonweb.domain.settings.entity.SysConfig;
import com.mutool.commonweb.domain.settings.entity.SysConfigGroup;
import com.mutool.commonweb.domain.settings.mapper.SysConfigGroupMapper;
import com.mutool.commonweb.domain.settings.mapper.SysConfigMapper;
import com.mutool.commonweb.domain.settings.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：<br>
 * 作者：les<br>
 * 日期：2021/7/22 12:21<br>
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {

    @Autowired
    private SysConfigGroupMapper sysConfigGroupMapper;
    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    public List<SysConfigGroup> queryAllConfigGroup() {
        return sysConfigGroupMapper.selectList(null);
    }

    @Override
    public List<SysConfig> getConfigByGroupId(Integer groupId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("group_id", groupId);
        return sysConfigMapper.selectList(queryWrapper);
    }

    @Override
    public SysConfig getConfigByKey(String key) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("key", key);
        return sysConfigMapper.selectOne(queryWrapper);
    }

    @Override
    public String getValueByKey(String key) {
        SysConfig config = getConfigByKey(key);
        return StrUtil.blankToDefault(config.getValue(), "");
    }

    @Override
    public Integer addConfigGroup(SysConfigGroup sysConfigGroup) {
        return sysConfigGroupMapper.insert(sysConfigGroup);
    }

    @Override
    public Integer addConfig(SysConfig config) {
        return sysConfigMapper.insert(config);
    }

    @Override
    public Integer deleteConfigGroup(Integer groupId) {
        return sysConfigGroupMapper.deleteById(groupId);
    }

    @Override
    public Integer deleteConfig(Integer configId) {
        return sysConfigMapper.deleteById(configId);
    }

    @Override
    public Integer batchDeleteConfigGroup(List<Integer> groupIdList) {
        return sysConfigGroupMapper.deleteBatchIds(groupIdList);
    }

    @Override
    public Integer batchDeleteConfig(List<Integer> configIdList) {
        return sysConfigMapper.deleteBatchIds(configIdList);
    }

    @Override
    public Integer updateConfig(SysConfig sysConfig) {
        return sysConfigMapper.updateById(sysConfig);
    }

    @Override
    public Integer updateConfigGroup(SysConfigGroup configGroup) {
        return sysConfigGroupMapper.updateById(configGroup);
    }
}
