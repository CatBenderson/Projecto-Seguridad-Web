package ws;

import java.util.Set;
import javax.ws.rs.core.Application;
import utils.CORSFilter;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.AutenticacionWS.class);
        resources.add(ws.EmpleadoWS.class);
        resources.add(CORSFilter.class);
    }
    
}
