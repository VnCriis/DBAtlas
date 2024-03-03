package org.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class Main {
    public static void main(String[] args) {
        // Cadena de conexión de MongoDB Atlas
        String connectionString = "mongodb+srv://esfot:esfot2024@cluster0.xzffuex.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("mydatabase"); // Reemplaza "mydatabase" por el nombre de tu base de datos
            MongoCollection<Document> collection = database.getCollection("mycollection"); // Reemplaza "mycollection" por el nombre de tu colección

            // Insertar datos
            List<Document> documents = new ArrayList<>();
            documents.add(new Document("name", "Cristian Usiña").append("age", 21));
            collection.insertMany(documents);

            System.out.println("Datos insertados correctamente.");

            // Revisar los datos ingresados
            System.out.println("Datos en la colección:");
            FindIterable<Document> iterDoc = collection.find();
            MongoCursor<Document> cursor = iterDoc.iterator();
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB Atlas: " + e.getMessage());
        }
    }
}
