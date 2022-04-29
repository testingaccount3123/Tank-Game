import greenfoot.*;  
import java.util.List;
public class Red extends Actor 
{
    double angleRadians;
    short t,count,i=1;
    boolean upKeyDown,canMove=false,upKeyDown1;
    int movt,coolDown=90,transTimer=0,x,y,angleDegrees,distX,distY;
    byte health=3,two=2;
    String ask;
    short s[]={4,3,2,1,0},d[]={0,5,4,3,2,1};//solo=s.....duo=d
    int previousChoice1=-1;
    int previousChoice2=-1;
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    static final int m=100;
    short r[]={695,105};
    short r2[]={105,495}; 
    int shoot=0,shootCount=1;
    byte firstShootCount=0;
    byte spawned=0;
    int thatX=100;
    int thatY=100;
    int thisDegree,thatDegree;
    RedProjectile rProjectile;
    MouseDetector mouseDet=new MouseDetector();
    GreenfootSound shootVol =new GreenfootSound("shoot.wav");
    int volume=40;
    public Red()
    {
      background = getImage();
    }
    
    protected void addedToWorld(World w)
    {
      //checks collision before addidng
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
        MyWorld world1 = (MyWorld) getWorld();
        DuoHelp();
        edgeCollision();
        ClickDuo();
        //ClickSolo();
        if(world1.solo==0 && world1.duo==1)
        doRotate();
        getPower();
        healthBar();
        decreaseHealth(); 
        death();
    } 
    
    public void DuoHelp()
    {
        MyWorld world1 = (MyWorld) getWorld();
        if(world1.duo==1)
        {
          if(canMove==false)
          {
            if(world1.counter.sec==2157)
            {
              firstShootCount=1;
              world1.getBackground().drawImage(new GreenfootImage("  Hey  "+world1.getName()+", \n tap on screen \n to move & shoot ",25,Color.GREEN , Color.BLACK),getWorld().getWidth()/2-80, 490);
              world1.getBackground().drawImage(new GreenfootImage("  Hey  "+world1.getName()+", \n tap on screen \n to move & shoot ",25,Color.YELLOW , new Color(0,0,0,0)),getWorld().getWidth()/2-79, 491);
            }
          }else if(canMove==true &&  firstShootCount==1)
          {
            world1.setBackground(new GreenfootImage("g2.png"));
            firstShootCount=2;
          }
        }
        if(world1.solo==1)
        {
          firstShootCount=1;
          if(count>=1 && Greenfoot.isKeyDown("up") &&  firstShootCount==1)
          {  
            world1.setBackground(new GreenfootImage("g2.png"));
            firstShootCount=2;
          }
        }
    }
    
    public void edgeCollision()
    {
        int w=getWorld().getWidth();
        int h=getWorld().getHeight();
        
        if(getX()>w-37)
          setLocation(w-35, getY());
        if(getY()>h-37)
          setLocation(getX(),h-35);
        if(getX()<37)
          setLocation(35, getY());
        if(getY()<37)
          setLocation(getX(), 35);
    }
    
