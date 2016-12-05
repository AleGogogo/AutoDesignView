package com.example.lyw.treeview.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target 表示这个注解是声明在哪个地方的：类？方法？属性？
 * @Retention 表示这个注解在什么时候可见: 编译时?运行时？
 * Created by LYW on 2016/11/30.
 */
@Target(ElementType.FIELD)//这个表示添加在属性上面的
@Retention(RetentionPolicy.RUNTIME)//表示运行时可见
public @interface TreeNodeId {

}
