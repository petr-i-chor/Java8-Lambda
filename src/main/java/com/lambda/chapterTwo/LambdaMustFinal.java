package com.lambda.chapterTwo;

import java.util.Arrays;

/**
 * @Author jh
 * @Description
 * @Date created in 11:39 2022/12/1
 */
public class LambdaMustFinal {

    /**
     * lambda表示式引进既成事实上的final变量
     *
     * 变量未发生变化切被lambda引进，则默认是以final变量引进
     */

    public void LambdaMustFinal(){
        String finalStr = "hello world";
//        Runnable finalArgument = ()-> System.out.println("final"+ finalStr);
        finalStr = "orther";
        String finalStr1 = finalStr;
        Runnable finalArgument2 = ()-> System.out.println("final"+ finalStr1);
    }

    public void LambdaMustFinal2(){
        String finalStr = "hello world";
        Runnable finalArgument = ()-> System.out.println("final"+ finalStr);
    }
}
