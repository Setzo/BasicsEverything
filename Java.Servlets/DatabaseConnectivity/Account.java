package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

    private Connection con;

    public Account(Connection con) {
        this.con = con;
    }

    public boolean login(String email, String password) throws SQLException {

        PreparedStatement prepSt =
                con.prepareStatement("select count(*) as count from users where email=? and password=?");

        prepSt.setString(1, email);        //SQL - index od 1
        prepSt.setString(2, password);

        ResultSet resSet = prepSt.executeQuery();
        resSet.next();

        int cnt = 0;

        if (resSet.next()) {
            cnt = resSet.getInt("count");
        }

        resSet.close();

        if (cnt == 0) {
            return false;
        }

        return true;
    }
}
