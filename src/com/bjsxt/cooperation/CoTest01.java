package com.bjsxt.cooperation;

/*
 * 协作模型：生产者消费者--管惩罚
 * Thread方法:
 * wait() 导致当前线程等待，直到另一个线程调用对象的notify()方法或notifyAll()方法
 * notify() 唤醒正在等待对象监视器的单个线程
 * notifyAll() 唤醒正在等待对象监视器的所有线程
 *
 */
public class CoTest01 {
	
	public static void main(String[] args) {
		SynContainer container = new SynContainer();
		new Productor(container).start();
		new Consumer(container).start();
	}
}
//生产者
class Productor extends Thread{
	SynContainer container;
	
	public Productor(SynContainer container) {
		super();
		this.container = container;
	}
	@Override
	public void run() {
		//开始生产
		for(int i=0;i<100;i++){
			System.out.println("生产-->"+i+"馒头");
			container.push(new Steamedbun(i));
		}
	}
}
//消费者
class Consumer extends Thread{
	SynContainer container;
	
	public Consumer(SynContainer container) {
		super();
		this.container = container;
	}

	@Override
	public void run() {
		//消费
		for(int i=0;i<100;i++){
			System.out.println("消费-->"+container.pop().id+"馒头");
		}
	}
}
//缓冲区
class SynContainer{
	Steamedbun[] buns = new Steamedbun[10];
	int count;

	//加入synchronized,锁定资源Steamedbun，在存时不能取
	//存
	public synchronized void push(Steamedbun bun){
		//何使能生产
		//无空间,只有等待
		if(count == buns.length){
			try {
				//阻塞当前访问的线程
				this.wait(); //线程阻塞，消费者通知生产者接触阻塞
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//有空间
		buns[count]=bun;
		count++;
		//存在空间可以消费
		this.notifyAll();
	}
	
	//取
	public synchronized Steamedbun pop(){
		//何使能消费
		
		//没有数据
		if(count ==0){
			//每个对象都有一个wait,此时线程阻塞，生产者通知消费解除阻塞
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//存在数据
		count--;
		Steamedbun bun = buns[count];
		//通知生产
		this.notifyAll(); //存在空间，唤醒所有等待者
		return bun;
	}
}
//数据
class Steamedbun {
	int id;

	public Steamedbun(int id) {
		super();
		this.id = id;
	}
	
}
