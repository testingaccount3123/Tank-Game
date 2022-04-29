import greenfoot.*;
public class BlueBotHlth extends Actor
{
    public void act() 
    {
      getImage().scale(30, 12);
      if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0)
      {
        BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
        setLocation(bbot.getX(), bbot.getY()-35);
      }else getWorld().removeObject(this);
    }   
}
