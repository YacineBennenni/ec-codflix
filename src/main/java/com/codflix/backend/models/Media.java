package com.codflix.backend.models;

import com.codflix.backend.features.episode.EpisodeDao;
import com.codflix.backend.features.genre.GenreDao;

import java.util.*;

public class Media {
    private int id;
    private int genreId;
    private String title;
    private String type;
    private String status;
    private Date releaseDate;
    private String summary;
    private String trailerUrl;
    private GenreDao genredao = new GenreDao();
    private EpisodeDao episodeDao = new EpisodeDao();

    public Media(int id, int genreId, String title, String type, String status, Date releaseDate, String summary, String trailerUrl) {
        this.id = id;
        this.genreId = genreId;
        this.title = title;
        this.type = type;
        this.status = status;
        this.releaseDate = releaseDate;
        this.summary = summary;
        this.trailerUrl = trailerUrl;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", genreId=" + genreId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", releaseDate=" + releaseDate +
                ", summary='" + summary + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    // Year of the date
    public int getReleaseYear() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(releaseDate);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    //Return Genre name with genre id
    public String getGenre() {
        Genre genre = this.genredao.getGenreById(this.genreId);

        if (genre != null)
            return genre.getName();

        return null;

    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public LinkedHashMap<Integer, ArrayList<Episode>> getEpisodes() {
        LinkedHashMap<Integer, ArrayList<Episode>> lhm = new LinkedHashMap<Integer, ArrayList<Episode>>();

        ArrayList<Episode> episodes = this.episodeDao.getEpisodeByMediaId(this.id);

        for (Episode episode : episodes) {
            if(lhm.get(episode.getSeason()) == null) {
                ArrayList<Episode> newListeEpisodes = new ArrayList<Episode>();
                newListeEpisodes.add(episode);
                lhm.put(episode.getSeason(), newListeEpisodes);

            } else {
                ArrayList<Episode> listeEpisodes = lhm.get(episode.getSeason());
                listeEpisodes.add(episode);
            }
        }
        return lhm;
    }
}

