package com.lambda.chapterTwo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 * @Author jh
 * @Description
 * @Date created in 11:15 2022/12/1
 */
public class LambdaThreeExpression {

    /**
     * lambda表达式的三种表现
     */

    public void LambdaThreeExpression(){
        /**
         * 1 无传参 无返回
         */
        Runnable noArgument= ()-> System.out.println("hello world");
        /**
         * 2 一个传参 无返回
         */
        ActionListener oneArgument = event -> System.out.println("button clicked");
        /**
         * 3 代码块，和普通方法一样处理代码逻辑
         */
        Runnable multiStatement= ()-> {
            System.out.println("hello ");
            System.out.print("world");
        };
        /**
         * 4 多个传参，有返回
         */
        BinaryOperator<Long> add = (x,y) -> x+y;
        BinaryOperator<Long> addExplicit = (Long x,Long y) -> x+y;  //同理可以在此基础上使用代码块

    }

}
