package edu.escuelaing.app;


import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.BiFunction;

/**
 * @author Ricardo Olarte
 */
public class SparkImp {
    public static void get(String path, BiFunction<HttpRequest, HttpResponse, String> body ){
        LambdaSpark.getInstance().get(path,body);
    }

    public static void startServer(){
        LambdaSpark.getInstance().startWebServer();
    }
}
