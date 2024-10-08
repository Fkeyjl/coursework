package com.exam.coursework.user.role;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class RoleDAOSql implements RoleDAO {
    private static final Logger logger = Logger.getLogger(RoleDAOSql.class);
    Connection connection;

    public RoleDAOSql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Role> getAll() throws UnsuccessfulDAOException {
        throw new UnsuccessfulDAOException();
    }

    @Override
    public boolean update(Role entity) throws UnsuccessfulDAOException {
        throw new UnsuccessfulDAOException();
    }

    @Override
    public boolean delete(Integer integer) throws UnsuccessfulDAOException {
        throw new UnsuccessfulDAOException();
    }

    @Override
    public boolean create(Role entity) throws UnsuccessfulDAOException {
        throw new UnsuccessfulDAOException();
    }

    @Override
    public Role getById(Integer integer) throws UnsuccessfulDAOException {
        Role role = null;
        try (PreparedStatement statement = connection.prepareStatement("select * from role where id=?")) {
            statement.setInt(1, integer);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                role = createRole(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulDAOException();
        }
        return role;
    }

    @Override
    public int getByName(String name) throws UnsuccessfulDAOException {
        Role role = null;
        try (PreparedStatement statement = connection.prepareStatement("select * from role where name=?")) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulDAOException();
        }
        return -1;
    }

    private Role createRole(ResultSet rs) throws SQLException {
        if (rs.getString("name").toUpperCase().equals(Role.ADMIN.name())) {
            System.out.println(Role.ADMIN);
            return Role.ADMIN;
        } else
            return Role.USER;

    }
}
