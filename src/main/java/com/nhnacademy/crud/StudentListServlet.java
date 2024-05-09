package com.nhnacademy.crud;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(name = "studentListServlet", urlPatterns = "/student/list")
public class StudentListServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // student list 구하기
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList", studentList);

        /*
        // /student/list.jsp <- forward 하기
        RequestDispatcher dispatcher = req.getRequestDispatcher("/student/list.jsp");
        dispatcher.forward(req, resp);
         */

        // view attribute - /student/list.jsp
        req.setAttribute("list", "redirect:/student/list.jsp");

    }
}
