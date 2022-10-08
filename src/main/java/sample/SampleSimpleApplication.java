/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sample.data.jpa.domain.Article;
import sample.data.jpa.service.ArticleDao;
import sample.simple.client.IRun;

@SpringBootApplication
public class SampleSimpleApplication implements CommandLineRunner {

	// Simple example shows how a command line spring application can execute an
	// injected bean service. Also demonstrates how you can use @Value to inject
	// command line args ('--name=whatever') or application properties

	@Autowired
	private IRun irun;
	
	@Autowired
	private ArticleDao articledao;
	
	private static final Logger log = LoggerFactory.getLogger(SampleSimpleApplication.class);

	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleSimpleApplication.class, args);
	}
	
	@Bean
	public KeycloakConfigResolver keycloakConfigResolver() {
	    return new KeycloakSpringBootConfigResolver();
	}

	public void run(String... args) {
		articledao.save(new Article("1A",4,30));
		articledao.save(new Article("1B",8,20));
		articledao.save(new Article("2A",7,50));
		articledao.save(new Article("3E",5,35));
		articledao.save(new Article("4G",3,10));
		this.irun.run();
	}

	
	

}
