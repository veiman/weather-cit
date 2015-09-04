package ru.veiman.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import ru.veiman.filter.StaticResourcesFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * Created by veiman. (04.09.15 10:57)
 */
@Configuration
public class WebConfig implements ServletContextInitializer {
    private static final Logger log = LoggerFactory.getLogger(WeatherConfig.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Configure web");
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);

        FilterRegistration.Dynamic staticResourcesFilter =
                servletContext.addFilter("staticResourcesFilter",
                        new StaticResourcesFilter());

        staticResourcesFilter.addMappingForUrlPatterns(disps, true, "/");
        staticResourcesFilter.addMappingForUrlPatterns(disps, true, "/index.html");
        staticResourcesFilter.addMappingForUrlPatterns(disps, true, "/assets/*");
        staticResourcesFilter.addMappingForUrlPatterns(disps, true, "/scripts/*");
        staticResourcesFilter.setAsyncSupported(true);


    }
}
