package com.codflix.backend.features.episode;

import com.codflix.backend.core.Template;
import com.codflix.backend.models.Episode;
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
}