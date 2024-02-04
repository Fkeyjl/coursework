package com.exam.coursework.shared.web;

import com.exam.coursework.exception.NotFoundCommandException;
import com.exam.coursework.shared.annotations.Action;
import com.exam.coursework.shared.command.ActionCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class CommandFactory {

    private static final Logger logger = Logger.getLogger(CommandFactory.class);
    private HttpServletRequest request;

    public CommandFactory(HttpServletRequest request) {
        this.request = request;
    }

    public ActionCommand getCommand() {
            return ActionCommandEnum.valueOf(getCommandName(ActionCommandEnum.class)).getCurrentCommand();

    }

    String getCommandName(Class enumCommand) {
        System.out.println("getCommandName");
        String url = request.getRequestURI().replaceFirst(request.getContextPath(), "");
        for (Field field : enumCommand.getDeclaredFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation instanceof Action) {

                    if (((Action) annotation).url().equals(url) && ((Action) annotation).method().equals(request.getMethod())) {
                        System.out.println(field.getName());
                        return field.getName();
                    }
                }
            }
        }
        throw new NotFoundCommandException("Not found Command for " + url);
    }
}
