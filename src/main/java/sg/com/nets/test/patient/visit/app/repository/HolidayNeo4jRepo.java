package sg.com.nets.test.patient.visit.app.repository;




import org.springframework.data.neo4j.repository.Neo4jRepository;
import sg.com.nets.test.patient.visit.app.entity.Holiday;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

public interface HolidayNeo4jRepo extends Neo4jRepository<Holiday, String>{
	
	public Holiday findHolidayByName(String name);
}
