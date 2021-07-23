package com.mutool.framework.domain.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mutool.framework.domain.sys.entity.Menu;
import com.mutool.framework.domain.sys.entity.MenuTree;
import com.mutool.framework.enums.MenuStatusEnum;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author les
 * @since 2021-04-20
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查询直属下级菜单列表（不包含本级）
     * @param menuId
     * @param menuStatusEnum
     * @return
     */
    List<Menu> getChildMenuList(Integer menuId, MenuStatusEnum menuStatusEnum);

    /**
     * 递归查询所有下级菜单（不包含本级）
     * @param menuId
     * @param menuStatusEnum
     * @return
     */
    List<Menu> getAllChildMenuList(Integer menuId, MenuStatusEnum menuStatusEnum);

    List<Menu> getMenuList(MenuStatusEnum menuStatusEnum);

    //根据菜单数据列表组织数据
    List<MenuTree> getAllMenuTree(MenuStatusEnum menuStatusEnum);
}
