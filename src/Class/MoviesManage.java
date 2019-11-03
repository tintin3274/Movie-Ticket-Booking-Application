package Class;

import java.util.HashSet;

public class MoviesManage {
    private HashSet<Movie> movieHashSet = new HashSet<>();

    private MoviesManage(){
    }

    private static MoviesManage moviesManageInstance;
    public static synchronized MoviesManage getInstance(){
        if(moviesManageInstance == null){
            moviesManageInstance = new MoviesManage();
        }
        return moviesManageInstance;
    }

    public HashSet<Movie> getMovieHashSet() {
        return movieHashSet;
    }

    public void addMovie(Movie movie){
        movieHashSet.add(movie);
    }

    public void removeMovie(Movie movie){
        movieHashSet.remove(movie);
    }
}