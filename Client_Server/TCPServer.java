import java.io.*;
import java.util.*;
import java.net.*;

public class TCPServer {

    public static void main(String[] args)  throws Exception{ 
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server Started");
        System.out.println("Waiting for connection..");

        Socket socket = server.accept();
        System.out.println("client connected");

        DataInputStream readip = new DataInputStream(socket.getInputStream());
        DataOutputStream writeop = new DataOutputStream(socket.getOutputStream());

        Scanner sc = new Scanner(System.in);

        FileWriter fr = new FileWriter("out.txt");

        String lines = readip.readUTF();

        fr.write(lines);
        fr.close();


        server.close();
        socket.close();
        readip.close();
        writeop.close();
        sc.close();
    }
}
