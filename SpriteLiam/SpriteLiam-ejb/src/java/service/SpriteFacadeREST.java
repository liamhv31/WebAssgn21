/**
 * This class is responsible for all of the applications API requests when 
 * dealing with Sprites
 * 
 * @author Liam Henley-Vachon
 * @version 1
 * @since 11/22/2020
 */
package service;

import cst8218.henl0008.entity.Sprite;
import cst8218.henl0008.entity.AbstractFacade;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Liam
 */
@Stateless
// Declares roles for RestGroup and gives access
@DeclareRoles({"Admin", "RestGroup"})
@RolesAllowed({"Admin", "RestGroup"})
@Path("cst8218.henl0008.entity.sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "SpriteLiam-ejbPU")
    private EntityManager em;

    /**
     * This is the default constructor for the SpriteFacadeREST class.
     * It calls the parent AbstractFacade class
     */
    public SpriteFacadeREST() {
        super(Sprite.class);
    }

    /**
     * This method creates an entity object.
     * It calls the create method in AbstractFacade to create the entity object
     * @param entity - This is the entity object being created
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Sprite entity) {
        super.create(entity);
    }

    /**
     * This method edits an entity object.
     * It calls the edit method from the parent class
     * @param id - the id of the entity object
     * @param entity - the entity object being edited
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Sprite entity) {
        super.edit(entity);
    }

    /**
     * This method deletes an entity object.
     * It calls the remove method from the parent class and the find method to find the entity object
     * @param id - the entity object's id
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
     * This method returns an entity object by id.
     * It calls the find method in he parent class
     * @param id - the entity object's id
     * @return - the id of the entity object
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sprite find(@PathParam("id") Long id) {
        return super.find(id);
    }

    /**
     * This method returns a list of all the entity objects.
     * It calls the findAll method in the parent class
     * @return - a list of all the sprites
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }

    /**
     * This method returns a list of all the sprites within a certain range.
     * It calls the findRange method from the parent class
     * @param from - starting integer value
     * @param to - ending integer value
     * @return - a list of sprite objects by range
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     * This method counts the number of sprites there are.
     * It calls the count method from the parent class
     * @return - the number of sprites
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    /**
     * This method returns an EntityManager object
     * @return - Returns EntityManager object
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
