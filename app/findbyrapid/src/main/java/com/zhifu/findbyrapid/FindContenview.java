package com.zhifu.findbyrapid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: FindContenview
 * @Description:
 * @Author: Mr.S
 * @CreateDate: 2020/5/19 15:20
 * @UpdateRemark:
 * @Version: 1.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FindContenview {
    int value();
}
