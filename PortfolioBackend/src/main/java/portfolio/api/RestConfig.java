package portfolio.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import controller.ViewerController;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/resources")
public class RestConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(ViewerController.class));
    }
}