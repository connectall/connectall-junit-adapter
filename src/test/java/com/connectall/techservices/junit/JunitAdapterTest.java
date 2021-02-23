package com.connectall.techservices.junit;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class JunitAdapterTest {
	
	private JunitAdapter instance; 
	
	@Before
	public void setup() {
		instance = new JunitAdapter();
	}

	@Test
	public void testPostFailure() throws Exception {
		JSONObject request = testRecord();
		instance.postFailure(request);
	}

	private JSONObject testRecord() {
		JSONObject json = new JSONObject();
		json.put("testHeader", "com.ts.connectall");
		json.put("message", "Error in test method");
		json.put("description", "Description is good");
		json.put("exception", "IllegalStateException");
		json.put("trace", "com.connectall.ts.Test");
		return json;
	}

}
