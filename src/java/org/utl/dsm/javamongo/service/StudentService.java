/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.javamongo.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.bson.Document;
import org.utl.dsm.exception.JavaMongoException;
import org.utl.dsm.javamongo.model.Student;
import org.utl.dsm.javamongo.repository.StudentRepository;

/**
 *
 * @author alejandro
 */
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(MongoClient client) {
        this.studentRepository = new StudentRepository(client);
    }

    public List<Student> find() throws JavaMongoException {
        return StreamSupport
                .stream(studentRepository.find().spliterator(), false)
                .map(this::getStudent)
                .collect(Collectors.toList()); // converting FindIterable<Document> to List<Student>
    }

    public Student find(String id) throws JavaMongoException {
        return StreamSupport
                .stream(studentRepository.find(id).spliterator(), false)
                .map(this::getStudent)
                .collect(Collectors.toList()).get(0);
    }

    public InsertOneResult insertOne(Student student) throws JavaMongoException {
        return studentRepository.insertOne(student);
    }

    public UpdateResult updateOne(Student student) throws JavaMongoException {
        return studentRepository.updateOne(student);
    }

    public DeleteResult deleteOne(String id) throws JavaMongoException {
        return studentRepository.deleteOne(id);
    }

    private Student getStudent(Document document) {
        return new Student(document);
    }
}
