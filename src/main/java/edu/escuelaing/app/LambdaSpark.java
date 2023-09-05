package edu.escuelaing.app;

import edu.escuelaing.app.handles.Servicio1Param;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Benavides (Modify by Ricardo Olarte)
 */
public class LambdaSpark implements Servicio1Param {

    private Map<String, BiFunction<HttpRequest, HttpResponse,String>> bodyPath = new HashMap();

    private final String BadRequest = "HTTP/1.1 400 Not Found\r\n"
            + "Content-Type: text/html\r\n"
            + "\r\n"
            + "<!DOCTYPE html>\n"
            + "<html>\n"
            + "<head>\n"
            + "<meta charset=\"UTF-8\">\n"
            + "<title>Error 404</title>\n"
            + "</head>\n"
            + "<body>\n"
            + "<h1>Error 404</h1>\n"
            + "<h3>URI not Found</h3>\n"
            + "</body>\n"
            +"<html>\n";

    private final String OkHeader = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: text/html\r\n"
            + "\r\n";

    private static LambdaSpark lambdaSpark = new LambdaSpark();

    private HttpServer httpServer = new HttpServer();

    public LambdaSpark() {
        httpServer.registerHandler(this,"/Movies");
    }
    public void startWebServer(){
        try {
            httpServer.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LambdaSpark getInstance(){
        return lambdaSpark;
    }

    /**
     * Store the path or any request and the result or value
     * @param path
     * @param body
     */
    void get(String path, BiFunction<HttpRequest, HttpResponse,String> body){
        bodyPath.put(path,body);
    }

    /**
     * Get the respective value from an especific request
     * @param path
     * @param req
     * @param res
     * @return
     */
    public  String getValue(String path, HttpRequest req ,HttpResponse res){
        if (bodyPath.containsKey(path)){
            return OkHeader + bodyPath.get(path).apply(req , res);
        }else {
            return BadRequest;
        }

    }

    @Override
    public String handle(String path, HttpRequest req ,HttpResponse res) {
        return getValue(path,req,res);
    }

}
