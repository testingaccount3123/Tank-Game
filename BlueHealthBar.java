import greenfoot.*;
public class BlueHealthBar extends Actor
{
    private Actor actor;
    public BlueHealthBar(Actor actor)
    {
        this.actor=actor;
    }
    public void act() 
    {
      getImage().scale(30, 12);
      if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0)
      {
        getImage().setTransparency(225);
        setLocation(actor.getX(), actor.getY()-35);
      }
      else 
        getImage().setTransparency(0);
    }     
}
