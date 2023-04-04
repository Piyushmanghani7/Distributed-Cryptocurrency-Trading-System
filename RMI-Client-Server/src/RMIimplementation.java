import java.rmi.RemoteException;
import java.rmi.Remote;
public class RMIimplementation implements RMIinterface{

    public String coinname;
    public String abbreviatedname;
    public double price;
    public String description;
    public double tradingvolume;
    public double marketcap;
    
    

    public  RMIimplementation(String coinname, String abbreviatedname,  double price, String description,double tradingvolume, double marketcap) throws RemoteException{
        this.coinname = coinname;
        this.abbreviatedname = abbreviatedname;
        this.price = price;
        this.description = description;
        this.tradingvolume = tradingvolume;
        this.marketcap = marketcap;
        
        
    }


    public String getCoinName() throws RemoteException{
        return this.coinname;
    }

    public String getCoinAbbrevName() throws RemoteException{
        return this.abbreviatedname;
    }
    public double getCoinPrice() throws RemoteException{
        return this.price;
    }

    public String getCoinDescrp() throws RemoteException{
        return this.description;
    }
    public double getCoinTradingvolume() throws RemoteException{
        return this.tradingvolume;
    }

    public double getCoinMarketcap() throws RemoteException{
        return this.marketcap;
    }

    

    

}