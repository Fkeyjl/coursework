package com.exam.coursework.user;

import com.exam.coursework.user.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class User {

    private int id;

    @NotBlank(message = "{msg.first-name}")
    @Size(min = 2, max = 45, message = "{msg.first-name}")
    private String firstname;

    @NotBlank(message = "{msg.surname}")
    @Size(min = 2, max = 45, message = "{msg.surname}")
    private String surname;

    @NotBlank(message = "{msg.password}")
    @Size(min = 4, max = 45,message = "{msg.password}")
    private String password;

    @Email(message = "{msg.email}")
    @Size(min = 2, max = 45, message = "{msg.email}")
    private String email;

    private int role;

    private Role roleEntity;

    public User(int id, String firstName, String surname, String password, String email, int role) {
        this.id = id;
        this.firstname = firstName;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User() {
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(Role roleEntity) {
        this.roleEntity = roleEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, surname, password, email, role);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
