package sg.com.nets.test.patient.visit.app.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import sg.com.nets.test.patient.visit.app.entity.Holiday;
import sg.com.nets.test.patient.visit.app.entity.Patient;
import sg.com.nets.test.patient.visit.app.entity.Physician;
import sg.com.nets.test.patient.visit.app.entity.Visit;
import sg.com.nets.test.patient.visit.app.exception.PatientVisitException;
import sg.com.nets.test.patient.visit.app.model.ErrorType;
import sg.com.nets.test.patient.visit.app.model.StringConstant;
import sg.com.nets.test.patient.visit.app.service.PatientVisitService;

/***
 * @author Miranda Aristotle
 **/

@RequestMapping("/api/manage/clinic")
@RestController
@Validated
@Api(value = "Patient Visit Controller")
public class PatientVisitController {

	@Autowired
	private PatientVisitService service;

	@Autowired
	private BillTranscationCaller billManager;

	@ApiOperation(value = "Get Patient Information", response = Patient.class, notes = "Returns a single object Patient.class.")
	@GetMapping("/patient/{id}")
	public Patient findPatientInfo(@ApiParam(value = "This is the Patient's entity id") @PathVariable("id") int id)
			throws PatientVisitException {
		return service.findPatientById(id);
	}

	@ApiOperation(value = "Get Physician Information", response = Physician.class, notes = "Returns a single object Physician.class.")
	@GetMapping("/physician/{id}")
	public Physician findPhysicianInfo(
			@ApiParam(value = "This is the Physician's entity id") @PathVariable("id") int id)
			throws PatientVisitException {
		return service.findPhysicianById(id);
	}

	@ApiOperation(value = "Find Holiday by passing the particular name", response = Holiday.class, notes = "Returns a single object Holiday.class.")
	@GetMapping("/holiday/{name}")
	public Holiday findHoliday(@ApiParam(value = "This is the Holiday's entity name") @PathVariable("name") String name)
			throws PatientVisitException {
		Holiday holiday = service.findHolidayByName(name);
		return holiday;
	}

	@ApiOperation(value = "Find Visit record by passing its entity id", response = Visit.class, notes = "Returns a single object Visit.class.")
	@GetMapping("/visit/{id}")
	public Visit findVisit(@ApiParam(value = "This is the Visit's entity id") @PathVariable("id") int id)
			throws PatientVisitException {
		return service.findVisitById(id);
	}

	@ApiOperation(value = "Add record into the Patient table", response = Visit.class, notes = "Returns the ResponseEntity<HttpStatus, Patient.class>.")
	@PostMapping("/patient")
	public ResponseEntity<?> addPatientInfo(
			@ApiParam(value = "Pass Patient details by providing a JSON formatted request body") @RequestBody Patient patient)
			throws PatientVisitException {
		try {
			checkTodayHoliday();
			Patient obj = this.findPatientInfo(patient.getId());
			this.checkDuplicate(obj, patient);
			service.savePatient(patient);
		} catch (Exception e) {
			throw e;
		}
		System.out.println(ResponseEntity.status(HttpStatus.CREATED).body(patient).getStatusCodeValue());
		return ResponseEntity.status(HttpStatus.CREATED).body(patient);
	}

