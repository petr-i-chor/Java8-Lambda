package com.lambda.test.chapterTwo;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.function.Function;

import static java.lang.ThreadLocal.withInitial;


public class chapterTwoTest {

    @Test
    public void test1() {
        /**
         * 例2-15
         */
        //a
        Function<Integer[],Integer> function = integers -> Arrays.hashCode(integers);
        //b 取出int数组的最大值
        //c 下列哪些 Lambda 表达式有效实现了 Function<Long,Long> ？
        Function<Long,Long> function1 = x -> x+1;

    }

    @Test
    public void test2() {
        /**
         * 例2-15
         */
        //a
        final ThreadLocal<DateFormat> formatter = withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy"));

    }
}
