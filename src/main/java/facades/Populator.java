/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.MoviesDTO;
import dtos.PersonDTO;
import entities.Movies;
import entities.Person;

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = PersonFacade.getFacadeExample(emf);
        MoviesFacade mf = MoviesFacade.getFacadeExample(emf);
//        fe.create(new PersonDTO(new Person("Claes", 24)));
//        fe.create(new PersonDTO(new Person("Oliver", 25)));
//        fe.create(new PersonDTO(new Person("Rasmus", 23)));
//        mf.create(new MoviesDTO(new Movies("Druk", 9, 2019)));
//        mf.create(new Movies("Toy stories", 8, 2001));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
