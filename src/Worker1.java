import java.util.concurrent.Semaphore;

public class Worker extends Thread
{
	private Semaphore sem;
	private final int LOOPS = 100;
	
	public Worker(Semaphore sem) { this.sem = sem; }
	
	@Override
	public void run()
	{
		for (int i = 0; i < LOOPS; i++)
		{
			try { sem.acquire(); }
			catch (InterruptedException e) { System.err.println("[ERROR] Failed to acquire semaphore lock: " + e); }
			
			// CRITICAL SECTION SHENANIGANS
			
			System.out.println("Worker: [IN]");
			
			sem.release();
			
			System.out.println("Worker: [OUT]");
		}
	}
}
