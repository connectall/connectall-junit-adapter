package com.connectall.techservices.junit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JUnitListener extends RunListener {

	private final JunitAdapter adapter = new JunitAdapter();

	@Override
	public void testRunFinished(Result result) throws Exception {
		List<JSONObject> failureJsons = result.getFailures().stream().map(feature -> formatToJson(feature))
				.collect(Collectors.toList());
		log.info("Completed capturing failures");
		failureJsons.forEach(failure -> adapter.postFailure(failure));
		log.info("Completed pushing failures");
	}

	private JSONObject formatToJson(Failure f) {
		String testHeader = f.getTestHeader();
		String message = f.getMessage();
		String description = f.getDescription().toString();
		Throwable exception = f.getException();
		String trace = f.getTrace();

		JSONObject json = new JSONObject();
		json.put("testHeader", testHeader);
		json.put("message", message);
		json.put("description", description);
		json.put("exception", exception);
		json.put("trace", trace);
		json.put("id", testHeader+"-"+System.currentTimeMillis());
		json.put("modifiedDate", formattedDate());
		return json;
	}

	private String formattedDate() {
		final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";
		final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		return format.format(new Date());
	}

}
