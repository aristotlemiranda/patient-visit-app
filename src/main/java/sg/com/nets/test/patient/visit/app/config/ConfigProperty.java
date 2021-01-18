package sg.com.nets.test.patient.visit.app.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/***
 *@author Miranda Aristotle
 **/
@Configuration
public class ConfigProperty {
	
	private Map<String, String> config = new HashMap<>();

	public Map<String, String> getConfig() {
		return this.config;
	}


	@Bean(name="configMapper")
	@ConfigurationProperties
	public ConfigProperty instantiate() {
		return new ConfigProperty();
	}
}
