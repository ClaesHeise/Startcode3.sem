package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
    @NamedQuery(name = "Movies.deleteAllRows", query = "DELETE from Movies")
    public class Movies implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        public Movies() {
        }

        // TODO, delete this class, or rename to an Entity class that makes sense for what you are about to do
        // Delete EVERYTHING below if you decide to use this class, it's dummy data used for the initial demo
        private String name;

        private int rating;

        private int year;


        public Movies(String name, int rating, int year) {
            this.name = name;
            this.rating = rating;
            this.year = year;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
            return "Movies{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", rating=" + rating +
                    ", year=" + year +
                    '}';
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return rating == movies.rating && year == movies.year && id.equals(movies.id) && name.equals(movies.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, year);
    }
}
