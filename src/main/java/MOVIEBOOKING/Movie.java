package MOVIEBOOKING;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private final String movieId;
    private final String title;
    private final int durationInMinutes;
    private final String language;

    private final List<Show> shows;

    public Movie(String movieId, String title, int durationInMinutes, String language) {
        this.movieId = movieId;
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.language = language;
        this.shows = new ArrayList<>();
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public List<Show> getShows() {
        return shows;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isAvailable() {
        for (Show show : shows) {
            if (show.hasAvailableSeats()) return true;
        }
        return false;
    }
}
