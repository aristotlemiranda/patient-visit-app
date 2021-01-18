package sg.com.nets.test.patient.visit.app.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sg.com.nets.test.patient.visit.app.config.ConfigProperty;
import sg.com.nets.test.patient.visit.app.model.StringConstant;

/***
 * 
 * DAO Factory
 * Note: only accepts mysql and neo4j
 * Please check application.properties and make sure
 * config.database-type is set to mysql or neo4j 
 * 
 *@author Miranda Aristotle
 **/

@Component("daoFactory")
public class DAOFactory {

	
	@Autowired
	@Qualifier("mysqlJPA")
	private PatientVisitDAO  mysqlDAO;
	
	@Autowired
	@Qualifier("noSQLRepo")
	private PatientVisitDAO neo4jDAO;
		
	@Autowired
	@Qualifier("configMapper")
	private ConfigProperty property;

	public PatientVisitDAO  getDAO() {
		
		PatientVisitDAO dao = null;
		String type = property.getConfig().get("database-type");
		
		switch (type) {
		case StringConstant.DB_MYSQL:
			dao = mysqlDAO;
			break;
		case StringConstant.DB_NEO4J:
			dao = neo4jDAO;
			break;
		default:
			System.out.println(StringConstant.UNKNOWN_DATABASE);
			break;
		}
		
		return dao;
	}
	
	
}
