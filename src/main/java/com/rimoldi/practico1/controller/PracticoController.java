package com.rimoldi.practico1.controller;
import spark.*;

public class PracticoController {
    public Route getFahrenheit = (Request req, Response res) -> {       
        return req.params(":celsius") + " equivalen a " + ((Integer.parseInt(req.params(":celsius")) *(9/5) )+32) + " °Fahrenheit.";
    };
}
