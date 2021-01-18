package sg.com.nets.test.patient.visit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/***
 *@author Miranda Aristotle
 **/
@SpringBootApplication
@EnableSwagger2
public class PatientVisitApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientVisitApplication.class, args);
	}

}
