import greenfoot.*;
public class Home extends Actor
{
    String ask;
    public Home()
    {
       getImage().scale(35, 35);
    }
    public void act() 
    { 
      MyWorld w = (MyWorld) getWorld();
      if(Greenfoot.mouseClicked(this))
      {
        do{
          ask=Greenfoot.ask("CONFIRM to go back to Home Screen (PRESS 1 TO CONFIRM ELSE 2 TO DECLINE)");
        }while(!ask.equals("1") && !ask.equals("2"));
        if(ask.equals("2"))
          return;
        else if(ask.equals("1"))
        {
            getImage().setTransparency(90);
            Greenfoot.delay(5);
            getImage().setTransparency(255);
            Greenfoot.delay(5);
            if(w.click3<=1 || w.click2<=4 || w.click4<=1 || w.click5<=1  || w.click1<=4)
               w.g=0;
           
            w.redSpawn.thisDegree=0;w.redSpawn.thatDegree=0;w.redSpawn.canMove=false;
            w.redSpawn.setRotation(0);
            
            w.duo=0; w.solo=0; w.play=0; w.autoPlay=0; w.spawnStone=0;
            w.counter.sec=2159; w.counter.bscore=0; w.counter.tscore=0;
            w.counter1.sec=2159;w.counter1.min=0;w.counter1.bscore=0;w.counter1.tscore=0;
            w.available1=3;w.available2=2;w.available3=1;w.available4=1;w.available5=1;
            w.click=0; w.shop=0;
            w.prepare();
        }
      }
    }    
}
