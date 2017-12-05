package com.bridgelabz.service;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.model.Resident;

@Service
public class SearchService {

/*	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	public List<Resident> getAll(String text){
		
		QueryBuilder query = QueryBuilders.boolQuery()
				.should(
						QueryBuilders.queryStringQuery(text)
						.lenient(true)
						.field("name")
						.field("nickName")
				).should(
						QueryBuilders.queryStringQuery("*"+text+"*")
						.lenient(true)
						.field("name")
						.field("nickName")
				);
		
		NativeSearchQuery build = new NativeSearchQueryBuilder()
				.withQuery(query)
				.build();
		
		List<Resident> residents = elasticsearchTemplate.queryForList(build, Resident.class);
		return residents;
			
	}*/	
}
