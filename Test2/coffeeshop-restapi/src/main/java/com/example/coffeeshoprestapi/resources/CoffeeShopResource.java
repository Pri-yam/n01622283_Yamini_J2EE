package com.example.coffeeshoprestapi.resources;
import com.example.coffeeshoprestapi.models.Coffee;

import com.example.coffeeshoprestapi.services.CoffeeService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

@Path("/coffees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeShopResource {

    @Inject
    private CoffeeService coffeeService;

    @GET
    public Collection<Coffee> getAllCoffees() {
        return coffeeService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getCoffee(@PathParam("id") int id) {
        Coffee coffee = coffeeService.getById(id);
        if (coffee == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(coffee).build();
    }

    @POST
    public Response addCoffee(@Context SecurityContext sc, Coffee coffee) {
        if (!sc.isUserInRole("admin")) {
            return Response.status(Response.Status.FORBIDDEN).entity("Admin only").build();
        }
        Coffee created = coffeeService.create(coffee);
        return Response.status(Response.Status.CREATED).entity(coffee).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCoffee(@PathParam("id") int id, Coffee coffee) {
        Coffee updated = coffeeService.update(id, coffee);
        if (updated == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCoffee(@PathParam("id") int id) {
        boolean deleted = coffeeService.delete(id);
        if (!deleted)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}