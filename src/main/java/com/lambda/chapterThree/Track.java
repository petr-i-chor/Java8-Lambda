package com.lambda.chapterThree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author jh
 * @Description
 * @Date created in 15:16 2022/12/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track { //歌曲

    private String name;

    private int length;

}
