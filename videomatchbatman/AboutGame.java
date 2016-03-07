import greenfoot.*;

/**
 * Write a description of class AboutGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AboutGame extends GameOptions implements ButtonInvoker
{
   private AboutDesc aboutDesc;
    private ButtonCommand newButtonCommand;
   
    public AboutGame()
    {
        aboutDesc = new AboutDesc();
    }
    public AboutDesc getAbout()
    {
        return aboutDesc;
    }
    
    public void act() 
    {
        super.act();
    }  
    
    public void click()
    {
        newButtonCommand.executeCommand();
        
    }
    
     public void setCommand(ButtonCommand newCommand)
     {
        this.newButtonCommand = newCommand;
     }
}
