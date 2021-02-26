package com.connectall.techservices.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConnectAllJUnitRunner.class)
public class ConnectALLJUnitRunnerTest {
	
	@Test
	public void testSample() {
		assertTrue("This should be true",true);
	}

}
