package com.lambda.chapterTwo;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Integer.compare;

/**
 * @Author jh
 * @Description
 * @Date created in 10:44 2022/12/1
 */
public class FirstLambdaExpression {

    /**
     * 比较器的接口实现
     */

    public void sort(){
        Integer[] ints = {1, 2, 3};

        Arrays.sort(ints,new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return compare(o1, o2);
            }
        });
    }
    /**
     * 接口 使用lambda
     */
    public void sortLambda(){
        Integer[] ints = {1, 2, 3};
        Arrays.sort(ints,(Comparator.comparingInt(o -> o)));
    }
}
