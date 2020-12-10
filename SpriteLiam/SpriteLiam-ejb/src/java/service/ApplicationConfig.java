/**
 * This class is responsible for basic authentication 
 * 
 * @author Liam Henley-Vachon
 * @version 1
 * @since 11/22/2020
 */
package service;

import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;

// Checks appuser table for the following information: userid, groupname, password
@DatabaseIdentityStoreDefinition (
    dataSourceLookup = "${'java:comp/DefaultDataSource'}",
    callerQuery = "#{'select password from app.appuser where userid = ?'}",
    groupsQuery = "select groupname from app.appuser where userid = ?",
   hashAlgorithm = PasswordHash.class,
   priority = 10
           )
// BasicAuthenticationMechanismDefinition for when user is promtped for id and pass
@BasicAuthenticationMechanismDefinition
//@FormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                // Didn't create error page
//                loginPage = "/index.xhtml",
//                errorPage = "/index.xhtml"))
@Named
@ApplicationScoped
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.SpriteFacadeREST.class);
    }
    
}