    public void ClickDuo()
    {
      MyWorld world1 = (MyWorld) getWorld();
      byte ds=0;
      if(world1.solo==1 && world1.duo==0)
      {
        //move
        if(Greenfoot.isKeyDown("w"))
        {
           movt++;
        }else 
        {
           turn(t);
           movt=0;
        }
        if(movt>7)
        {
           ds++;
        }
        if(ds!=0)
        {
           move(ds*5);
        }
        
        //turn
        if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0 
           && upKeyDown1 != Greenfoot.isKeyDown("w"))
        {
          upKeyDown1= !upKeyDown1;
          if(upKeyDown1)
          count+=1;
        }
        if(count % 2==0)
        {
           t=4;
        }else t=-4;
        
        //collision
        Actor BlueShield=getOneIntersectingObject(BlueShield.class);
        Actor Blue=getOneIntersectingObject(Blue.class);
        List<RedBot> rbot = getNeighbours(49,true,RedBot.class);
        List<BlueBot> bbot = getNeighbours(49,true,BlueBot.class);
        if(!getObjectsInRange(30, Stone.class).isEmpty()
        || rbot.size() !=0
        || bbot.size() !=0
        || Greenfoot.isKeyDown("w") && BlueShield !=null
        || Greenfoot.isKeyDown("w") && Blue   !=null)
        {
          move(-ds*5);
          count=0;
        }
        
        //increase enemy score when it touches bullet
        if(isTouching(BlueProjectile.class))
        {
          world1.counter1.tscore++;
        }
        
        //shoot
        if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0 
           && upKeyDown != Greenfoot.isKeyDown("w"))
        {
            upKeyDown= !upKeyDown;
            //shoot
            if(upKeyDown)
            shoot++;
            if(upKeyDown && shoot<=6)
            {
                shootVol.setVolume(volume);
                shootVol.play();
                rProjectile=new RedProjectile();
                getWorld().addObject(rProjectile, getX(),getY());
                rProjectile.setRotation(getRotation());
            }
        }
      }
      
      if(Greenfoot.isKeyDown("w") && shoot>6)
      {
          GreenfootSound shoot =new GreenfootSound("reload.mp3");
          shoot.setVolume(52);
          shoot.play();
      }
      
      if(shoot>=6)
        coolDown--;
              
      if(coolDown==0)
      {
        shoot=0;
        coolDown=90;
      }
      if(world1.solo==1 && world1.duo==0)
      {
          if(shoot==0)
          {
            GreenfootImage loadingImage = new GreenfootImage("slotF.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   370, 570); 
          }else if(shoot==1)
          {
            GreenfootImage loadingImage = new GreenfootImage("slotE.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   370, 570); 
          }else if(shoot==2)
          {
            GreenfootImage loadingImage = new GreenfootImage("slotD.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   370, 570); 
          }else if(shoot==3)
          {
            GreenfootImage loadingImage = new GreenfootImage("slotC.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   370, 570); 
          }else if(shoot==4)
          {
            GreenfootImage loadingImage = new GreenfootImage("slotB.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   370, 570); 
          }else if(shoot==5)
          {
            GreenfootImage loadingImage = new GreenfootImage("slotA.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   370, 570); 
          }else if(shoot==6)
          {
            GreenfootImage loadingImage = new GreenfootImage("EmptyslotA.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   370, 570); 
          }
      }
      //Speed_Dec
      Actor SpeedDec=getOneIntersectingObject(SpeedDec.class);
      if(SpeedDec!=null)
        move(-ds*3);
    }
    
    public void ClickSolo()
    {
      MyWorld world1 = (MyWorld) getWorld();
      byte ds=0;
      MouseInfo mouse=Greenfoot.getMouseInfo();
      if(world1.solo==0 && world1.duo==1)
      {
         if(Greenfoot.mousePressed(null) && mouse!=null)
         {
            try
            {
                  x = mouse.getX();  
                  y = mouse.getY();
                 if(mouse.getButton()==1 & world1.counter.sec<=2150 && !(x>0 && x<45 && y>180 && y<432))
                 {
                    thatX=mouse.getX();
                    thatY=mouse.getY() ;
    
                    distX = mouse.getX() - getX();
                    distY = mouse.getY() - getY();
    
                    angleRadians = Math.atan2(distY, distX);
                    angleDegrees = (int)Math.toDegrees(angleRadians)/6*6;
                   
                    if(angleDegrees<0)
                        thatDegree=angleDegrees+360;
                    else
                        thatDegree=angleDegrees;
                        
                    getWorld().addObject(mouseDet, thatX, thatY);    
                 }
            }catch(Exception e){}
         }
         
         try
         {
             //movement mechanism
             if(spawned==1)
             {
                 x = mouse.getX();  
                 y = mouse.getY();
                 if(Math.abs(getX()-thatX)<40 && Math.abs(getY()-thatY)<40)
                   canMove=false;
                 if(canMove && !(x>0 && x<45 && y>180 && y<432))
                   ds++;
                 else if(!canMove) 
                   move(-ds*3);
                 
                 if(ds!=0 )
                 {
                   move(ds*3);
                 }
             }
         }catch(Exception e){}
         
         //collision
         Actor Sheild=getOneIntersectingObject(BlueShield.class);
         Actor Blue=getOneIntersectingObject(Blue.class);
         List<RedBot> rbot = getNeighbours(49,true,RedBot.class);
         List<BlueBot> bbot = getNeighbours(49,true,BlueBot.class);
         if(!getObjectsInRange(30, Stone.class).isEmpty() || isTouching(ShopBar.class)
         || rbot.size() !=0 || bbot.size() !=0
         || canMove && Sheild !=null
         || canMove && Blue   !=null)
         {
           move(-ds*3);
           count=0;
         }
         
         //increase enemy score when it touches bullet
         if(isTouching(BlueProjectile.class))
         {
           world1.counter.tscore++;
         }
         
         //shoot & movement mechanism
         if(mouse!=null && world1.counter.sec<2150)
         {
            ++shootCount;
           try
           {
               //movement mechanism
               int x = mouse.getX();  
               int y = mouse.getY();
               if(Greenfoot.mousePressed(null) 
                  && !(x>0 && x<45 && y>180 && y<432)) 
               {
                  canMove=true;
                  spawned=1;
               }
           }catch(Exception e){}
           
           //shoot
           if(Greenfoot.mousePressed(null))
             shoot++;
           if(Greenfoot.mousePressed(null) && shoot<=3)
           {
             shootVol.setVolume(volume);
             shootVol.play();
             rProjectile=new RedProjectile();
             getWorld().addObject(rProjectile, getX(),getY());
             rProjectile.setRotation(getRotation());
           }
         }
      }
      
      if(Greenfoot.mousePressed(null) && shoot>3)
      {
          GreenfootSound shoot =new GreenfootSound("reload.mp3");
          shoot.setVolume(52);
          shoot.play();
      } 
      
      if(shoot>3)
        i++;
      
      if(shoot>=3)
        coolDown--;
              
      if(coolDown==0)
      {
        shoot=0;
        coolDown=45;
      }
      
      if(world1.solo==0 && world1.duo==1)
      {
          if(shoot==0)
          {
            GreenfootImage loadingImage = new GreenfootImage("slot3.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   400, 580); 
          }else if(shoot==1)
          {
            GreenfootImage loadingImage = new GreenfootImage("slot2.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   400, 580); 
          }else if(shoot==2)
          {
            GreenfootImage loadingImage = new GreenfootImage("slot1.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   400, 580); 
          }else if(shoot==3)
          {
            GreenfootImage loadingImage = new GreenfootImage("Emptyslot.png");
            getWorld().addObject(new Slot() {{ setImage(loadingImage); }},   400, 580); 
          }
      }
    }
    
    public void doRotate()
    {
        thisDegree=getRotation();
        if(thatDegree-thisDegree==0)
          ClickSolo();
        else if(thatDegree-thisDegree>0&&thatDegree-thisDegree<=180 
                || thatDegree-thisDegree<=-180)
          turn(6);
        else 
          turn(-6);
    }
    
    public void getPower()
    {
        MyWorld world1 = (MyWorld) getWorld();
        
        //solo
        if(isTouching(Crate.class) && world1.duo==1)
        {
          GreenfootSound random =new GreenfootSound("random.mp3");
          random.setVolume(49);
          random.play();
          int size1=getWorld().getObjects(RedShield.class).size();
          int rand1=Greenfoot.getRandomNumber(d.length-size1-previousChoice1< 0 ? 0:4);
          rand1+=size1;
          if(previousChoice1>=0 && rand1 >= previousChoice1)rand1++;
          if(rand1==3)
          {
             do{
                ask=Greenfoot.ask("[PRESS 1/2/3/4/5] [1]:Enforcer ~~~~~~~ [2]:Invisibility Potion ~~~~~~~ [3]:Shield ~~~~~~~ [4]:Health Kit ~~~~~~~ [5]:Bonus Point");
             }while(!ask.equals("1") && !ask.equals("2") && !ask.equals("3") && !ask.equals("4") && !ask.equals("5") || ask.length()==0);
          }
          if(rand1==0 && world1.getObjects(RedShield.class).isEmpty() || ask!=null && ask.equals("3")&& world1.getObjects(RedShield.class).isEmpty())
            world1.addObject(new RedShield(),getX(),getY());
          else
          {
            if(rand1==5 || ask!=null && ask.equals("5") && !ask.equals("2") 
               && !ask.equals("1") && !ask.equals("4") && world1.getObjects(RedScore.class).isEmpty())
              getWorld().addObject(new RedScore(world1.counter),400,300);
            if(rand1==4 && health!=4 || health!=4 && ask!=null && ask.equals("4") && !ask.equals("2") 
               && !ask.equals("1") && !ask.equals("5") && world1.getObjects(RedHealth.class).isEmpty())
              getWorld().addObject(new RedHealth(),400,300 );
            if(rand1==2 || ask!=null && ask.equals("2")  && !ask.equals("5") 
               && !ask.equals("1") && !ask.equals("4") && world1.getObjects(Potion.class).isEmpty())
              getWorld().addObject(new Potion(2),getX(),getY());
            if(rand1==1 || ask!=null && ask.equals("1")  && !ask.equals("2") 
               && !ask.equals("5") && !ask.equals("4"))
              getWorld().addObject(new MiniRed(5,45), 0, 0);
          }
          int n1=d[rand1];
          previousChoice1=rand1;
        }
        
        //duo
        if(isTouching(Crate.class) && world1.solo==1)
        {
          GreenfootSound random =new GreenfootSound("random.mp3");
          random.setVolume(49);
          random.play();
          int size2=getWorld().getObjects(RedShield.class).size();
          int rand2=Greenfoot.getRandomNumber(s.length-size2-previousChoice2< 0 ? 0:3);
          rand2+=size2;
          if(previousChoice2>=0 && rand2 >= previousChoice2)rand2++;
          if(rand2==0 && world1.getObjects(RedShield.class).isEmpty())
          {
            world1.addObject(new RedShield(),getX(),getY());
          }else
          {
            if(rand2==1 && world1.getObjects(MiniRed.class).isEmpty())
              getWorld().addObject(new MiniRed(5,45),400,300 );
            if(rand2==2  && health!=3 && world1.getObjects(RedHealth.class).isEmpty())
              getWorld().addObject(new RedHealth(),400,300 );
            if(rand2==3 && world1.getObjects(RedScore.class).isEmpty())
              getWorld().addObject(new RedScore(world1.counter),400,300);
            if(rand2==4 && world1.getObjects(Potion.class).isEmpty())
              getWorld().addObject(new Potion(2),getX(),getY());
          }
          int n2=s[rand2];
          previousChoice2=rand2;
        }
    }
    
    public void healthBar()
    {
        if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0 && getWorld().getObjects(RedHealthBar.class).size()>0)
        {
             Red red = getWorld().getObjects(Red.class).get(0);
             RedHealthBar RedhealthBar = getWorld().getObjects(RedHealthBar.class).get(0);
             GreenfootImage image = new GreenfootImage(background);
             if(red.health==4)
             {
               RedhealthBar.setImage("extra life.png");
             }
             if(red.health==3)
             {
              RedhealthBar.setImage("full.png");
             }
             if(red.health==2 )
             {
               RedhealthBar.setImage("half.png");
             }
             if(red.health==1)
             {
               RedhealthBar.setImage("dead.png");
             }
        }
    }
    
    public void decreaseHealth()
    {
        Actor BlueProjectile=getOneIntersectingObject(BlueProjectile.class);
        if(BlueProjectile !=null)
        {
          health-=1;
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
    }
    
    public void death()
    {
        MyWorld world1 = (MyWorld) getWorld();
        Actor stone=getOneObjectAtOffset(0, 0,Stone.class);
        if(health ==0 || stone!=null)
        {
            world1.addObject(new Explosion(), getX(), getY());
            Greenfoot.playSound("laser_dead.wav");
            //world1.removeObject(world1.mouseDetector);
            getWorld().removeObject(this);
        }
    }
}