import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.Socket;
import org.apache.commons.io.FileUtils;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ManghaniP2Client{

    public static void main(String[] args){

        Scanner scansys = new Scanner(System.in);
        try{
            // get registry from server to client
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1573);

            //lookup functions
            RMIinterface bitcoin = (RMIinterface) registry.lookup("bit");
            RMIinterface ethereum = (RMIinterface) registry.lookup("eth");
            RMIinterface litecoin = (RMIinterface) registry.lookup("lit");
            RMIinterface maidsafecoin = (RMIinterface) registry.lookup("maid");
            RMIinterface blackcoin = (RMIinterface) registry.lookup("black");
           
            try{      
                Socket sock =new Socket("localhost",11573); 
                DataInputStream instream = new DataInputStream(sock.getInputStream());   
                DataOutputStream outstream=new DataOutputStream(sock.getOutputStream());  
                
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Please Enter your Username: ");
                String read_username = br.readLine();
                System.out.println("Please Enter your Password: ");
                String read_password = br.readLine();
                outstream.writeUTF(read_username);
                outstream.writeUTF(read_password);  
                String  inp1 = (String)instream.readUTF();  
                System.out.println("\n"+inp1);

                String  inp2 = (String)instream.readUTF();  
                System.out.println("\n"+inp2);


                
                String coin_data = (String)instream.readUTF();
                String[] coindata_arr = coin_data.split(" ");
                System.out.println("\n Your Present Coin's record in the system is: \n"+ coin_data);



                System.out.println("\n");
                System.out.println("Now you choose amongs all these following coins with their respective parameters:\n");
    
                System.out.println("Bitcoin's in the System is: \n");
                System.out.println("Coin-name: " + bitcoin.getCoinName());
                System.out.println("Coin-Abv name: " + bitcoin.getCoinAbbrevName());
                System.out.println("Coin-price: " + "$" + bitcoin.getCoinPrice());
                System.out.println("Coin-description: " + bitcoin.getCoinDescrp());
                System.out.println("Coin-trading volume: " + bitcoin.getCoinTradingvolume() + "B");
                System.out.println("Coin-marketcap: " + bitcoin.getCoinMarketcap() + "B");
                
                System.out.println("\n");
                System.out.println("Etherum's in the System is: \n");
                System.out.println("Coin-name: " + ethereum.getCoinName());
                System.out.println("Coin-Abv name: " + ethereum.getCoinAbbrevName());
                System.out.println("Coin-price: " + "$" + ethereum.getCoinPrice());
                System.out.println("Coin-description: " + ethereum.getCoinDescrp());
                System.out.println("Coin-trading volume: " + ethereum.getCoinTradingvolume() + "B");
                System.out.println("Coin-marketcap: " + ethereum.getCoinMarketcap() + "B");
                
                System.out.println("\n");
                System.out.println("Litecoin's in the System is: \n");
                System.out.println("Coin-name: " + litecoin.getCoinName());
                System.out.println("Coin-Abv name: " + litecoin.getCoinAbbrevName());
                System.out.println("Coin-price: " + "$" + litecoin.getCoinPrice());
                System.out.println("Coin-description: " + litecoin.getCoinDescrp());
                System.out.println("Coin-trading volume: " + litecoin.getCoinTradingvolume() + "M");
                System.out.println("Coin-marketcap: " + litecoin.getCoinMarketcap() + "B");
                
                System.out.println("\n");
                System.out.println("Maidsafecoin's in the System is: \n");
                System.out.println("Coin-name: " + maidsafecoin.getCoinName());
                System.out.println("Coin-Abv name: " + maidsafecoin.getCoinAbbrevName());
                System.out.println("Coin-price: " + "$" + maidsafecoin.getCoinPrice());
                System.out.println("Coin-description: " + maidsafecoin.getCoinDescrp());
                System.out.println("Coin-trading volume: " + maidsafecoin.getCoinTradingvolume());
                System.out.println("Coin-marketcap: " + maidsafecoin.getCoinMarketcap() + "M");
            
                System.out.println("\n");
                System.out.println("Blackcoin's in the System is: \n");
                System.out.println("Coin-name: " + blackcoin.getCoinName());
                System.out.println("Coin-Abv name: " + blackcoin.getCoinAbbrevName());
                System.out.println("Coin-price: " + "$" + blackcoin.getCoinPrice());
                System.out.println("Coin-description: " + blackcoin.getCoinDescrp());
                System.out.println("Coin-trading volume: " + blackcoin.getCoinTradingvolume());
                System.out.println("Coin-marketcap: " + blackcoin.getCoinMarketcap() + "M");
                
                System.out.println("\nType: BUY - for Buying the coins & Type: SELL - for selling the coins");
                String  Client_decision = br.readLine();
               
                if(Client_decision.equals("BUY"))
                {
                    System.out.println("You choosed to buying the coins & the list of coins available is : Type: bitcoin - for Bitcoin\n Type: etherum -  for Ethereum\n Type: litecoin - for Litecoin\n Type: maidsafecoin - for Maidsafecoin\n Type: blackcoin - for Blackcoin");
                    String buy_Coin = br.readLine();
                    
                    if(buy_Coin.equals("bitcoin") )
                    {
                        System.out.println("How many bitcoins want to buy: ");
                        int buy_quant = scansys.nextInt();
                        String buy_quant_str = String.valueOf(buy_quant);
                        for(int i = 0; i < coindata_arr.length; i++)
                        {
                            String value = coindata_arr[i];
                            if(value.equals("bit"))
                            {
                                double value_arr = Double.parseDouble(coindata_arr[2]);
                                value_arr = value_arr - (buy_quant*bitcoin.getCoinPrice());
                                System.out.println(value_arr);
                                String valuearr_str = String.valueOf(value_arr);
                                coindata_arr[2] = valuearr_str;

                                int value2_arr = Integer.parseInt(coindata_arr[i+1]);
                                value2_arr = value2_arr + buy_quant;
                                System.out.println(value2_arr);
                                String value2arr_str = String.valueOf(value2_arr);
                                coindata_arr[i+1] = value2arr_str;

                                LocalDateTime timezone = LocalDateTime.now();
                                String timezone_str = String.valueOf(timezone);
                                coindata_arr[i+2] = timezone_str;

                                break;

                            } 
                            if(i == (coindata_arr.length-1) && value != "bit")
                            {
                                String[] append_arr = Arrays.copyOf(coindata_arr, coindata_arr.length + 4);
                                append_arr[coindata_arr.length] = "bit";
                                append_arr[coindata_arr.length + 1] = buy_quant_str;
                                append_arr[coindata_arr.length + 2] = String.valueOf(bitcoin.getCoinPrice());

                                LocalDateTime timezone = LocalDateTime.now();
                                String timezone_str = String.valueOf(timezone);
                                append_arr[coindata_arr.length + 3] = timezone_str;

                                double array_newprice = Double.parseDouble(append_arr[2]);
                                array_newprice = array_newprice - (buy_quant*bitcoin.getCoinPrice());
                                String arrprice_str = String.valueOf(array_newprice);
                                append_arr[2] = arrprice_str;

                                String new_arr = String.join(" ",append_arr);
                                System.out.println(new_arr);
                                outstream.writeUTF(new_arr);
                                coindata_arr[i+1] = "bit";
                                System.out.println(coindata_arr);
                            }
                        }

                        String client_str = String.join(" ",coindata_arr);
                        System.out.println(client_str);
                        outstream.writeUTF(client_str);
                    }

                    else if(buy_Coin.equals("etherum"))
                    {
                        System.out.println("How many Ethereum want to buy: ");
                        int buy_quant = scansys.nextInt();
                        String buy_quant_str = String.valueOf(buy_quant);
                        for(int i = 0; i < coindata_arr.length; i++)
                        {
                            String value = coindata_arr[i];
                            if(value.equals("eth"))
                            {
                                double value_arr = Double.parseDouble(coindata_arr[2]);
                                value_arr = value_arr - (buy_quant*ethereum.getCoinPrice());
                                System.out.println(value_arr);
                                String value_arr_str = String.valueOf(value_arr);
                                coindata_arr[2] = value_arr_str;

                                int value2_arr = Integer.parseInt(coindata_arr[i+1]);
                                value2_arr = value2_arr + buy_quant;
                                System.out.println(value2_arr);
                                String value2_arr_str = String.valueOf(value2_arr);
                                coindata_arr[i+1] = value2_arr_str;

                                LocalDateTime timezone = LocalDateTime.now();
                                String timezone_str = String.valueOf(timezone);
                                coindata_arr[i+2] = timezone_str;

                                break;

                            } 

                            if(i == (coindata_arr.length-1) && value != "eth")
                            {
                                String[] append_arr = Arrays.copyOf(coindata_arr, coindata_arr.length + 4);
                                append_arr[coindata_arr.length] = "eth";
                                append_arr[coindata_arr.length + 1] = buy_quant_str;
                                append_arr[coindata_arr.length + 2] = String.valueOf(ethereum.getCoinPrice());
                                
                                LocalDateTime timezone = LocalDateTime.now();
                                String timezone_str = String.valueOf(timezone);
                                append_arr[coindata_arr.length + 3] = timezone_str;


                                double array_newprice = Double.parseDouble(append_arr[2]);
                                array_newprice = array_newprice - (buy_quant*ethereum.getCoinPrice());
                                String arrprice_str = String.valueOf(array_newprice);
                                append_arr[2] = arrprice_str;

                                
                                String new_arr = String.join(" ",append_arr);
                                System.out.println(new_arr);
                                outstream.writeUTF(new_arr);
                                coindata_arr[i+1] = "eth";
                                System.out.println(coindata_arr);
                            }
                        }
                        String client_str = String.join(" ",coindata_arr);
                        System.out.println(client_str);
                        outstream.writeUTF(client_str);
                    }

                    else if(buy_Coin.equals("litecoin") )
                    {
                        System.out.println("How many Litecoin want to buy: ");
                        int buy_quant = scansys.nextInt();
                        String buy_quant_str = String.valueOf(buy_quant);
                        for(int i = 0; i < coindata_arr.length; i++)
                        {
                            String value = coindata_arr[i];
                            if(value.equals("lit"))
                            {
                                double value_arr = Double.parseDouble(coindata_arr[2]);
                                value_arr = value_arr - (buy_quant*litecoin.getCoinPrice());
                                System.out.println(value_arr);
                                String value_arr_str = String.valueOf(value_arr);
                                coindata_arr[2] = value_arr_str;

                                int value2_arr = Integer.parseInt(coindata_arr[i+1]);
                                value2_arr = value2_arr + buy_quant;
                                System.out.println(value2_arr);
                                String value2_arr_str = String.valueOf(value2_arr);
                                coindata_arr[i+1] = value2_arr_str;

                                LocalDateTime timezone = LocalDateTime.now();
                                String timezone_str = String.valueOf(timezone);
                                coindata_arr[i+2] = timezone_str;

                                break;

                            } 

                            if(i == (coindata_arr.length-1) && value != "lit")
                            {
                                String[] append_arr = Arrays.copyOf(coindata_arr, coindata_arr.length + 3);
                                append_arr[coindata_arr.length] = "lit";
                                append_arr[coindata_arr.length + 1] = buy_quant_str;
                                append_arr[coindata_arr.length + 2] = String.valueOf(litecoin.getCoinPrice());
                                
                                LocalDateTime timezone = LocalDateTime.now();
                                String timezone_str = String.valueOf(timezone);
                                append_arr[coindata_arr.length + 3] = timezone_str;

                                double array_newprice = Double.parseDouble(append_arr[2]);
                                array_newprice = array_newprice - (buy_quant*litecoin.getCoinPrice());
                                String array_newprice_str = String.valueOf(array_newprice);
                                append_arr[2] = array_newprice_str;


                                String new_arr = String.join(" ",append_arr);
                                System.out.println(new_arr);
                                outstream.writeUTF(new_arr);
                                coindata_arr[i+1] = "lit";
                                System.out.println(coindata_arr);
                            }
                        }
                        String client_str = String.join(" ",coindata_arr);
                        System.out.println(client_str);
                        outstream.writeUTF(client_str);
                    }

                    else if(buy_Coin.equals("maidsafecoin"))
                    {
                        System.out.println("How many Maidsafecoin want to buy: ");
                        int buy_quant = scansys.nextInt();
                        String buy_quant_str = String.valueOf(buy_quant);
                        for(int i = 0; i < coindata_arr.length; i++)
                        {
                            String value = coindata_arr[i];
                            if(value.equals("maid"))
                            {
                                double value_arr = Double.parseDouble(coindata_arr[2]);
                                value_arr = value_arr - (buy_quant*maidsafecoin.getCoinPrice());
                                System.out.println(value_arr);
                                String value_arr_str = String.valueOf(value_arr);
                                coindata_arr[2] = value_arr_str;

                                int value2_arr = Integer.parseInt(coindata_arr[i+1]);
                                value2_arr = value2_arr + buy_quant;
                                System.out.println(value2_arr);
                                String value2_arr_str = String.valueOf(value2_arr);
                                coindata_arr[i+1] = value2_arr_str;

                                LocalDateTime timezone = LocalDateTime.now();
                                String timezone_str = String.valueOf(timezone);
                                coindata_arr[i+2] = timezone_str;

                                break;

                            } 

                            if(i == (coindata_arr.length-1) && value != "maid")
                            {
                                String[] append_arr = Arrays.copyOf(coindata_arr, coindata_arr.length + 3);
                                append_arr[coindata_arr.length] = "maid";
                                append_arr[coindata_arr.length + 1] = buy_quant_str;
                                append_arr[coindata_arr.length + 2] = String.valueOf(maidsafecoin.getCoinPrice());
                                

                                LocalDateTime timezone = LocalDateTime.now();
                                String timezone_str = String.valueOf(timezone);
                                append_arr[coindata_arr.length + 3] = timezone_str;
                                
                                double array_newprice = Double.parseDouble(append_arr[2]);
                                array_newprice = array_newprice - (buy_quant*maidsafecoin.getCoinPrice());
                                String array_newprice_str = String.valueOf(array_newprice);
                                append_arr[2] = array_newprice_str;


                                String new_arr = String.join(" ",append_arr);
                                System.out.println(new_arr);
                                outstream.writeUTF(new_arr);
                                coindata_arr[i+1] = "maid";
                                System.out.println(coindata_arr);
                            }
                        }
                        String client_str = String.join(" ",coindata_arr);
                        System.out.println(client_str);
                        outstream.writeUTF(client_str);
                    }

                    else if(buy_Coin.equals("blackcoin"))
                    {
                        System.out.println("How many Blackcoin want to buy: ");
                        int buy_quant = scansys.nextInt();
                        String buy_quant_str = String.valueOf(buy_quant);
                        for(int i = 0; i < coindata_arr.length; i++)
                        {
                            String value = coindata_arr[i];
                            if(value.equals("black"))
                            {
                                double value_arr = Double.parseDouble(coindata_arr[2]);
                                value_arr = value_arr - (buy_quant*blackcoin.getCoinPrice());
                                System.out.println(value_arr);
                                String value_arr_str = String.valueOf(value_arr);
                                coindata_arr[2] = value_arr_str;

                                int value2_arr = Integer.parseInt(coindata_arr[i+1]);
                                value2_arr = value2_arr + buy_quant;
                                System.out.println(value2_arr);
                                String value2_arr_str = String.valueOf(value2_arr);
                                coindata_arr[i+1] = value2_arr_str;

                                LocalDateTime timezone = LocalDateTime.now();
                                String timezone_str = String.valueOf(timezone);
                                coindata_arr[i+2] = timezone_str;

                                break;

                            } 

                            if(i == (coindata_arr.length-1) && value != "black")
                            {
                                String[] append_arr = Arrays.copyOf(coindata_arr, coindata_arr.length + 3);
                                append_arr[coindata_arr.length] = "black";
                                append_arr[coindata_arr.length + 1] = buy_quant_str;
                                append_arr[coindata_arr.length + 2] = String.valueOf(blackcoin.getCoinPrice());
                                
                                LocalDateTime timezone = LocalDateTime.now();
                                String timezone_str = String.valueOf(timezone);
                                append_arr[coindata_arr.length + 3] = timezone_str;

                                double array_newprice = Double.parseDouble(append_arr[2]);
                                array_newprice = array_newprice - (buy_quant*blackcoin.getCoinPrice());
                                String array_newprice_str = String.valueOf(array_newprice);
                                append_arr[2] = array_newprice_str;


                                String new_arr = String.join(" ",append_arr);
                                System.out.println(new_arr);
                                outstream.writeUTF(new_arr);
                                coindata_arr[i+1] = "black";
                                System.out.println(coindata_arr);
                            }
                        }
                        String client_str = String.join(" ",coindata_arr);
                        System.out.println(client_str);
                        outstream.writeUTF(client_str);
                    }

                }

                else if(Client_decision.equals("SELL"))
                {
                    
                    System.out.println("You choosed to sell the coins & the list of coins available is : Type: bitcoin - for Bitcoin\n Type: etherum -  for Ethereum\n Type: litecoin - for Litecoin\n Type: maidsafecoin - for Maidsafecoin\n Type: blackcoin - for Blackcoin");
                    String sell_choice = br.readLine();
                    

                    if(sell_choice.equals("bitcoin"))
                    {
                        System.out.println("How many bitcoin want to sell: ");
                        int sell_quantity = scansys.nextInt();
                        for(int i = 0; i < coindata_arr.length; i++)
                        {
                            String value = coindata_arr[i];
                            if(value.equals("bit"))
                            {
                                double value_arr = Double.parseDouble(coindata_arr[2]);
                                value_arr = value_arr + (value_arr*bitcoin.getCoinPrice());
                                System.out.println(value_arr);
                                String value_arr_str = String.valueOf(value_arr);
                                coindata_arr[2] = value_arr_str;

                                int value2_arr = Integer.parseInt(coindata_arr[i+1]);
                                value2_arr = value2_arr - sell_quantity;
                                System.out.println(value2_arr);
                                String value2_arr_str = String.valueOf(value2_arr);
                                coindata_arr[i+1] = value2_arr_str;

                            } 
                        }
                        String client_str = String.join(" ",coindata_arr);
                        System.out.println(client_str);
                        outstream.writeUTF(client_str);
                    }

                    else if(sell_choice.equals("etherum"))
                    {
                        System.out.println("How many etherum want to sell: ");
                        int sell_quantity = scansys.nextInt();                      
                        for(int i = 0; i < coindata_arr.length; i++)
                        {
                            String value = coindata_arr[i];
                            if(value.equals("eth"))
                            {
                                double value_arr = Double.parseDouble(coindata_arr[2]);
                                value_arr = value_arr + (sell_quantity*ethereum.getCoinPrice());
                                System.out.println(value_arr);
                                String value_arr_str = String.valueOf(value_arr);
                                coindata_arr[2] = value_arr_str;

                                int value2_arr = Integer.parseInt(coindata_arr[i+1]);
                                value2_arr = value2_arr - sell_quantity;
                                System.out.println(value2_arr);
                                String value2_arr_str = String.valueOf(value2_arr);
                                coindata_arr[i+1] = value2_arr_str;

                            } 
                        }
                        String client_str = String.join(" ",coindata_arr);
                        System.out.println(client_str);
                        outstream.writeUTF(client_str);
                    }

                    else if(sell_choice.equals("litecoin"))
                    {
                        System.out.println("How many litecoin want to sell: ");
                        int sell_quantity = scansys.nextInt();
                        for(int i = 0; i < coindata_arr.length; i++)
                        {
                            String value = coindata_arr[i];
                            if(value.equals("lit"))
                            {
                                double value_arr = Double.parseDouble(coindata_arr[2]);
                                value_arr = value_arr + (sell_quantity*litecoin.getCoinPrice());
                                System.out.println(value_arr);
                                String value_arr_str = String.valueOf(value_arr);
                                coindata_arr[2] = value_arr_str;

                                int value2_arr = Integer.parseInt(coindata_arr[i+1]);
                                value2_arr = value2_arr - sell_quantity;
                                System.out.println(value2_arr);
                                String value2_arr_str = String.valueOf(value2_arr);
                                coindata_arr[i+1] = value2_arr_str;

                            } 
                        }
                        String client_str = String.join(" ",coindata_arr);
                        System.out.println(client_str);
                        outstream.writeUTF(client_str);
                    }

                    else if(sell_choice.equals("maidsafecoin"))
                    {
                        System.out.println("How many maidsafecoin want to sell: ");
                        int sell_quantity = scansys.nextInt();
                        for(int i = 0; i < coindata_arr.length; i++)
                        {
                            String value = coindata_arr[i];
                            if(value.equals("maid"))
                            {
                                double value_arr = Double.parseDouble(coindata_arr[2]);
                                value_arr = value_arr + (sell_quantity*maidsafecoin.getCoinPrice());
                                System.out.println(value_arr);
                                String value_arr_str = String.valueOf(value_arr);
                                coindata_arr[2] = value_arr_str;

                                int value2_arr = Integer.parseInt(coindata_arr[i+1]);
                                value2_arr = value2_arr - sell_quantity;
                                System.out.println(value2_arr);
                                String value2_arr_str = String.valueOf(value2_arr);
                                coindata_arr[i+1] = value2_arr_str;

                            } 
                        }
                        String client_str = String.join(" ",coindata_arr);
                        System.out.println(client_str);
                        outstream.writeUTF(client_str);
                    }

                    else if(sell_choice.equals("blackcoin"))
                    {
                        System.out.println("How many blackcoin want to sell: ");
                        int sell_quantity = scansys.nextInt();
                        for(int i = 0; i < coindata_arr.length; i++)
                        {
                            String value = coindata_arr[i];
                            if(value.equals("black"))
                            {
                                double value_arr = Double.parseDouble(coindata_arr[2]);
                                value_arr = value_arr + (sell_quantity*blackcoin.getCoinPrice());
                                System.out.println(value_arr);
                                String value_arr_str = String.valueOf(value_arr);
                                coindata_arr[2] = value_arr_str;

                                int value2_arr = Integer.parseInt(coindata_arr[i+1]);
                                value2_arr = value2_arr - sell_quantity;
                                System.out.println(value2_arr);
                                String value2_arr_str = String.valueOf(value2_arr);
                                coindata_arr[i+1] = value2_arr_str;

                            } 
                        }
                        String client_str = String.join(" ",coindata_arr);
                        System.out.println(client_str);
                        outstream.writeUTF(client_str);
                    }

                }
                
               
                sock.close();  
            }
            catch(Exception e){
                System.out.println("exception:"+e);
            }  

        }  
        catch(Exception e){
            System.out.println("Exception on client......." + e);
        }
    }
}
