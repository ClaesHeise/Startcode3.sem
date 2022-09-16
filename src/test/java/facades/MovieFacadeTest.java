package facades;

import dtos.MoviesDTO;
import entities.Movies;
import errorhandling.EntityNotFoundException;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

public class MovieFacadeTest {

    private static EntityManagerFactory emf;

    private  static MoviesFacade facade;

    Movies m1,m2;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MoviesFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {

    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movies.deleteAllRows").executeUpdate();
            m1 = new Movies("Toy stories", 8, 2001);
            m2 = new Movies("Star wars III", 7, 2009);
            em.persist(m1);
            em.persist(m2);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown(){

    }

    @Test
    void create() {
        System.out.println("Testing creating a movie");
        Movies m = new Movies("TestMovie", 7, 1999);
        Movies expected = m;
        Movies actual = facade.create(m);
        assertEquals(expected, actual);
    }

    @Test
    void getById() throws EntityNotFoundException {
        System.out.println("Testing getting a movie by id");
//        Movies m1DTO = new MoviesDTO(m1);
        Movies expected = m1;
        Movies actual = facade.getById(m1.getId());
        assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        System.out.println("Testing getAll()");
        //List<MoviesDTO> movies = new ArrayList<>();
        MoviesDTO expected1 = new MoviesDTO(m1);
        MoviesDTO expected2 = new MoviesDTO(m2);
        List<MoviesDTO> actual = facade.getAll();
        assertThat(actual, containsInAnyOrder(expected1, expected2));
//        assertEquals(expected1,actual.get(0));
//        assertEquals(expected2,actual.get(1));
    }
}
