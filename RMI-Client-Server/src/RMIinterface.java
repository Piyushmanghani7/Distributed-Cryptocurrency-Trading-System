import java.rmi.RemoteException;
import java.rmi.Remote;
public interface RMIinterface extends Remote{
    public String getCoinName() throws RemoteException;
    public String getCoinAbbrevName() throws RemoteException;
    public double getCoinPrice() throws RemoteException;
    public String getCoinDescrp() throws RemoteException;
    public double getCoinTradingvolume() throws RemoteException;
    public double getCoinMarketcap() throws RemoteException;
    
    
    
}