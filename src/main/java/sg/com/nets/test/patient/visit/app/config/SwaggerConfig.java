package sg.com.nets.test.patient.visit.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sg.com.nets.test.patient.visit.app.model.StringConstant;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/***
 *@author Miranda Aristotle
 **/

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("patientClinicModule")
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(StringConstant.API_INFO_BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build();
	}
	
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title(StringConstant.API_INFO_TITLE)
				.description(StringConstant.API_INFO_DESCRIPTION)
				.licenseUrl(StringConstant.API_INFO_LICENSE_URL)
				.termsOfServiceUrl(StringConstant.API_INFO_TERMS_SERVICE_URL)
				.license(StringConstant.API_INFO_LICENSE)
				.version(StringConstant.API_INFO_VERSION).build();
	}
}
