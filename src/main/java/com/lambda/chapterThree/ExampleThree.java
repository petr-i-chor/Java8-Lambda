package com.lambda.chapterThree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author jh
 * @Description
 * @Date created in 16:12 2022/12/1
 */
public class ExampleThree {

    public void stream() {
        /**
         * 3.3.1
         * 常见的流操作 collect(toList())
         */
        List<String> collect = Stream.of("1", "2", "3", "4").collect(toList());
    }

    /**
     * map 如果有一个函数可以将一种类型的值转换成另外一种类型，map 操作就可以
     * 使用该函数，将一个流中的值转换成一个新的流
     */

    public void map() {
        /**
         * 例3-8
         * 使用 for 循环将字符串转换为大写
         */
        ArrayList<String> list = new ArrayList<>();
        for (String s : Arrays.asList("a", "b", "abc")) {
            String toUpperCase = s.toUpperCase();
            list.add(toUpperCase);
        }

    }

    public void map2() {
        /**
         * 例3-9
         * 使用 map 操作将字符串转换为大写形式
         */
        ArrayList<String> list = new ArrayList<>();
        List<String> collect = Arrays.asList("a", "b", "abc").stream().map(v -> v.toUpperCase()).collect(toList());
    }

    /**
     * 3.3.3遍历数据并检查其中的元素时，可尝试使用 Stream 中提供的新方法 filter
     * <p>
     * 假设要找出一组字符串 中以数字开头的字符串，比如字符串 "1abc" 和 "abc"，其中 "1abc" 就是符合条件的字符串
     */
    public void filter() {
        /**
         * 使用循环遍历列表，使用条件语句做判断
         */
        ArrayList<String> beginningWithNumber = new ArrayList<>();
        for (String abc : Arrays.asList("1abc", "abc")) {
            if (Pattern.matches("\\d", String.valueOf(abc.charAt(0)))) {
                beginningWithNumber.add(abc);
            }
        }
    }

    public void filter2() {
        /**
         * 函数式风格
         */
        List<String> abc = Arrays.asList("1abc", "abc").stream()
                .filter(v -> Pattern.matches("\\d", String.valueOf(v.charAt(0))))
                .collect(toList());
    }

    /**
     * 3.3.4 flatMap 方法可用 Stream 替换值，然后将多个 Stream 连接成一个 Stream
     */
    public void flatMap() {
        List<String> list = Arrays.asList("1,2,3,4", "5,6,7");
        Stream<List<String>> listStream = Stream.of(Arrays.asList("1", "2"), Arrays.asList("1", "2"));
        /**
         * 下面的例子很好的体现
         * 将一个字符串流 "1,2,3,4" 变成四个流 "1" "2" "3" "4"
         */
        Stream<String> stream1 = list.stream();
        Stream<List<String>> stream2 = list.stream().map(v -> Arrays.asList(v.split(",")));
        Stream<String> stream3 = list.stream().map(v -> Arrays.asList(v.split(","))).flatMap(v -> v.stream());
    }

    /**
     * 3.3.5 max和min  stream 上常用的操作之一是求最大值和最小值
     */

    public void min() {
        /**
         * 例 3-13 使用 Stream 查找最短曲目
         *
         * 例 3-13 是查找专辑中最短曲目所用的代码，展示了如何使用 max 和 min 操作。 为了方便检查程序结果是否正确，代码片段中罗列了专辑中的曲目信息，我承认，这张专
         * 辑是有点冷门。
         */
        List<Track> list = Arrays.asList(new Track("love song", 240),
                new Track("golden hour", 223),
                new Track("every summer time", 246));
        Track min = list.stream().min(Comparator.comparing(track -> track.getLength())).get();

    }

    /**
     * 3.3.7 通用模式
     * reduce 操作可以实现从一组值中生成一个值。在上述例子中用到的 count、min 和 max 方 法，因为常用而被纳入标准库中。事实上，这些方法都是 reduce 操作
     */
    public void reduce(){
        /**
         * 例 3-16 中的代码展示了这一过程。Lambda 表达式就是 reducer，它执行求和操作，有两个 参数：传入 Stream 中的当前元素和 acc。将两个参数相加，acc 是累加器，保存着当前的
         * 累加结果。
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        //acc 是累加值 accumulator 加值
        Integer integer = list.stream().reduce(0,(acc, accumulator) -> acc + accumulator);
    }
    /**
     * 3.3.8 整合操作
     * 找出某张专辑上所有乐队的国籍
     */
    public void integration(Album album){
        /**
         * 1. 找出专辑上的所有表演者。 2. 分辨出哪些表演者是乐队。(利用一点领域知识，假定一般乐队名以定冠词 The 开头) 3. 找出每个乐队的国籍。
         * 4. 将找出的国籍放入一个集合。
         */
        List<String> list = album.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(Artist::getNationality)
                .collect(toList());
    }

}
