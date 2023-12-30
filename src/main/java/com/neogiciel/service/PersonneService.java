package com.neogiciel.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import com.neogiciel.model.Personne;

public class PersonneService {
 

    List<Personne> liste = new ArrayList<>();

    /*
     * PersonneService
     */
    public PersonneService() {
        
        Personne p1 = new Personne("patrice","radin","50");
        Personne p2 = new Personne("test","iris","44");
        Personne p3 = new Personne("jon","doe","40");
        Personne p4 = new Personne("natacha","lusi","20");
        liste.add(p1);
        liste.add(p2);
        liste.add(p3);
        liste.add(p4);

    }

    /*
     * getListe
     */
    public List<Personne> getListe() {
        return liste;
    } 
    
    /*
     * getListe
     */
    public JSONArray getListeJSON() {

        JSONArray jsonArray = new JSONArray();

        for (int i= 0; i < liste.size(); i++ ){
            Personne p = (Personne)liste.get(i);
            JSONObject obj = new JSONObject();
            obj.put("nom", p.prenom);
            obj.put("prenon", p.nom);
            obj.put("age", p.age);
            jsonArray.put(obj);
        }

        System.out.print(jsonArray.toString());
        return jsonArray;
    } 
    
    
    /*
     * add
     */
    public void add(Personne p) {
        liste.add(p);
    }    

    /*
     * get
     */
    public Personne get(int index) {
        return liste.get(index);
    } 

    /*
     * toJSON
     */
    public JSONObject toJSON(Personne p) {

        JSONObject obj = new JSONObject();
        obj.put("nom", p.prenom);
        obj.put("prenon", p.nom);
        obj.put("age", p.age);
            
        return obj;
    }    


}
