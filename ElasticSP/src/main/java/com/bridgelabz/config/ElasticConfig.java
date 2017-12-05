package com.bridgelabz.config;

import org.elasticsearch.common.settings.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfig {

	/*@Bean
	NodeBuilder nodeBuilder() {
		return new NodeBuilder();
	}
	
	@Bean
	ElasticsearchTemplate elasticsearchTemplate() {
		return new ElasticsearchTemplate(nodeBuilder()
				.settings(Settings.settingsBuilder()
                .put("cluster.name", "elasticsearch")
                .put("path.home", "/usr/share/elasticsearch"))
				.local(true)
				.node()
				.client());
	}*/
}
