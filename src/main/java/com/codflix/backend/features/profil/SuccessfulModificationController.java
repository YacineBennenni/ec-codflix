package com.codflix.backend.features.profil;

import com.codflix.backend.core.Template;
import com.codflix.backend.features.user.UserDao;
import com.codflix.backend.models.User;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class SuccessfulModificationController {
    UserDao ud = new UserDao();

    public String init(Request request, Response response) {
        Session session = request.session();
        User user = null;
        int userId = 0;

        if (session.attribute("user_id") instanceof Integer) {
            userId = session.attribute("user_id");
        } else if (session.attribute("user_id") instanceof String) {
            userId = Integer.parseInt(session.attribute("user_id"));
        }

        user = this.ud.getUserById(userId);

        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        return Template.render("successfulModification.html", model);
    }

}
