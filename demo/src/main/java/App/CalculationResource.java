package App;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejbs.Calculation;

@Stateless
@Path("/api")
public class CalculationResource {

    @EJB
    private CalculationService calculationService;

    @POST
    @Path("calc")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculate(Calculation calculation) {
        int result = calculationService.performCalculation(calculation.getNumber1(), calculation.getNumber2(), calculation.getOperation());
        calculation.setResult(result);
        calculationService.saveCalculation(calculation);

        return Response.ok().entity(calculation).build();
    }
    
    @GET
    @Path("/calculations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCalculations() {
        try {
            List<Calculation> calculations = calculationService.getAllCalculations();
            return Response.ok().entity(calculations).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}