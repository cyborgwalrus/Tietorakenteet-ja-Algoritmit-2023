package oy.interact.tira.factories;

import oy.interact.tira.model.Coder;
import oy.interact.tira.student.ArrayQueue;
import oy.interact.tira.student.LinkedListQueue;
import oy.interact.tira.util.QueueInterface;

public class QueueFactory {

	private QueueFactory() {
		// empty
	}

	public static QueueInterface<Integer> createIntegerQueue() {
		return new LinkedListQueue<Integer>();
	}

	public static QueueInterface<Integer> createIntegerQueue(int capacity) {
		return new ArrayQueue<Integer>(capacity);
	}

	public static QueueInterface<String> createStringQueue() {
		return new LinkedListQueue<String>();
	}

	public static QueueInterface<String> createStringQueue(int capacity) {
		return new ArrayQueue<String>(capacity);
	}

	public static QueueInterface<Coder> createCoderQueue() {
		return new LinkedListQueue<Coder>();
	}
}
