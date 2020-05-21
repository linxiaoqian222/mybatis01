package com.bjsxt.cooperation;

/*
 * Э��ģ�ͣ�������������--�ܳͷ�
 * Thread����:
 * wait() ���µ�ǰ�̵߳ȴ���ֱ����һ���̵߳��ö����notify()������notifyAll()����
 * notify() �������ڵȴ�����������ĵ����߳�
 * notifyAll() �������ڵȴ�����������������߳�
 *
 */
public class CoTest01 {
	
	public static void main(String[] args) {
		SynContainer container = new SynContainer();
		new Productor(container).start();
		new Consumer(container).start();
	}
}
//������
class Productor extends Thread{
	SynContainer container;
	
	public Productor(SynContainer container) {
		super();
		this.container = container;
	}
	@Override
	public void run() {
		//��ʼ����
		for(int i=0;i<100;i++){
			System.out.println("����-->"+i+"��ͷ");
			container.push(new Steamedbun(i));
		}
	}
}
//������
class Consumer extends Thread{
	SynContainer container;
	
	public Consumer(SynContainer container) {
		super();
		this.container = container;
	}

	@Override
	public void run() {
		//����
		for(int i=0;i<100;i++){
			System.out.println("����-->"+container.pop().id+"��ͷ");
		}
	}
}
//������
class SynContainer{
	Steamedbun[] buns = new Steamedbun[10];
	int count;

	//����synchronized,������ԴSteamedbun���ڴ�ʱ����ȡ
	//��
	public synchronized void push(Steamedbun bun){
		//��ʹ������
		//�޿ռ�,ֻ�еȴ�
		if(count == buns.length){
			try {
				//������ǰ���ʵ��߳�
				this.wait(); //�߳�������������֪ͨ�����߽Ӵ�����
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//�пռ�
		buns[count]=bun;
		count++;
		//���ڿռ��������
		this.notifyAll();
	}
	
	//ȡ
	public synchronized Steamedbun pop(){
		//��ʹ������
		
		//û������
		if(count ==0){
			//ÿ��������һ��wait,��ʱ�߳�������������֪ͨ���ѽ������
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//��������
		count--;
		Steamedbun bun = buns[count];
		//֪ͨ����
		this.notifyAll(); //���ڿռ䣬�������еȴ���
		return bun;
	}
}
//����
class Steamedbun {
	int id;

	public Steamedbun(int id) {
		super();
		this.id = id;
	}
	
}
