package com.backend.parcial.test;

import com.backend.parcial.dao.impl.OdontoloDaoH2;
import com.backend.parcial.dao.impl.OdontologoDaoMemoria;
import com.backend.parcial.model.Odontologo;
import com.backend.parcial.service.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @BeforeAll
    static void doBefore() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/odontologo;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    void deberiaAgregarYRetornarListadoDeOdontologosEnH2() {
        odontologoService = new OdontologoService(new OdontoloDaoH2());

        Odontologo odontologoAPersistir1 = new Odontologo(123456, "Pedro", "zamora");
        Odontologo odontologoAPersistir2 = new Odontologo(789101, "Ana", "Hidalgo");
        Odontologo odontologoAPersistir3 = new Odontologo(111213, "Ramon", "Mercy");

        Odontologo odontologoPersistido1 = odontologoService.registrarOdontologo(odontologoAPersistir1);
        Odontologo odontologoPersistido2 = odontologoService.registrarOdontologo(odontologoAPersistir2);
        Odontologo odontologoPersistido3 = odontologoService.registrarOdontologo(odontologoAPersistir3);

        assertNotNull(odontologoPersistido1.getId());
        assertNotNull(odontologoPersistido2.getId());
        assertNotNull(odontologoPersistido3.getId());

        assertFalse(odontologoService.listarOdontologos().isEmpty());

    }

    @Test
    void deberiaAgregarYRetornarListadoDeOdontologosEnDaoDeMemoria() {
        odontologoService = new OdontologoService(new OdontologoDaoMemoria(new ArrayList<>()));

        Odontologo odontologoAPersistir1 = new Odontologo(123456, "Pedro", "zamora");
        Odontologo odontologoAPersistir2 = new Odontologo(789101, "Ana", "Hidalgo");
        Odontologo odontologoAPersistir3 = new Odontologo(111213, "Ramon", "Mercy");

        Odontologo odontologoPersistido1 = odontologoService.registrarOdontologo(odontologoAPersistir1);
        Odontologo odontologoPersistido2 = odontologoService.registrarOdontologo(odontologoAPersistir2);
        Odontologo odontologoPersistido3 = odontologoService.registrarOdontologo(odontologoAPersistir3);

        assertNotNull(odontologoPersistido1.getId());
        assertNotNull(odontologoPersistido2.getId());
        assertNotNull(odontologoPersistido3.getId());

        assertFalse(odontologoService.listarOdontologos().isEmpty());

    }
}