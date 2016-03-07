import greenfoot.*; 

public class Menu extends World
{
    private GreenfootSound soundFondo;
    //Buttons
    private SanJoseCity sanjoseCity;
    private GothamCity gothamCity;
    private AboutGame aboutGame;
    private DisplayControls displayControls;
    
    //Commands
    private SanJoseCommand sanjoseCommand;
    private GothamCommand gothamCommand;
    private AboutCommand aboutCommand;
    private DisplayCommand displayCommand;
    
    protected GreenfootSound clicSound;

    public Menu()
    {    
        super(1171, 550, 1, false); 
        
        soundFondo = new GreenfootSound("shootToThrill.mp3");
        sanjoseCity = new SanJoseCity();
        gothamCity = new GothamCity();
        displayControls = new DisplayControls();
        aboutGame = new AboutGame();
        
        sanjoseCommand = new SanJoseCommand();
        gothamCommand = new GothamCommand();
        aboutCommand = new AboutCommand();
        displayCommand = new DisplayCommand();
        
        prepare();
    }
    
    public void act()
    {      
    }
    
    public void prepare()
    {
    
       addObject(sanjoseCity, -200, 200);
       addObject(gothamCity, -300, 300);
       addObject(displayControls, -300, 400);  
       addObject(aboutGame, -400, 500);  
        
        
       sanjoseCity.setCommand(sanjoseCommand);
       gothamCity.setCommand(gothamCommand);
       displayControls.setCommand(displayCommand);
       aboutGame.setCommand(aboutCommand);
        
       gothamCommand.setReceiver(
      
            new Receiver()
            {
                public void performAction()
                {
                    if(Greenfoot.mouseClicked(gothamCity)){
                        Greenfoot.setWorld(new Scenario("gotham"));
                    }
            }
           });
           
               
       sanjoseCommand.setReceiver(      
            new Receiver()          
            {
                public void performAction()
                {
                    if(Greenfoot.mouseClicked(sanjoseCity)){
                        //clicSound.play();
                        Greenfoot.setWorld(new Scenario("sanjose"));
                    }
            }
           });
           
        displayCommand.setReceiver(
        new Receiver()
            {
                public void performAction()
                {
                        if(Greenfoot.mouseClicked(displayControls)){
                                HowToPlay howtoplay = displayControls.getHowToPlay();
                                addObject(howtoplay, getWidth()/2, getHeight()/2);
                                        
                                addObject(new PreviousScreen(howtoplay), 
                                                howtoplay.getX() - howtoplay.getImage().getWidth()/2,
                                                    howtoplay.getY() - howtoplay.getImage().getHeight()/2);
                                
                            }
                }
           });

        aboutCommand.setReceiver(
        new Receiver()
            {
                public void performAction()
                {
                        if(Greenfoot.mouseClicked(aboutGame)){
                                AboutDesc aboutDesc = aboutGame.getAbout();
                                addObject(aboutDesc, getWidth()/2, getHeight()/2);
                                        
                                addObject(new PreviousScreen(aboutDesc), 
                                                aboutDesc.getX() - aboutDesc.getImage().getWidth()/2,
                                                    aboutDesc.getY() - aboutDesc.getImage().getHeight()/2);
                            }
                }
           });
        }
}
