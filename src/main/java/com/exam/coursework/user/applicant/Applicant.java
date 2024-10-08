package com.exam.coursework.user.applicant;

import com.exam.coursework.user.User;
import com.exam.coursework.user.applicant.period.Period;
import com.exam.coursework.user.applicant.status.Status;
import com.exam.coursework.user.speciality.Speciality;

import java.util.Objects;

/**
 * A model class for applicant database table
 */
public class Applicant {
    private Integer rating;
    private int id;
    private int user;
    private int period;
    private Integer speciality;
    private int status;
    private Status statusEntity;
    private Period periodEntity;
    private Speciality specialityEntity;
    private User userEntity;

    public Applicant(int id, int user, int period, int speciality, int status, int rating) {
        this.id = id;
        this.user = user;
        this.period = period;
        this.speciality = speciality;
        this.status = status;
        this.rating = rating;
    }

    public Applicant() {
    }

    public User getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(User userEntity) {
        this.userEntity = userEntity;
    }

    public Status getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(Status statusEntity) {
        this.statusEntity = statusEntity;
    }

    public Period getPeriodEntity() {
        return periodEntity;
    }

    public void setPeriodEntity(Period periodEntity) {
        this.periodEntity = periodEntity;
    }

    public Speciality getSpecialityEntity() {
        return specialityEntity;
    }

    public void setSpecialityEntity(Speciality specialityEntity) {
        this.specialityEntity = specialityEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Integer getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Integer speciality) {
        this.speciality = speciality;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Applicant)) return false;
        Applicant applicant = (Applicant) o;
        return id == applicant.id &&
                rating == applicant.rating &&
                Objects.equals(user, applicant.user) &&
                Objects.equals(period, applicant.period) &&
                Objects.equals(speciality, applicant.speciality) &&
                Objects.equals(status, applicant.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, period, speciality, status, rating);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Applicant{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", period=").append(period);
        sb.append(", speciality=").append(speciality);
        sb.append(", status=").append(status);
        sb.append(", rating=").append(rating);
        sb.append('}');
        return sb.toString();
    }
}
