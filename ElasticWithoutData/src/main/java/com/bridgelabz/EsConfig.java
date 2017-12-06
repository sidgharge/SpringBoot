package com.bridgelabz;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PreDestroy;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EsConfig {

	@Bean
	public TransportClient transportClient() {
		TransportClient client = null;
		try {
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
			
			/*ServiceProvider sp = new ServiceProvider();
			sp.setName("SP1");
			sp.setSP_ID(1);
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(sp);
			
			System.out.println(json);
			IndexResponse indexResponse = client.prepareIndex("elasticsp", "sp", "1")
					.setSource(json, XContentType.JSON).get();
			System.out.println(indexResponse.getIndex());*/
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	
}
