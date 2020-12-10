/**
 * This class extends AbstractFacde and returns an EntityManager object that
 * contains the sprite object to have the CRUD operations performed on
 * 
 * @author Liam Henley-Vachon
 * @version 1
 * @since 11/22/2020
 */
package cst8218.henl0008.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tgk
 */
@Stateless
public class SpriteFacade extends AbstractFacade<Sprite> {
    @PersistenceContext(unitName = "SpriteLiam-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SpriteFacade() {
        super(Sprite.class);
    }
    
}
