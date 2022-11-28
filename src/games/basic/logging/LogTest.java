package src.games.basic.logging;

public class LogTest {

	public static void main(String[] args) {
		Logger logger = Logger.getInstance();
		
		logger.log("Message 1");
		
		int sum = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		
		logger.log("Message 2: sum = " + sum);

	}
}
