package edu.escuelaing.app;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static edu.escuelaing.app.SparkImp.*;


/**
 * Simulator of Spark
 * @author Benavides(Modify by Ricardo Olarte)
 */
public class MySpark {
    public static void main(String[] args) {
        get("/customers",(req , res) -> getDataChange(req));
        startServer();
    }

    /**
     * From the database show all data
     * @return String
     */
    public static String getDataChange(HttpRequest request){

        return "Error 404";
    }

}