package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDAO extends DAO {

    public UserDAO() {
        super();
    }

    public boolean checkLogin(User user) throws SQLException {
        boolean result = false;
        String sql = "SELECT * FROM tbluser WHERE username = ? AND password = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getBoolean("isActive")) {
                user.setID(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPosition(rs.getString("position"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setIsActive(true);
                result = true;
            }

        }

        return result;
    }
}
