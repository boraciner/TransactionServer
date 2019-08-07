package com.transaction.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;
import com.transaction.server.controller.TransactionController;


/**
 * The Class TransactionServer.
 */
public class TransactionServer {

	/** The server. */
	HttpServer server;
	
	/** The executor. */
	ExecutorService executor;

	/**
	 * Initialize Executor and HttpServer and configure endpoint.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void initialize() throws IOException {
		executor = Executors.newCachedThreadPool();
		server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/transfer", new TransactionController());
		server.setExecutor(executor);
	}
	
	/**
	 * Start.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void start() throws IOException {
		server.start();
		System.out.println("The server is running");
	}
	
	
	/**
	 * Gets the server.
	 *
	 * @return the server
	 */
	public HttpServer getServer() {
		return server;
	}

	/**
	 * Sets the server.
	 *
	 * @param server the new server
	 */
	public void setServer(HttpServer server) {
		this.server = server;
	}

	/**
	 * Gets the executor.
	 *
	 * @return the executor
	 */
	public ExecutorService getExecutor() {
		return executor;
	}

	/**
	 * Sets the executor.
	 *
	 * @param executor the new executor
	 */
	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

}
