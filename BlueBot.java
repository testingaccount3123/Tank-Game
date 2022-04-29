import greenfoot.*;  
import java.util.List;
import rccookie.game.raycast.*;
import rccookie.geometry.Vector2D;
import rccookie.geometry.Ray2D;
public class BlueBot extends Actor
{
    private int moveTimer = 120,transTimer=0;
    private int condition;
    private boolean canMove = true;
    private int speed = 0;
    int time=0;
    short s[]={0,1};
    private Integer lastDraw;
    short health=150;
    BlueShield bshield = new BlueShield();
    short cratetimr;
    static final int m=100;
    short r[]={680,120};
    short r2[]={120,480};
    byte turn=0;
    int turnTime=0;
    int minDist=Integer.MAX_VALUE;
    Stone min=null;
    public BlueBot(boolean canmove, int speedInt)
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
        MyWorld world = (MyWorld) getWorld();
        
        //int oldX=getX(), oldY=getY();
        
        if(getRotation()%6!=0)
          setRotation(0);
          
        TouchCrate();
           
        //update() ;
        if(canMove)
        {
            moveTimer++;
            if(moveTimer >= 120)
            {
               condition = (Greenfoot.getRandomNumber(4));
               moveTimer = 0;
            }
            checkTouching();
            bumpTank();
        }
        getPower();
        
        //if(world.duo==1 && oldX==getX() && oldY==getY()) 
        //  moveBack();
        
