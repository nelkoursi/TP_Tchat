package tpchat;

public interface InterfaceRMIChat extends java.rmi.Remote {

        public void printMessage()
                throws java.rmi.RemoteException;
}