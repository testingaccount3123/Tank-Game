import greenfoot.*; 
public class MouseDetector extends Actor
{
   //int count,trans;
   public void act() 
   {
       /*trans=getImage().getTransparency();
       if(trans<=0) 
         trans=getImage().getTransparency()+15;

       if(count%3==0)
       {
         trans-=15;
         getImage().setTransparency(trans);
       }
       
       count++;
       */
       MouseInfo mouse=Greenfoot.getMouseInfo();
       if(mouse!=null && Greenfoot.mousePressed(null))
       {
         int x = mouse.getX();  
         int y = mouse.getY();
         setLocation(x, y);
       }
   }    
}
