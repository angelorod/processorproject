package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DemoService {

    private final DemoRepository studentRepository;

    @Autowired
    public DemoService(DemoRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<DemoEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public DemoEntity saveStudent(DemoEntity student) {
        return studentRepository.save(student);
    }
}
