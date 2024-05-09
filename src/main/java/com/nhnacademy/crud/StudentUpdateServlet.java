package com.nhnacademy.crud;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        // 학생조회
        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            throw new RuntimeException("Student not found : " + id);
        }
        req.setAttribute("student", student);

        /*
        // forward : /student/register.jsp
        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
        rd.forward(req,resp);
         */
        //view attribute 설정 - /student/register.jsp
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
        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)) {
            throw new RuntimeException("id, name, gender, age 확인해주세요!");
        }

        // student 저장
        Student student = new Student(id, name, gender, age);
        studentRepository.update(student);

        // /student/view?id=student1 <-- redirect
        // resp.sendRedirect("student/view?id=" + student.getId());
        // view attribute 설정 - redirect
        req.setAttribute("view", "redirect:/student/view?id="+student.getId());

    }
}
