import greenfoot.*;  
import java.util.List;
public class RedBot extends Actor
{
    private int moveTimer = 120,transTimer=0;
    private int condition = 0;
    private boolean canMove = true;
    private int speed = 0;
    short health=150;
    short cratetimr;
    static final int m=100;
    short r[]={680,120};
    short r2[]={120,480};
    public RedBot(boolean canmove, int speedInt)
    {
        canMove = canmove;
        speed = speedInt;
    }
    
    protected void addedToWorld(World w)
    {
     int rand=Greenfoot.getRandomNumber(r.length);
     int rand2=Greenfoot.getRandomNumber(r2.length);
     int i=0;
     while(getOneIntersectingObject(Actor.class)!=null && i++<m)
     {
         setLocation(r[rand], r2[rand2]);
     }
     if(i>=m)
     {
         w.removeObject(this);
         return;
     }
    }
    
    public void act() 
    {
        if(canMove)
        {
            moveTimer++;
            if(moveTimer >= 180)
            {
                condition = (Greenfoot.getRandomNumber(4));
                moveTimer = 0;
            }
            checkTouching();
            bumpTank();
        }
        die();
    }   
    
    public void checkTouching()
    {
        Stone block = (Stone) getOneIntersectingObject(Stone.class);
        int r = getRotation();
        if(block == null)
        {
            moveAround();
        }
        else if(block != null)
        {
            if(r == 0)
            {
                setLocation(getX() - speed, getY());
                if(getY() < 300)
                {
                    condition = 3;
                }
                else if(getY() >= 300)
                {
                    condition = 2;
                }
            }
            else if(r == 90)
            {
                setLocation(getX(), getY() - speed);
                if(getX() < 500)
                {
                    condition = 1;
                }
                else if(getX() >= 300)
                {
                    condition = 0;
                }
            }
            else if(r == 180)
            {
                setLocation(getX() + speed, getY());
                if(getY() < 300)
                {
                    condition = 3;
                }
                else if(getY() >= 300)
                {
                    condition = 2;
                }
            }
            else if(r == 270)
            {
                setLocation(getX(), getY() + speed);
                if(getX() < 500)
                {
                    condition = 1;
                }
                else if(getX() >= 300)
                {
                    condition = 0;
                }
            }
        }
    }
    
    public void moveAround()
    {
        int turn=0;
        if(condition == 0 )
        {
            setLocation(getX() - speed, getY());
            if(getRotation()!=180 && !isTouching(Stone.class))
            {
              if(getRotation()!=180 && getX()<=26 
              || getRotation()!=180 && getX()>=getWorld().getWidth()-26
              || getRotation()!=180 && getY()<=26 
              || getRotation()!=180 && getY()>=getWorld().getHeight()-26)
              {
                turn=0; 
                setRotation(180);
                move(5);
              }
              else turn=+5;
              turn(turn);
              speed=0;
            }else speed=3;
        }
        else if(condition == 1)
        {
            setLocation(getX() + speed, getY());
            if(getRotation()!=0 && !isTouching(Stone.class))
            {
              if(getRotation()!=0 && getX()<=26 
              || getRotation()!=0 && getX()>=getWorld().getWidth()-26
              || getRotation()!=0 && getY()<=26 
              || getRotation()!=0 && getY()>=getWorld().getHeight()-26)
              {
                  turn=0;
                  setRotation(0);
                  move(5);
              }
              else turn=-5;
              turn(turn);
              speed=0;
            }else speed=3;
        }
        else if(condition == 2 )
        {
            setLocation(getX(), getY() - speed);
            if(getRotation()!=270 && !isTouching(Stone.class))
            {
              if(getRotation()!=270 && getX()<=26 
              || getRotation()!=270 && getX()>=getWorld().getWidth()-26
              || getRotation()!=270 && getY()<=26 
              || getRotation()!=270 && getY()>=getWorld().getHeight()-26)
              {
                  turn=0;
                  setRotation(270);
                  move(5);
              }
              else turn=+5;
              turn(turn);
              speed=0;
            }else speed=3;
        }
        else if(condition == 3)
        {
            setLocation(getX(), getY() + speed);
            if(getRotation()!=90 && !isTouching(Stone.class))
            {
              if(getRotation()!=90 && getX()<=26 
              || getRotation()!=90 && getX()>=getWorld().getWidth()-26
              || getRotation()!=90 && getY()<=26 
              || getRotation()!=90 && getY()>=getWorld().getHeight()-26)
              {
                  turn=0;
                  setRotation(90);
                  move(5);
              }
              else turn=-5;
              turn(turn);
              speed=0;
            }else speed=3;
        }
    }
    
    public void bumpTank()
    {
        int r = getRotation();
        List<BlueBot> bbot = getNeighbours(49,true,BlueBot.class);
        List<Crate> crate = getNeighbours(49,true,Crate.class);
        List<Red> red = getNeighbours(49,true,Red.class);
        List<Stone> stone = getNeighbours(36,true,Stone.class);
        List<Blue> blue = getNeighbours(49,true,Blue.class);
        if(red.size() !=0 || blue.size() !=0 || bbot.size() !=0 || stone.size()!=0
        || crate.size() !=0 || isTouching(MiniRed.class) || isTouching(MiniBlue.class)
        || getX()<=22 || getX()>=getWorld().getWidth()-22
        || getY()<=22 || getY()>=getWorld().getHeight()-22)
        {
            if(r == 0)
            {
                condition = 0;
            }
            else if(r == 90)
            {
                condition = 2;
            }
            else if(r == 180)
            {
                condition = 1;
            }
            else if(r == 270)
            {
                condition = 3;
            }
        }
    }
    
    public void die()
    {
       if(isTouching(BlueProjectile.class) && getWorld()!=null && getWorld().getObjects(Counter.class).size()>0)
       {
         Counter counter = getWorld().getObjects(Counter.class).get(0);
         counter.tscore++;
       }
       Actor BlueProjectile=getOneIntersectingObject(BlueProjectile.class);
       if(BlueProjectile !=null)
       {
         health-=50;
         transTimer=11;
         GreenfootSound shoot =new GreenfootSound("lost_life.wav");
         shoot.setVolume(87);
         shoot.play();
         getWorld().removeObject(BlueProjectile);
       }
       if(transTimer>0)
       {
          getImage().setTransparency(100);
          transTimer--;
       }
       else getImage().setTransparency(255);
       
       List<Crate> crate = getNeighbours(30,true,Crate.class);
       List<Stone> stone = getNeighbours(15,true,Stone.class);
       List<Red> red = getNeighbours(30,true,Red.class);
       List<Blue> blue = getNeighbours(30,true,Blue.class);
       List<BlueBot> bbot = getNeighbours(30,true,BlueBot.class);
       MyWorld world1 = (MyWorld) getWorld();
       if(health ==0 || crate.size() !=0 || stone.size() !=0 || red.size() !=0 
       || blue.size() !=0 || bbot.size() !=0
       || getX()<=17 || getX()>=getWorld().getWidth()-17
       || getY()<=17 || getY()>=getWorld().getHeight()-17)
       {
            world1.addObject(new Explosion(), getX(), getY());
            Greenfoot.playSound("laser_dead.wav");
            getWorld().removeObject(this);
       }
    }
}
