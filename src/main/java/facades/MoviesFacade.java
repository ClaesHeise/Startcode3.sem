package facades;

import dtos.MoviesDTO;
import entities.Movies;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MoviesFacade {

    private static MoviesFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MoviesFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MoviesFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MoviesFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Movies create(Movies movies){
        Movies rme = movies;
//        Movies rme = new Movies(MoviesDTO.getName(), MoviesDTO.getRating(), MoviesDTO.getYear());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rme);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return rme;
    }
    public Movies getById(long id) throws EntityNotFoundException {
        EntityManager em = emf.createEntityManager();
        Movies rm = em.find(Movies.class, id);
        if (rm == null)
            throw new EntityNotFoundException("The Movies entity with ID: "+id+" Was not found");
        return rm;
    }

//    //TODO Remove/Change this before use
//    public long getRenameMeCount(){
//        EntityManager em = getEntityManager();
//        try{
//            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM Movies r").getSingleResult();
//            return renameMeCount;
//        }finally{
//            em.close();
//        }
//    }

    public List<MoviesDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Movies> query = em.createQuery("SELECT r FROM Movies r", Movies.class);
        List<Movies> rms = query.getResultList();
        return MoviesDTO.getDtos(rms);
    }

    public List<Movies> getAllNonDtos(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Movies> query = em.createQuery("SELECT r FROM Movies r", Movies.class);
        List<Movies> rms = query.getResultList();
        return rms;
    }


    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        MoviesFacade fe = getFacadeExample(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }

}
