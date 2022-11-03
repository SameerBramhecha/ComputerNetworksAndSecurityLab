import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.net.URL;

public class Server {

    private ServerSocket server = null;
    private Socket soc = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    Scanner sc = new Scanner(System.in);

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server Started...");
            System.out.println("waiting for client..");
            soc = server.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(new BufferedInputStream(soc.getInputStream()));

            out = new DataOutputStream(soc.getOutputStream());

            String line = "";

            while (!(line.equals("over"))) {
                try {
                    line = in.readUTF();
                    InetAddress add = InetAddress.getByName(new URL(line).getHost());
                    String a = "IP Address of " + line + " is " + add;
                    out.writeUTF(a);
                } catch (Exception e) {
                    // e.printStackTrace();
                }
                if ((line.equals("over"))) {

                    System.out.println("Closing Connection....");
                    server.close();
                    in.close();
                    out.close();
                }

            }

        } catch (Exception e) {
            // e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server s = new Server(5000);
    }
}