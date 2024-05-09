package com.nhnacademy.crud.contol;

import com.nhnacademy.crud.Gender;
import com.nhnacademy.crud.Student;
import com.nhnacademy.crud.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

public class StudentRegisterController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

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

        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender)|| Objects.isNull(age)) {
            throw new RuntimeException("id, name, gender, age 확인해주세요!");
        }

        Student student = new Student(id, name, gender, age);
        studentRepository.save(student);

        return "redirect:/student/view?id="+student.getId();
    }
}
