/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.javamongo.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


/**
 *
 * @author alejandro
 */
public class MongoConnection {
    
    private static final String url = "mongodb://localhost:27017";
    
    public static MongoClient getConnection() {
        return MongoClients.create(url);
    }
    
}
