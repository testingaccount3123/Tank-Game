import greenfoot.*;
public class BuyHealth extends Actor
{
    short buy;
    protected void addedToWorld(World w)
    {
       w.addObject(new Label2(buy,20), getX()+20, getY()+20);
    }
    public BuyHealth(short buy)
    {
        this.buy=buy;
    }
    public void act() 
    {
        getImage().scale(30, 30);
    }    
}
