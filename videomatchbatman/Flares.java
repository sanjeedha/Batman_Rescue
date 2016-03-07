import greenfoot.*; 
public class Flares extends Actor
{
    SimpleTimer timer;
   
    public Flares()
    {
        timer = new SimpleTimer();
        timer.mark();
    }

    public void act() 
    {
        displayFlares();
    }    

    public void displayFlares()
    {
        if(timer.millisElapsed() < 180) {

        }
        else
            getWorld().removeObject(this);     
      
    }
}
