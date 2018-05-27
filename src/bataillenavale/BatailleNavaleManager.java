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
    
    // Battle field définiton - Max value = 9
    private final int xSize = 8;
    private final int ySize = 8;
    
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
        "=          v1.0             =\n" +
        "=                           =\n" +
        "============================= \n\n" +
        "Info: Entrer quit pour sortir du jeux.\n") ;
    }
            
    public void runGame()
    {
        // Used to display the shooting result to the player
        Boolean shootSuccessful = false;
        
        // variable to store the number of shoots (will be displayed at the end of the game)
        int shootNumber = 0;
        
        // variable to store the username
        String username = new String();
        
        // hit position as entered by user (char type)
        int [] hitPosition = new int[2];
        
        // hit position for internal use (int type for array)
        int [] hitPositionNumerical = new int[2];
        
        // battlefield cell utility
        BattlefieldCell battleCell = new BattlefieldCell();
        
        //scanner for input
        Scanner playerScanner = new Scanner(System.in);
        String playerInput;

        //Display the banner
        printBanner();
        
        // ask the player for his name
        username = getUsername();

        // ask player for hit coordinate and provide result, till all boats are sunk
        while ((exitGame == false) && (numberOfBoatLeft > 0))
        {
            // Prepare input 
            System.out.print("Entrez les coordonnées du tir, " + username + " : ");
            playerInput = playerScanner.nextLine();
            playerInput = playerInput.toUpperCase();
           
            // Check if exit
            if (playerInput.equalsIgnoreCase("quit"))
            {
                exitGame = true;
                System.out.println("\nSortie du jeu (demande joueur)\n"); 
            }
            else
            {
            
                // only two caracter for valid player coordinates entry
                if (playerInput.length() != 2)
                {
                    // Position invalide
                    System.out.println("Nombre de caractères incorrect, essayez à nouveau (2 caractères)");
                }
                else
                {
                    // check position integrity
                    if (battleCell.initialise(playerInput.charAt(0), playerInput.charAt(1), xSize, ySize) == false)
                    {
                        // position invalide
                        System.out.println("Valeur de tir incorrecte, essayez à nouveau (valeur en dehors de la grille)");
                    }
                    else
                    {
                        // Increase the number of shoots
                        shootNumber ++;
                        
                        // traiter le tir
                        for (int i=0; i < boatNumber; i++)
                        {
                            battleCell.returnNumericalPosition(hitPositionNumerical);
                            if (gameBoat[i].getSinkStatus() == false && gameBoat[i].comparePosition(hitPositionNumerical, true))
                            {
                                System.out.println("Touché ! Bateau: " + gameBoat[i].getboatName() + " , Bateau coulé ? " + gameBoat[i].getSinkStatus());
                                shootSuccessful = true;
                                
                                // decreae the number of boat left if a boat is sunk
                                if (gameBoat[i].getSinkStatus() == true)
                                {
                                    numberOfBoatLeft--;
                                } 
                            } 
                        }
                        if (shootSuccessful == false)
                            {
                                System.out.println("Raté !");
                            }
                    }
                }
            }
        }
        
        if (numberOfBoatLeft == 0)
        {
            System.out.println("Bravo " + username + " !");
        }
        
        System.out.println("Vous avez tiré " + shootNumber + " tirs !");
    }
    
    private String getUsername()
    {
        Scanner playerScanner = new Scanner(System.in);
        String playerInput;
        System.out.print("Entrez votre nom: ");
        playerInput = playerScanner.nextLine();
        
            /* Check if exit
            if (playerInput.equalsIgnoreCase("quit"))
            {
                exitGame = true;
                System.out.println("\nQuiting game (user request)\n"); 
            } */
        return playerInput;
    }
    
    public void testGameFunction()
    {     
        // set of test for functions
        
        System.out.println("\nWARNING : RUNNING IN TEST MODE\n");
        
        // Boat class function testing 
        int testBoat1Position [] = {0,0,1,1,2,2};
        int testBoat2Position [] = {0,0,4,4,5,5};
        Boat testBoat1 = new Boat("testBoat1", testBoat1Position);
        // Verify tha creation of the boat objet is correct
        testBoat1.printPosition();
        // veify the comparePosition function
        System.out.println("Intersect result: " + testBoat1.comparePosition(testBoat2Position, false));
        
        // BattleFieldCell class function testing
        BattlefieldCell cell = new BattlefieldCell();
        
        // BattlefiledCell initialise
        if (cell.initialise('A', '1', xSize, ySize)== true)
            System.out.println("Test BattlefieldCell #1: OK");
        else
            System.out.println("Test BattlefieldCell #1: KO");
        
        if (cell.initialise('X', '1', xSize, ySize)== false)
            System.out.println("Test BattlefieldCell #2: OK");
        else
            System.out.println("Test BattlefieldCell #2: KO");
        
        if (cell.initialise('A', '9', xSize, ySize)== false)
            System.out.println("Test BattlefieldCell #3: OK");
        else
            System.out.println("Test BattlefieldCell #3: KO");
        
        if (cell.initialise('D', '5', xSize, ySize)== true)
            System.out.println("Test BattlefieldCell #4: OK");
        else
            System.out.println("Test BattlefieldCell #4: KO");
    }
}
