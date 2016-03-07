import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coronel here.
 * 
 * @author  
 * @version 
 */
public class Enemy3 extends Enemy
{
    /**
     * Act - do whatever the Enemy3 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       super.act(); 
    }   
    
    /**
     * Constructor for the Strategy Pattern
     */
    public Enemy3()
    {
        attackType = new BombEnemy();
    }
    
    /**
     * Enemy3 moves in a straight line, if the Enemy3 conveys the right edge of the world changes its position 5000 to re-appear in the world.
     */
    public void mover()
    {
        setLocation(getX()-7, getY());
        if(stepByPlayer())
        {
            setLocation(5000,getY());
        }
    }
    
    /**
     * Putting bombs in the world every 2 seconds.
     */
    public void attack()
    {  
       super.attack();
    }
}
