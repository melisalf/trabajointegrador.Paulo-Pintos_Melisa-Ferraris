package com.trabajointegrador.PauloPintos_MelisaFerraris;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class PauloPintosMelisaFerrarisApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(PauloPintosMelisaFerrarisApplication.class);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(PauloPintosMelisaFerrarisApplication.class, args);
        LOGGER.info("Proyecto integrador is now running ...");
    }

}
