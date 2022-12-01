package com.lambda.chapterThree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author jh
 * @Description
 * @Date created in 15:08 2022/12/1
 */
@Data
@AllArgsConstructor
public class Artist {
    private String name;
    private String city;

    public boolean isFrom(String city){
        return this.city.equals(city);
    }
}
