import greenfoot.*;
public class RedHealthBar extends Actor
{
    private Actor actor;
    public RedHealthBar(Actor actor)
    {
        this.actor=actor;
    }
    public void act() 
    {
      getImage().scale(30, 12);
      if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0)
      {
        getImage().setTransparency(225);
        setLocation(actor.getX(), actor.getY()-33);
      }
      else getImage().setTransparency(0);
    }    
}