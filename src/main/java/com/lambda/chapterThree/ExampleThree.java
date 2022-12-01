package com.lambda.chapterThree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author jh
 * @Description
 * @Date created in 16:12 2022/12/1
 */
public class ExampleThree {

    public void stream(){
        /**
         * 3.3.1
         * 常见的流操作 collect(toList())
         */
        List<String> collect = Stream.of("1", "2", "3", "4").collect(Collectors.toList());
    }

    /**
     * map 如果有一个函数可以将一种类型的值转换成另外一种类型，map 操作就可以
     * 使用该函数，将一个流中的值转换成一个新的流
     * @param artists
     */

    public void map(ArrayList<Artist> artists){
        /**
         * 例3-8
         * 使用 for 循环将字符串转换为大写
         */
        List<String> collect = Stream.of("1", "2", "3", "4").collect(Collectors.toList());
    }



}
