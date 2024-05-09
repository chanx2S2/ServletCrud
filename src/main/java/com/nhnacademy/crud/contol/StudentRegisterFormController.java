package com.nhnacademy.crud.contol;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentRegisterFormController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "redirect:/student/register.jsp";
    }
}
