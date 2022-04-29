import greenfoot.*; 
public class Loading extends Actor
{
    int load;
    public void act() 
    {
      setImage(new GreenfootImage(300,200));
      getImage().setColor(Color.BLACK);
      getImage().fillRect(9,9,260,40);
      getImage().setColor(Color.DARK_GRAY);
      getImage().fillRect(14,14,250,30);
      getImage().setColor(Color.CYAN);
      getImage().fillRect(14,14,load,30);
      
      load++;
      if(load==59)
        load=140;
      if(load>=250)
      {
        load=251;
        getImage().setTransparency(0);
      }
    }   
}