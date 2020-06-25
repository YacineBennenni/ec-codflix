package com.codflix.backend.models;

import com.codflix.backend.features.genre.GenreDao;

import java.util.Date;

import com.codflix.backend.features.genre.GenreDao;
import com.codflix.backend.features.media.MediaDao;

public class Episode {
    private int id;
    private int mediaId;
    private String title;
    private int season;
    private int episodeNumber;
    private int timeMinute;
    private String summary;
    private String trailerUrl;
    private MediaDao mediaDao = new MediaDao();


    public Episode(int id, int mediaId, String title, int season, int episodeNumber, int timeMinute, String summary, String trailerUrl) {
        this.id = id;
        this.mediaId = mediaId;
        this.title = title;
        this.season = season;
        this.episodeNumber = episodeNumber;
        this.timeMinute = timeMinute;
        this.summary = summary;
        this.trailerUrl = trailerUrl;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", mediaId=" + mediaId +
                ", title='" + title + '\'' +
                ", season='" + season + '\'' +
                ", episodeNumber='" + episodeNumber + '\'' +
                ", timeMinute=" + timeMinute +
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

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getTimeMinute() {
        return timeMinute;
    }

    public void setTimeMinute(int releaseDate) {
        this.timeMinute = timeMinute;
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

    // return title of media form episode
    public String getMediaTitle() {
        Media media = this.mediaDao.getMediaById(this.mediaId);

        if (media != null)
            return media.getTitle();

        return "";
    }
}
