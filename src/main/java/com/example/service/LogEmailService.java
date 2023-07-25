package com.example.service;

import com.example.request.CreateLogEmailRequest;
import com.example.response.LogEmailResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LogEmailService {
	
	@Autowired
	GraphQLWebClient graphQLWebClient;

	private final Logger LOG = LoggerFactory.getLogger(getClass());


	public LogEmailResponse createLogEmail(
			CreateLogEmailRequest createLogEmailRequest) {
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("fullname", createLogEmailRequest.getFullName());
		variables.put("email", createLogEmailRequest.getEmail());
		String mutationStr = "mutation CreateLogEmail($fullname: String,$email: String)  {\r\n"
				+ "  createLogEmail (createLogEmailRequest :  {\r\n"
				+ "    fullName:$fullname \r\n"
				+ "    email:$email \r\n"
				+ "  } ) { \r\n"
				+ "    id \r\n"
				+ "    fullName \r\n"
				+ "    email \n"
				+ "  }"
				+ "}";
		
		GraphQLRequest request = GraphQLRequest.builder()
				.query(mutationStr).variables(variables).build();
		
		GraphQLResponse graphQLResponse = graphQLWebClient.post(request).block();
		LOG.info("Send email");
		return graphQLResponse.get("createLogEmail", LogEmailResponse.class);
	}
}
