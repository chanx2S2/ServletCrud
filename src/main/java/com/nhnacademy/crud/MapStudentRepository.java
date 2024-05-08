package com.nhnacademy.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapStudentRepository implements StudentRepository{
    private final Map<String, Student> studentMap = new ConcurrentHashMap<>();

    // 등록
    @Override
    public void save(Student student) {
        studentMap.put(student.getId(), student);
    }

    // 수정
    @Override
    public void update(Student student) {
        if (studentMap.containsKey(student.getId())) {
            studentMap.put(student.getId(), student);
        }
    }

    // 삭제
    @Override
    public void deleteById(String id) {
        studentMap.remove(id);
    }

    // 조회(by id)
    @Override
    public Student getStudentById(String id) {
        return studentMap.get(id);
    }

    // 리스트
    @Override
    public List<Student> getStudents() {
        return new ArrayList<>(studentMap.values());
    }

    // 아이디 존재여부
    @Override
    public boolean existById(String id) {
        return studentMap.containsKey(id);
    }
}
