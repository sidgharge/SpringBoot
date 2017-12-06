package com.bridgelabz.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.bridgelabz.model.Resident;

public class ResidentListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			Resident resident = (Resident) objectMessage.getObject();
			System.out.println("Got resident: " + resident);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
