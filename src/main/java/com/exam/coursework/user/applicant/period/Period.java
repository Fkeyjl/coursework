package com.exam.coursework.user.applicant.period;


import com.exam.coursework.user.applicant.period.state.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A model class for period database table
 */
public class Period {
    private int id;
    @NotBlank(message = "{msg.period}")
    @Size(min = 2, max = 45, message = "{msg.period}")
    private String name;
    private int state;

    public State getStateEntity() {
        return stateEntity;
    }

    public void setStateEntity(State stateEntity) {
        this.stateEntity = stateEntity;
    }

    private State stateEntity;

    public Period(int id, String name, int state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Period() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return id == period.id &&
                Objects.equals(name, period.name) &&
                state == period.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, state);
    }

    @Override
    public String toString() {
        return "Period{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
