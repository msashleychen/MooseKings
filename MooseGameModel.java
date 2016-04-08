import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
  


class MooseGameModel extends GameModel{
private ImageIcon [] images = new ImageIcon[16];
private int [] turn = {-1,-1};
int attempts = 0;
int matches = 0;

MooseGameModel(){
 for(int i=0;i<images.length;i++){
 images[i] = new ImageIcon("moose.jpg");
 }
}

void takeTurn(int clicked){
if(turn[0]==-1){
turn[0]=clicked;
}//if
else{
turn[1]=clicked;
}//else
}//check

int matchStatus(){
int status = 0;
if(turn[1]==-1){
status = 1;
}//if
else{
if(Math.abs(turn[0]-turn[1])==8){
status = 3;
matches = matches +1;
}//if
else{
status = 2;
}//else
turn[0] = -1;
turn[1] = -1;
attempts = attempts +1;
}//else

//if so, check if they are the same
//return true if same, false if different
//reset check funtion if they are the same
// 3=match   2=not match   1=only one flipped
return status;}





   boolean gameOverStatus(){return true;};
   
   ImageIcon get(int i){return new ImageIcon("moose.jpg");};  
   int getRows(){return 4;};
   int getCols(){return 4;}; 
   void display(){};      
   String reportWinner(){return "";}; 




}