import greenfoot.*; 
public class FortunePointer extends Actor
{
    public void FortunePointer()
    {
        //getImage().scale(50, 50);
    }
    public void act() 
    {
        getImage().scale(40, 40);
        if(getWorld()!=null && getWorld().getObjects(Fortune.class).size()>0)
        {
          Fortune f = getWorld().getObjects(Fortune.class).get(0);
          setLocation(f.getX(), f.getY()-65);
        }
    }    
}
