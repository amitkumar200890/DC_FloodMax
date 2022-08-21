/*

Contributions:
Amit Kumar (AXK210047) - Algorithm
Shanmukha Sai Bapiraj Vinnakota (SXV200113) - Inter Process Communication

*/

import java.io.*;
import java.net.*;
import java.util.*;

public class FloodMax {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        String min_max = args[1];
        Network g = new Network(fileName, min_max);

        for(int r = 1; r <= g.size; r++){
            Thread mainThread = new Thread(new Runnable(){
                public void run(){
                    ArrayList<Thread> processThreads = new ArrayList<Thread>();
                    for(int i = 0; i < g.size; i++){
                        //final Integer index = new Integer(i);
						final Integer index = Integer.valueOf(i);
                        Thread t = new Thread(new Runnable(){
                            public void run(){
                                for(int j: g.processes[index].adj){                 
                                    g.processes[index].sendMessage(g.processes[j].port);
                                }
                            }
                        });
                        t.start();
                        processThreads.add(t);

                        for(Thread th: processThreads){
                            try{
                                th.join();
                            }
                            catch(InterruptedException e){
                                e.printStackTrace();
                            }  
                        }
                    }

                    processThreads.clear();

                    for(int i = 0; i < g.size; i++){
                        //final Integer index = new Integer(i);
						final Integer index = Integer.valueOf(i);
                        Thread t = new Thread(new Runnable(){
                            public void run(){
                                g.processes[index].receiveMessage();
                            }
                        });
                        t.start();
                        processThreads.add(t);
                    }
                    for(Thread th: processThreads){
                        try{
                            th.join();
                        }
                        catch(InterruptedException e){
                            e.printStackTrace();
                        }  
                    }
                }
            });
            mainThread.start();
            mainThread.join();
        }

        for(int i = 0; i < g.size; i++){
            //System.out.println(g.processes[i].uid+" "+g.processes[i].maxuid);
            if(g.processes[i].status == EnumStatusType.LEADER){
                System.out.println("Leader ID:"+g.processes[i].uid);
            } 
        }
    }
}