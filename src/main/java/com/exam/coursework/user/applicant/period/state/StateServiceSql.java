package com.exam.coursework.user.applicant.period.state;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.FactoryDAO;
import com.exam.coursework.shared.dao.FactoryDAOSql;
import com.exam.coursework.shared.service.AbstractServiceSql;
import com.exam.coursework.shared.service.Service;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class StateServiceSql extends AbstractServiceSql<Integer, State> implements Service<Integer,State> {
    private final Logger logger = Logger.getLogger(StateServiceSql.class);
    FactoryDAO factoryDAO;

    public StateServiceSql() {
        super(StateDAOSql.class);
        factoryDAO = new FactoryDAOSql();
    }

    public StateName getByPeriodId(int id) {
        StateName stateName = null;
        try (Connection connection = factoryDAO.createConnection()) {
            StateDAO stateDAO = factoryDAO.createStateDAO(connection);
            State state = stateDAO.getByPeriodId(id);
            stateName= StateName.getByName(state.getName());
        } catch (SQLException | UnsuccessfulDAOException e) {
            logger.error(e.getMessage());
        }
        return stateName;
    }

    public State getByName(StateName stateName) {
        State state =null;
        try (Connection connection = factoryDAO.createConnection()) {
            StateDAO stateDAO = factoryDAO.createStateDAO(connection);
            state = stateDAO.getByName(stateName.name().toLowerCase());
        } catch (SQLException | UnsuccessfulDAOException e) {
            logger.error(e.getMessage());
        }
        return state;
    }


}
