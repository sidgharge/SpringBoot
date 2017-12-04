package com.bridgelabz;

import java.util.Map;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import com.bridgelabz.model.Book;
import com.bridgelabz.service.BookService;

@SpringBootApplication
public class ElasticApplication implements CommandLineRunner {

	@Autowired
	private ElasticsearchOperations es;

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(ElasticApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		printElasticSearchInfo();

		bookService.save(new Book("1001", "Elasticsearch Basics", "Rambabu Posa"));
		bookService.save(new Book("1002", "Apache Lucene Basics", "Rambabu Posa"));
		bookService.save(new Book("1003", "Apache Solr Basics", "Rambabu Posa"));

		// fuzzey search
		Page<Book> books = bookService.findByAuthor("Rambabu", new PageRequest(0, 10));

		// List<Book> books = bookService.findByTitle("Elasticsearch Basics");

		books.forEach(x -> System.out.println(x));
	}
	
	 private void printElasticSearchInfo() {

	        System.out.println("--ElasticSearch--");
	        Client client = es.getClient();
	        Map<String, String> asMap = client.settings().getAsMap();

	        asMap.forEach((k, v) -> {
	            System.out.println(k + " = " + v);
	        });
	        System.out.println("--ElasticSearch--");
	    }

}
