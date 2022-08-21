
/*

Contributions:
Amit Kumar (AXK210047) - Algorithm
Shanmukha Sai Bapiraj Vinnakota (SXV200113) - Inter Process Communication

*/

import java.io.*;
class Message implements Serializable{
    int uid;
    Message(int uid){
        this.uid = uid;
    }
}