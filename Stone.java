import greenfoot.*; 
public class Stone extends Actor implements RaycastCollider
{
    public void act()
    {
      //MyWorld world1 = (MyWorld) getWorld();
      if(isTouching(Stone.class))
        getWorld().removeObject(this);
    }
}