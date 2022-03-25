package com.ruoyi.common.utils;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableSupport;

/**
* @author 终于白发始于青丝
* @Classname PageUtils
* @Description 类方法说明：分页工具类
* @Date 2022/3/25 下午 14:03
*/
public class PageUtils extends PageHelper {
    /**
     * 设置请求分页数据
     */
    public static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }
}
