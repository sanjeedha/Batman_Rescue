public class StoryCommand implements ButtonCommand
{
    Receiver playReceiver;
    
    public void setReceiver(Receiver newRec)
    {
        this.playReceiver = newRec;
    }
    
    public void executeCommand()
    {
        playReceiver.performAction();
    }
}