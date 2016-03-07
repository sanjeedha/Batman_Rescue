import greenfoot.*; 

public class Scenario extends World
{
    private Batman batman;
    
    private GreenfootImage bgImage; 
    private GreenfootSound bgSound;
    private GreenfootSound bgSound2;
    private GreenfootSound bgSound3;
    private GreenfootSound actualSound;
    
    private ScoreUpdater cntLife;
    private ScoreUpdater cntHealth;
    private ScoreUpdater cntShots;
    private ScoreUpdater cntEnemies;
    private ScoreUpdater cntPoints;
    private ScoreUpdater cntHealthJoker;
    private ScoreUpdater cntHealthHarley;    
    private GamePoints gamePoints;
    
    private int x;         
    private int x2;       
    
    private int numEnemies;
    private int level;
    private int trackStage; 
    private int stage;
    private boolean lostPlayer;
    private String track;
    private Harley harley;
    private Joker gb;
    
    long lastAdded = System.currentTimeMillis();
    public Scenario()
    {    
       super(1000,600, 1,false);       
    }

    

    public Scenario(String loctrack)
    {    

        super(1250,600, 1,false); 
        x = 0;
        stage = 1;
        trackStage = 0;
        //depending on the track selected, change the images
        if(loctrack == "gotham"){             
             numEnemies = 20;             
             bgImage = new GreenfootImage("2.jpg");   
        }else if(loctrack == "sanjose"){
            numEnemies = 30;            
            bgImage = new GreenfootImage("backsan.jpg");
         }else{
            System.out.println("Invalid ck");
        }
                
        prepare();
        
        track = loctrack;
        bgSound = new GreenfootSound("TN.mp3");
        bgSound2 = new GreenfootSound("EraTheMass.mp3");
        bgSound3 = new GreenfootSound("AcesHigh.mp3");
        trackStage = 0;
        
        lostPlayer = false;
        
        gb = new Joker();
        harley = new Harley();
    }
    
   
    public void act() 
    {
        if(batman.getLives() > 0)
        {
           // if(track.equals("gotham"))
            {
               {   
                    bgSound.playLoop();
                    actualSound = bgSound;
                    
                    if(trackStage == 0)
                    {
                        cntEnemies.setValue(numEnemies);
                        setupTrack1();
                        trackStage++;
                    }
                
                    if(numEnemies <= 50 && trackStage < 3)
                    {
                        prepareTrack1();                   
                    }
                
                    if(numEnemies == 8 && trackStage == 1)
                    {
                        prepareTrack1();
                        addObject(new BonusShot(), 300,300);
                        addObject(new BonusShot(), 350,300);
                        addObject(new BonusShot(), 400,350);
                        trackStage = 2;
                    }
                    if(trackStage == 2 && numEnemies == 0 && track.equals("gotham")){
                        
                        prepareLevel3();
                        addObject(new Message("NEXTLEVEL", false), getWidth()/2, getHeight()/2);                                       
                        batman.setNumShots(batman.getNumShots() + 200); 
                        batman.setHealth(50);
                        
                    }
                    else if(trackStage == 2 && numEnemies == 0 && track.equals("sanjose")){
                        
                        prepareLevel4();
                        addObject(new Message("NEXTLEVEL", false), getWidth()/2, getHeight()/2);                                       
                        batman.setNumShots(batman.getNumShots() + 200); 
                        batman.setHealth(50);
                        
                    }
                  
                    this.scroll();
                }
            }
        }
        else if(batman.getLives() <= 0 && lostPlayer == false) 
        {
            addObject(new Message("DEFEAT", true), getWidth()/2, getHeight()/2);
            lostPlayer = true;
            Greenfoot.stop();
        }
        this.updateScoreUpdaters();
    }

    public void scroll()
    {
        getBackground().drawImage(bgImage,x,0);
        x-=10;
        if(x == -500)
            x2 = 1000;
        if(x < -500)
        {
            getBackground().drawImage(bgImage,x2,0);
            x2-=10;
        }
        if(x == -1500)
            x = 0;
    }


    private void prepare()
    {
        batman = new Batman();
        addObject(batman, 93, 249);

        cntLife = new ScoreUpdater("Lives : ");
        cntHealth = new ScoreUpdater("Health : ");
        cntShots= new ScoreUpdater("Shots : ");
        cntEnemies = new ScoreUpdater();
        cntPoints = new ScoreUpdater("Points : ");
        gamePoints = new GamePoints(0);

        cntLife.setValue(batman.getLives());
        cntHealth.setValue(batman.getHealth());
        cntShots.setValue(batman.getNumShots());
        cntEnemies.setPrefix("Enemies : " + numEnemies + "/");
        //cntEnemies.setValue(54);
        cntPoints.setValue(batman.getPoints());

        addObject(cntLife,100,570);
        addObject(cntHealth,300,570);
        addObject(cntShots,500,570);
        addObject(cntEnemies, 700, 570);
        addObject(cntPoints,900, 570);
        batman.attach(gamePoints);
        //addObject(gamePoints,1100,570);
        
    }
    
    public GamePoints getGamePoints(){
        return gamePoints;
    }
    

    private void setupTrack1()
    {
        
        EnemyFactory theEnemyFactory = new EnemyFactory();
        Enemy  theEnemy = null;       
        int enemyType = Greenfoot.getRandomNumber(3);        
        if(enemyType >= 0){
            theEnemy = theEnemyFactory.selectEnemyFactory(enemyType);
            addObject(theEnemy, Greenfoot.getRandomNumber(5000), Greenfoot.getRandomNumber(450));           
        }        
    }
    
    private void prepareTrack1()
    {
        EnemyFactory theEnemyFactory = new EnemyFactory();
        Enemy  theEnemy = null;
        long curTime  = System.currentTimeMillis();
        int enemyType = Greenfoot.getRandomNumber(3);
        if(enemyType >= 0 && curTime >= lastAdded + 2500){        
            theEnemy = theEnemyFactory.selectEnemyFactory(enemyType);
            addObject(theEnemy, Greenfoot.getRandomNumber(5000), Greenfoot.getRandomNumber(450));
            lastAdded  = curTime;
        }
        
    }
    
       public void prepareLevel3()
    {        
        addObject(gb, getWidth()+gb.getImage().getWidth(), getHeight()/2);        
        removeObject(cntEnemies);        
        cntHealthJoker = new ScoreUpdater();
        cntHealthJoker.setPrefix("JOKER: " + gb.getHealth() + "/80");
        addObject(cntHealthJoker, 925, 485);
    }
    
       
    public void prepareLevel4()
    {
        
        addObject(harley, getWidth()+harley.getImage().getWidth(), getHeight()/2);        
        removeObject(cntEnemies);        
        cntHealthHarley = new ScoreUpdater();
        cntHealthHarley.setPrefix("HARLEY: " + harley.getHealth() + "/80");
        addObject(cntHealthHarley, 925, 485);
    }
    
    
    public Batman getBatman()
    {
        return batman;
    }

    public void updateScoreUpdaters()
    {
        cntLife.setValue(batman.getLives());
        cntHealth.setValue(batman.getHealth());
        cntShots.setValue(batman.getNumShots());
        cntEnemies.setPrefix("Enemies : " + numEnemies + "/");
        cntPoints.setValue(batman.getPoints());
       
    }
    
    public void reduceEnemies()
    {
        numEnemies--;
    }
    
    
    public int getLevel()
    {
        return level;
    }
    
      public GreenfootSound getActualSound()
    {
        return actualSound;
    }
    
}
