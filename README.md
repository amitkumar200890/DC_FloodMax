# DC_FloodMax
A simple simulator that simulates a synchronous distributed system using multi  threading


Developed a simple simulator that simulates a synchronous distributed system using multi 
threading. There are n+1 threads in the system: Each of the n processes will be simulated by one thread 
and there is one master thread. The master thread will “inform” all threads when one round starts. 
Thus, each thread simulating one process, before it can begin round x, must wait for the master thread 
for a "go ahead" signal for round x. Clearly, the master thread can give the signal to start round x to the 
threads only if the master thread is sure that all the n threads (simulating n processes) have completed 
their previous round (round x-1).
The simulation will simulate the FloodMax algorithm for leader election with termination detection 
built in. The code (algorithm) executed by all processes must be the same. 
The input for this problem is to be read from a file inputdata.txt, which is a text file and it consists of 
(1) n (the number of processes of the distributed system which is equal to the number of threads to be 
created), (2) one array uid[n] of size n with array elements separated by one or more space(s); the ith
element of this array gives the unique identifier of the ith process or ith
thread and all ids are integers, and (3) n rows of adjacency information. The jth row of the adjacency 
information lists the indices of all processes that the jth process is connected to. Each row has 1 or 
more indices (indicating its neighbors) separated by white spaces.
All links are bidirectional.

The master thread reads these inputs and then spawns n threads. Process identified by its index i can 
communicate only with its neighbors. No process knows the identifiers of any process, except it 
knows about its own unique identifier. (Process i knows that its identifier is uid[i].) 
Output the leader’s id at the end of the execution. 
Upload one tar file containing your source code, a README file that tells us how to compile and run, 
one sample input file and the result of running your program on your sample input file. Your project 
must compile and run on UTD’s Unix servers (cs1 or cs2).
