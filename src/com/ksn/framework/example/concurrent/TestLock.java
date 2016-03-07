package com.ksn.framework.example.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock提供了更加灵活地同步方式，在一个Lock对象中可以指定多个Condition对象，也就指明了一条线程地阻塞与恢复的条件;
 * 如下BoundedBuffer对象的Lock对象有两个Condition：notFull和notEmpty，提供了两个方法put和take，
 * 当BoundedBuffer的count=0,则需要notEmpty条件的线程将会阻塞，当count=100，则需要notFull条件的线程将会阻塞;
 * @author kenshenz
 *
 */
public class TestLock {

	public static void main(String[] args) {
		/*BoundedBuffer bb = new BoundedBuffer();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					bb.take();
				}
			}
			
		});
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					bb.put(new Object());
				}
			}
			
		});
		
		t1.start();
		t2.start();*/
	}

}

class BoundedBuffer {
	final Lock lock = new ReentrantLock(); 
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();
	
	final Object[] items = new Object[100];
	int ptr, count;
	
	public void put(Object x) {
		lock.lock();
		try {
			while(count == items.length) {
				notFull.await();
			}
			
			items[ptr] = x;
			System.out.println("put succes, ptr = " + ptr);
			ptr++;
			count++;
			notEmpty.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
	
	public Object take() {
		lock.lock();
		try {
			while(count == 0) {
				notEmpty.await();
			}
			Object x = items[--ptr];
			System.out.println("take succes, ptr = " + ptr);
			count--;
			notFull.signal();
			return x;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		return null;
		
	}
	
}
