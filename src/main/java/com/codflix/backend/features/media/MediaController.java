package com.codflix.backend.features.media;

import com.codflix.backend.core.Template;
import com.codflix.backend.features.genre.GenreDao;
import com.codflix.backend.models.Genre;
import com.codflix.backend.models.Media;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaController {
    private final MediaDao mediaDao = new MediaDao();

    public String list(Request request, Response response) {
        List<Media> medias;

        String title = request.queryParams("title");
        String films = request.queryParams("films");
        String series = request.queryParams("series");

        System.out.println("\n#########################################################################");

        System.out.println("Search by title ? ------------- : " + (title == null ? "non" : "OUI"));
        System.out.println("All films ? ------------- : " + (films == null ? "non" : "OUI"));
        System.out.println("All series ? ------------- : " + (series == null ? "non" : "OUI"));

        if (title != null && !title.isEmpty()) {
            medias = mediaDao.filterMedias(title);

        } else if (films != null) {
            medias = mediaDao.getAllMediasByType("film");

        } else if (series != null) {
            medias = mediaDao.getAllMediasByType("serie");

        } else {
            medias = mediaDao.getAllMedias();
        }

        GenreDao gd = new GenreDao();

        for (Genre genre : gd.getAllGenres()) {
            System.out.println("-------------------------------------------------------------------------");

            String genreOnly = request.queryParams(genre.getName());
            System.out.println(genre.getName() + " only ? ------------- : " + (genreOnly == null ? "non" : "OUI"));

            String seriesWithGenre = request.queryParams("series-" + genre.getName());
            System.out.println("series " + genre.getName() + " only ? ------------- : " + (seriesWithGenre == null ? "non" : "OUI"));

            String filmsWithGenre = request.queryParams("films-" + genre.getName());
            System.out.println("films " + genre.getName() + " only ? ------------- : " + (filmsWithGenre == null ? "non" : "OUI"));

            if (genreOnly != null)
                medias = mediaDao.getAllMediasByGenre(genre.getId());
            else if (seriesWithGenre != null)
                medias = mediaDao.getAllMediasByTypeAndGenre("serie", genre.getId());
            else if (filmsWithGenre != null)
                medias = mediaDao.getAllMediasByTypeAndGenre("film", genre.getId());
        }

        Map<String, Object> model = new HashMap<>();
        model.put("medias", medias);
        model.put("genre", new GenreDao());
        return Template.render("media_list.html", model);
    }

    public String detail(Request request, Response res) {
        int id = Integer.parseInt(request.params(":id"));
        Media media = mediaDao.getMediaById(id);

        Map<String, Object> model = new HashMap<>();
        model.put("media", media);
        return Template.render("media_detail.html", model);
    }
}
