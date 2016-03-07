import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ShotPlayer extends Attack
{
   
    public ShotPlayer()
    {
        velShot = 10;
    }
    
    /**
     * Act - do whatever the ShotPlayer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    

    public void shoot()
    {        
        Scenario es = (Scenario)getWorld();
        if(isTouching(Enemy.class))
        {
            explosionSound.play();
            Enemy e = (Enemy)getOneIntersectingObject(Enemy.class);
            getWorld().addObject(new Flares(), e.getX(), e.getY());
            getWorld().removeObject(e);
            es.reduceEnemies();
            es.getBatman().setPoints(Greenfoot.getRandomNumber(6)+10);
            getWorld().removeObject(this);
        }
        else if(getX() > getWorld().getWidth())
            getWorld().removeObject(this);
        
        
        
        else if(isTouching(ShotEnemy.class))
        {
            explosionSound.play();
            ShotEnemy de = (ShotEnemy)getOneIntersectingObject(ShotEnemy.class);
            getWorld().removeObject(de);
            getWorld().addObject(new Flares(), getX(), getY());
            es.getBatman().setPoints(3);
            getWorld().removeObject(this);
        }
        
        else if(isTouching(Joker.class))
        {
            Joker g = (Joker)getOneIntersectingObject(Joker.class);
            g.reduceHealth(7, explosionSound);
            es.getBatman().setPoints(5);
            //getWorld().removeObject(this);
        }

        else if(isTouching(Harley.class))
        {
            Harley g = (Harley)getOneIntersectingObject(Harley.class);
            g.reduceHealth(7, explosionSound);
            es.getBatman().setPoints(5);
            //getWorld().removeObject(this);
        }

        else
            setLocation(getX()+velShot,getY());
    }
}