package com.nhnacademy.crud;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // /student/register.jsp forward
        // req.getRequestDispatcher("/student/register.jsp").forward(req, resp);
        // view attribute 설정 - /student/register.jsp
        req.setAttribute("register", "redirect:/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Gender gender = null;
        if (Objects.nonNull(req.getParameter("gender"))) {
            gender = Gender.valueOf(req.getParameter("gender"));
        }

        Integer age = null;
        if (Objects.nonNull(req.getParameter("age"))) {
            age = Integer.parseInt(req.getParameter("age"));
        }

        // null check
        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender)|| Objects.isNull(age)) {
            resp.sendRedirect("/student/register.jsp");
            return;
        }

        // save 구현
        Student student = new Student(id, name, gender, age);
        studentRepository.save(student);

        // redirect /student/view?id=student1
        // resp.sendRedirect("/student/view?id=" + student.getId());
        // redirect view attribute 설정   resp.sendRedirect("/student/view?id="+student.getId());
        req.setAttribute("view", "redirect:/student/view?id="+student.getId());

    }
}
