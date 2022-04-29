import greenfoot.*;
public class AboutShop extends Actor
{
    short click=0;
    GreenfootImage img1=new GreenfootImage("AboutShop.png");
    GreenfootImage img2=new GreenfootImage("Close.png");
    ShopHelp helpPng=new ShopHelp();
    public AboutShop()
    {
       getImage().scale(37, 37);
    }
    public void act() 
    { 
        if(Greenfoot.mouseClicked(this))
        {
          getImage().setTransparency(90);
          Greenfoot.delay(10);
          click++;
          getImage().setTransparency(255);
        }
        if(click%2==0)
        {
          getWorld().removeObject(helpPng);
          setImage(img1);
          img1.scale(37, 37);
        }else if(click%2!=0)
        {
          setImage(img2);
          img2.scale(37, 37);
          getWorld().addObject(helpPng, 400, 300);
        }
    }
}