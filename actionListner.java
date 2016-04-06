//arrayy = array of Jbuttons
//playGame = GameModel
//matchStatus = boolean in GameModel
//banner = TextArea
//playGame.reset() = reests backend check grid

   public void actionPerformed(ActionEvent ae){
   
      JButton source = (JButton)ae.getSource(); 
      // Find button
      if (source != reset){ //check if the button is the grid or the other buttons
         int i=0;
         while( source != arrayy[i])
            i++; 
         
         // send in button push and let RandomPrizes object figure out the game 
         playGame.check(i); 
         arrayy[i].setIcon(dealGame.get(i)); 
         
         //Check for match
         if(playGame.matchStatus() == true)){
            banner.setText("Right!");
            arrayy[i].removeActionListener(this); 
         }
         else{
            banner.setText("Wrong!");
         }
         
      }
      else{ //what happens when other buttons are pressed
         playGame.reset();
      }
   
   }// main
