package com.nhnacademy.crud;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.io.IOException;

public class WebApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = null;
        try {
            studentRepository = new JsonStudentRepository();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i = 1; i <= 10; i++) {
            String id = "std" + i;
            String name = "Student" + i;
            Gender gender = Math.random() < 0.5 ? Gender.M : Gender.F;
            int age = (int) (Math.random() * 10 + 1) + 20;

            Student student = new Student(id, name, gender, age);
            studentRepository.save(student);
        }
    }
}
