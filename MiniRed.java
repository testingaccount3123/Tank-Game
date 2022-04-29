import greenfoot.*;
import rccookie.game.raycast.*;
import rccookie.geometry.Vector2D;
import rccookie.geometry.Ray2D;
public class MiniRed extends Actor
{
    private int timer = Greenfoot.getRandomNumber(50);
    private int charge = 0;
    private boolean tracking = false;
    private boolean exists = true;
    int removeTimer=0,count=0;
    GreenfootSound shoot =new GreenfootSound("shoot.wav");
    int volume=40;
    public MiniRed(int speed, int rate)
    {
        charge = rate;
        getImage().scale(20, 20);
    }
    public MiniRed(int speed, int rate, boolean Tracking)
    {
       charge = rate;
       tracking = Tracking;
    }
    public void act() 
    {
      MyWorld world1 = (MyWorld) getWorld();
        
      removeTimer++;
      if(removeTimer>380 && removeTimer<500)
        count++;
      
      if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0)
      {
         Red red = getWorld().getObjects(Red.class).get(0);
         setLocation(red.getX(), red.getY()+50);
      }
      
      if(isTouching(MiniRed.class))
        setLocation(getX()-40, getY());
        
      if(isTouching(Stone.class) || isTouching(BlueBot.class) || getWorld().getObjects(Red.class).isEmpty() || isTouching(Crate.class))
      {
        getImage().setTransparency(0);
      }else if(!isTouching(BlueBot.class) || !isTouching(Crate.class) || !isTouching(Stone.class) || !getWorld().getObjects(Red.class).isEmpty()) 
        getImage().setTransparency(255);
        
      if(exists)
      {
         if(!tracking)
         {
            fireMissile();
         }
      }
      Remove();
    }
    public void fireMissile()
    {
        timer++;
        if(timer >= charge && !getWorld().getObjects(Red.class).isEmpty() && !isTouching(Stone.class) && !isTouching(Crate.class) && getWorld().getObjects(BlueBot.class).size()>0)
        {
            RedProjectile rProjectile=new RedProjectile();
            BlueBot bluebot = getWorld().getObjects(BlueBot.class).get(0);
            Vector2D direction=new Vector2D(bluebot.getX()-getX(), bluebot.getY()-getY());
            Raycast result0 = Raycast.raycast(new Ray2D(Vector2D.angledVector(direction.angle()+90, 12),direction),getWorld(),Stone.class,Double.POSITIVE_INFINITY);
            Raycast result1 = Raycast.raycast(this, direction,Stone.class);
            Raycast result2 = Raycast.raycast(new Ray2D(Vector2D.angledVector(direction.angle()-90, 12),direction),getWorld(),Stone.class,Double.POSITIVE_INFINITY);
            boolean obstructed=result0.collided || result1.collided || result2.collided;
            Red red = getWorld().getObjects(Red.class).get(0);
            if(!obstructed)
            {
              shoot.setVolume(volume);
              shoot.play();
              turnTowards(bluebot.getX(), bluebot.getY());
              getWorld().addObject(rProjectile, getX(),getY());
              rProjectile.getImage().scale(17, 7);
              rProjectile.setRotation(getRotation());
              timer = 0;
            }else setRotation(red.getRotation());
        }
    }
    public void Remove()
    {
      if(count<10 && count>0 && !isTouching(Stone.class) && !isTouching(Crate.class))
        getImage().setTransparency(255);
      else if(count>10 && count<20)
        getImage().setTransparency(0);
      else if(count>20) 
        count=0;
      MyWorld world1 = (MyWorld) getWorld();
      if(removeTimer>500 || isTouching(MiniRed.class))
      {
        Greenfoot.playSound("laser_dead.wav");
        world1.addObject(new Explosion(), getX(), getY());
        getWorld().removeObject(this);
      }
    }
}