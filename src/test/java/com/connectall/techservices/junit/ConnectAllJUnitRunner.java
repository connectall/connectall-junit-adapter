package com.connectall.techservices.junit;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class ConnectAllJUnitRunner extends BlockJUnit4ClassRunner {

	public ConnectAllJUnitRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
	}

	@Override
	public void run(final RunNotifier notifier) {
		notifier.addListener(new JUnitListener());
		super.run(notifier);
	}
}
