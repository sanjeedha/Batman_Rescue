import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Joker here.
 * 
 * @author  
 * @version 
 */
public class Joker extends Character
{
    private int health;
    private int movementMode;
    private int attackType;
    private boolean movementOrder;
    private int attackCnt;
    private SimpleTimer timer; // timer to appear on screen and attack every so often
    private boolean died;
    private boolean winNotice;
    
    public Joker()
    {
        health = 800;
        movementMode = 0;
        attackType = Greenfoot.getRandomNumber(3);
        movementOrder = false;
        attackCnt=0;
        timer = new SimpleTimer();
        timer.mark();
        died=false;
        winNotice=false;
    }
    
    /**
     * Act - do whatever the Joker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(health>0)
        {
            mover();
            attack();
            checkScoreUpdater();
        }
        else if(health==0 && !winNotice)
        {
            died = true;
            timer.mark();
            winNotice = true;
            //health = -1;//so only once between            
            setLocation(getWorld().getWidth()/2, getWorld().getHeight()/2);
        }
        isHealthZero();
    }
    
    
    public void mover()
    {
        switch(movementMode)
        {
            case 0://moves to the left
                if(getX()>800 && movementOrder==false)
                {
                    if(timer.millisElapsed() > 3000)
                        setLocation(getX()-2, getY());
                }
                else
                {
                    movementOrder=true;
                    movementMode=2;
                }
            break;
            
            case 1://moves to the right until it leaves the screen
                if(getX() < getWorld().getWidth()+getImage().getWidth()/2 && movementOrder==false)
                {
                    setLocation(getX()+2, getY());
                   
                }
                else
                {
                    timer.mark();
                    movementMode = 0;
                }
            break;
            
            case 2://moves up
                if(getY() > 0+getImage().getHeight()/2)
                    setLocation(getX(), getY()-5);
                else
                    movementMode = 3;
            break;
            
            case 3://moves down
                if(getY() < getWorld().getHeight()-getImage().getHeight()/2)
                    setLocation(getX(), getY()+5);
                else
                    movementMode=2;
            break;
        }
    }
    
    /**
     * Different attacks makes the player.
     */
    public void attack()
    {
        if(movementOrder == true)
        {
            switch(attackType)
            {
                case 0:
                    if(shotTimer.millisElapsed() > 300 && timer.millisElapsed() > 5000)
                    {
                        getWorld().addObject(new ShotBoss(Greenfoot.getRandomNumber(3)), getX()-40, getY()-getImage().getHeight()/2);
                        attackCnt++;
                        shotTimer.mark();
                    }
                break;
            
                case 1:
                    if(shotTimer.millisElapsed() > 500 && timer.millisElapsed() > 6000)
                    {
                        getWorld().addObject(new BombEnemy(), getX()-40, getY()-getImage().getHeight()/2);
                        attackCnt++;
                        shotTimer.mark();
                    }
                break;
            
                case 2:
                    if(shotTimer.millisElapsed() > 1000  && timer.millisElapsed() > 10000)
                    {
                        generateEnemies();
                        getWorld().addObject(new ShotBoss(Greenfoot.getRandomNumber(3)), getX()-40, getY()-getImage().getHeight()/2);
                        attackCnt++;
                        shotTimer.mark();
                    }
                break;
            }
        }
    }
    
    /**
     * Generates a random number to create an enemy that gets in the world.
     */
    public void generateEnemies()
    {
        
    }
    
   
    public void checkScoreUpdater()
    {
        if(attackCnt > 5)
        {
            attackCnt=0;
            attackType = Greenfoot.getRandomNumber(3);
            movementMode=1;
            movementOrder = false;
        }
    }
    
    /**
     * Check if the KAzaki is on the screen, if so subtract 's' health.
     * 
     */
    public void reduceHealth(int s, GreenfootSound sound)
    {
        if(getX()-getImage().getWidth()/2 < getWorld().getWidth() && health > 0)
        {
            health -= s;
            sound.play();
        }
        else if(health <= 0)
            health = 0;
            
    }
    
    /**
     * Return Joker health
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * If health is <= zero the boss explodes and disappears.
     */
    public void isHealthZero()
    {
        if(died)
        {
            if(timer.millisElapsed() > 300 && timer.millisElapsed() < 600)
            {
                getWorld().addObject(new Flares(), getX(), getY());
                getWorld().addObject(new Message("WIN", true), getWorld().getWidth()/2, getWorld().getHeight()/2);
                getWorld().removeObject(this);
            }
        }
    }
}