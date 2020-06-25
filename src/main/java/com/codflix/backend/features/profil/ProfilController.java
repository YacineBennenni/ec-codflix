package com.codflix.backend.features.profil;

import com.codflix.backend.core.Template;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProfilController {

    public String profil(Request request, Response response) {

        Map<String, Object> model = new HashMap<>();
        return Template.render("profil.html", model);
    }
}
