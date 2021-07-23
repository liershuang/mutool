package com.mutool.framework.util;

import cn.hutool.core.collection.CollUtil;
import com.mutool.framework.domain.sys.entity.Menu;
import com.mutool.framework.domain.sys.entity.MenuTree;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述：<br>
 * 作者：les<br>
 * 日期：2021/7/20 22:21<br>
 */
public class MenuUtil {

    /**
     * 递归获取子菜单
     * @param menuId
     * @param menuTreeList
     * @return
     */
    public static List<MenuTree> getChild(Integer menuId, List<MenuTree> menuTreeList){
        List<MenuTree> childList = menuTreeList.stream()
                .filter(i -> i.getPid() != null && menuId.intValue() == i.getPid().intValue()).collect(Collectors.toList());
        if(CollUtil.isEmpty(childList)){
            return Collections.emptyList();
        }
        childList.forEach(i -> i.setChildList(getChild(i.getId(), menuTreeList)));
        return childList;
    }


    public static List<MenuTree> turnToMenuTreeList(List<Menu> menuList){
        if(CollUtil.isEmpty(menuList)){
            return Collections.emptyList();
        }
        return menuList.stream().map(i -> turnToMenuTree(i)).collect(Collectors.toList());
    }

    public static MenuTree turnToMenuTree(Menu menu){
        MenuTree menuTree = new MenuTree();
        menuTree.setId(menu.getId());
        menuTree.setPid(menu.getPid());
        menuTree.setName(menu.getName());
        menuTree.setType(menu.getType());
        menuTree.setIcon(menu.getIcon());
        menuTree.setPerms(menu.getPerms());
        menuTree.setUrl(menu.getUrl());
        menuTree.setSort(menu.getSort());
        menuTree.setStatus(menu.getStatus());
        menuTree.setRemark(menu.getRemark());
        //menuTree.setChildList();
        return menuTree;
    }


}