        die();
    }   
    public void moveBack()
    {
        for(Stone w:getWorld().getObjects(Stone.class))
        {
            int dx=getX()-w.getX(), dy=getY()-w.getY();
            int dist=dx*dx+dy*dy;
            if(dist<minDist)
            {
              minDist=dist;
              min=w;
            }
        }
        if(min!=null)
        {
          turnTowards(min.getX(), min.getY());
          turn(180);
          move(4);
        }
        //System.out.println("ihwwqdd");
    }
    public void TouchCrate()
    {
        MyWorld world = (MyWorld) getWorld();
       
        /*if(getWorld()!=null && world.duo==1 &&
           getWorld().getObjects(Crate.class).size()>0 &&
           getObjectsInRange(40, Stone.class).isEmpty() &&
           getObjectsInRange(50, Red.class).isEmpty())
        {
          Actor crate1 = (Actor)getWorld().getObjects(Crate.class).get(0);
          
          Vector2D direction=new Vector2D(crate1.getX()-getX(), crate1.getY()-getY());
          
          Raycast result0 = Raycast.raycast(new Ray2D(Vector2D.angledVector(direction.angle()+90,
          13),direction),getWorld(),Stone.class,Double.POSITIVE_INFINITY);
          
          Raycast result1 = Raycast.raycast(this, direction,Stone.class);
          
          Raycast result2 = Raycast.raycast(new Ray2D(Vector2D.angledVector(direction.angle()-90,
          13),direction),getWorld(),Stone.class,Double.POSITIVE_INFINITY);
          
          Raycast result3 = Raycast.raycast(new Ray2D(Vector2D.angledVector(direction.angle(),
          13),direction),getWorld(),Stone.class,Double.POSITIVE_INFINITY);
          
          boolean obstructed= result0.collided || result1.collided || result2.collided || result3.collided;
          if(!obstructed)
          {
               canMove=false;
               turnTowards(crate1.getX(), crate1.getY());
               move(3);
               //setRotation(0);
          }else if(obstructed)
          {
              canMove=true;
          }
        }else
        {
             moveAround();
             bumpTank();
             move(-speed);
             canMove=true;
             return;
        }
        */
         
        if(getWorld()!=null && world.duo==1 &&
           getWorld().getObjects(Crate.class).size()>0 &&
           getNeighbours(43,true, Stone.class).isEmpty() &&
           getNeighbours(50,true, Red.class).isEmpty())
        {
          Actor crate1 = (Actor)getWorld().getObjects(Crate.class).get(0);
          if(!getObjectsInRange(254, Crate.class).isEmpty())
          {
               canMove=false;
               turnTowards(crate1.getX(), crate1.getY());
               move(3);
               //setRotation(0);
          }else {canMove=true; }
        }else canMove=true; 
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
        turnTime++;
        
        if(condition == 0)
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
              if(turnTime%2==0)turn=+6;
              else turn=0;
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
              if(turnTime%2==0)turn=-6;
              else turn=0;
              turn(turn);
              speed=0;
            }else speed=3;
        }
        else if(condition == 2)
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
              if(turnTime%2==0)turn=+6;
              else turn=0;
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
              if(turnTime%2==0)turn=-6;
              else turn=0;
              turn(turn);
              speed=0;
            }else speed=3;
        }
    }
    
    public void bumpTank()
    {
        int r = getRotation();
        MyWorld world1 = (MyWorld) getWorld();
        List<RedBot> rbot = getNeighbours(45,true,RedBot.class);
        List<Red> red = getNeighbours(49,true,Red.class);
        List<Blue> blue = getNeighbours(49,true,Blue.class);
        List<Stone> stone = getNeighbours(36,true,Stone.class);
        List<Crate> crate = getNeighbours(49,true,Crate.class);
        if(red.size() !=0 || blue.size() !=0 || rbot.size() !=0 || stone.size() !=0 
        || crate.size() !=0 && world1.solo==1 || isTouching(MiniRed.class) 
        || isTouching(MiniBlue.class) || isTouching(ShopBar.class)
        || getX()<=22 || getX()>=getWorld().getWidth()-22
        || getY()<=22 || getY()>=getWorld().getHeight()-22)
        {
           if(r==0)
            {
                condition = 0;
            }
            else if(r==90)
            {
                condition = 2;
            }
            else if(r==180)
            {
                condition = 1;
            }
            else if(r==270)
            {
                condition=3;
            }
        }
    }
    
    public void getPower()
    {
        MyWorld world1 = (MyWorld) getWorld();
        if(world1.duo==1)
        {
         if(isTouching(Crate.class))
         {
           GreenfootSound random =new GreenfootSound("random.mp3");
           random.setVolume(49);
           random.play();
           int rand=Greenfoot.getRandomNumber(s.length-(lastDraw!=null ? 1:0));
           if(lastDraw!=null && rand >= lastDraw)
             rand++;
           lastDraw=new Integer(rand);
           if(rand==1)
             getWorld().addObject(new BlueScore(world1.counter),400,300);
             
           if(rand==0 && health!=200)
             getWorld().addObject(new BlueHealth(),400,300 );
           else if(rand==0 && health==200)
             getWorld().addObject(new BlueScore(world1.counter),400,300);
         }
        }
    }
    
    public void die()
    {
       if(isTouching(RedProjectile.class) && getWorld()!=null && getWorld().getObjects(Counter.class).size()>0)
       {
         Counter counter = getWorld().getObjects(Counter.class).get(0);
         counter.bscore++;
       }
       Actor RedProjectile=getOneIntersectingObject(RedProjectile.class);
       if(RedProjectile !=null)
       {
         health-=50;
         transTimer=11;
         GreenfootSound shoot =new GreenfootSound("lost_life.wav");
         shoot.setVolume(87);
         shoot.play();
         getWorld().removeObject(RedProjectile);
       }
       if(transTimer>0)
       {
          getImage().setTransparency(100);
          transTimer--;
       }
       else getImage().setTransparency(255);
       
       List<Stone> stone = getNeighbours(20,true,Stone.class);
       List<Red> red = getNeighbours(30,true,Red.class);
       List<Blue> blue = getNeighbours(30,true,Blue.class);
       List<BlueBot> bbot = getNeighbours(30,true,BlueBot.class);
       List<Crate> crate = getNeighbours(30,true,Crate.class);
       MyWorld world1 = (MyWorld) getWorld();
       if(health ==0 || crate.size() !=0  && world1.solo==1 || stone.size() !=0 
       || red.size() !=0 || blue.size() !=0 || bbot.size() !=0 
       || getX()<=18 || getX()>=getWorld().getWidth()-18
       || getY()<=18 || getY()>=getWorld().getHeight()-18)
       {
          world1.addObject(new Explosion(), getX(), getY());
          Greenfoot.playSound("laser_dead.wav");
          getWorld().removeObject(this);
       }
    }
}
