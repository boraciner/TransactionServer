package com.transaction.server.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.transaction.server.TransactionServer;
import com.transaction.server.repository.TransactionRepo;
import com.transaction.server.repository.TransactionRepoImpl;

/**
 * The Class TransactionController manages the transaction activites.
 */
public class TransactionController implements HttpHandler {

	/** The transaction. */
	TransactionRepo transaction = new TransactionRepoImpl();

	/**
	 * Handle.
	 *
	 * @param httpExchange the http exchange
	 * @throws IOException Signals that an I/O exception has occurred.
	 * example GET request : http://localhost:8000/transfer?from=user1&to=user2&amount=1000
	 */
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		Map<String, String> parms = queryToMap(httpExchange.getRequestURI().getQuery());
		String accountFrom = parms.get("from");
		String accountTo = parms.get("to");
		double amount = Double.parseDouble(parms.get("amount"));

		boolean result = transaction.transferMoney(accountFrom, accountTo, amount);

		writeResponse(httpExchange, result);
	}

	/**
	 * Write response.
	 *
	 * @param httpExchange the http exchange
	 * @param result the result
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeResponse(HttpExchange httpExchange, boolean result) throws IOException {
		String response = result == true ? "Transferred !" : "There was a problem.";
		httpExchange.sendResponseHeaders(result == true ? 200 : 500, response.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	/**
	 * returns the url parameters in a map.
	 *
	 * @param query the query
	 * @return map
	 */
	public static Map<String, String> queryToMap(String query) {
		Map<String, String> result = new HashMap<String, String>();
		for (String param : query.split("&")) {
			String pair[] = param.split("=");
			if (pair.length > 1) {
				result.put(pair[0], pair[1]);
			} else {
				result.put(pair[0], "");
			}
		}
		return result;
	}
}
