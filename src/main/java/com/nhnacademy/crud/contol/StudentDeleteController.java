package com.nhnacademy.crud.contol;

import com.nhnacademy.crud.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentDeleteController implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        String id = request.getParameter("id");
        studentRepository.deleteById(id);

        return "redirect:/student/list.do";
    }
}
