package com.zhifu.findbyrapid;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * @ClassName: FindViewByid
 * @Description:
 * @Author: Mr.S
 * @CreateDate: 2020/5/19 15:16
 * @UpdateRemark:
 * @Version: 1.0
 */
public class FindViewByid {
    private static Class<?> activityClass;
    public static void inject(Object obj){
        activityClass=obj.getClass();
        injectContent(obj);
        injectView(obj);
        injectOnclick(obj);
        }
    public static void injectContent(Object obj){
        //获取注解
        FindContenview findContenview=activityClass.getAnnotation(FindContenview.class);
        if(findContenview!=null){
            //如果不为空的话就获取id，
            int id=findContenview.value();
            try {
                Method method=activityClass.getMethod("setContentView",int.class);
                method.invoke(obj,id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static void injectView(Object obj){
        //
        Field[] allview=null;
         if(obj!=null){
             allview=activityClass.getDeclaredFields();
         }
         if(allview!=null){
             for (Field field:allview){
                 FindViewByidd findViewByidd=field.getAnnotation(FindViewByidd.class);
                 if(findViewByidd!=null){
                   int value=findViewByidd.value();
                   Object object=null;
                     try {
                         object=activityClass.getMethod("findViewById",int.class).invoke(obj,value);
                         if (Modifier.PUBLIC != field.getModifiers()) {

                             // 打破封装性
                             field.setAccessible(true);
                         }
                         // 这里相当于 field= acitivity.obj
                         field.set(obj, object);
                     } catch (Exception e) {
                         e.printStackTrace();
                     }

                 }}
             }
         }
         public static void injectOnclick(Object object){
             //获得所有方法
             Method[] methods  = null;
             methods = activityClass.getMethods();
             // 遍历所有的activity下的方法
             for (Method method : methods) {
                 // 获取方法的注解
                 FindViewOnclick fmyClickView = method.getAnnotation(FindViewOnclick.class);
                 // 如果存在此注解
                 if (fmyClickView != null) {
                     // 所有注解的控件的id
                     int[] ids = fmyClickView.value();
                     // 代理处理类
                     MInvocationHandler handler = new MInvocationHandler(object,method);
                     // 代理实例 这里也可以返回     new Class<?>[] { View.OnClickListener.class }中的接口类
                     //第一个参数用于加载其他类 不一定要使用View.OnClickListener.class.getClassLoader() 你可以使用其他的
                     //第二个参数你所实现的接口
                     Object newProxyInstance = Proxy.newProxyInstance(
                             View.OnClickListener.class.getClassLoader(),
                             new Class<?>[] { View.OnClickListener.class }, handler);
                     // 遍历所有的控件id 然后设置代理
                     for (int i : ids) {
                         try {
                             Object view = null;
                             //如果对象是activity
                             view = activityClass.getMethod("findViewById",
                                     int.class).invoke(object, i);
                             if (view != null) {
                                 Method method2 = view.getClass().getMethod(
                                         "setOnClickListener",
                                         View.OnClickListener.class);
                                 method2.invoke(view, newProxyInstance);
                             }
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }

                 }
             }
         }
    static class MInvocationHandler implements InvocationHandler {
        //这里我们到时候回传入activity
        private Object target;
        // 用户自定义view 的点击事件方法
        private Method method;
        public MInvocationHandler(Object target, Method method) {
            super();
            this.target = target;
            this.method = method;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            // 调用用户自定义方法的点击事件 让activity调用中开发者设定的方法
            return this.method.invoke(target, args);
        }
    }
}