	@ApiOperation(value = "Add record into the Physician table", response = Visit.class, notes = "Returns the ReponseEntity<HttpStatus, Physician.class>")
	@PostMapping("/physician")
	public ResponseEntity<?> addPhysicianInfo(
			@ApiParam(value = "Pass Physician details by providing a JSON formatted request body") @RequestBody Physician physician)
			throws PatientVisitException {
		try {
			checkTodayHoliday();
			Physician obj = this.findPhysicianInfo(physician.getId());
			this.checkDuplicate(obj, physician);
			service.savePhysician(physician);
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(physician);
	}

	@ApiOperation(value = "Add record into the Visit table", response = Visit.class, notes = "Returns the ResponseEntity<HttpStatus, Visit.class>")
	@PostMapping("/visit")
	public ResponseEntity<?> addVisit(
			@ApiParam(value = "Pass Visit details by providing a JSON formatted request body") @RequestBody Visit visit)
			throws PatientVisitException {
		try {
			checkTodayHoliday();
			Visit obj = this.findVisit(visit.getId());
			this.checkDuplicate(obj, visit);

			if (visit.getBillingId() == null) {
				UUID uuid = billManager.addBilling(visit);
				visit.setBillingId(uuid.toString());
			}

			service.saveVisit(visit);

		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(visit);
	}

	@ApiOperation(value = "Add record into the Holiday table", response = Visit.class, notes = "Returns the ResponseEntity<HttpStatus, msg>")
	@PostMapping("/holiday")
	public ResponseEntity<?> addHoliday(
			@ApiParam(value = "Pass Holiday details by providing a JSON formatted request body") @RequestBody Holiday holiday)
			throws PatientVisitException {
		service.saveHoliday(holiday);
		return ResponseEntity.status(HttpStatus.OK).body(StringConstant.MSG_HOLIDAY_ADDED);
	}

	@ApiOperation(value = "Update Patient record", response = Visit.class, notes = "Returns the persisted class.")
	@PutMapping("/patient")
	public Patient updatePatientInfo(
			@ApiParam(value = "Pass Patient details by providing a JSON formatted request body") @RequestBody Patient patient)
			throws PatientVisitException {
		try {
			checkTodayHoliday();
			return service.savePatient(patient);
		} catch (Exception e) {
			throw e;
		}

	}

	@ApiOperation(value = "Update Physician record", response = Visit.class, notes = "Returns the persisted class.")
	@PutMapping("/physician")
	public Physician updatePhysicianInfo(
			@ApiParam(value = "Pass Physician details by providing a JSON formatted request body") @RequestBody Physician physician)
			throws PatientVisitException {
		try {
			checkTodayHoliday();
			return service.savePhysician(physician);
		} catch (Exception e) {
			throw e;
		}
	}

	@ApiOperation(value = "Update Visit record", response = Visit.class, notes = "Returns the persisted class.")
	@PutMapping("/visit")
	public Visit updateVisitInfo(
			@ApiParam(value = "Pass Visit details by providing a JSON formatted request body") @RequestBody Visit visit)
			throws PatientVisitException {
		try {
			checkTodayHoliday();

			if (visit.getBillingId() == null) {
				UUID uuid = billManager.addBilling(visit);
				visit.setBillingId(uuid.toString());
			}

			return service.saveVisit(visit);
		} catch (Exception e) {
			throw e;
		}

	}

	@ApiOperation(value = "Delete Patient record", response = Visit.class, notes = "Returns a ResponseEntity [HttpStatus, message].")
	@DeleteMapping("/patient")
	public ResponseEntity<?> removePatient(
			@ApiParam(value = "Pass Patient details by providing a JSON formatted request body") @RequestBody Patient patient)
			throws PatientVisitException {
		try {
			this.checkObjectExist(patient);
			service.deletePatient(patient);
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(StringConstant.MSG_PATIENT_DELETED);
	}

	@ApiOperation(value = "Delete Physician record", response = Visit.class, notes = "Returns a ResponseEntity [HttpStatus, message].")
	@DeleteMapping("/physician")
	public ResponseEntity<?> removePhysicianInfo(
			@ApiParam(value = "Pass Physician details by providing a JSON formatted request body") @RequestBody Physician physician)
			throws PatientVisitException {
		try {
			this.checkObjectExist(physician);
			service.deletePhysician(physician);
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(StringConstant.MSG_PHYSICIAN_DELETED);
	}

	@ApiOperation(value = "Delete Visit record", response = Visit.class, notes = "Returns a ResponseEntity [HttpStatus, message].")
	@DeleteMapping("/visit")
	public ResponseEntity<?> removeVisitInfo(
			@ApiParam(value = "Pass Visit details by providing a JSON formatted request body") @RequestBody Visit visit)
			throws PatientVisitException {
		try {
			this.checkObjectExist(visit);
			service.deleteVisit(visit);
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(StringConstant.MSG_VISIT_DELETED);
	}

	/**
	 * Check duplicate record. Must be called before persisting a new record.
	 */
	private void checkDuplicate(Object oldRecord, Object newRecord) throws PatientVisitException {

		if (oldRecord != null) {
			throw new PatientVisitException(ErrorType.DUPLICATE_ID.getCode());
		}
	}

	/**
	 * Intercept operation [add, update, delete], checks for today's Holiday.
	 */
	private void checkTodayHoliday() throws PatientVisitException {
		List<Holiday> holidays = service.findHolidayByHolidayDate();

		System.out.println("Holidays......" + holidays);
		if (holidays != null && !holidays.isEmpty()) {
			throw new PatientVisitException(ErrorType.ACTION_NOT_ALLOWED_DURING_HOLIDAY.getCode());
		}
	}

	/**
	 * Check if object exist
	 */
	private <T> void checkObjectExist(Object entity) {
		checkTodayHoliday();
		Object objToRemove = null;

		if (entity instanceof Patient) {
			objToRemove = findPatientInfo(((Patient) entity).getId());
		} else if (entity instanceof Physician) {
			objToRemove = findPhysicianInfo(((Physician) entity).getId());
		} else if (entity instanceof Holiday) {
			objToRemove = findHoliday(((Holiday) entity).getName());
		} else if (entity instanceof Visit) {
			objToRemove = findVisit(((Visit) entity).getId());
		}

		if (objToRemove == null) {
			throw new PatientVisitException(ErrorType.NO_RECORD_FOUND.getCode());
		}
	}

}
