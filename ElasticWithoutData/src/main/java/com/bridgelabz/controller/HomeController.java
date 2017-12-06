package com.bridgelabz.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.Resident;
import com.bridgelabz.model.ServiceProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class HomeController {
	
	@Autowired
	private TransportClient client;
	
	@PreDestroy
	public void closeClient() {
		System.out.println("Closing client....");
		try {
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/sp")
	public String addSP(@RequestBody ServiceProvider serviceProvider) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(serviceProvider);
			IndexResponse indexResponse = client.prepareIndex("elasticsp", "sp", String.valueOf(serviceProvider.getSpId()))
					.setSource(json, XContentType.JSON).get();
			return indexResponse.getId();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "No!!!";
	}
	
	@PostMapping("/resident")
	public String addResident(@RequestBody Resident resident) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(resident);
			IndexResponse indexResponse = client.prepareIndex("elasticsp2", "resident", String.valueOf(resident.getResidentId()))
					.setSource(json, XContentType.JSON).get();
			return indexResponse.getId();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "No!!!";
	}
	
	@GetMapping("get/{id}")
	public ServiceProvider getSP(@PathVariable("id") int id) {
		GetResponse response = client.prepareGet("elasticsp", "sp", String.valueOf(id)).get();
		ObjectMapper mapper = new ObjectMapper();
		ServiceProvider provider = null;
		try {
			provider = mapper.readValue(response.getSourceAsString(), ServiceProvider.class);
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
		return provider;
	}
	
	@DeleteMapping("delete/{id}")
	public Result deleteSP(@PathVariable("id") int id) {
		DeleteResponse response = client.prepareDelete("elasticsp", "sp", String.valueOf(id)).get();
		return response.getResult();
	}
	
	@GetMapping("search/sp/{spId}/text/{text}")
	public List<Resident> searchResidents(@PathVariable("spId") int spId, @PathVariable("text") String text) {
		BoolQueryBuilder builder = new BoolQueryBuilder();

		builder.must(QueryBuilders.matchQuery("spId", spId));
		builder.should(QueryBuilders.matchQuery("name", text));
		builder.should(QueryBuilders.matchQuery("mob", text));
		builder.should(QueryBuilders.matchQuery("houseInfo", text));
		builder.should(QueryBuilders.matchQuery("nickName", text));
		builder.should(QueryBuilders.matchQuery("altMob", text));
		builder.minimumShouldMatch(1);
		
		
		SearchResponse response = client.prepareSearch("elasticsp2")
				.setTypes("resident")
				.setQuery(builder)
				.execute().actionGet();
		System.out.println("Count: " + response.getHits().totalHits);
		List<Resident> residents = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		response.getHits().forEach(hit -> {try {
			residents.add(mapper.readValue(hit.getSourceAsString(), Resident.class));
		} catch (IOException e) {
			e.printStackTrace();
		}});
		return residents;
	}
}
