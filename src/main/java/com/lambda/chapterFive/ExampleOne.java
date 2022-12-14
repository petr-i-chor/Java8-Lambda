package com.lambda.chapterFive;

/**
 * @Author jh
 * @Description
 * @Date created in 13:58 2022/12/7
 */

import com.lambda.chapterThree.Album;
import com.lambda.chapterThree.Artist;
import com.lambda.chapterThree.Track;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;

/**
 * 第 3 章只介绍了集合类的部分变化，事实上，Java 8 对集合类的改进不止这些。现在是时 候介绍一些高级主题了，
 * 包括新引入的 Collector 类。同时我还会为大家介绍方法引用， 它可以帮助大家在 Lambda 表达式中轻松使用已有代码。
 * 编写大量使用集合类的代码时， 使用方法引用能让程序员获得丰厚的回报。本章还会涉及集合类的一些更高级的主题，比
 * 如流中元素的顺序，以及一些有用的API
 */

public class ExampleOne {

    /**
     * 5.1 方法引用
     */
    public void MethodReference(List<Artist> artists){
        /**
         * 读者可能已经发现，Lambda 表达式有一个常见的用法：Lambda 表达式经常调用参数。比 如想得到艺术家的姓名，
         * Lambda 的表达式如下：
         * artist -> artist.getName()
         * 这种用法如此普遍，因此 Java 8 为其提供了一个简写语法，叫作方法引用
         */
        artists.stream().map(artist -> artist.getName());
        artists.stream().map(Artist::getName);

        /**
         * 构造函数也有同样的缩写形式，如果你想使用 Lambda 表达式创建一个 Artist 对象，可能 会写出如下代码：
         * (name, nationality) -> new Artist(name, nationality) 使用方法引用，上述代码可写为
         * Artist::new
         */

        Stream.of("1","2","3").map(v-> new Artist(v));
        Stream.of("1","2","3").map(Artist::new);

        /**
         * 这段代码不仅比原来的代码短，而且更易阅读。Artist::new 立刻告诉程序员这是在创建 一个 Artist 对象，
         * 程序员无需看完整行代码就能弄明白代码的意图。另一个要注意的地方
         * 是方法引用自动支持多个参数，前提是选对了正确的函数接口
         */
    }

