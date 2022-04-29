import greenfoot.*; 
public class BlueHealth extends Actor
{
    short timer=0;
    int x=50,y=50,loop=0;
    public BlueHealth()
    {
       GreenfootSound shoot =new GreenfootSound("up.mp3");
       shoot.setVolume(45);
       shoot.play();
    }
    public void act() 
    {
      moveAround();
    }
    public void moveAround()
    {
     MyWorld world1 = (MyWorld) getWorld();
     if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0)
     {
        Blue blue = getWorld().getObjects(Blue.class).get(0);
        setLocation(blue.getX(), blue.getY());
     }
     if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0 && world1.duo==1)
     {
        BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
        setLocation(bbot.getX(), bbot.getY());
     }
     timer++;
     if(x!=20 && y!=20)
     {
        if(timer%4==0)
        {
            if(loop==0)
            {
              x-=(int)1;
              y-=(int)1;
            }
        }
     }
     getImage().scale(x, y);
     if(timer%2==0)
     {
         if(getImage().getTransparency()!=10)
           getImage().setTransparency(getImage().getTransparency()-5);
          
         if(getImage().getTransparency()==250)
         {
             if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0)
             {
                 Blue blue = getWorld().getObjects(Blue.class).get(0);
                 if(blue.health<3)
                    blue.health+=1;
             }
             
             if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0 && world1.duo==1)
             {
                 BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
                 if(bbot.health<200)
                    bbot.health+=50;
             }
         }  
           
         if(getImage().getTransparency()==10)
           getWorld().removeObject(this);
     }
    }
}