package thread01;

public class Thread01 extends Thread{
	
	//run�������߳����
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			System.out.println("pp");
		}
		
	}
}
//ģ�����˶�ͬһ��Ʊ��������
class Thread02 implements Runnable{
	
	private int ticketNums = 99;
	
	@Override
	public void run() {
		while(true){
			if(ticketNums<0){
				break;
			}
			System.out.println(ticketNums);
		}
		
	}
}