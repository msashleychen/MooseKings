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
   
      JLabel intro = new JLabel("                                         Click two boxes. Try to match all the pictures.");
      intro.setFont(new Font ("ComicSans", Font.PLAIN, 14));
      add(intro,BorderLayout.NORTH);
      add(new JLabel (filler), BorderLayout.EAST);
      add(new JLabel (filler), BorderLayout.WEST);
   
      
      
      Panel pResults = new Panel();
      
      counterLabel = new JLabel("Number of Matches");
       counterLabel.setFont(new Font("ComicSans", Font.BOLD, 14));
       counterLabel.setForeground(new Color(238,200,239));
       pResults.add(counterLabel);
       
      counter = new TextArea(filler,1,5,TextArea.SCROLLBARS_NONE);
         counter.setEditable(false);
         pResults.add(counter);
      panels[16] = new JButton("Reset");
      panels[16].addActionListener(this);
         panels[16].setBackground(new Color(238,200,239));
         panels[16].setForeground(Color.WHITE);
         panels[16].setFont(new Font ("ComicSans",Font.BOLD, 32));
         panels[16].setBorder(customBorder);
      resultslabel = new JLabel("Results:");
         resultslabel.setFont(new Font("ComicSans", Font.BOLD, 14));
         resultslabel.setForeground(new Color(238,200,239));
      results = new TextArea(filler,1,10,TextArea.SCROLLBARS_NONE);
      results.setEditable(false);
      pResults.add(panels[16]);
      pResults.add(resultslabel);
      pResults.add(results);
   
      label2 = new JLabel(filler);
       label2.setFont(new Font("ComicSans", Font.BOLD, 14));
       label2.setForeground(new Color(238,200,239));
       pResults.add(label2);
       
         
      add (pResults,BorderLayout.SOUTH);
   
      Panel pPanels = new Panel();
      
      playGame = new MooseGameModel();
      
      pPanels.setLayout(new GridLayout(4,4));
      for(int i=0;i<16;i++){
         String panelNum = i+1+"";
         panels[i] = new JButton (panelNum);
         panels[i].setBackground(new Color(185,228,246));
         panels[i].setForeground(Color.WHITE);
         panels[i].setFont(new Font ("ComicSans",Font.BOLD, 32));
         panels[i].setBorder(customBorder);
         //set font
         panels[i].addActionListener(this);
         pPanels.add(panels[i]);
      }

      add (pPanels,BorderLayout.CENTER);
      

      setVisible(true);
      
   }//public
   
   public void actionPerformed(ActionEvent ae){
   
      JButton source = (JButton)ae.getSource(); 
      // Find button
      if (source != panels[16]){ //check if the button is the grid or the other buttons
         int i=0;
         while( source != panels[i])
            i++; 
         
         // send in button push and let RandomPrizes object figure out the game 
         playGame.takeTurn(i); 
         panels[i].setIcon(playGame.get(i)); 
         
         //Check for match
         if(playGame.matchStatus() == 3){//matched
            results.setText("Right!");
            panels[i].removeActionListener(this); 
         }
         else if(playGame.matchStatus() == 2){ //2 cards. Not matching
            results.setText("Wrong!");
         }
         else{ //one card has been flipped
            results.setText("Flip another card!");
         }
         
      }
      else if (source == panels[16]){ //what happens when other buttons are pressed
         int i=0;
         panels[i].setIcon(null);
      }

   
   }// main

    
}//class
   
   
   
   
   