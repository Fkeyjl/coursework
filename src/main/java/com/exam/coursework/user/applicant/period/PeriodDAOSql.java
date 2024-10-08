package com.exam.coursework.user.applicant.period;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.user.applicant.period.state.State;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodDAOSql implements PeriodDAO {
    private static final Logger logger = Logger.getLogger(PeriodDAOSql.class);
    private Connection connection;

    public PeriodDAOSql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Period> getAll() throws UnsuccessfulDAOException {
        List<Period> periods = new ArrayList<>();
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "select * from period ")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                periods.add(createPeriod(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulDAOException();
        }
        return periods;
    }

    private Period createPeriod(ResultSet resultSet) throws SQLException {
        Period period = new Period();
        period.setId(resultSet.getInt("id"));
        period.setName(resultSet.getString("name"));
        period.setState(resultSet.getInt("state"));
        return period;
    }

    @Override
    public boolean update(Period entity) throws UnsuccessfulDAOException {
        int changeNumber = 0;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "update period set name=?, state=? where id = ?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getState());
            preparedStatement.setInt(3, entity.getId());
            changeNumber = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return changeNumber > 0;
    }

    @Override
    public boolean delete(Integer integer) throws UnsuccessfulDAOException {
        return false;
    }

    @Override
    public boolean create(Period period) throws UnsuccessfulDAOException {
        int changeNumber = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement
                             ("INSERT INTO period(name,state) VALUE (?,?)")) {
            statement.setString(1, period.getName());
            statement.setInt(2, period.getState());
            changeNumber = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulDAOException();
        }
        return changeNumber > 0;
    }

    public Period getById(Integer integer) throws UnsuccessfulDAOException {
        Period period = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "select * from period  where id =?")) {
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                period = createPeriod(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulDAOException();
        }
        return period;
    }


    public List<Period> selectPeriodsChoiceSubject() throws UnsuccessfulDAOException {
        List<Period> periods = getAll();
        //      periods.removeIf(period -> !period.getState().equals(State.CHOICE_SUBJECTS));
        return periods;
    }

    @Override
    public List<Period> getAvailablePeriodWithStateForUserIdOrStateId(int userId, int stateId) throws UnsuccessfulDAOException {
        List<Period> periods = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("select distinct period.id, period.name, period.state, state.name " +
                             "from period inner join state on period.state=state.id left join applicant on period.id=applicant.period " +
                             "where applicant.user=? or state.id = ?")) {
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2, stateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                periods.add(createPeriodWithStateEntity(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulDAOException(e.getMessage());
        }
        return periods;
    }

    private Period createPeriodWithStateEntity(ResultSet resultSet) throws SQLException {
        Period period = new Period();
        period.setId(resultSet.getInt("period.id"));
        period.setState(resultSet.getInt("period.state"));
        period.setName(resultSet.getString("period.name"));
        State state = new State();
        state.setName(resultSet.getString("state.name"));
        state.setId(resultSet.getInt("period.state"));
        period.setStateEntity(state);
        return period;
    }
}
