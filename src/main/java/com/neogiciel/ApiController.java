package com.neogiciel;


import java.util.List;

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

import com.neogiciel.model.Personne;
import com.neogiciel.model.Service;
import com.neogiciel.model.ServicePersonne;
import com.neogiciel.service.BddManager;
import com.neogiciel.util.Trace;


@Path("/")
public class ApiController {

    @ConfigProperty(name = "application.version") 
    String applicationVersion;

    //Metriques
    @Inject
    MeterRegistry registry;

    //BddManager
    @Inject
    BddManager bdd; 

    /*
     * api
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api")
    public String api() {
        Trace.info("Applel REST : API");        
        registry.counter("api", Tags.of("name", "api")).increment();
        return getJSON("api", "api quarkus").toString();
    }

    /*
     * liste 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/liste")
    public String liste() {
        Trace.info("Applel REST : /api/liste");        
        registry.counter("api liste", Tags.of("name", "liste")).increment();
        List<Personne> liste = bdd.getListPersonnes();

        if(liste.size()> 0){
            JSONArray jsonArray = new Personne().totListeJSON(liste);
            return jsonArray.toString();
        }
        return getJSON("nb", "0").toString();
     }

    /*
     * get 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/get/{id}")
    public String get(int id) {
       Trace.info("Applel REST : /api/get/{"+id+")");        
       registry.counter("api get", Tags.of("name", "get = "+id)).increment();
       Personne personne = bdd.getPersonneFromId(id);
       return personne.toJSON(personne).toString();
    }

    /*
     * add
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/add")
    public String add() {
        Trace.info("Applel REST : /api/add");        
        registry.counter("api add", Tags.of("name", "add ")).increment();
        Long id = bdd.addPersonneSQL(new Personne("toto1","toto1",26));
        return getJSON("add", "ajoute avec succes = "+id).toString();
    }

    /*
     * delete
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/delete/{id}")
    public String delete(int id) {
        Trace.info("Applel REST : /api/delete{"+id+"}");        
        registry.counter("api delete", Tags.of("name", "delete"+id)).increment();

        bdd.deletePersonneSQL(id);

        return getJSON("delete", "supprime avec succes = "+id).toString();

    }

    /*
     * update
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/update/{id}")
    public String update(int id) {
        Trace.info("Applel REST : /api/delete{"+id+"}");        
        registry.counter("api update", Tags.of("name", "update"+id)).increment();

        Personne personne = new Personne("cyril","despres",44);
        personne.id = id;
        bdd.updatePersonneSQL(personne);


        return getJSON("update", "modifie avec succes = "+id).toString();

    }


    /*
     * version
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/version")
    public String version() {
        Trace.info("Applel REST : /api/version");        
        return getJSON("version",applicationVersion).toString();

    }

    
    /*
     * service liste 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/service/liste")
    public String serviceliste() {
        Trace.info("Applel REST : /api/service/liste");        
        registry.counter("api service liste", Tags.of("name", "liste")).increment();
        List<Service> liste = bdd.getListServices();
        JSONArray jsonArray = new Service().totListeJSON(liste);
        return jsonArray.toString();
        
     }

    /*
     * service liste 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/service/listepersonnnefromservice/{idservice}")
    public String listepersonnnefromservice(int idservice) {
        Trace.info("Applel REST : /api/service/listepersonnnefromservice = "+idservice);        
        registry.counter("api listepersonnnefromservice", Tags.of("name", "listepersonnnefromservice")).increment();
               
        List<ServicePersonne> liste = bdd.getListPersonneFromServices(idservice);

        if(liste.size()> 0){
            JSONArray jsonArray = new ServicePersonne().totListeJSON(liste);
            return jsonArray.toString();
        }
        return getJSON("nb", "0").toString();
        
     }

     /*
      * getJSON
      */
     public JSONObject getJSON(String value,String key){
        JSONObject obj = new JSONObject();
        obj.put(value, key);
        return obj;
     }



}
