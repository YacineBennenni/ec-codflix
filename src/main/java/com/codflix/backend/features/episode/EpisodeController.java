package com.codflix.backend.features.episode;

import com.codflix.backend.core.Template;
import com.codflix.backend.models.Episode;
import com.codflix.backend.models.Media;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EpisodeController {
    private final EpisodeDao episodeDao = new EpisodeDao();

    public String list(Request request, Response response) {
        List<Episode> episodes;

        String title = request.queryParams("titl");

        episodes = episodeDao.getAllEpisodes();

        Map<String, Object> model = new HashMap<>();
        model.put("episodes", episodes);
        return Template.render("media_list.html", model);
    }

    public String detail(Request request, Response res) {
        int id = Integer.parseInt(request.params(":id"));
        Episode episode = episodeDao.getEpisodeById(id);

        Map<String, Object> model = new HashMap<>();
        model.put("episode", episode);
        return Template.render("episode_detail.html", model);
    }
}