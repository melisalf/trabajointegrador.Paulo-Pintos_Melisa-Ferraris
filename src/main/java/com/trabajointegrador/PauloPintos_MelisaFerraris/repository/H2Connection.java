package com.trabajointegrador.PauloPintos_MelisaFerraris.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class H2Connection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/Paulo-Pintos_Melisa-Ferraris;", "sa", "sa");
    }

}
