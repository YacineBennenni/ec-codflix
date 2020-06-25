package com.codflix.backend.features.user;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.User;
import com.codflix.backend.utils.PasswordChopper;

import java.sql.*;

public class UserDao {
    public boolean emailAlreadyUsed(String email) {
        if (this.getUserByEmail(email) == null)
            return false;

        return true;
    }

    // return the user who use the mail passed in parameters
    public User getUserByEmail(String email) {
        User user = null;

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE email=?");

            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = mapToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUserByCredentials(String email, String password) {
        User user = null;

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");

            st.setString(1, email);
            st.setString(2, PasswordChopper.hashPassword(password));

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = mapToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // This function update a user mail
    public boolean updateEmail(User user, String email) {
        Connection connection = Database.get().getConnection();

        try {
            String requete = "UPDATE user SET email='" + email + "' WHERE id=" + user.getId();
            Statement stmt = connection.createStatement();
            int nbMaj = stmt.executeUpdate(requete);

            if (nbMaj == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // This function update a user password
    public boolean updatePassword(User user, String newPassword) {
        Connection connection = Database.get().getConnection();

        String hashePassword = PasswordChopper.hashPassword(newPassword);

        try {
            String requete = "UPDATE user SET password='" + hashePassword + "' WHERE id=" + user.getId();
            Statement stmt = connection.createStatement();
            int nbMaj = stmt.executeUpdate(requete);

            if (nbMaj == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // This function create a user with mail and the hashed password
    public boolean createUserByCredentials(String email, String password) {
        Connection connection = Database.get().getConnection();

        try {
            String requete = "INSERT INTO user (email, password) VALUES ('" + email + "', '" + PasswordChopper.hashPassword(password) + "')";
            Statement stmt = connection.createStatement();
            int nbMaj = stmt.executeUpdate(requete);

            if (nbMaj == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private User mapToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt(1), // id
                rs.getString(2), // email
                rs.getString(3) // password
        );
    }

    public User getUserById(int userId) {
        User user = null;

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE id=?");

            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = mapToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
