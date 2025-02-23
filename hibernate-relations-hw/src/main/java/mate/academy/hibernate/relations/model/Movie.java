package mate.academy.hibernate.relations.model;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Cloneable {
    private Long id;
    private String title;
    private List<Actor> actors;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public Movie clone() {
        try {
            Movie movie = (Movie) super.clone();
            if (movie.getActors() != null) {
                List<Actor> actors = new ArrayList<>();
                for (Actor actor : movie.getActors()) {
                    actors.add(actor.clone());
                }
                movie.setActors(actors);
            }
            return movie;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can't make clone of " + this, e);
        }
    }

    @Override
    public String toString() {
        return "Movie{"
                + "id=" + id
                + ", title='" + title + '\''
                + '}';
    }
}
