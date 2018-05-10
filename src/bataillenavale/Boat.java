/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavale;

/**
 *
 * @author LDV
 * The boat class includes all parameter of boat, status and actions.
 * test de diff
 */

// This classe define "boat" used in bataille navale
public class Boat {
    //boat parameters
    private String name;

    // position of the boat, passed to the boat object by the game manager object
    private int position [];
    
    // hit point, set to 0 initialy
    private int hitPoint = 0;
    
    // true if boat is sunk
    boolean sinkStatus = false;
    
    // constructor
    public Boat (String name, int position [])
    {  
        this.name = name;
        this.position = new int[position.length];
        
        System.arraycopy(position, 0, this.position, 0, position.length);
    }  
    
    // print the position of the boat (use to check that position is OK)
    public void printPosition ()
    {
        System.out.println("Name: " + name);
        System.out.print("Position: ");
        for (int i=0; i < position.length; i++)
        {
            System.out.print(position [i] + " ");
        }
        System.out.println();
    }
    
    // Compare too positions, if they intersect, return true
    // use false for checking and true hor hit
    public boolean comparePosition (int position [], boolean hit)
    {
        boolean intersection = false;
        
        for (int i = 0; i < position.length/2; i++)
        {
            if ((this.position [i*2] == position [i*2]) && (this.position [i*2+1] == position [i*2+1]))
            {
                intersection = true;
            }
        }
        
        if (intersection == true && hit == true)
        {
            hitPoint++;
            if (hitPoint == position.length)
                sinkStatus = true;
        }
        return intersection;
    }
    
    
    // get the postion
    public int[] getPosition()
    {
        return position;
    }
    
    // get sinkStatus
    public boolean getSinkStatus()
    {
        return sinkStatus;
    }
}