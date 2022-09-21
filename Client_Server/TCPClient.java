import java.io.*;
import java.util.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception{
        Scanner sc =new Scanner(System.in);
        System.out.println("Connecting to Server");

        Socket socket = new Socket("127.0.0.1",5000);
        System.out.println("Connection established to Server");

        DataInputStream readip = new DataInputStream(socket.getInputStream());
        DataOutputStream writeop = new DataOutputStream(socket.getOutputStream());

        FileReader fr = new FileReader("input.txt");

        BufferedReader br = new BufferedReader(fr);

        String transfer = "";
        try{
            String line = br.readLine();
            while(line!=null){
                transfer = transfer + line + "\n";
                line = br.readLine();
            }
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        writeop.writeUTF(transfer);
        System.out.println("Transfer Done..\nClosing Connection");


        sc.close();
        readip.close();
        writeop.close();
        socket.close();
    }    
}
