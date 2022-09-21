import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket soc = null;
    private DataInputStream in = null;
    private DataInputStream inp = null;
    private DataOutputStream out = null;
    Scanner sc = new Scanner(System.in);

    public Client(String addres, int port) {
        try {
            soc = new Socket(addres, port);
            System.out.println("Connected..");

            in = new DataInputStream(System.in);

            inp = new DataInputStream(soc.getInputStream());

            out = new DataOutputStream(soc.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line = "";
        String s = "";
        System.out.println("Enter Message to send: ");
        while (!((line).equals("over"))) {
            try {
                line = sc.nextLine();
                if (line.equals("over")) {
                    out.writeUTF(line);
                    break;
                }
                out.writeUTF(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                s = inp.readUTF();
                System.out.println(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Enter Message to send: ");
        }

        try {
            soc.close();
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Client c = new Client("127.0.0.1", 5000);
        // address is the IP address of the Server
    }
}
