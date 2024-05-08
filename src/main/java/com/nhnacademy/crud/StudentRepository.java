package com.nhnacademy.crud;

import java.util.List;

public interface StudentRepository {
    void save(Student student);         // 학생-등록
    void update(Student student);       // 학생-수정
    void deleteById(String id);         // 학생-삭제
    Student getStudentById(String id);  // 학생-조회
    List<Student> getStudents();        // 학생-리스트
    boolean existById(String id);       // 학생-아이디 존재여부
}
