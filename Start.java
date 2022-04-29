import greenfoot.*; 
public class Start extends World
{
    int x=50,y=50,loop=0,loop1=0,volume=90;
    Color gray=new Color(40,40,40);
    static GreenfootSound bgSound;
    CalendarActor calendar=new CalendarActor();
    GreenfootImage img=new GreenfootImage(250,158);
    LogoText logoText =new LogoText();
    LogoText1 logoText1 =new LogoText1();
    CounterShop counterShop=new CounterShop();
    public String getName()
    {
        UserInfo myData = UserInfo.getMyInfo();
        if (myData == null)
          return "Anonymous";
        return myData.getUserName();
    }
    public Start()
    {    
        super(800, 600, 1); 
        Greenfoot.start();
        Greenfoot.setSpeed(50);
        
        setPaintOrder(Label2.class,LogoText.class,LogoText1.class,ATitleScreen.class,CalendarActor.class);
         
        GreenfootImage bg=getBackground();
        bg.setColor(gray);
        bg.fill();
        
        //to store date
        if(getName().length()!=0)
        {
            if(UserInfo.isStorageAvailable()) 
            {
                UserInfo myData2 = UserInfo.getMyInfo();
                if (myData2 != null) 
                {
                    int newValue2 = calendar.storeDay();
                    if (!"".equals(newValue2))
                    {
                        myData2.setInt(2,newValue2);
                    }
                        myData2.store();
                }
            }
            
            //to store month
            if(UserInfo.isStorageAvailable()) 
            {
                UserInfo myData3 = UserInfo.getMyInfo();
                if (myData3 != null) 
                {
                    int newValue3 = calendar.storeMonth();
                    if(!"".equals(newValue3))
                    {
                        myData3.setInt(3,newValue3);
                    }
                        myData3.store();
                }
            }
            
            //to store year
            if(UserInfo.isStorageAvailable()) 
            {
                UserInfo myData4 = UserInfo.getMyInfo();
                if (myData4 != null) 
                {
                    int newValue4 = calendar.storeYear();
                    if(!"".equals(newValue4))
                    {
                        myData4.setInt(4,newValue4);
                    }
                        myData4.store();
                }
            }
        }
        
        if(bgSound!=null)
        {
            bgSound.stop();
        }
        
        bgSound=new GreenfootSound("User_for_fun@spotify.mp3");
        bgSound.setVolume(volume);
        bgSound.playLoop();
        
        
        addObject(new ATitleScreen(), getWidth()/2, 226);
        
        Color gray1=new Color(90,90,90);
        img=new GreenfootImage(261,114);
        img.setColor(Color.BLACK);
        img.fillRect(45, 49, 179, 55);
        img.setColor(gray1);
        img.fillRect(47, 51, 175, 50);
        img.setColor(Color.BLACK);
        img.fillRect(52,56, 165, 40);
        getBackground().drawImage(img, -40, 422);
        addObject(new Label(" REPORT USERS ",25), 95, 500);
    }
    public void act()
    {
        MouseInfo mouse=Greenfoot.getMouseInfo();
        if(mouse!=null && Greenfoot.mousePressed(null))
        {
            int x = mouse.getX();  
            int y = mouse.getY();
            
            if(x>5 && x<183 && y>470 && y<525 && getName().equals("Roshan123"))
            {
              String s=Greenfoot.ask("PASSWORD");
              if(s.equals("rsb"))
                Greenfoot.setWorld(new Choose());
            }else if(x>5 && x<183 && y>470 && y<525)
            {
               addObject(new Label("YOU DON'T HAVE \n ACCESS TO \nBAN USERS ",24), 90, 564);
            }
            
            if(x>300 && x<508 && y>490 && y<560)
            {
                bgSound.stop();
                Greenfoot.setWorld(new MyWorld());
            }
            //showText(x+"  "+y,400,70);   
        }
        
        Label2 label =new Label2("[-_-]",30);
        if(x!=100 && y!=100)
        {
            if(loop==0)
            {
              x+=2;
              y+=2;
            }
            if(x==100 && y==100)
              loop=1;
        }
        if(x!=50 && y!=50)
        {
            if(loop==1)
            {
              x-=5;
              y-=5;
            }
            if(x==50 && y==50)
              loop=0;
        }
        label.getImage().scale(x, y-15);
        addObject(label, getWidth()/2, 90);   
        
        loop1++;
        if(loop1<=30)
          logoText.setImage("LogoText1.png");
        else if(loop1<=60 && loop1>=31)
          logoText.setImage("LogoText2.png");
        else if(loop1>=61) 
            loop1=0;
        logoText.getImage().scale(200, 350);
        addObject(logoText, getWidth()/2+4, 520);
        logoText1.getImage().scale(logoText.getImage().getWidth()+20, 80);
        addObject(logoText1, getWidth()/2+4, 525);
    }
}
