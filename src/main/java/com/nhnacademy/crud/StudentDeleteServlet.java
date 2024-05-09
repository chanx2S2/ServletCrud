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
@WebServlet(name = "studentDeleteServlet", urlPatterns = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get parameter : id, id가 존재하지 않을 경우 throw new RuntimeException("...")
        String id = req.getParameter("id");

        log.error("id:{}", id);
        if (Objects.isNull(id)) {
            throw new RuntimeException("id가 존재하지 않습니다.");
        }
        studentRepository.deleteById(id);

        // /student/list <-- redirect
        // resp.sendRedirect("/student/list");

        // view attribute - redirect:/student/list.jsp
        req.setAttribute("list", "redirect:/student/list.jsp");

    }
}
