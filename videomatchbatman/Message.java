import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Message extends Actor
{
  
    SimpleTimer timer;
       
    public Message(String text, boolean f)
    {
        if(text.equals("NEXTLEVEL")) {
            setImage(new GreenfootImage("FinalButton.png")); 
        } else if(text.equals("DEFEAT")){
             setImage(new GreenfootImage("Defeated.png")); 
        } else if(text.equals("WIN")){
             setImage(new GreenfootImage("Won.png")); 
        }
        
        timer = new SimpleTimer();
        timer.mark();
    }
    
    public void act() 
    {
       displayMessage();      
    }    
    
    public void displayMessage()
    {
        if(timer.millisElapsed() < 2000) {
        }
        else
            getWorld().removeObject(this);     
      
    }
}
