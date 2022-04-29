import greenfoot.*;
public class Shop extends Actor
{
    String buy;
    int r,g,b,t;
    public Shop(String buy, int r, int g, int b,int t)
    {
       this.r=r; this.g=g; this.b=b; this.t=t; 
       this.buy=buy;
       setImage(new GreenfootImage(buy,17,Color.WHITE,new Color(r,g,b,t)));
    }
    public void act() 
    {
       setImage(new GreenfootImage(buy,17,Color.WHITE,new Color(r,g,b,t)));
       if(isTouching(Shop.class))getWorld().removeObject(this);
    }    
}
