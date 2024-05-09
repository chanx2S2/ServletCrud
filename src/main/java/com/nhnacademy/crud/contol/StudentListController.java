package com.nhnacademy.crud.contol;

import com.nhnacademy.crud.Student;
import com.nhnacademy.crud.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class StudentListController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList", studentList);
        return "redirect:/student/list.jsp";
    }
}
