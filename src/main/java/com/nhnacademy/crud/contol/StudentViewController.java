package com.nhnacademy.crud.contol;

import com.nhnacademy.crud.Student;
import com.nhnacademy.crud.StudentNotFoundException;
import com.nhnacademy.crud.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class StudentViewController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");

        if (Objects.isNull(id)) {
            throw new RuntimeException("parameter [id] : null");
        }

        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException(id);
        }
        log.error("student:{}", student);
        req.setAttribute("student", student);

        return "redirect:/student/view.jsp";
    }
}
