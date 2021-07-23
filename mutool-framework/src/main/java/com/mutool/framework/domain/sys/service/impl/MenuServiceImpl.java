package com.mutool.framework.domain.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mutool.framework.domain.sys.entity.Menu;
import com.mutool.framework.domain.sys.entity.MenuTree;
import com.mutool.framework.domain.sys.mapper.MenuMapper;
import com.mutool.framework.domain.sys.service.IMenuService;
import com.mutool.framework.enums.MenuStatusEnum;
import com.mutool.framework.util.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author les
 * @since 2021-04-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getChildMenuList(Integer menuId, MenuStatusEnum menuStatusEnum){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid", menuId);
        if(menuStatusEnum != null){
            queryWrapper.eq("status", menuStatusEnum.getCode());
        }
        queryWrapper.orderByAsc("sort");
        return menuMapper.selectList(queryWrapper);
    }

    @Override
    public List<Menu> getAllChildMenuList(Integer menuId, MenuStatusEnum menuStatusEnum){
        List<Menu> allChildMenuList = new ArrayList<>();
        return getChildMenu(menuId, menuStatusEnum, allChildMenuList);
    }

    @Override
    public List<Menu> getMenuList(MenuStatusEnum menuStatusEnum) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(menuStatusEnum != null){
            queryWrapper.eq("status", menuStatusEnum.getCode());
        }
        queryWrapper.orderByAsc("sort");
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        if(CollUtil.isEmpty(menuList)){
            return Collections.emptyList();
        }
        return menuList;
    }

    //根据菜单数据列表组织数据
    @Override
    public List<MenuTree> getAllMenuTree(MenuStatusEnum menuStatusEnum){
        List<Menu> menuList = getMenuList(menuStatusEnum);
        if(CollUtil.isEmpty(menuList)){
            return Collections.emptyList();
        }
        List<MenuTree> menuTreeList = MenuUtil.turnToMenuTreeList(menuList);
        List<MenuTree> topMenuList = menuTreeList.stream()
                .filter(i -> i.getPid() == null || i.getPid() == 0).collect(Collectors.toList());
        topMenuList.forEach(i -> i.setChildList(MenuUtil.getChild(i.getId(), menuTreeList)));
        return topMenuList;
    }


    /**
     * 递归获取所有菜单
     * @param menuId
     * @param allMenuList 获取到的所有菜单列表数据
     * @return
     */
    private List<Menu> getChildMenu(Integer menuId, MenuStatusEnum menuStatusEnum, List<Menu> allMenuList){
        List<Menu> childList = getChildMenuList(menuId, menuStatusEnum);
        if(CollUtil.isEmpty(childList)){
            return Collections.emptyList();
        }
        allMenuList.addAll(childList);
        childList.forEach(i -> allMenuList.addAll(getChildMenu(i.getId(), menuStatusEnum, allMenuList)));
        return childList;
    }

    private void setChildMenuTree(MenuTree menuTree, MenuStatusEnum menuStatusEnum){
        List<Menu> childList = getChildMenuList(menuTree.getId(), menuStatusEnum);
        if(CollUtil.isEmpty(childList)){
            return;
        }
        List<MenuTree> childMenuTreeList = MenuUtil.turnToMenuTreeList(childList);
        childMenuTreeList.forEach(i -> setChildMenuTree(i, menuStatusEnum));
        menuTree.setChildList(childMenuTreeList);
    }



}
