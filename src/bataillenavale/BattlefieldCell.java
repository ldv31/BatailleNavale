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
public class BattlefieldCell {
    
    private char xPosition;
    private int xPositionNumeric;
    private char yPosition;
    private int yPositionNumeric;
    private int xSize;
    private int ySize;
    
    // Verify that the position is within the battle field
    private boolean checkIntegrity ()
    {
        // check if position is in the battlefiel
        if ((xPositionNumeric >= xSize) || (yPositionNumeric >= ySize) || (xPositionNumeric <0) || (yPositionNumeric <0))
            return false;
        else
            return true;
    }
    
    // setter for internal instance variables
    public boolean initialise (char xPosition, char yPosition, int xSize, int ySize)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xSize = xSize;
        this.ySize = ySize;
        xPositionNumeric = (int)xPosition - (int)'A';
        yPositionNumeric = (int)yPosition - (int)'1';
        return checkIntegrity();
    }
    
    // move from A5 to 15 (letter/user to number/internal program)
    public boolean returnNumericalPosition(int [] position)
    { 
        position[0] = xPositionNumeric;
        position[1] = yPositionNumeric;
        
        return checkIntegrity();
    }
}
