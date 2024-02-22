/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.javamongo.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.utl.dsm.exception.JavaMongoException;
import org.utl.dsm.javamongo.model.Student;

/**
 *
 * @author alejandro
 */
public class StudentRepository { // DATA ACCESS OBJECT

    private MongoClient client;

    public StudentRepository(MongoClient client) {
        this.client = client;
    }

    public FindIterable<Document> find() throws JavaMongoException { // db.students.find()
        try {
            MongoDatabase db = client.getDatabase("java");
            FindIterable<Document> result = db.getCollection("students").find();
            return result;
        } catch (Exception e) {
            throw new JavaMongoException(e.getMessage());
        }
    }

    public FindIterable<Document> find(String id) throws JavaMongoException { // db.students.find({_id: ''})
        try {
            MongoDatabase db = client.getDatabase("java");
            FindIterable<Document> result = db.getCollection("students").find(Filters.eq("_id", id));
            return result;
        } catch (Exception e) {
            throw new JavaMongoException(e.getMessage());
        }
    }

    public InsertOneResult insertOne(Student student) throws JavaMongoException { // db.students.insertOne({_id: '', firstName: '', lastName: '', email: '', phone: ''})
        try {
            MongoDatabase db = client.getDatabase("java");
            Document document = Document.parse(student.toString());
            InsertOneResult result = db.getCollection("students").insertOne(document);
            return result;
        } catch (Exception e) {
            throw new JavaMongoException(e.getMessage());
        }
    }

    public UpdateResult updateOne(Student student) throws JavaMongoException {  // db.students.updateOne({_id: ''}, {$set: {firstName: '', lastName: '', email: '', phone: ''}})
        try {
            MongoDatabase db = client.getDatabase("java");
            Document document = Document.parse(student.toString());
            Bson bson = Filters.eq("_id", student.getId());
            UpdateResult result = db.getCollection("students").updateOne(bson, new Document("$set", document));
            return result;
        } catch (Exception e) {
            throw new JavaMongoException(e.getMessage());
        }
    }

    public DeleteResult deleteOne(String id) throws JavaMongoException { // db.students.deleteOne({_id: ''})
        try {
            Bson bson = Filters.eq("_id", id);
            MongoDatabase db = client.getDatabase("java");
            DeleteResult result = db.getCollection("students").deleteOne(bson);
            return result;
        } catch (Exception e) {
            throw new JavaMongoException(e.getMessage());
        }
    }
}
