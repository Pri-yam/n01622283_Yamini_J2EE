package com.example.lab3_empmgmtsystem.resources;

import com.example.lab3_empmgmtsystem.model.Employee;
import com.example.lab3_empmgmtsystem.service.EmpAppService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("/emp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpAppResource {
    @Inject
    private EmpAppService empAppService;

    @GET
    public Collection<Employee> getAllCoffees() {
        return empAppService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getCoffee(@PathParam("id") int id) {
        Employee employee = empAppService.getById(id);
        if (employee == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(employee).build();
    }

    @POST
    public Response addEmployee(Employee employee) {
        Employee created = empAppService.create(employee);
        return Response.status(Response.Status.CREATED).entity(employee).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") int id, Employee employee) {
        Employee updated = empAppService.update(id, employee);
        if (updated == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        boolean deleted = empAppService.delete(id);
        if (!deleted)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }

}
