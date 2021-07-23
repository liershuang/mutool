package com.mutool.framework.domain.sys.controller;


import com.mutool.commonweb.base.BaseController;
import com.mutool.framework.domain.sys.entity.Menu;
import com.mutool.framework.domain.sys.entity.MenuTree;
import com.mutool.framework.domain.sys.service.IMenuService;
import com.mutool.framework.enums.MenuStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author les
 * @since 2021-04-20
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends BaseController<IMenuService, Menu> {

    @Autowired
    private IMenuService menuService;

    @RequestMapping("getMenuTreeList")
    public List<MenuTree> getMenuTreeList(){
        return menuService.getAllMenuTree(MenuStatusEnum.NORMAL);
    }

    @RequestMapping("getAllMenuList")
    public List<Menu> getAllMenuList(){
        return menuService.getMenuList(null);
    }


}
