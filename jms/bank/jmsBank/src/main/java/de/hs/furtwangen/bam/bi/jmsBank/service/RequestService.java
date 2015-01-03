package de.hs.furtwangen.bam.bi.jmsBank.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hs.furtwangen.bam.bi.jmsBank.databaseaccess.RequestRepository;
import de.hs.furtwangen.bam.bi.jmsBank.model.Request;

@Service
@Transactional
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	public Iterable<Request> findAll(){
		return requestRepository.findAll();
	}
	
	public List<Request> getValideRequests() {
		return getValideInvalideRequests(true);
	}

	public List<Request> getInValideRequests() {
		return getValideInvalideRequests(false);
	}

	private List<Request> getValideInvalideRequests(boolean valide) {
		List<Request> requestList = new ArrayList<Request>();

		for (Request request : requestRepository.findAll()) {
			if (valide == request.validMsg) 
			{
				requestList.add(request);
			}
		}
		return requestList;
	}
	
	public Request findOne(String requestId){
		return requestRepository.findOne(requestId);
	}
	
	public void delete(String requestId){
		requestRepository.delete(requestId);
	}
	
	public void save(Request request){
		requestRepository.save(request);
	}

}
