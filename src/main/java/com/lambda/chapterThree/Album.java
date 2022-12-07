package com.lambda.chapterThree;

import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

/**
 * @Author jh
 * @Description
 * @Date created in 16:22 2022/12/6
 */
@Data
public class Album {    //专辑

    List<Artist> musicians;

    List<Track> tracks;

    public Stream<Artist> getMusicians() {
        return musicians.stream();
    }
}
