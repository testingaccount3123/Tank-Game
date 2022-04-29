import greenfoot.*;  
public class BlueScore extends Actor
{
    Counter counter;
    short timer=0;
    int x=40,y=40,loop=0;
    public  BlueScore(Counter counter)
    {
      this.counter=counter;
      GreenfootSound shoot =new GreenfootSound("up.mp3");
      shoot.setVolume(45);
      shoot.play();
    }
    public void act() 
    {
        move((int)5.2);
        moveAround();
    }    
    public void moveAround()
    {
         MyWorld world1 = (MyWorld) getWorld();
         if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0)
         {
            Blue blue = getWorld().getObjects(Blue.class).get(0);
            setLocation(blue.getX(), blue.getY());
         }else if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0)
         {
            BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
            setLocation(bbot.getX(), bbot.getY());
         }
         timer++;
         if(x!=20 && y!=20)
         {
            if(timer%5==0)
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
              
             if(getImage().getTransparency()<15)
             {
                 if(world1.duo==1)
                    world1.counter.tscore+=1;
                 if(world1.solo==1)
                   world1.counter1.tscore+=1;
             }  
               
             if(getImage().getTransparency()==10)
               getWorld().removeObject(this);
         }
    }
}