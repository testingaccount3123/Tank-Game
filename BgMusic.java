import greenfoot.*;
public class BgMusic extends Actor
{
    int count1=0;
    public void act() 
    {
        MyWorld world1 = (MyWorld) getWorld();
        if(Greenfoot.mouseClicked(this))
        {
             count1+=1;
        }
        if(count1%2==0)
        {
            setImage("on.png");
            world1.bgSound.playLoop();
        }else if(count1%2!=0){setImage("off.png");world1.bgSound.pause();}
        getImage().scale(60, 30);
    }    
}
