package com.exam.coursework.user.applicant;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.AbstractDAO;
import com.exam.coursework.user.User;

import java.util.List;

/**
 * Basic interface used for implementing FactoryDAO for switching easily
 */
public interface ApplicantDAO extends AbstractDAO<Integer, Applicant> {
    int getApplicantIdByUserIdAndPeriod(User user, Integer period) throws UnsuccessfulDAOException;

    List<ApplicantSubject> getByPeriodAndSubject(int period, int subject) throws UnsuccessfulDAOException;

    public boolean updateApplicantSubjectMarks(List<ApplicantSubject> applicantSubjects) throws UnsuccessfulDAOException;

    int calculateRatingForSubjectSpeciality(int idApplicant, int idSpeciality) throws UnsuccessfulDAOException;

    boolean updateRatingByApplicantId(int applicantId, Integer rating) throws UnsuccessfulDAOException;

    List<Applicant> getBySpecialityIdAndPeriodIdWithUserEntity(int specialityID, int periodId, int limitStart, int numberOrLine) throws UnsuccessfulDAOException;

    List<Applicant> getBySpecialityIdAndPeriodId(int specialityID, int periodId) throws UnsuccessfulDAOException;

    int getNumberOfApplicantBySpecialityIdAndPeriodIdWithUserEntity(int specialityID, int periodId) throws UnsuccessfulDAOException;

    boolean updateList(List<Applicant> applicants) throws UnsuccessfulDAOException;
}
