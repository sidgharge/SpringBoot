package com.bridgelabz.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.ServiceProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class HomeController {
	
	@Autowired
	private TransportClient client;
	
	@PostMapping("/add")
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
}
