package com.lambda.chapterTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 * @Author jh
 * @Description
 * @Date created in 11:50 2022/12/1
 */
public class FunctionInterface {

    /**
     * 常见的函数式编程接口
     */

    public void FunctionInterface(){
        Predicate<Integer> predicate = integer -> false;
        Consumer<Integer> consumer = integer -> System.out.println("hello world");
        Function<String, List<String>> function = s -> new ArrayList<>();
        Supplier supplier = ()-> "hello world";
        BinaryOperator<Long> binaryOperator = (aLong, aLong2) -> aLong * aLong2;
    }

}
