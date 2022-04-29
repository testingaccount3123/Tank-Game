import greenfoot.*;  
public class RedProjectile extends Actor
{
    private int collisionCount=0,timer=0;
    public void act() 
    { 
      Actor Stone=(Stone) getOneIntersectingObject(Stone.class);
      int r = getRotation();
      
      if(getY()<=5 || getY()>=getWorld().getHeight()-5)
      {
        setRotation(-r);
        collisionCount+=1;
      }else if(getX()<=5 || getX()>=getWorld().getWidth()-5)
      {
        setRotation(-r+180);
        collisionCount+=1;
      }
      
      if(Stone!=null && Stone.getRotation()==0 || Stone!=null && Stone.getRotation()==180)
      { 
          GreenfootSound struck=new GreenfootSound("struck.mp3");
          struck.setVolume(48);
          struck.play();
          collisionCount+=1;
          setRotation(-r);
      }
      else if(Stone!=null && Stone.getRotation()==90 || Stone!=null && Stone.getRotation()==270)
      { 
          GreenfootSound struck=new GreenfootSound("struck.mp3");
          struck.setVolume(48);
          struck.play();
          collisionCount+=1;
          setRotation(-r+180);
      }
      
      move(7);
      timer++;
      
      if(collisionCount==2 || isTouching(BlueShield.class)
      || isTouching(Crate.class) || isTouching(MiniBlue.class) 
      || isTouching(Red.class) && timer>=11 || isTouching(RedBot.class) && timer>=11)
      {
          getWorld().removeObject(this); 
      }
    }    
}