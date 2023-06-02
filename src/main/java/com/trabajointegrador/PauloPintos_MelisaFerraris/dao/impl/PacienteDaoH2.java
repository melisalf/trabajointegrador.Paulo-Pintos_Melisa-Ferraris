package com.trabajointegrador.PauloPintos_MelisaFerraris.dao.impl;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dao.H2Connection;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dao.IDao;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Domicilio;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements IDao<Paciente> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteDaoH2.class);


    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);


            // instanciamos el domicilio
            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            domicilioDaoH2.guardar(paciente.getDomicilio());

            //instanciamos el turno
            TurnoDaoH2 turnoDaoH2 = new TurnoDaoH2();
            turnoDaoH2.guardar(paciente.getTurno());

            PreparedStatement ps = connection.prepareStatement("INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA, DOMICILIO_ID, TURNO) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDni());
            ps.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            ps.setInt(5, paciente.getDomicilio().getId());
            // setiar el turno en la base de datos.
            // ps.setDate(6,Turno.;
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                paciente.setId(rs.getInt(1));
            }

            connection.commit();
            LOGGER.info("Se ha registrado el paciente: " + paciente);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + e.getMessage());
                e.printStackTrace();
            }
        }

        return paciente;
    }

    @Override
    public List<Paciente> listarTodos() {
        Connection connection = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paciente paciente = crearObjetoPaciente(rs);
                pacientes.add(paciente);
            }

            LOGGER.info("Listado de todos los pacientes: " + pacientes);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pacientes;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("DELETE FROM PACIENTES WHERE ID = ?");
            ps.setInt(1, id);
            ps.execute();
            connection.commit();
            LOGGER.info("Se ha eliminado el paciente con id: " + id);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

    }

    @Override
    public Paciente buscarPorId(int id) {
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                paciente = crearObjetoPaciente(rs);
            }
            LOGGER.info("Se ha encontrado el paciente con id " + id + ": " + paciente);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public Paciente buscarPorCriterio(String criterio) {
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES WHERE DNI = ?");
            ps.setString(1, criterio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                paciente = crearObjetoPaciente(rs);
            }
            LOGGER.info("Se ha encontrado el paciente con dni " + criterio + ": " + paciente);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public Paciente modificar(int id) {
        return null;
    }


    private Paciente crearObjetoPaciente(ResultSet resultSet) throws SQLException {
        int idPaciente = resultSet.getInt("id");
        String nombrePaciente = resultSet.getString("nombre");
        String apellidoPaciente = resultSet.getString("apellido");
        String dniPaciente = resultSet.getString("dni");
        LocalDate fechaIngreso = resultSet.getDate("fecha").toLocalDate();
        Domicilio domicilioPaciente = new DomicilioDaoH2().buscarPorId(resultSet.getInt("domicilio_id"));
        return new Paciente(idPaciente, nombrePaciente, apellidoPaciente, dniPaciente, fechaIngreso, domicilioPaciente);
    }

}
