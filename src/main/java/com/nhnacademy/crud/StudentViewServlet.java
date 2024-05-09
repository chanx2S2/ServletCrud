package com.nhnacademy.crud;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        // id null check
        if (Objects.isNull(id)) {
            throw new RuntimeException("parameter [id] : null");
        }

        // student 조회
        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException(id);
        }
        log.error("student:{}", student);
        req.setAttribute("student", student);

        /*
        // /student/view.jsp <-- forward
        RequestDispatcher rd = req.getRequestDispatcher("/student/view.jsp");
        rd.forward(req,resp);
        */
        //view attribute 설정 - /student/view.jsp
        req.setAttribute("view", "redirect:/student/view.jsp");
    }

}
