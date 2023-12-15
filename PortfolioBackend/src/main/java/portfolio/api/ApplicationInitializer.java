package portfolio.api;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

	@Order(Ordered.HIGHEST_PRECEDENCE)
	public class ApplicationInitializer implements WebApplicationInitializer {
	 
	    @Override
	    public void onStartup(ServletContext servletContext) throws ServletException { 
	        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	        servletContext.addListener(new ContextLoaderListener(context));
	        servletContext.setInitParameter("contextConfigLocation", "portfolio.api");
	    }
	}


