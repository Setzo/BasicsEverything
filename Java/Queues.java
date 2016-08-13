package queues;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Queues {
	public static void main(String[] args) {
		Queue<Integer> q1 = new ArrayBlockingQueue<Integer>(3);
		
		q1.add(1);
		q1.add(2);
		q1.add(3);
		try {
			q1.add(4);
		} catch (Exception e) {
			System.out.println("Queue size overflow");
		}
		System.out.println();
		for (Integer i : q1) {
			System.out.println(i);
		}
		System.out.println();
	
		int x = q1.remove();
		System.out.println(x);
		System.out.println(q1.remove());
		System.out.println(q1.remove());
		System.out.println();
		try {
			q1.remove();
		} catch (NoSuchElementException e) {
			System.out.println("No such element exception");
		}
		System.out.println();
		System.out.println("//////////////////////////////////////////////////////");
		System.out.println();
		
		/*
		 *		Q2 
		 */
		Queue<Integer> q2 = new ArrayBlockingQueue<Integer>(3);
		
		q2.offer(1);
		q2.offer(2);
		q2.offer(3);
		if (!q2.offer(4)) {
			System.out.println("Can't add element");
		}
		System.out.println();
		for (Integer i : q2) {
			System.out.println(i);
		}
		System.out.println();
		System.out.println(q2.poll());
		System.out.println(q2.poll());
		System.out.println(q2.poll());
		System.out.println(q2.poll());
		
		System.out.println(q2.peek());
		
	}
}
