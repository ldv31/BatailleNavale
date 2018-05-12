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
    
    char xPosition;
    int xPositionNumeric;
    char yPosition;
    int yPositionNumeric;
    int xSize;
    int ySize;
    
    public boolean checkIntegrity ()
    {
        // check if position is in the battlefiel
        if ((xPositionNumeric > xSize) || (xPositionNumeric > ySize) || (xPositionNumeric <=0) || (xPositionNumeric <=0))
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
        yPositionNumeric = (int)xPosition - (int)'1';
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
