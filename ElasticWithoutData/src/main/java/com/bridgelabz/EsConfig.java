package com.bridgelabz;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

	/*@SuppressWarnings("resource")
	@Bean
	public TransportClient transportClient() {
		TransportClient client = null;
		try {
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

			
			 * ServiceProvider sp = new ServiceProvider(); sp.setName("SP1");
			 * sp.setSP_ID(1);
			 * 
			 * ObjectMapper mapper = new ObjectMapper(); String json =
			 * mapper.writeValueAsString(sp);
			 * 
			 * System.out.println(json); IndexResponse indexResponse =
			 * client.prepareIndex("elasticsp", "sp", "1") .setSource(json,
			 * XContentType.JSON).get(); System.out.println(indexResponse.getIndex());
			 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return client;
	}
	*/
	
	@Bean
	public RestHighLevelClient transportClient() throws UnknownHostException {
		RestHighLevelClient client = null;
			client = new RestHighLevelClient(
			        RestClient.builder(
			                new HttpHost("localhost", 9200, "http")));
			
			CreateIndexRequest createIndexRequest = new CreateIndexRequest("resident");
			createIndexRequest.settings(
				Settings
					.builder()
					.put("index.number_of_shards", 2)
					.put("index.number_of_replicas", 0)
			);
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
			
			
			TransportClient tc;
			tc = new PreBuiltTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
			
			IndicesExistsRequest existsRequest = new IndicesExistsRequest("resident");
			
			if(!tc.admin().indices().exists(existsRequest).actionGet().isExists()){
				CreateIndexResponse resp = tc.admin().indices().create(createIndexRequest).actionGet();
				System.out.println("Index created: " + resp.isAcknowledged());
				tc.close();
			} else {
				System.out.println("Index already exists");
			}
			
			
		return client;
	}
}
