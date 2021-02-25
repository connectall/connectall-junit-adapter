package com.connectall.techservices.junit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JunitAdapter {

	private transient Client client;
	private final JUnitAdapterConfig config;

	private static final String POST_RECORD = "/connectall/api/2/postRecord";

	public JunitAdapter() {
		client = ClientBuilder.newClient();
		config = JUnitAdapterConfig.getInstance();
	}

	public void postFailure(JSONObject result) {
		final String applicationLink = config.getValue(JUnitAdapterConfig.ApplicationLink);
		final String url = config.getValue(JUnitAdapterConfig.BaseUrl) + POST_RECORD+"?appLinkName="+applicationLink;
		final String apiKey = config.getValue(JUnitAdapterConfig.ApiKey);		
		JSONObject request = formatRequest(result);
		WebTarget target = client.target(url);
		Response response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("apikey", apiKey).post(Entity.json(request));
		log.info("Response {}", response.getStatus());
		log.info("Response String :{}",response.readEntity(String.class));
	}

	private JSONObject formatRequest(JSONObject result) {
		final String applicationLink = config.getValue(JUnitAdapterConfig.ApplicationLink);
		JSONObject request = new JSONObject();
		request.put("appLinkName", applicationLink);
		request.put("fields", result);
		return result;
	}

}
