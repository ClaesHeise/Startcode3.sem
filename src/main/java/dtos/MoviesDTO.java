/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Movies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tha
 */
public class MoviesDTO {
    private long id;
    private String name;
    private int rating;
    private int year;

    public MoviesDTO(String name, int rating, int year) {
        this.rating = rating;
        this.name = name;
        this.year = year;
    }

    public static List<MoviesDTO> getDtos(List<Movies> rms){
        List<MoviesDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new MoviesDTO(rm)));
        return rmdtos;
    }


    public MoviesDTO(Movies rm) {
        if(rm.getId() != null)
            this.id = rm.getId();
        this.name = rm.getName();
        this.rating = rm.getRating();
        this.year = rm.getYear();
    }

    public String getName() {
        return name;
    }

    public void setName(String dummyname) {
        this.name = dummyname;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "MoviesDTO{" + "id=" + id + ", name: " + name + ", rating: " + rating + ", year: " + year + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviesDTO moviesDTO = (MoviesDTO) o;
        return id == moviesDTO.id && rating == moviesDTO.rating && year == moviesDTO.year && name.equals(moviesDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, year);
    }
}
