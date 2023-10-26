package lv.spoti.config;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
 
 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**")
		
                .addResourceLocations("file:///C:/Users/robis/OneDrive/Dators/spoti.lv/spoti/src/main/resources/uploads/") //vajag user dir izmantot
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
        
	}
}