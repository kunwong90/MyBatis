package com.learn.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * ServletRequestListener用于监听用户请求的到达
 * ServletRequestAttributeListener用于监听ServletRequest(request)范围内属性的变化
 */
//@WebListener
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {

    /**
     * 用户请求到达、被初始化时触发该方法
     * @param sre
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest)sre;
        System.out.println("------发向" + request.getRequestURI() + "请求被初始化------");
    }

    /**
     * 当用户请求结束、被销毁时触发
     * @param sre
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest)sre;
        System.out.println("------发向" + request.getRequestURI() + "请求被销毁------");
    }

    /**
     * 当程序向request范围添加属性时触发该方法
     * @param srae
     */
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        ServletContext context = srae.getServletContext();
        String name = srae.getName();
        Object value = srae.getValue();
        System.out.println(context + "范围内添加了名为" + name + ",值为" + value + "的属性");
    }

    /**
     * 当程序向request范围删除属性时触发该方法
     * @param srae
     */
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        ServletContext context = srae.getServletContext();
        String name = srae.getName();
        Object value = srae.getValue();
        System.out.println(context + "范围内添加了名为" + name + ",值为" + value + "的属性被删除了");
    }

    /**
     * 当程序向request范围的属性被替换时触发该方法
     * @param srae
     */
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        ServletContext context = srae.getServletContext();
        String name = srae.getName();
        Object value = srae.getValue();
        System.out.println(context + "范围内添加了名为" + name + ",值为" + value + "的属性被替换了");
    }
}
