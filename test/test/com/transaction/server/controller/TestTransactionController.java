package test.com.transaction.server.controller;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import com.transaction.server.TransactionServer;
import static org.hamcrest.Matchers.*;

// TODO: Auto-generated Javadoc
/**
 * The Class TestTransactionController.
 */
@RunWith(Parameterized.class)
public class TestTransactionController {

	/** The transaction server. */
	TransactionServer transactionServer;
	
	/** The address. */
	InetSocketAddress address;
	
	/** The http client. */
	HttpClient httpClient;
	
	/**
	 * Initialize TransactionServer InetSocketAddress and HttpClient.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Before
	public void prepare() throws IOException {
		transactionServer = new TransactionServer();
		transactionServer.initialize();
		transactionServer.start();
		address = transactionServer.getServer().getAddress();
		httpClient = HttpClient.newBuilder()
				.proxy(ProxySelector.of(new InetSocketAddress(address.getHostName(), address.getPort()))).build();
	}

    /**
     * Dataset which contains transfer amount from user 1 to user2
     * with expected result code
     * 
     * @return the iterable
     */
    @Parameters(name = "Try To Transfer Money From User1 To User2")
    public static Iterable<Object []> data()
    {
        return Arrays.asList(new Object[][] { { 1000, 200 },
                                            { 2, 200 },
                                            { 3000, 500 },
                                            { 4000, 500 },
                                            { 5, 200 } });
    }
 
    /** The input. */
    private final int input;
    
    /** The result expected. */
    private final int resultExpected;
    
    
    /**
     * Instantiates a new test transaction controller.
     *
     * @param input the input
     * @param result the result
     */
    public TestTransactionController(final int input, final int result)
    {
        this.input = input;
        this.resultExpected = result;
    }
    
    
	/**
	 * test_transfer_action_from_user1_to_user2.
	 * is a parameterized test which creates http server and makes http requests with
	 * different parameters and tests the result codes.
	 *  
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	@Test
    public void test_transfer_action_from_user1_to_user2() throws IOException, InterruptedException { 

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8000/transfer?from=user1&to=user2&amount="+input)).build();
		HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
		System.out.println("Response status code: " + response.statusCode());
		transactionServer.getServer().stop(0);
		assertThat(response.statusCode(),is(equalTo(resultExpected)));

	}

}
