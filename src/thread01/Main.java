package thread01;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		Thread04 t = new Thread04();
		t.start();
		if(t.isAlive()){
			System.out.println("»î×Å");
		}
	}
}
