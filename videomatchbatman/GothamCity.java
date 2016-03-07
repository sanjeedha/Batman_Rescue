import greenfoot.*;

/**
 * Write a description of class SanJoseCity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GothamCity extends GameOptions implements ButtonInvoker
{
    ButtonCommand newButtonCommand;
    
    public void act() 
    {
        super.act();
    }    
    
    /**
     * click on this button to start the game
     */
    public void click()
    {
        newButtonCommand.executeCommand();
        if(Greenfoot.mouseClicked(this)){
            clickSound.play();            
        }
    }
    
    /**
     * Method to set command
     */
    public void setCommand(ButtonCommand newCmd)
    {
        this.newButtonCommand = newCmd;
    }
}
