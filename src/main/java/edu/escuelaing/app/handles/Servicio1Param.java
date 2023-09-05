package edu.escuelaing.app.handles;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Interface to process the path, request and response
 */
public interface Servicio1Param {
    String handle(String s, HttpRequest req, HttpResponse res);
}