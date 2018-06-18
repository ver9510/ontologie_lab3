package ontologie_lab3.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

public class WebAppConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                AppConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected void customizeRegistration(final ServletRegistration.Dynamic registration) {
        //exception will be thrown if no mapping found and we can handle it in ExceptionHandler to return custom json
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        super.customizeRegistration(registration);
    }
}
