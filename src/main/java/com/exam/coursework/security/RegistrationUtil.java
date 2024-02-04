package com.exam.coursework.security;

import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.User;
import com.exam.coursework.user.UserService;
import com.exam.coursework.utils.PasswordHashManager;
import com.exam.coursework.utils.PathPageManager;
import com.exam.coursework.utils.ValidationManager;
import jakarta.servlet.http.HttpServletRequest;


public class RegistrationUtil {
    UserService service;
    HttpServletRequest request;

    public RegistrationUtil(HttpServletRequest request) {
        this.request = request;
        service = new UserService();
    }

    public Page registration(User user) {
        if (!ValidationManager.isValidate(user)) {
            request.setAttribute("error",  ValidationManager.getFirsErrorMessage(user));
            request.setAttribute("reg",user);
            user.setPassword(null);
            return new Page(PathPageManager.getProperty("page.sign-up")).setDispatchType(Page.DispatchType.FORWARD);
        } else {
            user.setPassword(PasswordHashManager.hash(user.getEmail(),user.getPassword()));
            if (service.create(user)) {
                return new Page ("/applicant").setDispatchType(Page.DispatchType.REDIRECT);
            }
            else {
                user.setPassword(null);
                request.setAttribute("error", "msg.error");
                request.setAttribute("reg",user);
                return new Page(PathPageManager.getProperty("page.sign-up")).setDispatchType(Page.DispatchType.FORWARD);
            }
        }
    }


}
