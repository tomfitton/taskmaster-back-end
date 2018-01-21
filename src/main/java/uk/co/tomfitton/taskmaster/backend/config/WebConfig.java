package uk.co.tomfitton.taskmaster.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("uk.co.tomfitton.taskmaster.backend")
public class WebConfig extends WebMvcConfigurerAdapter {

	public WebConfig() {
		super();
	}
	
	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/api/**");
	}
	
}
