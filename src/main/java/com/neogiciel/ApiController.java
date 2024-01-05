package com.neogiciel;


import org.eclipse.microprofile.config.inject.ConfigProperty;
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

    @ConfigProperty(name = "application.version") 
    String applicationVersion;

    //Metriques
    @Inject
    MeterRegistry registry;

    //Peronnse
    PersonneService personneService = new PersonneService();

    /*
     * api
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api")
    public String api() {

        registry.counter("api", Tags.of("name", "api")).increment();
        
        JSONObject obj = new JSONObject();
        obj.put("api", "api quarkus");
        return obj.toString();
    }

    /*
     * liste 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/liste")
    public String liste() {

        //Trace
        registry.counter("api liste", Tags.of("name", "liste")).increment();

        JSONArray jsonArray = personneService.getListeJSON();
        return jsonArray.toString();
     }

    /*
     * get 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/get/{id}")
    public String get(int id) {
        
        //Trace
        registry.counter("api get", Tags.of("name", "get = "+id)).increment();

        return personneService.toJSON(personneService.get(id)).toString();
    }

    /*
     * add
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/add")
    public String add() {
        
         registry.counter("api add", Tags.of("name", "add ")).increment();
 
        personneService.add(new Personne("toto","toto","16"));
        
        JSONObject obj = new JSONObject();
        obj.put("add", "ajoute avec succes");
        return obj.toString();
    }

    /*
     * add
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/version")
    public String version() {
        
        JSONObject obj = new JSONObject();
        obj.put("version", applicationVersion);
        return obj.toString();
    }

}
