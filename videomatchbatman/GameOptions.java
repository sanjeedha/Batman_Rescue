import greenfoot.*;

/**
 * Write a description of class GameOptions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOptions extends Actor
{
   
    private int x;
    private GreenfootSound sound;
    protected GreenfootSound clickSound;
    private boolean playSound ;
    public GameOptions()
    {
        x=0;
        sound = new GreenfootSound("soundB.wav");
        clickSound = new GreenfootSound("buttonSound.wav");
        playSound  = false;
    }
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        adjust();
        click();
    }   
    
    /**
     * Adjust button position
     */
    public void adjust()
    {
        if(getX() < (getWorld().getWidth()/2)+420)
            move(x);
        else
        {
            setLocation((getWorld().getWidth()/2)+420, getY());
            if(!playSound)
            {
                sound.play();
                playSound  = true;
            }
        }
        if(x < 10)
            x++;
    }
    
    
    public void click(){}
}
