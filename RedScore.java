import greenfoot.*; 
public class RedScore extends Actor
{
    Counter counter;
    short timer=0;
    int x=50,y=50,loop=0;
    public  RedScore(Counter counter)
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
         if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0)
         {
            Red red = getWorld().getObjects(Red.class).get(0);
            setLocation(red.getX(), red.getY());
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
                    world1.counter.bscore+=1;
                 if(world1.solo==1)
                   world1.counter1.bscore+=1;
             }  
               
             if(getImage().getTransparency()==10)
               getWorld().removeObject(this);
         }
    }
}