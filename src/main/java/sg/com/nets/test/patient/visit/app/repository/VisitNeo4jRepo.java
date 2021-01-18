package sg.com.nets.test.patient.visit.app.repository;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import sg.com.nets.test.patient.visit.app.entity.Visit;


/**
 * @author Miranda Aristotle Mungcal
 * 
 **/
public interface VisitNeo4jRepo extends Neo4jRepository<Visit, Integer>{

}
