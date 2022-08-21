
/*

Contributions:
Amit Kumar (AXK210047) - Algorithm
Shanmukha Sai Bapiraj Vinnakota (SXV200113) - Inter Process Communication

*/

import java.io.*;
import java.net.*;
import java.util.*;

class Process{
    int uid;
    int maxuid;
    EnumStatusType status;
    int port;
    ServerSocket serverSocket;
    Vector<Integer> adj;
    int round;
    int size;
    String min_max;

    Process(int uid, int port, int size, String min_max, ServerSocket serverSocket){
        this.uid = uid;
        maxuid = uid;
        this.port = port;
        this.serverSocket = serverSocket;
        adj = null;
        status = EnumStatusType.UNKNOWN;
        round = 0;
        this.size = size;
        this.min_max = min_max;

    }

    void sendMessage(int port){
        try{
            //Socket clientSocket = new Socket("localhost", port);
			Socket clientSocket = new Socket("127.0.0.1", port);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            Message msg = new Message(this.maxuid);
            out.writeObject(msg);
            clientSocket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }

    void receiveMessage(){
        int numMessages = 0;
        while(numMessages < adj.size()){
            try{
                Socket s = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                Message m = (Message) in.readObject();
                if(this.min_max.equals("min")){
                    this.maxuid = Math.min(this.maxuid, m.uid);
                }
                else{
                    this.maxuid = Math.max(this.maxuid, m.uid);
                }
                
                in.close();
                s.close();
                numMessages++;
            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            
        }
        this.round++;
        if(this.round == this.size){
            if(this.uid == this.maxuid){
                this.status = EnumStatusType.LEADER;
            }
            else{
                this.status = EnumStatusType.NON_LEADER;
            }
        }
    }
}