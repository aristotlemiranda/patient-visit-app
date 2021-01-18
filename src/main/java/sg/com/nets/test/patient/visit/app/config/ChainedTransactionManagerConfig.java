package sg.com.nets.test.patient.visit.app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;

/***
 *@author Miranda Aristotle
 **/

@Configuration
public class ChainedTransactionManagerConfig {

	@Bean("chainedTractionMgr")
	public PlatformTransactionManager chainedTransactionManager
		(@Qualifier("neo4jTxMgr") Neo4jTransactionManager neo4jTransactionMgr,
				@Qualifier("jpaTxMgr") JpaTransactionManager jpaTransactionMgr) {
				
		ChainedTransactionManager transactionManager = new ChainedTransactionManager(neo4jTransactionMgr, jpaTransactionMgr);
		return transactionManager;
		
	}
	
}
