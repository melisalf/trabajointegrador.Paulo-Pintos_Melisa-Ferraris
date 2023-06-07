package com.trabajointegrador.PauloPintos_MelisaFerraris.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class H2Connection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/Paulo-Pintos_Melisa-Ferraris;", "sa", "sa");
    }

    public static Connection crearBd() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/Paulo-Pintos_Melisa-Ferraris;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
    }
}
