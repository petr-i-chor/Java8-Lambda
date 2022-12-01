package com.lambda.chapterThree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author jh
 * @Description
 * @Date created in 14:55 2022/12/1
 */
public class ExampleOne {

    /**
     * 使用for循环计算来自伦敦的艺术家人数
     * 外部循环到内部循环
     */
    public void loop(ArrayList<Artist> artists){
        /**
         * 3-1
         * 外部循环
         */
        int count = 0;
        for (Artist artist : artists) {
            if (artist.isFrom("London")){
                count++;
            }
        }
    }
    public void loop2(ArrayList<Artist> artists){
        /**
         * 3-2
         * 使用迭代器迭代
         */
        int count = 0;
        Iterator<Artist> iterator = artists.iterator();
        while (iterator.hasNext()){
            Artist artist = iterator.next();
            if (artist.isFrom("London")){
                count++;
            }
        }
    }

    public void loop3(ArrayList<Artist> artists){
        /**
         * 3-3
         * 使用内部迭代
         */
        long count = artists.stream().filter(v -> v.isFrom("London")).count();
    }

    public static void main(String[] args) {
        ArrayList<Artist> artists = new ArrayList<>();
        artists.add(new Artist("小江","London"));
        artists.add(new Artist("小涣","London"));
        artists.add(new Artist("小明","London"));
//        loop5(artists);
        loop6(artists);
    }

    public static void loop5(ArrayList<Artist> artists){
        /**
         * 例 3-5 由于使用了惰性求值，没有输出艺术家的名字
         */
        artists.stream().filter(v -> {
            System.out.println(v.getName());
            return v.isFrom("London");
        });
    }
    public static void loop6(ArrayList<Artist> artists){
        /**
         * 例 3-6 输出艺术家的名字,如果将同样的输出语句加入一个拥有终止操作的流，如例 3-3 中的计数操作，艺术家的名字就会被输出
         */
        artists.stream().filter(v -> {
            System.out.println(v.getName());
            return v.isFrom("London");
        }).count();
    }

}
