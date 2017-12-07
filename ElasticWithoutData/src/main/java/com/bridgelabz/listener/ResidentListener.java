package com.bridgelabz.listener;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.model.Resident;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResidentListener implements MessageListener {
	
	@Autowired
	RestHighLevelClient client;

	@Override
	public void onMessage(Message message) {
		
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			Resident resident = (Resident) objectMessage.getObject();
			System.out.println("Got resident: " + resident);
			
			
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				String json = mapper.writeValueAsString(resident);
				IndexRequest indexRequest = new IndexRequest("elasticsp2", "resident",
						String.valueOf(resident.getResidentId()));
				indexRequest.source(json, XContentType.JSON);
				IndexResponse indexResponse = client.index(indexRequest);
				System.out.println("Resident added at index: " + indexResponse.getId());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
