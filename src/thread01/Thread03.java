package thread01;

public class Thread03 extends Thread{

	private boolean isStop = true;
	
	@Override
	public void run() {
		int i=100;
		while(isStop){
			System.out.println("----"+i);
			i--;
			MyStop(i);
		}
	}
	
	public void MyStop(int i){
		if(i<5){
			isStop = false;
		}
	}
}
