import greenfoot.*;
public class Potion extends Actor
{
    short timer=0;
    int tank;
    int x=50,y=50,loop=0,removeTimer=0;
    public Potion(int tank)
    {
       //[Blue tank] 1 = blue 
       //[Red tank]  2 = red
       this.tank=tank;
       GreenfootSound shoot =new GreenfootSound("up.mp3");
       shoot.setVolume(45);
       shoot.play();
    }
    public void act() 
    {
      MyWorld world1 = (MyWorld) getWorld();
      if(isTouching(Blue.class) && tank==1 && getImage().getTransparency()<=110)
      {
         Blue blue = getWorld().getObjects(Blue.class).get(0);
         blue.getImage().setTransparency(95);
      }
      if(isTouching(Red.class) && tank==2 && getImage().getTransparency()<=110)
      {
         Red red = getWorld().getObjects(Red.class).get(0);
         red.getImage().setTransparency(95);
      }
      moveAround();
    }
    public void moveAround()
    {
     MyWorld world1 = (MyWorld) getWorld();
     if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0 && tank==1)
     {
        Blue blue = getWorld().getObjects(Blue.class).get(0);
        setLocation(blue.getX(), blue.getY());
     }
     if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0  && tank==2)
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
     if(timer%3==0)
     {
         if(getImage().getTransparency()!=10)
           getImage().setTransparency(getImage().getTransparency()-5);
          
         if(getImage().getTransparency()==250)
         {
             if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0 && tank==1)
             {
                 Blue blue = getWorld().getObjects(Blue.class).get(0);
                 blue.getImage().setTransparency(60);
             }
             
             if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0 && tank==2)
             {
                 Red red = getWorld().getObjects(Red.class).get(0);
                 red.getImage().setTransparency(60);
             }
         }  
           
         if(getImage().getTransparency()==10)
           removeTimer++;
     }
     if(removeTimer>110)
       getWorld().removeObject(this);
    }
}
