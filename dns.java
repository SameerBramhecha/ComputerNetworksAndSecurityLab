import java.net.*;
import java.util.*;
import java.io.*;
public class dns{
public static void main(String[] args){
String host;
Scanner ch= new Scanner(System.in);
System.out.print("1.Host Name to IP address \n2. IP address to Host \nChoice=");
int choice = ch.nextInt();
if(choice==1)
{
Scanner input = new Scanner(System.in);
System.out.print("\n Enter host name: ");
host= input.nextLine();
try{
InetAddress address= InetAddress.getByName(host);
System.out.println("IP address:"+address.getHostAddress());
System.out.println("Host name :"+address.getHostName());
System.out.println("Host name and IP address " + address.toString());
}
catch (UnknownHostException ex) { System.out.println("Could not find " + host);
}
}
else{
Scanner input = new Scanner(System.in);
System.out.print("\n Enter IP address: ");
host= input.nextLine();
try{
InetAddress address = InetAddress.getByName(host);
System.out.println("Host name: "+ address.getHostName());
System.out.println("IP address: "+address.getHostAddress());
System.out.println("Host name and IP address: " + address.toString());
}
catch (UnknownHostException ex)
{ System.out.println("Could not find " + host); }
}
}
}
