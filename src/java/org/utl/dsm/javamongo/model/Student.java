/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.javamongo.model;

import com.google.gson.Gson;
import org.bson.Document;

/**
 *
 * @author alejandro
 */
public class Student {

    private String _id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Student(Document document) {
        this._id = document.getString("_id");
        this.firstName = document.getString("firstName");
        this.lastName = document.getString("lastName");
        this.email = document.getString("email");
        this.phone = document.getString("phone");
    }

    public Student(String _id, String firstName, String lastName, String email, String phone) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
