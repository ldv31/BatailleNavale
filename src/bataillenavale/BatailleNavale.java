/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavale;

/**
 *
 * @author LDV
 */
public class BatailleNavale {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        // 0 for test, 1 for normal mode 
        int runMode = 1;
        
        // Create a "BatailleNavaleManager" that will handle all the game logic
        // ... and also start the game
        BatailleNavaleManager myBatailleNavaleManager = new BatailleNavaleManager();
        if (runMode == 1)
            myBatailleNavaleManager.runGame();
        else
            myBatailleNavaleManager.testGameFunction();
    }  
}
