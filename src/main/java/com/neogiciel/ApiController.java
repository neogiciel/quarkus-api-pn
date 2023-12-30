package com.neogiciel;


import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;


import com.neogiciel.service.PersonneService;
import com.neogiciel.model.Personne;


@Path("/api")
public class ApiController {

     @Inject
    MeterRegistry registry;

    PersonneService personneService = new PersonneService();

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/liste")
    public String liste() {

        //Trace
        registry.counter("api liste", Tags.of("name", "liste")).increment();

        JSONArray jsonArray = personneService.getListeJSON();
        return jsonArray.toString();
     }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public String get(int id) {
        //Trace
        registry.counter("api liste", Tags.of("name", "get")).increment();

        return personneService.toJSON(personneService.get(id)).toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    public String add() {
        
        personneService.add(new Personne("toto","toto","16"));
        
        JSONObject obj = new JSONObject();
        obj.put("add", "ajoute avec succes");
        return obj.toString();
    }

}
