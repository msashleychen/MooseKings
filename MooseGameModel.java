import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

class MooseGameModel extends GameModel{
   private ImageIcon [] images = new ImageIcon[16];
   private int [] turn = {-1,-1};
   private int [] matched = new int [16];
   private int attempts = 0;
   private int matches = 0;

   MooseGameModel(){
      images[0] = new ImageIcon("meese/moose1.jpg"); 
      images[8] = new ImageIcon("meese/moose1.jpg");  
      images[1] = new ImageIcon("meese/moose2.jpg");  
      images[9] = new ImageIcon("meese/moose2.jpg");  
      images[2] = new ImageIcon("meese/moose3.jpg");  
      images[10] = new ImageIcon("meese/moose3.jpg");  
      images[3] = new ImageIcon("meese/moose4.jpg");  
      images[11] = new ImageIcon("meese/moose4.jpg");  
      images[4] = new ImageIcon("meese/moose5.jpg");  
      images[12] = new ImageIcon("meese/moose5.jpg");  
      images[5] = new ImageIcon("meese/moose6.jpg");  
      images[13] = new ImageIcon("meese/moose6.jpg");  
      images[6] = new ImageIcon("meese/moose7.jpg");  
      images[14] = new ImageIcon("meese/moose7.jpg");  
      images[7] = new ImageIcon("meese/moose8.jpg");  
      images[15] = new ImageIcon("meese/moose8.jpg");   
      
   }//moosegamemodel
   

   void takeTurn(int clicked){
      if(turn[0]==-1){
         turn[0]=clicked;
      }//if
      else{
         turn[1]=clicked;
      }//else
   }//check
   
   
   int[] getTurn(){
      return(turn); }
   
   int getAttempts(){
      return(attempts); }
   
   int getMatches(){
      return(matches); }

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
            attempts = attempts +1;
         }//else
      }//else
      return status;}


   boolean gameOverStatus(){
      boolean over = false;
      if(matches == 8){
         over = true;
      }//if
      return over;};
      
   
   void reset(){
   // button.setIcon(null);
      turn[0] = -1;
      turn[1] = -1;
   }//reset   
      
   
   int [] getMatched(){
      return matched;
   }
   
   void addMatched(int i, int a){
      matched[matches/2] = i;
      matched[matches/2+1] = a;
   }
   
   ImageIcon get(int i){
      return(images[i]);}
   
   
   int getRows(){
      return 4;}
   int getCols(){
      return 4;} 
   void display(){}     
   String reportWinner(){
      return "";}




}