import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GServer {
    public static void main(String[] args) throws IOException {
        System.out.println("-----SERVER-----");
        System.out.println("Waiting for Connection");
        try {
            InetAddress addr = InetAddress.getByName("localhost");
        } catch (Exception e) {
            System.out.println(e);
        }
        ServerSocket ss = new ServerSocket(500);

        Socket client = new Socket();
        client = ss.accept();

        BufferedInputStream in = new BufferedInputStream(client.getInputStream());
        DataOutputStream out = new DataOutputStream(client.getOutputStream());

        System.out.println("Received request for sending frames");
        int p = in.read();

        boolean[] f = new boolean[p];

        int pc = in.read();
        System.out.println("Sending...");

        if (pc == 0) {
            for (int i = 0; i < p; ++i) {
                System.out.println("Sending frame number " + i);
                out.write(i);
                out.flush();
                System.out.println("Waiting for acknowledgement");
                try {
                    Thread.sleep(7000);
                } catch (Exception e) {
                }
                int a = in.read();
                System.out.println("Received acknowledgement for frame " + i + " as" + a);
            }
            out.flush();
        } else {
            for (int i = 0; i < p; ++i) {
                if (i == 2) {
                    System.out.println("Sending frame no: " + i);

                } else {
                    System.out.println("Sending frame no: " + i);
                    out.write(i);
                    out.flush();
                    System.out.println("Waiting for acknowledgement ");
                    try {
                        Thread.sleep(7000);
                    } catch (Exception e) {
                    }
                    int a = in.read();
                    if (a != 255) {
                        System.out.println("Received ack for frame no: " + i + " as" + a);
                        f[i] = true;
                    }
                }
            }

            for (int a = 0; a < p; ++a) {
                if (f[a] == false) {
                    System.out.println("Resending frame " + a);
                    out.write(a);
                    out.flush();
                    System.out.println("Waiting for ack");
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                    }
                    int b = in.read();
                    System.out.println("Received ack for frame no: " + a + " as" + b);
                    f[a] = true;
                }
            }
            out.flush();
        }
        in.close();
        out.close();
        System.out.println("Quitting");
    }
}
