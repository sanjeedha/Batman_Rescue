import greenfoot.*;


public class DisplayControls extends GameOptions implements ButtonInvoker
{
    private HowToPlay howToPlay;
    private ButtonCommand newButtonCommand;
   
    public DisplayControls()
    {
        howToPlay = new HowToPlay();
    }
    public HowToPlay getHowToPlay()
    {
        return howToPlay;
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