    /**
     * 5.2 元素顺序
     */
    public void OrderOfElements(){
        /**
         * 例 5-1 顺序测试永远通过
         */
        List<Integer> numbers = asList(1, 2, 3, 4);
        List<Integer> sameOrder = numbers.stream() .collect(toList());
        assertEquals(numbers, sameOrder);
        /**
         * 如果集合本身就是无序的，由此生成的流也是无序的。HashSet 就是一种无序的集合，
         * 因 此不能保证例 5-2 所示的程序每次都通过。
         */
    }
    public void OrderOfElements2(){
        /**
         * 例 5-2 顺序测试不能保证每次通过
         */
        Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));
        List<Integer> sameOrder = numbers.stream() .collect(toList());
        // 该断言有时会失败
        assertEquals(asList(4, 3, 2, 1), sameOrder);
    }
    public void OrderOfElements3(){
        /**
         * 例 5-3 生成出现顺序
         */
        Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));
        List<Integer> sameOrder = numbers.stream()
                .sorted()
                .collect(toList());
        assertEquals(asList(1, 2, 3, 4), sameOrder);
        /**
         * 一些中间操作会产生顺序，比如对值做映射时，映射后的值是有序的，这种顺序就会保留 下来。如果进来的流是无序的，出去的流也是无序的。
         * 看一下例 5-4 所示代码，我们只能 断言 HashSet 中含有某元素，但对其顺序不能作出任何假设，因为 HashSet 是无序的，使
         * 用了映射操作后，得到的集合仍然是无序的
         */
    }
    public void OrderOfElements4(){
        /**
         * 例 5-4 本例中关于顺序的假设永远是正确的
         */
        List<Integer> numbers = asList(1, 2, 3, 4);
        List<Integer> stillOrdered = numbers.stream().map(x -> x + 1).collect(toList());
        // 顺序得到了保留
        assertEquals(asList(2, 3, 4, 5), stillOrdered);
        Set<Integer> unordered = new HashSet<>(numbers);
        List<Integer> stillUnordered = unordered.stream() .map(x -> x + 1) .collect(toList());
        // 顺序得不到保证
        assertThat(stillUnordered, hasItem(2));
        assertThat(stillUnordered, hasItem(3));
        assertThat(stillUnordered, hasItem(4));
        assertThat(stillUnordered, hasItem(5));
        /**
         *些操作在有序的流上开销更大，调用 unordered 方法消除这种顺序就能解决该问题。大 多数操作都是在有序流上效率更高，比如 filter、map 和 reduce 等。
         *这会带来一些意想不到的结果，比如使用并行流时，forEach 方法不能保证元素是 按顺序处理的（第 6 章会详细讨论这些内容）。如果需要保证按顺序处理，应该使用
         *forEachOrdered 方法，它是你的朋友。
         */
    }

    /**
     * 5.3 使用收集器
     *
     * 前面我们使用过 collect(toList())，在流中生成列表。显然，List 是能想到的从流中生 成的最自然的数据结构，但是有时人们还希望从流生成其他值，
     * 比如 Map 或 Set，或者你希望定制一个类将你想要的东西抽象出来
     */
    public void collection(){
        List<Integer> list = Stream.of(1, 2, 3, 4).collect(toList());
        Set<Integer> set = Stream.of(1, 2, 3, 4).collect(toSet());
        Map<String, String> map = Stream.of(new Artist("name","nationality"))
                .collect(toMap(Artist::getName,Artist::getNationality));
        TreeSet<Integer> treeSet = Stream.of(1, 2, 3, 4).collect(toCollection(TreeSet::new));
    }

    public void transferValue(List<Track> tracks){
        /**
         * 5.3.2 转换成值
         */
        Track max = tracks.stream().collect(maxBy(Comparator.comparing(Track::getLength))).get();
        Track min = tracks.stream().collect(minBy(Comparator.comparing(Track::getLength))).get();
//        Track track1 = tracks.stream().max(Comparator.comparing(Track::getLength)).get();

    }

    public void average(List<Album> a){
        /**
         * 例 5-7 找出一组专辑上曲目的平均数
         */
        Double collect = a.stream().collect(averagingInt(albums -> albums.getTracks().size()));

    }

    public void partitionByTrueOrFalse(List<Artist> artists){
        /**
         * 例 5-8 将艺术家组成的流分成乐队和独唱歌手两部分
         */

        Map<Boolean, List<Artist>> map = artists.stream().collect(partitioningBy(artist -> artist.isSolo()));
        Map<Boolean, List<Artist>> map2 = artists.stream().collect(partitioningBy(Artist::isSolo));
    }


    public void partitionByGroup(List<Artist> artists){
        /**
         * 例 5-10 使用主唱对专辑分组
         */
        Map<String, List<Artist>> map = artists.stream().collect(groupingBy(Artist::getCity));
    }

    /**
     * 5.3.5 字符串
     * 很多时候，收集流中的数据都是为了在最后生成一个字符串。假设我们想将参与制作一张 专辑的所有艺术家的名字输出为一个格式化好的列表，
     * 以专辑 Let It Be 为例，期望的输出为：
     * "[George Harrison, John Lennon, Paul McCartney, Ringo Starr, The Beatles]"
     */
    public void concatStr(List<Artist> artists){
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Artist artist : artists) {
            stringBuilder.append(artist.getName()+", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
    }

    public void concatStr2(List<Artist> artists){
        String collect = artists.stream().map(Artist::getName).collect(joining(",", "[", "]"));
    }

    /**
     * 5.3.6 组合收集器
     */
    public void combinedCollector(List<Album> albums){
        /**
         * 之前我们使用主唱将专辑分组，现在来考虑如何计算一个艺术家的专辑数量。一个简单的 方案是使用前面的方法对专辑先分组后计数，
         * 如例 5-13 所示。
         * 例 5-13 计算每个艺术家专辑数的简单方式
         */
        Map<Artist, List<Album>> map = albums.stream().collect(groupingBy(Album::getMainMusicians));
        HashMap<Artist, Integer> res = new HashMap<>();
        Set<Map.Entry<Artist, List<Album>>> set = map.entrySet();
        for (Map.Entry<Artist, List<Album>> entry : set) {
            res.put(entry.getKey(),entry.getValue().size());
        }

        /**
         * 例 5-14 使用收集器计算每个艺术家的专辑数
         */
        Map<Artist, Long> res2 = albums.stream().collect(groupingBy(Album::getMainMusicians, counting()));
    }

    public void combinedCollector2(List<Album> albums){
        /**
         * 例 5-15 使用简单方式求每个艺术家的专辑名
         */
        Map<Artist, List<Album>> map = albums.stream().collect(groupingBy(Album::getMainMusicians));
        HashMap<Artist, List<String>> res = new HashMap<>();
        Set<Map.Entry<Artist, List<Album>>> set = map.entrySet();
        for (Map.Entry<Artist, List<Album>> entry : set) {
            res.put(entry.getKey(),entry.getValue().stream().map(Album::getName).collect(toList()));
        }

        /**
         * 例 5-16 使用收集器求每个艺术家的专辑名
         */
        Map<Artist, List<String>> res2 = albums.stream()
                .collect(groupingBy(Album::getMainMusicians, mapping(Album::getName, toList())));

    }

    /**
     * 5.3.7 重构和定制收集器
     */


}
