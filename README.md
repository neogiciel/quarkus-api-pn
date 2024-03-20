## <h1>Application Quarkus MySQL</h1>
***
<table><tr>
  <td><img src="https://th.bing.com/th/id/OIP.pWQN5n8eqyY2qMH7tlAabQHaEP?rs=1&pid=ImgDetMain" alt="drawing" height="240px"/></td>
  <td><img src="https://blog.desdelinux.net/wp-content/uploads/2023/07/MySQL.jpg" alt="drawing" height="240px"/></td>
</tr></table>

## Informations Générales
***
Mise en place d'un base de données redis avec sous Quarkus.

## Technologies
***
Technologies utilisées:
* Java 17
* Hibernate
* Maven 3.6
* Quarkus: 3.6.4
## Instalation
***
Lancement de l'application Quarkus<br>
```
$ mvn  clean
$ mvn quarkus:dev
```
Le service est accessible sur http://localhost:8080

## FAQs
***
**Manager d'appel à la Base de données**

L'appel au requetes se fait directement via EntityManager.
Nous avons centralisé les requêtes dans un Manager qui permets de gérer toutes les table de notre base de données.
Ce choix délibéré de ne pas eclater les différents appels dans différentes classe s'inspire directement de mon experience s'avere bien plus productif à long terme.

Cette solution est un véritable atout en terme d ajout, de modification, d'optimisation des requêtes SQL. 
Sachant que dans un micro service les base de données sont souvent éclatés et le nombre de table assez réduite.

```
public interface BddManager {
    
     /*
     * Table Personne
     */
    public List<Personne> getListPersonnes();
    public List<Personne> getListPersonnesSQL();
    public Long addPersonne(Personne personne);
    public Personne getPersonneFromId(int Id);
    public void deletePersonne(int id);
	  public void updatePersonne(Personne personne);
	
    /*
     * Table Service
     */
    public List<Service> getListServices();
    public Service getServiceFromId(int Id);
    public Long addService(Service service);
    public void deleteService(int id);
    public void updateService(Service service);

    /*
     * Table Service Persoone
     */
    public List<ServicePersonne> getListPersonneFromServices(int id);

}
```



