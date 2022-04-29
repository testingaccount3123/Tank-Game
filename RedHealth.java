import greenfoot.*; 
public class RedHealth extends Actor
{
    short timer=0;
    int x=50,y=50,loop=0;
    public RedHealth()
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
     if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0)
     {
        Red red = getWorld().getObjects(Red.class).get(0);
        setLocation(red.getX(), red.getY());
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
             if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0)
             {
                 Red red = getWorld().getObjects(Red.class).get(0);
                 if(red.health<3 && world1.duo==0)
                    red.health+=1;
                  else if(red.health<4 && world1.duo==1)
                    red.health+=1;
             }
         }  
           
         if(getImage().getTransparency()==10)
           getWorld().removeObject(this);
     }
    }
}