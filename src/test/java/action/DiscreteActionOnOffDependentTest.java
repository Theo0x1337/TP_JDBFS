package action;

import static org.junit.jupiter.api.Assertions.assertTrue;

import timer.OneShotTimer;

/**
 * @author Beda Igiraneza
 *
 */

class TestObject2 {
	public TestObject2() {
	}

	void methodTestOFF() {
		System.out.println("ON");
	}

	void methodTestON() {
		System.out.println("OFF");
	}
}

class DiscreteActionOnOffDependentTest {

	@org.junit.jupiter.api.Test
	void nextAction() {
		Object object = new TestObject();
		OneShotTimer oneShotTimerON = new OneShotTimer(1);
		OneShotTimer oneShotTimerOFF = new OneShotTimer(5);
		DiscreteActionOnOffDependent discreteActionOnOffDependent = new DiscreteActionOnOffDependent(object,
				"methodTestON", oneShotTimerON, "methodTestOFF", oneShotTimerOFF);
		discreteActionOnOffDependent.nextAction();
		assertTrue(discreteActionOnOffDependent.currentAction == discreteActionOnOffDependent.onAction);
		discreteActionOnOffDependent.nextAction();
		assertTrue(discreteActionOnOffDependent.currentAction == discreteActionOnOffDependent.offAction);

	}

	@org.junit.jupiter.api.Test
	void spendTime() {
		Object object = new TestObject();
		OneShotTimer oneShotTimerON = new OneShotTimer(1);
		OneShotTimer oneShotTimerOFF = new OneShotTimer(5);
		DiscreteActionOnOffDependent discreteActionOnOffDependent = new DiscreteActionOnOffDependent(object,
				"methodTestON", oneShotTimerON, "methodTestOFF", oneShotTimerOFF);
		discreteActionOnOffDependent.spendTime(5);
		assertTrue(discreteActionOnOffDependent.getCurrentLapsTime() == -5);

	}

	@org.junit.jupiter.api.Test
	void compareTo() {
		Object object = new TestObject();
		OneShotTimer oneShotTimerON = new OneShotTimer(1);
		OneShotTimer oneShotTimerOFF = new OneShotTimer(5);
		DiscreteActionOnOffDependent discreteActionOnOffDependent = new DiscreteActionOnOffDependent(object,
				"methodTestON", oneShotTimerON, "methodTestOFF", oneShotTimerOFF);
		discreteActionOnOffDependent.compareTo(discreteActionOnOffDependent);

	}

	@org.junit.jupiter.api.Test
	void next() {
		Object object = new TestObject();
		OneShotTimer oneShotTimerON = new OneShotTimer(1);
		OneShotTimer oneShotTimerOFF = new OneShotTimer(5);
		DiscreteActionOnOffDependent discreteActionOnOffDependent = new DiscreteActionOnOffDependent(object,
				"methodTestON", oneShotTimerON, "methodTestOFF", oneShotTimerOFF);
		discreteActionOnOffDependent.next();
	}

	@org.junit.jupiter.api.Test
	void hasNext() {
		Object object = new TestObject();
		OneShotTimer oneShotTimerON = new OneShotTimer(1);
		OneShotTimer oneShotTimerOFF = new OneShotTimer(5);
		DiscreteActionOnOffDependent discreteActionOnOffDependent = new DiscreteActionOnOffDependent(object,
				"methodTestON", oneShotTimerON, "methodTestOFF", oneShotTimerOFF);
		discreteActionOnOffDependent.hasNext();
	}
}