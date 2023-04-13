package com.lab.task.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lab.task.model.Calculation;
import com.lab.task.repo.CalculationRepo;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@Path("/")
public class CalculationService {
    @Inject
    private CalculationRepo repo;

    public CalculationService() {
    }

    @Path("calc")
    @POST
    public int calculate(Calculation calculation) {
        int result;
        switch (calculation.getOperation()) {
            case "+":
                result = calculation.getNumber1() + calculation.getNumber2();
                break;
            case "-":
                result = calculation.getNumber1() - calculation.getNumber2();
                break;
            case "*":
                result = calculation.getNumber1() * calculation.getNumber2();
                break;
            case "/": {
                if (calculation.getNumber2() == 0)
                    throw new IllegalArgumentException("Can't divide by zero");
                result = calculation.getNumber1() / calculation.getNumber2();
                break;
            }
            default:
                throw new IllegalArgumentException("Unsupported operation");
        }
        repo.insert(calculation);
        return result;
    }

    @Path("calculations")
    @GET
    public List<Calculation> getAllCalculations() {
        return repo.selectAll();
    }

    @Path("/")
    @GET
    public String getHealth() {
        return "Up and running";
    }
}
