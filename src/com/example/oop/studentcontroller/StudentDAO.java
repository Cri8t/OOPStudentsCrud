package com.example.oop.studentcontroller;

import com.example.oop.model.Student;

import java.util.List;


public interface StudentDAO {
    
    public void save(Student student);
     public void update(Student student);
      public void AddImage(Student student);
      public Student get(int id);
      public List<Student> getAll();
    
}