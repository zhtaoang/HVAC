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
				String[] args = new String[]{};
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
		client = new Socket("localhost", 9999);
	}

	@After
	public void tearDown() throws Exception {
		client.close();
	}

	@Test
	public void testServerSocketConnection() throws IOException {
		assertTrue(client.isConnected());
	}

	@Test
	public void testReadFromAndWriteToServerSocket() throws IOException, InterruptedException {
		CommonUtils.writeToSocket(client, "testWriteToServerSocket");
		assertEquals("testWriteToServerSocket", CommonUtils.readFromSocket(client));
	}


}
