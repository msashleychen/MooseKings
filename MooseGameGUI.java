import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MooseGameGUI extends JFrame implements ActionListener{


   private final int WINDOW_WIDTH = 600;
   private final int WINDOW_HEIGHT = 800;
   //private JButton panels[17];
   private JButton [] panels = new JButton[17];
   private MooseGameModel playGame; 
   private String filler = "        ";
   private JLabel intro;
   private JLabel resultslabel;
   private JLabel label2;
   private JLabel counterLabel;
   private TextArea counter;
   private TextArea results;
   
   
   public MooseGameGUI(){
      setTitle("Moose Matching Game");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
      Border customBorder = new LineBorder(Color.WHITE, 8);
   
      JLabel intro = new JLabel("             Click two boxes. Try to match all the pictures in 10 attempts.");
      intro.setFont(new Font ("Comic Sans MS", Font.BOLD, 14));
      intro.setForeground(new Color(152,116,165)); //dark pink
      add(intro,BorderLayout.NORTH);
      add(new JLabel (filler), BorderLayout.EAST);
      add(new JLabel (filler), BorderLayout.WEST);
   
      
      
      Panel pResults = new Panel();
      
      counterLabel = new JLabel("Number of Matches");
      counterLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
      counterLabel.setForeground(new Color(87,151,188)); //dark blue
      pResults.add(counterLabel);
       
      counter = new TextArea(filler,1,5,TextArea.SCROLLBARS_NONE);
      counter.setEditable(false);
      pResults.add(counter);
      panels[16] = new JButton("Reset");
      panels[16].addActionListener(this);
      panels[16].setBackground(new Color(238,200,239)); //pink
      panels[16].setForeground(new Color(152,116,165)); //dark pink
      panels[16].setFont(new Font ("Comic Sans MS",Font.BOLD, 32));
      panels[16].setBorder(customBorder);
     
   
      resultslabel = new JLabel("Results:");
      resultslabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
      resultslabel.setForeground(new Color(87,151,188)); //dark blue
      results = new TextArea(filler,1,10,TextArea.SCROLLBARS_NONE);
      results.setEditable(false);
      pResults.add(panels[16]);
      pResults.add(resultslabel);
      pResults.add(results);
   
      label2 = new JLabel(filler);
      label2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
      label2.setForeground(new Color(238,200,239)); //blue
      pResults.add(label2);
       
         
      add (pResults,BorderLayout.SOUTH);
   
      Panel pPanels = new Panel();
      
      playGame = new MooseGameModel();
      
      pPanels.setLayout(new GridLayout(4,4));
      for(int i=0;i<16;i++){
         String panelNum = i+1+"";
         panels[i] = new JButton (panelNum);
         panels[i].setBackground(new Color(185,228,246)); //blue
         panels[i].setForeground(new Color(87,151,188)); //dark blue
         panels[i].setFont(new Font ("Comic Sans MS",Font.BOLD, 32));
         panels[i].setBorder(customBorder);
         //set font
         panels[i].addActionListener(this);
         pPanels.add(panels[i]);
      }
   
      add (pPanels,BorderLayout.CENTER);
      
   
      setVisible(true);
      
   }//public
   
   public void actionPerformed(ActionEvent ae){
   
      int [] cardArray = playGame.getTurn();
      int [] matched = playGame.getMatched();
      
     // label2.setText(Integer.toString(playGame.getAttempts()/2)); //for debugging
   
      JButton source = (JButton)ae.getSource(); 
      
      // Find button
      //panels[16] = reset button
      if (source != panels[16]){ //check if the button is the grid or the other buttons
         int i=0;
         while( source != panels[i])
            i++; 
         
         //What happens when any button is pushed
         playGame.takeTurn(i);  //puts pushed button # into turn array
         panels[i].setIcon(playGame.get(i));  //gets image
         
         //Check for match
         if(playGame.matchStatus() == 3){//matched
            results.setText("Right!");
            playGame.addMatched(cardArray[0],cardArray[1]);  //adds indexes to array of matched cards
            
            
         
            
            //##############
            for(int a=0;a<matched.length;a++){ //Makes sure matched cards are disabled
               panels[matched[a]].removeActionListener(this);
            }
            //#############
         
            counter.setText(Integer.toString(playGame.getMatches()));//add to counter
            playGame.reset();
         }//if matched cards
         else if(playGame.matchStatus() == 2){ //2 cards. Not matching
            results.setText("Wrong!");
            for(int a=0;a<16;a++){ //Makes sure user clicks on reset button before revealing other cards
               panels[a].removeActionListener(this); 
            }
           
         }//if not matched cards
         else{ //one card has been flipped
            results.setText("Flip another card!");
         }
         if(playGame.getMatches() == 8){
            JOptionPane.showMessageDialog(null, "You've Won!");
         }
         if(playGame.getAttempts() == 20){
            JOptionPane.showMessageDialog(null, "You've Lost!");
         }
         
      }
      else if (source == panels[16]){ //what happens when reset button is pressed
         if(cardArray[0]>-1){ //Resets card 1
            panels[cardArray[0]].setIcon(null);
         }
         if(cardArray[1]>-1){ //Resets card 2
            panels[cardArray[1]].setIcon(null);
         }
         
         playGame.reset();
         
         for(int a=0;a<17;a++){ //Resets all action listener
            panels[a].addActionListener(this); 
         }
       
         matched = playGame.getMatched();
         for(int a=0;a<matched.length;a++){ //Makes sure matched cards are disabled    
            if(matched[a]>-1)
            {
            
               panels[matched[a]].removeActionListener(this);
            }
         }
         
      
      
         
      }
   
   
   }// main

    
}//class