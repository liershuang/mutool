package com.mutool.commonweb.base;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mutool.commonweb.common.IdEntity;
import com.mutool.commonweb.enums.ErrorCodeEnum;
import com.mutool.commonweb.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * 描述：<br>
 * 作者：les<br>
 * 日期：2021/4/13 22:40<br>
 */
public class BaseController<Service extends IService<T>, T extends IdEntity> {
    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;
    @Autowired
    protected Service baseService;

    public BaseController() {
    }

    private static final String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtil.parse(text, parsePatterns));
            }
        });
        // JSONObject 类型转换
        binder.registerCustomEditor(JSONObject.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(JSONUtil.parseObj(text));
            }
        });
        // JSONArray 类型转换
        binder.registerCustomEditor(JSONArray.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(JSONUtil.parseObj(text));
            }
        });
    }

    @GetMapping({"/{id}"})
    public T get(@PathVariable Long id) {
        return this.baseService.getById(id);
    }

    @PostMapping("add")
    public Boolean add(@RequestBody T model) {
        return this.baseService.save(model);
    }

    @PostMapping("update")
    public Boolean update(@RequestBody T model) {
        if(null == model.getId()){
            throw new BizException(ErrorCodeEnum.ID_REQUIRED);
        }
        return this.baseService.updateById(model);
    }

    @PostMapping({"/remove/{id}"})
    public Boolean remove(@PathVariable("id") Long id) {
        return this.baseService.removeById(id);
    }

    public Page getPage() {
        long current = 1L;
        long size = 10L;
        String page = this.request.getParameter("page");
        if (StringUtils.isNotBlank(page)) {
            Long pageNum = Long.valueOf(page);
            if (pageNum > 0L) {
                current = pageNum;
            }
        }

        String limitStr = this.request.getParameter("limit");
        if (StringUtils.isNotBlank(page)) {
            Long limit = Long.valueOf(limitStr);
            if (limit > 0L) {
                size = limit;
            }
        }

        return new Page(current, size);
    }

}
