package sg.com.nets.test.patient.visit.app.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sg.com.nets.test.patient.visit.app.config.ClientRequestAuthenticator;
import sg.com.nets.test.patient.visit.app.entity.Visit;
import sg.com.nets.test.patient.visit.app.exception.PatientVisitException;
import sg.com.nets.test.patient.visit.app.model.Billing;
import sg.com.nets.test.patient.visit.app.model.StringConstant;

/***
 * @author Miranda Aristotle
 **/

@Component
public class BillTranscationCaller {

	@Value("${config.enable-billing}")
	private boolean billEnabled;

	public UUID addBilling(Visit visit) throws PatientVisitException {
		UUID billingId = null;
		if (billEnabled) {
			RestTemplate rt = this.getNewRestTemplate();
			Billing bill = new Billing();
			bill.setBilledDateTime(Calendar.getInstance().getTime());
			bill.setVisitId(visit.getId());

			if (visit.getPatient() != null) {
				bill.setPatientId(visit.getPatient().getId());
			}

			if (visit.getPhysician() != null) {
				bill.setPhysicianId(visit.getPhysician().getId());
			}

			Billing resp = rt.postForObject(StringConstant.BILLING_URI, bill, Billing.class);

			billingId = resp.getId();

		}
		return billingId;
	}

	private RestTemplate getNewRestTemplate() {
		RestTemplate rt = new RestTemplate();

		final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new ClientRequestAuthenticator("user", "password"));
		rt.setInterceptors(interceptors);

		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		rt.getMessageConverters().add(new StringHttpMessageConverter());
		return rt;
	}
}
