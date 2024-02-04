package com.exam.coursework.shared.service;

import com.exam.coursework.user.UserService;
import com.exam.coursework.user.applicant.ApplicantService;

public class ServiceLoader {
    public ApplicantService applicantService() {
        return new ApplicantService();
    }

    public UserService userService() {
        return new UserService();
    }
}
