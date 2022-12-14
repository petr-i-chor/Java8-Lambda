package com.lambda.chapterThree;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

/**
 * @Author jh
 * @Description
 * @Date created in 15:08 2022/12/1
 */
@Data
@AllArgsConstructor
public class Artist {   //表演者
    private String name;
    private String city;
    private String nationality;
    private Boolean isSolo;
    private List<Artist> artists;

    public Artist(String name) {
        this.name = name;
    }

    public Artist(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public boolean isFrom(String city){
        return this.city.equals(city);
    }

    public boolean isSolo(){
        return this.isSolo;
    }

    public Stream<Artist> getMembers(){
        return artists.stream();
    }

}
