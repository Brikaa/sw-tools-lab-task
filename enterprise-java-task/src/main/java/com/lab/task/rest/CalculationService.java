package com.lab.task.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lab.task.model.Calculation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@Path("/")
public class CalculationService {
    public CalculationService() {
    }

    @Path("calc")
    @POST
    public int calculate(Calculation calc) {
        switch (calc.getOperation()) {
            case "+":
                return calc.getNumber1() + calc.getNumber2();
            case "-":
                return calc.getNumber1() - calc.getNumber2();
            case "*":
                return calc.getNumber1() * calc.getNumber2();
            case "/":
                return calc.getNumber1() / calc.getNumber2();
            default:
                throw new IllegalArgumentException("Unknown operation: " + calc.getOperation());
        }
    }

    @Path("/")
    @GET
    public String getHealth() {
        return "Up and running";
    }
}
