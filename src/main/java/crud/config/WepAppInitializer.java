package crud.config;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import crud.config.root.RootContextConfig;
import crud.config.servlet.WebMvcConfig;
import crud.spring.AppConfig;


public class WepAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

//        CharacterEncodingFilter utf8 = new CharacterEncodingFilter();
//        utf8.setEncoding("UTF-8");
//        utf8.setForceEncoding(true);
//        FilterRegistration.Dynamic utf8Filter = servletContext.addFilter("UTF8-filter", utf8);
//        utf8Filter.addMappingForUrlPatterns(null, true, "/*");
//
//        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("CORSFilter", new SimpleCORSFilter());
//        corsFilter.addMappingForUrlPatterns(null, true, "/*");
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
}
