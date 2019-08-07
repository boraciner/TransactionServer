package com.transaction.server;

/**
 * The Class Main.
 */
public class Main {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		TransactionServer server = new TransactionServer();
		server.initialize();
		server.start();
	}
}
