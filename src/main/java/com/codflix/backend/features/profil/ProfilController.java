package com.codflix.backend.features.profil;

import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Template;
import com.codflix.backend.features.user.UserDao;
import com.codflix.backend.models.User;
import com.codflix.backend.utils.PasswordChopper;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class ProfilController {

    public String email(Request request, Response response) {
        Session session = request.session();
        String email = request.queryParams("email");
        UserDao ud = new UserDao();
        User user = null;

        int userId = 0;

        if (session.attribute("user_id") instanceof Integer) {
            userId = session.attribute("user_id");

        } else if (session.attribute("user_id") instanceof String) {
            userId = Integer.parseInt(session.attribute("user_id"));
        }

        if (email != null && !email.isEmpty()) {
            user = ud.getUserById(userId);
            ud.updateEmail(user, email);
            user = ud.getUserById(userId);
        } else
            return "Erreur champs de l'email vide.";

        // Redirect to medias page
        response.redirect(Conf.SUCCESSFUL_MODIFICATION);
        return "OK";
    }

    public String password(Request request, Response response) {
        Session session = request.session();
        String password = request.queryParams("password");
        String newPassword = request.queryParams("newPassword");
        String passwordConfirm = request.queryParams("passwordConfirm");

        UserDao ud = new UserDao();
        User user = null;

        int userId = 0;

        if (session.attribute("user_id") instanceof Integer) {
            userId = session.attribute("user_id");

        } else if (session.attribute("user_id") instanceof String) {
            userId = Integer.parseInt(session.attribute("user_id"));
        }

        if (password != null && !password.isEmpty()
                && newPassword != null && !newPassword.isEmpty()
                && passwordConfirm != null && !passwordConfirm.isEmpty()) {

            String hashedPassword = PasswordChopper.hashPassword(password);
            user = ud.getUserById(userId);

            if (user.getPassword().equals(hashedPassword) && newPassword.equals(passwordConfirm)) {
                ud.updatePassword(user, newPassword);
                user = ud.getUserById(userId);

            } else
                return "Erreur dans les mot de passe.";
        } else
            return "Erreur champs manquant.";

        // Redirect to medias page
        response.redirect(Conf.SUCCESSFUL_MODIFICATION);
        return "OK";
    }

    public String detail(Request request, Response res) {
        Session session = request.session();
        UserDao userDao = new UserDao();

        int userId = 0;

        if (session.attribute("user_id") instanceof Integer) {
            userId = session.attribute("user_id");

        } else if (session.attribute("user_id") instanceof String) {
            userId = Integer.parseInt(session.attribute("user_id"));
        }

        User user = userDao.getUserById(userId);

        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        return Template.render("profil.html", model);
    }
}
