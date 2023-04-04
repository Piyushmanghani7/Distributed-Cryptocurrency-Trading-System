import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import org.apache.commons.io.FileUtils;

public class ManghaniP2Server{

    public static void main(String[] args){
        try{

          
            System.out.println("\nServer is running, Now Please go to client side and start to connect the client with server\n");

            // objects instantiation of class "RMIimplementation"
            RMIimplementation obj1 = new RMIimplementation("Bitcoin", "BTC", 1000, "Bitcoin, often described as a cryptocurrency, a virtual currency or a digital currency - is a type of money that is completely virtual. It's like an online version of cash.", 54, 600.98);
            RMIimplementation obj2 = new RMIimplementation("Ethereum", "ETH", 3000, "Ethereum is a platform powered by blockchain technology that is best known for its native cryptocurrency, called ether, or ETH, or simply ethereum.",8.67, 350.87);
            RMIimplementation obj3 = new RMIimplementation("Litecoin", "LTC", 590 , "Litecoin is a peer-to-peer (P2P) virtual currency, which means it is not governed by a central authority. Litecoin's network offers instant, near-zero cost payments that can be conducted by individuals or institutions across the globe.", 45.34, 700);
            RMIimplementation obj4 = new RMIimplementation("Maidsafecoin", "MAID", 45 , "MaidSafecoin is the temporary cryptocurrency coin used for the alpha and beta versions of the SAFE network, which stands for Secure Access For Everyone.", 45786.35, 132);
            RMIimplementation obj5 = new RMIimplementation("Blackcoin", "BLK", 5.4 , "Blackcoin is a peer-to-peer cryptocurrency. What this means is that the cryptocurrency allows the exchange of data and transaction between parties without the need for a central authority.", 27456.2, 49.87);
            
            //Using those objects for communication with the client using stubs
            RMIinterface stub1 = (RMIinterface) UnicastRemoteObject.exportObject(obj1,0);
            RMIinterface stub2 = (RMIinterface) UnicastRemoteObject.exportObject(obj2,0);
            RMIinterface stub3 = (RMIinterface) UnicastRemoteObject.exportObject(obj3,0);
            RMIinterface stub4 = (RMIinterface) UnicastRemoteObject.exportObject(obj4,0);
            RMIinterface stub5 = (RMIinterface) UnicastRemoteObject.exportObject(obj5,0);
            
            Registry registry = LocateRegistry.createRegistry(1573);

            registry.bind("bit", stub1);
            registry.bind("eth", stub2);
            registry.bind("lit", stub3);
            registry.bind("maid", stub4);
            registry.bind("black", stub5);
        }
        catch(Exception e){
            System.out.println("Server Exception:-" + e);
        }
       
        try{  
            ServerSocket serversock=new ServerSocket(11573);  
            Socket sock=serversock.accept();  
            DataInputStream instream = new DataInputStream(sock.getInputStream());  
            DataOutputStream outstream=new DataOutputStream(sock.getOutputStream()); 
            String  user = (String)instream.readUTF();  
            String pass = (String)instream.readUTF();
            System.out.println("Client Username is : "+ user);  
            System.out.println("Client Password is: "+ pass); 

            File credentials = new File("/Users/piyushmanghani/Desktop/piyush_AOS_proj-2/AOS_Proj2/src/credentials.txt");
            Scanner Scanusercred = new Scanner(credentials);
            
            while(Scanusercred.hasNextLine()){
                String line = Scanusercred.nextLine();
                while (true){
                    String[] Stringarr = line.split(" ");
                    
                    if(user.equals(Stringarr[0]))
                    {
                        if(pass.equals(Stringarr[1]))
                        {
                            
                            outstream.writeUTF("Credentials matches & we validate the client sucessfully login with username: " + user + " and password: " + pass);
                            System.out.println("\nclient is sucessfully login in the system");

                            File Clientpurchaseddata = new File("/Users/piyushmanghani/Desktop/piyush_AOS_proj-2/AOS_Proj2/src/Clients_data.txt");
                            Scanner ScanClientpurchaseddata = new Scanner(Clientpurchaseddata);
                            while(true){
                                String purchaseline = ScanClientpurchaseddata.nextLine();
                                while(true){
                                    
                                    String[] Stringarr2 = purchaseline.split(" ");
                                    if(user.equals(Stringarr2[0])){
                                        outstream.writeUTF("Client has valid record in the system that allows to buy or sell coins");
                                        String Coinsdata = String.join(" ", Stringarr2);
                                        outstream.writeUTF(Coinsdata);
                                        String coin_details = (String)instream.readUTF();
                                        
                                        String Clientpurchaseddata2 = "/Users/piyushmanghani/Desktop/piyush_AOS_proj-2/AOS_Proj2/src/Clients_data.txt";
                                        Scanner ScanClientpurchaseddata2 = new Scanner(new File(Clientpurchaseddata2));
                                        StringBuffer buffer = new StringBuffer();

                                        while (ScanClientpurchaseddata2.hasNextLine()) {
                                            buffer.append(ScanClientpurchaseddata2.nextLine()+System.lineSeparator());
                                        }
                                        String textfile = buffer.toString();
                                        ScanClientpurchaseddata2.close();
                                        String previous_arr = Coinsdata;
                                        String newappend_arr = coin_details;
                                        textfile = textfile.replaceAll(previous_arr, newappend_arr);                                   
                                        FileWriter wr = new FileWriter(Clientpurchaseddata2);  
                                        System.out.println("\n");                                  
                                        System.out.println("After client purchase new data of client is : "+ newappend_arr);
                                        wr.append(textfile);
                                        wr.flush();
                                                
                                    }
                                    purchaseline = ScanClientpurchaseddata.nextLine();
                                    if(purchaseline.equals(" "))
                                    {
                                        break;
                                    }
                                }
                            } 
                        }                            
                    }
                    
                    line = Scanusercred.nextLine();
                    if(line.equals(" "))
                    {
                       
                        break;
                    }
                }     
            } 
            serversock.close(); 
    
        }   
        catch(Exception e){
            System.out.println("Exception on server..."+ e);

        } 
   
    }
}
