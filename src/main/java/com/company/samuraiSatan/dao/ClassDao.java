package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.models.Class;
import com.jakewharton.fliptables.FlipTableConverters;
import org.postgresql.core.SqlCommand;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClassDao extends Dao {

    public List<Class> getClasses() {
        List<Class> classes = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM public.\"Classes\";");
            while (results.next()) {
                classes.add(createClass(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    private Class createClass(ResultSet results) throws SQLException {
        int class_ID = results.getInt("Class_ID");
        String class_Name = results.getString("Class_Name");
        return new Class(class_ID, class_Name);
    }

    public void addClass(Class classs) {
        connect();
        PreparedStatement addClass;
        String sql = "INSERT INTO public.\"Classes\" (\"Class_Name\") VALUES (?)";
        try {
            addClass = connection.prepareStatement(sql);
            addClass.setString(1, classs.getClass_Name());
            addClass.executeUpdate();
            addClass.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
