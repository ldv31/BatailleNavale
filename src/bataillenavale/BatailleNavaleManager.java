/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavale;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author LDV
 */
public class BatailleNavaleManager {
    
    // Boats parameters definition on the battlefield
    private final int boatNumber = 3;
    private final int boatSize = 3;
    private final String boatName[] = {"Boat1", "boat2", "boat3"};
    
    // Battle field définiton
    private final int xSize = 10;
    private final int ySize = 10;
    
    // array of boats
    private Boat gameBoat [] = new Boat [boatNumber];
    
    //in game variable
    
    // Exit game if yes
    private boolean exitGame = false;
    
    // number of boat left (will end the game if set to 0)
    private int numberOfBoatLeft = boatNumber;
    
    // number of trial from player (used for providing statistics to the player)
    
    // Run the game
    public BatailleNavaleManager ()
    {
        // place boat on the battle field
        initialiseBoat();
    }
    
    private void initialiseBoat()
    {
        // set parameters values for all boats
        for(int i=0; i<boatNumber; i++)
        {
            gameBoat[i] = new Boat (boatName[i],getPosition());
            gameBoat[i].printPosition();
        }
    }
    
    // get a position for a boat on the battle field
    private int[] getPosition ()
    {
        int position [] = new int [boatSize*2];
        int maxLoop = 50;
        boolean positionFound = false;
        
        // check if the position is already used, if yes, loop till a position is found (max 20 loop)
        while (maxLoop > 0 && positionFound == false)
        {
           // calculate random position with the battlefield
           for (int i=0; i < boatSize; i++)
           {
               Random rand = new Random();
               position[i*2] = rand.nextInt(xSize);
               position[i*2+1] = rand.nextInt(ySize);
           }
           positionFound = true;
            
            // for each existing boat, check is the new position intersects
            for (int j = 0; j < boatNumber; j++)
            {
                if (gameBoat[j] != null)
                {
                    if (gameBoat[j].comparePosition(position,false) == true)
                        positionFound = false;
                }
            }
           maxLoop--;
        }
        
        return position;
    }
    
    //Display the banner when the game starts
    private void printBanner()
    {
        // Introduction of the game //
        System.out.println("\n=============================\n" +
        "=                           =\n" +
        "=     Bataille Navale       =\n" +
        "=                           =\n" +
        "============================= \n") ;
    }
            
    public void runGame()
    {
        //scanner for input
        Scanner playerScanner = new Scanner(System.in);
        String playerInput;

        //Display the banner
        printBanner();
          
        // ask player for hit coordinate and provide result, till all boats are sunk
        while ((exitGame == false) && (numberOfBoatLeft > 0))
        {
            // Prepare input 
            System.out.print("Entrez les coordonnées du tir: ");
            playerInput = playerScanner.nextLine();
           
            // only two caracter for valid player coordinates entry
            if (playerInput.length() != 2)
            {
                System.out.println("Nombre de caractères incorrect, essayez à nouveau (2 caractères)");
            }
            else
            {
                // convert caractère to coordinate
                
            }
        }
        
        
        /*    
        Test section, to be commented for normal use
        
        int testBoat1Position [] = {0,0,1,1,2,2};
        int testBoat2Position [] = {0,0,4,4,5,5};
        Boat testBoat1 = new Boat("testBoat1", testBoat1Position);
        // Verify tha creation of the boat objet is correct
        testBoat1.printPosition();
        // veify the comparePosition function
        System.out.println("Intersect result: " + testBoat1.comparePosition(testBoat2Position, false));
        */
    }
}
