package com.exam.coursework.user.applicant.period;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.FactoryDAO;
import com.exam.coursework.shared.dao.FactoryDAOSql;
import com.exam.coursework.shared.service.AbstractServiceSql;
import com.exam.coursework.shared.service.Service;
import com.exam.coursework.user.applicant.ApplicantService;
import com.exam.coursework.user.applicant.period.state.State;
import com.exam.coursework.user.applicant.period.state.StateDAO;
import com.exam.coursework.user.applicant.period.state.StateName;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PeriodService extends AbstractServiceSql<Integer, Period> implements Service<Integer, Period> {
    private static final Logger logger = Logger.getLogger(PeriodService.class);
    FactoryDAO factoryDAO;

    public PeriodService() {
        super(PeriodDAOSql.class);
        factoryDAO = new FactoryDAOSql();
    }


    public boolean checkAllPeriodIsEnded() throws UnsuccessfulDAOException {
        /*List<Period> periods = null;
        try (Connection connection = factoryDAO.createConnection()){
            PeriodDAO periodDAO = factoryDAO.createPeriodDAO(connection);
            periods = periodDAO.getAll();
            for (Period p :periods) {
                if(!p.getState().equals(State.ENDED))
                    return false;
            }
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }*/
        return false;
    }

    public List<Period> getAvailablePeriodsByUserId(int userID) {
        List<Period> periods = null;
        try (Connection connection = factoryDAO.createConnection()) {
            PeriodDAO periodDAO = factoryDAO.createPeriodDAO(connection);
            periods = periodDAO.getAvailablePeriodWithStateForUserIdOrStateId(userID, 1);
        } catch (SQLException | UnsuccessfulDAOException e) {
            logger.error(e.getMessage());
        }
        return periods;
    }


    public Period getByIdWithState(int id) {
        Period period = null;
        try (Connection connection = factoryDAO.createConnection()) {
            PeriodDAO periodDAO = factoryDAO.createPeriodDAO(connection);
            StateDAO stateDAO = factoryDAO.createStateDAO(connection);
            period = periodDAO.getById(id);
            State state = stateDAO.getById(period.getState());
            period.setStateEntity(state);
        } catch (SQLException | UnsuccessfulDAOException e) {
            logger.error(e);
        }
        return period;
    }


    public boolean setNextState(Period period) {
        try (Connection connection = factoryDAO.createConnection()) {
            PeriodDAO periodDAO = factoryDAO.createPeriodDAO(connection);
            StateDAO stateDAO = factoryDAO.createStateDAO(connection);
            State currentState = stateDAO.getById(period.getState());
            StateName stateName = StateName.getByName(currentState.getName());
            if (stateName != StateName.ENDED) {
                stateName = stateName.getNext();
                currentState = stateDAO.getByName(stateName.getNames().get(stateName));
                period.setState(currentState.getId());
                if (stateName.equals(StateName.ENDED)) {
                    ApplicantService service = new ApplicantService();
                    service.updatePeriod(period.getId());
                }
                return periodDAO.update(period);
            }
        } catch (
                UnsuccessfulDAOException | SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean createWithStateName(Period period, StateName name) {
        try (Connection connection = factoryDAO.createConnection()) {
            PeriodDAO periodDAO = factoryDAO.createPeriodDAO(connection);
            StateDAO stateDAO = factoryDAO.createStateDAO(connection);
            period.setState(stateDAO.getByName(StateName.getNames().get(name).toLowerCase()).getId());
            return periodDAO.create(period);
        } catch (SQLException | UnsuccessfulDAOException e) {
            logger.error(e);
        }
        return false;

    }
}
