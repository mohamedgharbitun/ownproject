package crud.config;


import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import crud.config.root.RootContextConfig;
import crud.config.servlet.WebMvcConfig;
import crud.rest.filter.SimpleCORSFilter;
import crud.spring.AppConfig;


public class WepAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
            RootContextConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
            WebMvcConfig.class,
            AppConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    
    @Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new SimpleCORSFilter() };
        return singleton;
    }
}
