package com.ksn.framework.example.concurrent;


/**
 * synchronized可以同步方法或者代码块;
 * 一个类或一个对象只有一个锁，同步方法获得锁之后，同一个类或对象中的其他同步方法/块会阻塞等待锁的释放，但其他普通方法依然能够访问;
 * 同步代码块能够指定锁资源，如果指定锁资源为this，则效果就类似同步方法了;
 * @author kenshenz
 *
 */

public class TestSynchronizers {
	
	
	
	public static void main(String[] args) {
		Account account = new Account();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程A start");
				account.printName("kkkkkk");
				account.in(100);
				System.out.println("线程A end");
			}
			
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程B start");
				account.printName("ssssss");
				account.out(50);
				System.out.println("线程B end");
			}
			
		});
		
		t1.start();
		t2.start();

	}

}

class Account {
	private double money = 1000;
	private String a = "a";
	
	public synchronized void in(double m) {
		money += m;
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("in之后余额：" + money);
	}
	
	public synchronized void out(double m) {
		money -= m;
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("out之后余额：" + money);
	}
	
	public void printName(String name) {
		synchronized(a) {
			System.out.println("我的名字：" + name);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
