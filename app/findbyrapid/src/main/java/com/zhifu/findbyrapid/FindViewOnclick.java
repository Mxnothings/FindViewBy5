package com.zhifu.findbyrapid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: FindViewOnclick
 * @Description:
 * @Author: Mr.S
 * @CreateDate: 2020/5/19 16:20
 * @UpdateRemark:
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FindViewOnclick {
int[] value();
}
