/*

Contributions:
Amit Kumar (AXK210047) - Algorithm
Shanmukha Sai Bapiraj Vinnakota (SXV200113) - Inter Process Communication

*/

import java.io.*;
import java.net.*;
import java.util.*;

public class Network{
    int size;
    Process[] processes;

    Network(String fileName, String min_max) throws IOException{
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        int n = sc.nextInt();
        size = n;
        processes = new Process[n];
        int port = 5000;
        for(int i = 0; i < n; i++){
            ServerSocket serverSocket = new ServerSocket(port);
            Process node = new Process(sc.nextInt(), port, n, min_max, serverSocket);
            processes[i] = node;
            port++;
        }
		sc.nextLine();
        //System.out.println(sc.nextLine());
        for(int i = 0; i < n; i++){
            String[] adj_nodes = sc.nextLine().split(" ");
            Vector<Integer> vec = new Vector<Integer>();
            for(String node: adj_nodes){
                vec.add(Integer.parseInt(node)-1);
            }
            processes[i].adj = vec;
        }
        // for(int i = 0; i < n; i++){
        //     for(int j: processes[i].adj){
        //         System.out.print(j+" ");
        //     }
        //     System.out.println();
        // }
        sc.close();
    }
}
