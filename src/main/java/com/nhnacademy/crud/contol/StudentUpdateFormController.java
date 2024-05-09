package com.nhnacademy.crud.contol;

import com.nhnacademy.crud.Student;
import com.nhnacademy.crud.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

public class StudentUpdateFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");

        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            throw new RuntimeException("Student not found : " + id);
        }
        req.setAttribute("student", student);

        return "redirect:/student/register.jsp";
    }
}
