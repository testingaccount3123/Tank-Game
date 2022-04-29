import greenfoot.*;
public class Slot extends Actor
{
    public void act() 
    {
        if(isTouching(Slot.class))
          getWorld().removeObject(this);
    }    
}
