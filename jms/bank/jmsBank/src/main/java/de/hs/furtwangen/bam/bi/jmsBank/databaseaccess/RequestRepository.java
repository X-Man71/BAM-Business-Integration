package de.hs.furtwangen.bam.bi.jmsBank.databaseaccess;

import org.springframework.data.repository.CrudRepository;

import de.hs.furtwangen.bam.bi.jmsBank.model.Request;
/**
 * 
 * Provides Database access.
 * Implementation of this Interfaces is provides by Spring Data.
 * 
 * @author Christian Henle
 */
public interface RequestRepository extends CrudRepository<Request, String> {
	
	public Request findRequestByRequestId(String requestId);

}
