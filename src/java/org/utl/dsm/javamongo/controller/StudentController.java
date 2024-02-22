/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.javamongo.controller;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.exception.JavaMongoException;
import org.utl.dsm.javamongo.model.Student;
import org.utl.dsm.javamongo.service.StudentService;
import org.utl.dsm.javamongo.util.MongoConnection;

/**
 *
 * @author alejandro
 */
@Path("/student")
@Produces("application/json")
public class StudentController {

    @GET
    @Path("/list")
    public Response list() {
        Gson gson = new Gson();
        try (MongoClient client = MongoConnection.getConnection()) { // client.close(); ❌ try with resources https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
            StudentService studentService = new StudentService(client);
            return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(studentService.find()))
                    .build();
        } catch (JavaMongoException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(ex)).build();
        }
    }

    @GET
    public Response read(@QueryParam("id") String id) {
        Gson gson = new Gson();
        try (MongoClient client = MongoConnection.getConnection()) {
            StudentService studentService = new StudentService(client);
            return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(studentService.find(id)))
                    .build();
        } catch (JavaMongoException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(ex)).build();
        }
    }

    @POST
    public Response create(
            @FormParam("id") String id,
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("phone") String phone) {
        Gson gson = new Gson();
        try (MongoClient client = MongoConnection.getConnection()) {
            StudentService studentService = new StudentService(client);
            return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(studentService.insertOne(
                            new Student(
                                    id,
                                    firstName,
                                    lastName,
                                    email,
                                    phone
                            ))
                    ))
                    .build();
        } catch (JavaMongoException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(ex)).build();
        }
    }

    @PUT
    public Response update(
            @FormParam("id") String id,
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("phone") String phone) {
        Gson gson = new Gson();
        try (MongoClient client = MongoConnection.getConnection()) {
            StudentService studentService = new StudentService(client);
            return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(studentService.updateOne(
                            new Student(id, firstName, lastName, email, phone))
                    ))
                    .build();
        } catch (JavaMongoException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(ex)).build();
        }
    }

    @DELETE
    public Response delete(@QueryParam("id") String id) {
        Gson gson = new Gson();
        try (MongoClient client = MongoConnection.getConnection()) {
            StudentService studentService = new StudentService(client);
            return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(studentService.deleteOne(id)))
                    .build();
        } catch (JavaMongoException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(ex)).build();
        }
    }
}
