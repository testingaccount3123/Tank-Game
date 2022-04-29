import greenfoot.*; 
public class BlueShield extends Actor
{
    short timer,count;
    public void act() 
    {
      if(timer>490 && timer<610)
        count++;
      moveAround();
    }
    public void moveAround()
    {
     MyWorld world1 = (MyWorld) getWorld();
     if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0 )
     {
       Blue blue = getWorld().getObjects(Blue.class).get(0);
       setRotation(blue.getRotation()-94);
       turn(blue.t);
       setLocation(blue.getX(), blue.getY());
     }
     if(count<10 && count>0)
       getImage().setTransparency(255);
     else if(count>10 && count<20)
       getImage().setTransparency(0);
     else if(count>20) 
       count=0;
     if(isTouching(Blue.class) && ++timer>610 || isTouching(RedShield.class))
     {
       removeTouching(BlueShield.class);   
       removeTouching(RedShield.class);   
       getWorld().removeObject(this);   
     }
    }
}