import greenfoot.*;

/**
 * 
 * @author 
 * @version 
 */
public class BombEnemy extends Attack implements AttackStrategy
{
    private SimpleTimer timerShot;
    private GreenfootImage bomba0;
    private GreenfootImage bomba1;
   
    public BombEnemy()
    {
        timerShot = new SimpleTimer();
        timerShot.mark();
        bomba0 = new GreenfootImage("bombEnemy_v.png");
        bomba1 = new GreenfootImage("bombEnemy_h.png");
        setImage(bomba0);
    }
    
    /**
     * Act - do whatever the BombEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
 
    
    public void shoot()
    {
        if(isTouching(Batman.class))
        {
            Batman i = (Batman)getOneIntersectingObject(Batman.class);
            i.reduceHealth(5);
            getWorld().addObject(new Flares(), getX(), getY());
            getWorld().removeObject(this);
        }
        else if(getY()-getImage().getHeight()/2 > getWorld().getHeight())
            getWorld().removeObject(this);
        else if(isTouching(ShotPlayer.class))
        {
            explosionSound.play();
            ShotPlayer dp = (ShotPlayer)getOneIntersectingObject(ShotPlayer.class);
            getWorld().removeObject(dp);
            getWorld().addObject(new Flares(), getX(), getY());
            Scenario es = (Scenario)getWorld();
            es.getBatman().setPoints(4);
            getWorld().removeObject(this);
        }
        else
            movement();
    }
    
   
    public void movement()
    {
        if(timerShot.millisElapsed() > 10000)
        {
            setImage(bomba1);
            Scenario e = (Scenario)getWorld();
            Batman i = e.getBatman();
            turnTowards(i.getX(), i.getY());
            move(5);
        }
    }
}