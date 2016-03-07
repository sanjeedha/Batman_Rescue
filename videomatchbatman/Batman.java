import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Batman extends Character implements Subject
{
    private GreenfootImage batmanImage; 
    private int lives; // Life of Players
    private int health; // Number of player health.
    private int points;
    
    private int numShots; //Number of shots that can make the player.
    private BatmanState hasShotState;
    private BatmanState outOfShotState;
    private BatmanState currState = outOfShotState;
    
    private GreenfootSound explosionSound;
    private GreenfootSound shotPlayerSound;
    
    private PointsObserver obs;
    
    /**
     * Batman builder class. Batman images are loaded, variable life, health, and points are initialized. Attacks sounds are loaded.
     */
    public Batman()
    {
        batmanImage = new GreenfootImage("bat.png");
   
        lives = 3;
        health = 50;
        this.setImage(batmanImage);
        numShots = 30;
        points=0;
        
        
        explosionSound = new GreenfootSound("explosionNave.wav");
        shotPlayerSound = new GreenfootSound("shotPlayer.wav");
       
        
        
        hasShotState = new HasShotState(this,shotTimer);
        outOfShotState = new OutOfShotState(this);
        if(numShots > 0){
            this.currState = hasShotState;
        }
        
        
    }
    
    /**
     * Act - do whatever the Batman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        this.mover();
        this.attack();
        this.isTouchingEnemy();        
        this.generateBonus();
        
    }    
    
  
    public void mover()
    {
        if(Greenfoot.isKeyDown("right"))
        {
            this.setImage(batmanImage);
            if(this.getX()+this.getImage().getWidth()/2 < getWorld().getWidth())
                this.setLocation(this.getX() + 5,this.getY());
        }
        else if(Greenfoot.isKeyDown("left"))
        {
            this.setImage(batmanImage);
            if(this.getX()-this.getImage().getWidth()/2 > 0)
                this.setLocation(this.getX() - 5, this.getY());
        }
        else if(Greenfoot.isKeyDown("up"))
        {
            this.setImage(batmanImage);
            if(this.getY()-this.getImage().getHeight()/2 > 0)
                this.setLocation(this.getX(), this.getY() - 5);
        }
        else if(Greenfoot.isKeyDown("down"))
        {
            this.setImage(batmanImage);
            if(this.getY()+this.getImage().getHeight()/2 < getWorld().getHeight())
                this.setLocation(this.getX(), this.getY() + 5);
        }
    }
    
    /**
     * When the player presses the spacebar, "Batman" will launch a shot. 
     */
    public void attack()
    {
        
        if(shotTimer.millisElapsed() > 200){
            if(Greenfoot.isKeyDown("space")){
                shotPlayerSound.play();
                currState.attackEnemy();
            }
        }
        
    }
    
    /**
     * Subtraction method 's' Player health.
     * And if health reaches 0 you subtract one life and restores 50 health.
     */
    public void reduceHealth(int s)
    {
        
        if(health > 0){
            health-=s;
            if(health <= 0){
                 lives--;
                 health = 50;
                 if(lives==0)
                    health=0;
            }
        }
        else if(health <= 0)
        {
            lives--;
            health = 50;
            if(lives==0)
                health=0;
        }
    }
    
    /**
     * Check if Batman touch an enemy and destroys it.
     */
    public void isTouchingEnemy()
    {
        Enemy e = (Enemy)getOneIntersectingObject(Enemy.class);
        Joker g = (Joker)getOneIntersectingObject(Joker.class);
        Harley h = (Harley)getOneIntersectingObject(Harley.class);


        if(e!=null)
        {
            Scenario es = (Scenario)getWorld();
            es.reduceEnemies();
            explosionSound.play();
            getWorld().addObject(new Flares(), e.getX(), e.getY());
            getWorld().removeObject(e);
            this.reduceHealth(5);
        }
        else if(g!=null)
        {
            if(g.getHealth() > 0)
            {
                this.reduceHealth(20);
                getWorld().addObject(new Flares(), getX()+getImage().getWidth()/2, getY());
            }
        }
        else if(h!=null)
        {
            if(h.getHealth() > 0)
            {
                this.reduceHealth(20);
                getWorld().addObject(new Flares(), getX()+getImage().getWidth()/2, getY());
            }
        }

    }
    
  
    public int getLives()
    {
        return lives;
    }
    
   
    public int getHealth()
    {
        return health;
    }
    
    /**
     * Returns the number of shots the player.
     */
    public int getNumShots()
    {
        return numShots;
    }
    
    
   
    public int getPoints()
    {
        return points;
    }
    
    
    /**
     * Assigns a number of shots
     */
    public void setNumShots(int d)
    {
        numShots = d;
    }
    
   
    public void setShots()
    {
        numShots = 300;
    }
    
    
    public void setHealth(int s)
    {
        health = s;
    }
    
    public void attach(PointsObserver points){
         Scenario scenario = (Scenario) getWorld();
         obs = scenario.getGamePoints();
    }
    
    /**
     * Assign a value to the points..
     */
    public void setPoints(int p)
    {
        points += p;
        notifyObservers();
    }
    
    public void notifyObservers(){
       
        obs.updatePoints(points); 
    }
    
    /**
     * Increase Life
     */
    public void increaseLife()
    {
        lives++;
    }
    
    /**
     * Randomly generated bonus throughout the game.
     */
    public void generateBonus()
    {
        if(numShots < 15)
        {
            if(Greenfoot.getRandomNumber(5000) < 20)
            {
                getWorld().addObject( new BonusShot(),
                Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
            }
        }
        
        if(Greenfoot.getRandomNumber(18000) < 5 && lives<5)
        {
            getWorld().addObject( new BonusLife(), 
            Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
        }
        
    }
    
   
    
    public void setCurrState(BatmanState state) {
        this.currState = state;
    }
    
    public BatmanState getOutOfShotState(){
        return this.outOfShotState;
    }
    
    public BatmanState getHasShotState(){
        return this.hasShotState;
    }
}
