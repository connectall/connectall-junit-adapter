package com.connectall.techservices.junit.cucumber;

import java.io.IOException;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.connectall.techservices.junit.JUnitListener;

import cucumber.api.junit.Cucumber;

public class ConnectAllCucumberRunner extends Cucumber {

	public ConnectAllCucumberRunner(Class clazz) throws InitializationError, IOException {
		super(clazz);
	}
	
	@Override
	public void run(final RunNotifier notifier) {
		notifier.addListener(new JUnitListener());
		super.run(notifier);
	}

}
