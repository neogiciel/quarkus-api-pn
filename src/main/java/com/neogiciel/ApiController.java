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


@Path("/")
public class ApiController {

    
    //Metriques
    @Inject
    MeterRegistry registry;

    //Peronnse
    PersonneService personneService = new PersonneService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api")
    public String api() {
        
        JSONObject obj = new JSONObject();
        obj.put("api", "api quarkus");
        return obj.toString();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/liste")
    public String liste() {

        //Trace
        registry.counter("api liste", Tags.of("name", "liste")).increment();

        JSONArray jsonArray = personneService.getListeJSON();
        return jsonArray.toString();
     }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/get/{id}")
    public String get(int id) {
        //Trace
        registry.counter("api liste", Tags.of("name", "get")).increment();

        return personneService.toJSON(personneService.get(id)).toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/add")
    public String add() {
        
        personneService.add(new Personne("toto","toto","16"));
        
        JSONObject obj = new JSONObject();
        obj.put("add", "ajoute avec succes");
        return obj.toString();
    }

}
