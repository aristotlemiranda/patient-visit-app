package sg.com.nets.test.patient.visit.app.config;

import org.neo4j.driver.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;

/***
 *@author Miranda Aristotle
 **/

@Configuration
public class TransactionManagerConfig {
	
	@Bean("neo4jTxMgr")
	public Neo4jTransactionManager neo4jTransactionMgr(Driver driver) {
		return new Neo4jTransactionManager(driver);
	}
	
	@Bean("jpaTxMgr")
	public JpaTransactionManager jpaTransactionMgr() {
		return new JpaTransactionManager();
	}
	
	@Bean("transactionManager")
	public JpaTransactionManager transactionMgr() {
		return new JpaTransactionManager();
	}
}
