package com.lambda.test.chapterThree;

import com.lambda.chapterThree.Album;
import com.lambda.chapterThree.Artist;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class chapterThreeTest {

    @Test
    public void test1() {
        /**
         * 1. 常用流操作。实现如下函数：
         * a. 编写一个求和函数，计算流中所有数之和。例如，int addUp(Stream<Integer> numbers)；
         * b. 编写一个函数，接受艺术家列表作为参数，返回一个字符串列表，其中包含艺术家的 姓名和国籍；
         * c. 编写一个函数，接受专辑列表作为参数，返回一个由最多包含 3 首歌曲的专辑组成的
         * 列表。
         */
        //a
        System.out.println(addUp(Arrays.asList(1, 2, 3).stream()));
        //b
        System.out.println(getNameAndNation(new ArrayList<>()));
        //c
        System.out.println(getAlbum(new ArrayList<>()));

    }

    int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, accumulator) -> acc + accumulator);
    }

    List<String> getNameAndNation(List<Artist> artists) {
        return artists.stream().flatMap(artist -> Stream.of(artist.getNationality(), artist.getNationality()))
                .collect(Collectors.toList());
    }

    List<Album> getAlbum(List<Album> albums) {
        return albums.stream().filter(v -> v.getTracks().size() <= 3).collect(Collectors.toList());
    }

    @Test
    public void test2(List<Artist> artists) {
        /**
         * 2. 迭代。修改如下代码，将外部迭代转换成内部迭代：
         *
         */
        int totalMembers = 0;
        for (Artist artist : artists) {
            Stream<Artist> members = artist.getMembers();
            totalMembers += members.count();
        }
        /**
         * 转换后
         */
        Long reduce = artists.stream().map(artist -> artist.getMembers().count()).reduce(0L, (acc, x) -> acc + x);
    }
    /**
     * 3. 求值。根据 Stream 方法的签名，判断其是惰性求值还是及早求值。
     * a. boolean anyMatch(Predicate<? super T> predicate);   -> 惰性
     * b. Stream<T> limit(long maxSize);    -> 及早
     */


}
