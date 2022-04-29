import greenfoot.*;
import rccookie.game.raycast.*;
import rccookie.geometry.Vector2D;
import rccookie.geometry.Ray2D;
public class RedCannon extends Actor
{   private int timer = Greenfoot.getRandomNumber(50),transTimer=0;
    private boolean exists = true;
    private int charge = 0;
    private boolean tracking = false;
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    GreenfootSound shoot =new GreenfootSound("shoot.wav");
    int volume=40;
    public RedCannon(int speed, int rate)
    {
        charge = rate;
        background = getImage();
    }
    public RedCannon(int speed, int rate, boolean Tracking)
    {
        charge = rate;
        tracking = Tracking;
    }
    public void act() 
    {
        MyWorld world1 = (MyWorld) getWorld();
        if(getWorld()!=null && getWorld().getObjects(RedBot.class).size()>0)
        {
          RedBot rrbot = getWorld().getObjects(RedBot.class).get(0);
          setLocation(rrbot.getX(), rrbot.getY());
        }
        if(getWorld()!=null && getWorld().getObjects(RedBot.class).size()>0 && getWorld().getObjects(RedBotHlth.class).size()>0)
        {
             RedBot rrbot = getWorld().getObjects(RedBot.class).get(0);
             RedBotHlth redBotHlth = getWorld().getObjects(RedBotHlth.class).get(0);
             GreenfootImage image = new GreenfootImage(background);
             if(rrbot.health==150)
             {
               redBotHlth.setImage("full.png");
             }
             if(rrbot.health==100)
             {
               redBotHlth.setImage("half.png");
             }
             if(rrbot.health==50)
             {
               redBotHlth.setImage("dead.png");
             }
             if(rrbot.getImage().getTransparency()==100)
               transTimer=1;
        }
        if(transTimer>0)
        {
          getImage().setTransparency(70);
          transTimer--;
        }
        else getImage().setTransparency(255);
        
        if(exists)
        {
            if(!tracking)
            {
                fireMissile();
            }
        }
        checkTank();
        checkshooter();
    }    
    public void fireMissile()
    {
        timer++;
        if(timer >= charge && getWorld()!=null && getWorld().getObjects(Blue.class).size()>0 && getWorld().getObjects(Potion.class).isEmpty())
        {
            RedProjectile rProjectile1=new RedProjectile();
            Blue blue = getWorld().getObjects(Blue.class).get(0);
            Vector2D direction=new Vector2D(blue.getX()-getX(), blue.getY()-getY());
            Raycast result0 = Raycast.raycast(new Ray2D(Vector2D.angledVector(direction.angle()+90, 12),direction),getWorld(),Stone.class,Double.POSITIVE_INFINITY);
            Raycast result1 = Raycast.raycast(this, direction,Stone.class);
            Raycast result2 = Raycast.raycast(new Ray2D(Vector2D.angledVector(direction.angle()-90, 12),direction),getWorld(),Stone.class,Double.POSITIVE_INFINITY);
            boolean obstructed=result0.collided || result1.collided || result2.collided;
            if(!obstructed)
            {
              shoot.setVolume(volume);
              shoot.play();
              getWorld().addObject(rProjectile1, getX(),getY());
              rProjectile1.setRotation(getRotation());
              timer = 0;
            }
        }else if(timer >= charge && getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0)
        {
            RedProjectile rProjectile2=new RedProjectile();
            BlueBot bluebot = getWorld().getObjects(BlueBot.class).get(0);
            Vector2D direction=new Vector2D(bluebot.getX()-getX(), bluebot.getY()-getY());
            Raycast result0 = Raycast.raycast(new Ray2D(Vector2D.angledVector(direction.angle()+90, 12),direction),getWorld(),Stone.class,Double.POSITIVE_INFINITY);
            Raycast result1 = Raycast.raycast(this, direction,Stone.class);
            Raycast result2 = Raycast.raycast(new Ray2D(Vector2D.angledVector(direction.angle()-90, 12),direction),getWorld(),Stone.class,Double.POSITIVE_INFINITY);
            boolean obstructed=result0.collided || result1.collided || result2.collided;
            if(!obstructed)
            {
              shoot.setVolume(volume);
              shoot.play();
              getWorld().addObject(rProjectile2, getX(),getY());
              rProjectile2.setRotation(getRotation());
              timer = 0;
            }
        }
        
    }
    
    public void checkTank() 
    {
        MyWorld world1 = (MyWorld) getWorld();
        if(getWorld()!=null && getObjectsInRange(325,BlueBot.class).size()>0 && world1.play==1)
        {
            BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
            turnTowards(bbot.getX(), bbot.getY());
        }else if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0 && world1.play==0)
        {
            BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
            turnTowards(bbot.getX(), bbot.getY());
        }else if(getWorld()!=null &&  getWorld().getObjects(Blue.class).size()>0 && getWorld().getObjects(Potion.class).isEmpty())
        {
            Blue blue = getWorld().getObjects(Blue.class).get(0);
            turnTowards(blue.getX(), blue.getY());
        }
     }
    public void checkshooter() 
    {
       if( getWorld().getObjects(RedBot.class).isEmpty())
       {
          getWorld().removeObject(this);
       }
    }
}