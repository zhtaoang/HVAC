import java.io.IOException;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerSocketWrapperTest {

	private Socket client;

	@BeforeClass
	public static void beforeClass() {
		new Thread() {
			public void run() {
				String[] args = new String[]{"65", "75"};
				try {
					HVACMain.main(args);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Before
	public void setUp() throws Exception {
		client = new Socket("localhost", Constants.PORT);
	}

	@After
	public void tearDown() throws Exception {
		client.close();
	}

	@Test
	public void testServerSocketConnection() throws IOException {
		assertTrue(client.isConnected());
	}



}
