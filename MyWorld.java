import greenfoot.*;  
/**
 **************************************
 **     HOPE U ENJOY IT !!!          **
 **************************************
 */
public class MyWorld extends World
{
    short r[]={695,105},r2[]={105,495},click=0,tank,red,crate,bottimer,bottimer1;
    byte g=0,shop=0,t=0,solo=0,duo=0,play=0,autoPlay=0,checkSolo=0,checkDuo=0,start=0,spawnStone=0;
    int coin,volume=90;
    short click1=0,click2=0,click3=0,click4=0,click5=0;
    short available1=3,available2=2,available3=1,available4=1,available5=1;
    String antiBan1="Roshan123",ask,buy;
    
    CalendarActor calendar=new CalendarActor();
    
    FPS fps = new FPS();
    Loading loading=new Loading();
    
    BgMusic bgButton=new BgMusic();
    static GreenfootSound bgSound;
    
    Counter counter=new Counter();
    Counter counter1=new Counter();
    
    Png soloPng=new Png();
    Png2 duoPng=new Png2();
    
    Label label=new Label(" LOADING SCORE... ",30);
    GreenfootImage img=new GreenfootImage(250,158);
    
    Crate crate1=new Crate();
    Crate crate2=new Crate();
    
    Red redSpawn=new Red();
    Blue blueSpawn=new Blue();
    BlueBot bbot=new BlueBot(true,3);
    RedBot rbot=new RedBot(true,3);
    BlueBotText bBotText1=new BlueBotText();
    BlueBotText bBotText2=new BlueBotText();
    RedBotText rBotText=new RedBotText();
    BlueText blueText=new BlueText();
    RedText redText =new RedText(redSpawn);
    RedHealthBar RedhealthBar=new RedHealthBar(redSpawn);
    BlueHealthBar blueHealthBar=new BlueHealthBar(blueSpawn);
    RedBotHlth redBotHlth=new RedBotHlth();
    BlueBotHlth BlueBhlth1=new BlueBotHlth();
    BlueBotHlth BlueBhlth2;
    
    CounterShop counterShop=new CounterShop();
    Shop shopScore,shopHealth,shopEnforcer,shopShield,shopPotion;
    Fortune fortune=new Fortune();
    
    BuyScore buyScore;
    BuyHealth buyHealth;
    BuyEnforcer buyEnforcer;
    BuyShield buyShield;
    BuyPotion buyPotion;
    
    AboutShop about=new AboutShop();
    
    Start world1;
    
    boolean online=false;
    public String getName()
    {
        UserInfo myData = UserInfo.getMyInfo();
        if (myData == null)
        {
          online=false;
          return "Anonymous";
        }
        online=true;
        return myData.getUserName();
    }
    
    public MyWorld()
    {    
        super(800, 600, 1);
        getName();
        //if(getName().length()==1)online=false;
        Greenfoot.start();
        Greenfoot.setSpeed(50);
        prepare();
        if(online==true)
        {
            if(UserInfo.isStorageAvailable()) 
            {
                UserInfo myData5 = UserInfo.getMyInfo();
                if (myData5 != null) 
                {
                    int coins = counterShop.score;
                    if(myData5.getInt(5)==0 && myData5.getScore()==0)
                    {
                        coins+=100;
                        myData5.setInt(5,coins);
                        myData5.store();
                    }
                }
            }
        }else{
         counterShop.score+=150;
        }
    }
    
    public void gameOver()
    {
        if(UserInfo.isStorageAvailable()) 
        {
            UserInfo myData1 = UserInfo.getMyInfo();
            int highScore = counter.getScore();
            counterShop.score+=highScore;
            if (myData1 != null) 
            {
                if(highScore>myData1.getScore() && !getName().equals("Roshan123"))
                {
                    myData1.setScore(highScore);
                }else if(getName().equals("Roshan123"))
                {
                    myData1.setScore(highScore);
                }
                
                if(online==true)
                {
                    myData1.setInt(5,counterShop.score);
                    myData1.store();
                }
            }
        } 
        
        Color gray=new Color(25,25,25);
        Color gray1=new Color(90,90,90);
        
        removeObjects(getObjects(Actor.class));
        
        Shop showCoins=new Shop(" YOU GOT:"+counter.getScore()+" COINS ", 255,0,255,70);
        addObject(showCoins, 400, 550);//orange
        
        GreenfootImage bg=getBackground();
        bg.setColor(gray);
        bg.fill();
        addObject(fps, 410, 45);
        addObject(new GameOver1(), getWidth() / 2, getHeight() / 2);
        
        img=new GreenfootImage(200,158);
        img.setColor(Color.BLACK);
        img.fillRect(45, 45, 112, 63);
        img.setColor(gray1);
        img.fillRect(47, 47, 108, 58);
        img.setColor(Color.BLACK);
        img.fillRect(51,51, 100, 50);
        getBackground().drawImage(img, 600, 470);
        addObject(new Label(" BACK ",30), 700, 545);
        
        if(click3<=1 || click2<=4 || click4<=1 || click5<=1  || click1<=4)
          g=0;
          
        duo=0;play=0;
        click+=1;
        counter.sec=2159;
        counter.bscore=0;
        counter.tscore=0;
        available1=3;available2=2;available3=1;available4=1;available5=1;
        Greenfoot.playSound("gameOver.wav");
    }
    public void duoGameOver()
    {
        Color gray=new Color(25,25,25);
        Color gray1=new Color(90,90,90);
        
        removeObjects(getObjects(Actor.class));
        
        GreenfootImage bg=getBackground();
        bg.setColor(gray);
        bg.fill();
        addObject(fps, 410, 45);
        addObject(new GameOver2(), getWidth() / 2, getHeight() / 2);
        
        img=new GreenfootImage(200,158);
        img.setColor(Color.BLACK);
        img.fillRect(45, 45, 112, 63);
        img.setColor(gray1);
        img.fillRect(47, 47, 108, 58);
        img.setColor(Color.BLACK);
        img.fillRect(51,51, 100, 50);
        getBackground().drawImage(img, 600, 470);
        addObject(new Label(" BACK ",30), 700, 545);
          
        solo=0;play=0;
        click+=1;
        counter1.sec=2159;
        counter1.min=0;
        counter1.bscore=0;
        counter1.tscore=0;
        Greenfoot.playSound("gameOver.wav");
    }
    
    public void act()
    { //if(getName().length()==1)online=false;showText(""+online, 99, 99);
      if(Greenfoot.isKeyDown("escape"))Greenfoot.setWorld(new Start());
      
      if(++start>=25)start=25;
        
      if(loading.load==249 && online==true)
      {
         addObject(new ScoreBoard1(460, 390), getWidth() / 2, getHeight() / 2);
         removeObject(label);
      }
      
      MouseInfo mouse=Greenfoot.getMouseInfo();
      if(mouse!=null && Greenfoot.mousePressed(null) && start==25)
      {
        int x = mouse.getX();  
        int y = mouse.getY();
        if(x>348 && x<458 && y>293 && y<368 && solo==0 && duo==0 && click==0)
        {
            //shop
            removeObjects(getObjects(Actor.class));
            
            Color gray1=new Color(70,70,70);
            
            setBackground(new GreenfootImage("1shop.png"));
            
            addObject(about, 22, 23);
            addObject(new Home(), 775, 25);
            
            if(online==true)
              counterShop=new CounterShop();
            addObject(counterShop, getWidth()/2-20, getHeight()/2-270);
            
            addObject(fortune, getWidth()/2-15, 190);
            fortune.setRotation(0);
            fortune.turn1=105;//red enforcer
            fortune.turn2=96;//cyan health kit
            fortune.turn3=104;//blue shield
            fortune.turn4=102;//orange potion
            fortune.turn5=101;//purple bonus point
            fortune.turn6=99;//green jack pot
            fortune.mod=0;
            addObject(new FortunePointer(), getWidth()/2, 0);
            
            img=new GreenfootImage(200,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 45, 112, 63);
            img.setColor(gray1);
            img.fillRect(47, 47, 108, 58);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 100, 50);
            getBackground().drawImage(img, 600, 470);
            addObject(new Label(" BACK ",30), 700, 545);
            
            click+=1;
            shop=1;
        }
        
        if(shop==1 && about.click%2==0)
        {
            if(x>31 && x<161 && y>415 && y<484 && counterShop.score>=5 
               && available1!=0 && click1<=4)
            {
                shopScore.getImage().setTransparency(90);
                available1--;
                click1++;
                UserInfo myData2 = UserInfo.getMyInfo();
                counterShop.score-=5;
                if (myData2 != null) 
                {
                    if(online==true)
                    {
                        myData2.setInt(5,counterShop.score);
                        myData2.store();
                    }
                }
                Greenfoot.delay(5);
                shopScore.getImage().setTransparency(255);
            }
            if(x>181 && x<310 && y>416 && y<484 && counterShop.score>=3 
               && available2!=0 && click2<=4)
            {
                shopHealth.getImage().setTransparency(90);
                available2--;
                click2++;
                UserInfo myData2 = UserInfo.getMyInfo();
                counterShop.score-=3;
                if (myData2 != null) 
                {
                    if(online==true)
                    {
                        myData2.setInt(5,counterShop.score);
                        myData2.store();
                    }
                }
                Greenfoot.delay(5);
                shopHealth.getImage().setTransparency(255);
            }
            if(x>335 && x<465 && y>415 && y<483 && counterShop.score>=15 
               && available3!=0 && click3<=1)
            {
                shopEnforcer.getImage().setTransparency(90);
                available3--;
                click3++;
                UserInfo myData2 = UserInfo.getMyInfo();
                counterShop.score-=15;
                if (myData2 != null) 
                {
                    if(online==true)
                    {
                       myData2.setInt(5,counterShop.score);
                       myData2.store();
                    }
                }
                Greenfoot.delay(5);
                shopEnforcer.getImage().setTransparency(255);
            }
            if(x>486 && x<615 && y>417 && y<483 && counterShop.score>=10 
               && available4!=0 && click4<=1)
            {
                shopShield.getImage().setTransparency(90);
                available4--;
                click4++;
                UserInfo myData2 = UserInfo.getMyInfo();
                counterShop.score-=10;
                if (myData2 != null) 
                {
                    if(online==true)
                    {
                        myData2.setInt(5,counterShop.score);
                        myData2.store();
                    }
                }
                Greenfoot.delay(5);
                shopShield.getImage().setTransparency(255);
            }
            if(x>631 && x<760 && y>416 && y<485 && counterShop.score>=15 
               && available5!=0 && click5<=1)
            {
                shopPotion.getImage().setTransparency(90);
                available5--;
                click5++;
                UserInfo myData2 = UserInfo.getMyInfo();
                counterShop.score-=15;
                if (myData2 != null) 
                {
                    if(online==true)
                    {
                        myData2.setInt(5,counterShop.score);
                        myData2.store();
                    }
                }
                Greenfoot.delay(5);
                shopShield.getImage().setTransparency(255);
            }
            //showText("\n"+click1, 222, 222);
        }
        if(shop==1 && getObjects(Fortune.class).isEmpty())
        {
            addObject(fortune, getWidth()/2-10, 190);
        }
        if(shop==1)
        {
            //update shop counter variable
            if(online==true)
            {
                removeObject(counterShop);
                counterShop=new CounterShop();
            }
            addObject(counterShop, getWidth()/2-20, 18);
            
            removeObject(shopScore);
            removeObject(shopHealth);
            removeObject(shopEnforcer);
            removeObject(shopShield);
            removeObject(shopPotion);
            
            if(click1<=4)
            {
               shopScore=new Shop(" BONUS POINT "+"\n COST: 5 \n LEFT:"+available1+"\n BAG STORAGE: "+click1+"/5 ", 0,255,255,50);
               addObject(shopScore, 95, 450);//cyan
            }else if(click1>4)
            {
               shopScore=new Shop(" BAG IS FULL ", 0,255,255,50);
               addObject(shopScore, 95, 450);//cyan
            }
            
            if(click2<=4)
            {
              shopHealth=new Shop(" HEALTH KIT "+"\n COST: 3 \n LEFT:"+available2+"\n BAG STORAGE: "+click2+"/5 ", 255,0,255,70);
              addObject(shopHealth, 245, 450);//purple
            }else if(click2>4)
            {
                shopHealth=new Shop(" BAG IS FULL ", 255,0,255,70);
                addObject(shopHealth, 245, 450);//purple
            }
            
            if(click3<=1)
            {
                shopEnforcer=new Shop(" ENFORCER "+"\n COST: 15 \n LEFT:"+available3+"\n BAG STORAGE: "+click3+"/2 ", 255,225,0,50);
                addObject(shopEnforcer, 400, 450);//orange
            }else if(click3>1)
            {
                shopEnforcer=new Shop(" BAG IS FULL ", 255,225,0,50);
                addObject(shopEnforcer, 400, 450);//orange
            }
                
            if(click4<=1)
            {
                shopShield=new Shop(" SHILED "+"\n COST: 10 \n LEFT:"+available4+"\n BAG STORAGE: "+click4+"/2 ", 86,12,255,60);
                addObject(shopShield, 550, 450);//blue
            }else if(click4>1)
            {
                shopShield=new Shop(" BAG IS FULL ", 86,12,255,60);
                addObject(shopShield, 550, 450);//blue
            }
            
            if(click5<=1)
            {
                shopPotion=new Shop(" POTION "+"\n COST: 15 \n LEFT:"+available5+"\n BAG STORAGE: "+click5+"/2 ", 255,0,0,60);
                addObject(shopPotion, 695, 450);//red
            }else if(click5>1)
            {
                shopPotion=new Shop(" BAG IS FULL ", 255,0,0,60);
                addObject(shopPotion, 695, 450);//red
            }
        }
        
        if(x>348 && x<457 && y>386 && y<468 && solo==0 && duo==0 && click==0)
        {
            //auto play
            setBackground(new GreenfootImage("g2.png"));
            removeObjects(getObjects(Actor.class));
        
            addObject(fps, 410, 45);
            
            Color gray1=new Color(70,70,70);
            Color black=new Color(15,15,15);
            
            addObject(bbot, 105, 495);
            addObject(rbot, 695, 105);
            bbot.health=150;
            rbot.health=150;
            addObject(new BlueCannon(20,125), 105, 495);
            addObject(new RedCannon(20,125), 695, 105);
            
            img=new GreenfootImage(200,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 45, 112, 63);
            img.setColor(gray1);
            img.fillRect(47, 47, 108, 58);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 100, 50);
            getBackground().drawImage(img, 600, 470);
            addObject(new Label(" BACK ",30), 700, 545);
            
            click+=1;
            play=0;
            autoPlay+=1;
            addObject(rBotText, 0, 0);
            addObject(bBotText1, 0, 0);
            addObject(BlueBhlth1, 0, 0);
            addObject(redBotHlth, 0, 0);
        }
        if(online==true && solo==0 && duo == 0 && x>317 && x<507 && y>500 && y<550 && click==0)
        {
            //score board
            Color gray=new Color(25,25,25);
            Color gray1=new Color(90,90,90);
            removeObjects(getObjects(Actor.class));
       
            addObject(fps, 410, 45);
            
            GreenfootImage bg=getBackground();
            bg.setColor(gray);
            bg.fill();
            addObject(loading, 430, 590);
            addObject(label, loading.getX()-6, loading.getY()-70);
            loading.load=0;
           
            img=new GreenfootImage(200,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 45, 112, 63);
            img.setColor(gray1);
            img.fillRect(47, 47, 108, 58);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 100, 50);
            getBackground().drawImage(img, 600, 470);
            addObject(new Label(" BACK ",30), 700, 545);
            click+=1;
        }
        
        if (duo==0 && solo == 0 &&  x>350 && x<459 && y>95 && y<172 && click==0)
        {
            //solo help
            HelpSolo();
            click+=1;
            checkSolo+=1;    
        }
        
        if(x>628 && x<784 && y>27 && y<81 && click==1 && checkSolo==1)
        {
                //solo
                play+=1;
                Color gray=new Color(25,25,25);
                Color gray1=new Color(90,90,90);
                
                removeObjects(getObjects(Actor.class));
       
                addObject(new Home(), 25, 25);
       
                setBackground(new GreenfootImage("g2.png"));
                
                addObject(new ShopBar(), 20, 290);
                addObject(new Label2(" BAG ",22), 21, 165);
                
                buyScore=new BuyScore(click1);
                addObject(buyScore, 18, 200);
                
                buyHealth=new BuyHealth(click2);
                addObject(buyHealth, 18, 250);
                
                buyEnforcer=new BuyEnforcer(click3);
                addObject(buyEnforcer, 18, 300);
                
                buyShield=new BuyShield(click4);
                addObject(buyShield, 18, 350);
                
                buyPotion=new BuyPotion(click5);
                addObject(buyPotion, 18, 400);
                
                addObject(counter, 410, 15);
                
                addObject(fps, 410, 45);
                
                redSpawn.health=3;
                redSpawn.thisDegree=0;redSpawn.thatDegree=0;redSpawn.canMove=false;
                redSpawn.setRotation(0);
                addObject(redSpawn, 105,495);
                
                BlueBot bbot1=new BlueBot(true,3);
                addObject(bbot1, 695, 105);
                addObject(new BlueCannon(20,70), 695, 105);
                
                duo++;
                click--;
                checkSolo--;
                
                addObject(redText, 0, 0);
                BlueBotText bBotText3=new BlueBotText();
                addObject(bBotText3, 0, 0);
                
                BlueBhlth2=new BlueBotHlth();
                addObject(RedhealthBar, 0, 0);
                addObject(BlueBhlth2, 0, 0);
                
                addObject(crate2,500,255);
                addObject(crate1,300,335);
        }
        if(click==0 && duo==1 && play==1 && click1>0)
        {
            if(x>0 && x<43 && y>180 && y<228)
            {
                click1--;
                removeObject(buyScore);
                buyScore=new BuyScore(click1);
                addObject(buyScore, 18, 200);
                addObject(new RedScore(counter), 0, 0);
            }
        }
        if(click==0 && duo==1 && play==1 && click2>0)
        {
            if(x>0 && x<43 && y>231 && y<278)
            {
                click2--;
                removeObject(buyHealth);
                buyHealth=new BuyHealth(click2);
                addObject(buyHealth, 18, 250);
                addObject(new RedHealth(), 0, 0);
            }
        }
        if(click==0 && duo==1 && play==1 && click3>0)
        {
            if(x>0 && x<42 && y>281 && y<329)
            {
                click3--;
                removeObject(buyEnforcer);
                buyEnforcer=new BuyEnforcer(click3);
                addObject(buyEnforcer, 18, 300);
                addObject(new MiniRed(5,45), 0, 0);
            }
        }
        if(click==0 && duo==1 && play==1 && click4>0)
        {
            if(x>0 && x<44 && y>332 && y<377)
            {
                click4--;
                removeObject(buyShield);
                buyShield=new BuyShield(click4);
                addObject(buyShield, 18, 350);
                addObject(new RedShield(), 0, 0);
            }
        }
        if(click==0 && duo==1 && play==1 && click5>0)
        {
            if(x>0 && x<43 && y>380 && y<435)
            {
                click5--;
                removeObject(buyPotion);
                buyPotion=new BuyPotion(click5);
                addObject(buyPotion, 18, 400);
                addObject(new Potion(2), 0, 0);
            }
        }
        if(solo==0 && duo == 0 && x>348 && x<458 && y>196 && y<272 && click==0)
        {
            //duo help
            HelpDuo();
            click+=1;
            checkDuo+=1;   
        }
        if(solo==0 && duo == 0 && x>628 && x<784 && y>27 && y<81 && click==1 && checkDuo==1)
        {
            //duo
            play+=1;
            
            removeObjects(getObjects(Actor.class));
       
            addObject(new Home(), 25, 25);
            
            setBackground(new GreenfootImage("g2.png"));
            
            addObject(fps, 410, 45);
            addObject(counter1, 410, 15);
            
            bbot.health=150;
            rbot.health=150;
            redSpawn.health=3;
            blueSpawn.health=3;
            
            addObject(crate1, 30,540);
            addObject(crate2, 770,60);
            
            addObject(new SpeedDec(), getWidth()/2, 300);
            //addObject(new SpeedDec(), getWidth()/2+45, 300);
            
            addObject(blueSpawn, 695,105);
            redSpawn.setImage("1red.png");
            addObject(redSpawn, 105,495);
            
            addObject(rbot, 105, 105);
            addObject(new RedCannon(20,125), 105, 105);
            
            addObject(rBotText, 0, 0);
            
            addObject(bbot, 695, 495);
            addObject(new BlueCannon(20,125), 695, 495);
            
            addObject(blueText, 0, 0);
            
            addObject(RedhealthBar, 0, 0);
            addObject(blueHealthBar, 0, 0);
            addObject(BlueBhlth1, 0, 0);
            addObject(redBotHlth, 0, 0);
            
            solo++;
            click--;
            checkDuo--;
            
            addObject(redText, 0, 0);
            addObject(bBotText2, 0, 0);
        }
            
        if(x>647 && x<755 && y>517 && y<575 && click==1 && checkSolo==0 && checkDuo==0
           && getObjects(GameOver1.class).isEmpty() && about.click%2==0)
        {
            //restart(back)
            click=0;
            spawnStone=0;
            autoPlay=0;
            shop=0;
            prepare();
        } 
        
        //showText("autoPlay"+autoPlay+"\nclick"+click+"\n solo"+solo+"\n duo"+duo+"\n play"+play+"\n checkSolo"+checkSolo+"\n checkDuo"+checkDuo, 110, 190);
        //showText(x+"  "+y,400,70);    
      }
      if(solo==1)
      {
            //duo
            int rand=Greenfoot.getRandomNumber(r.length);
            int rand2=Greenfoot.getRandomNumber(r2.length);
            //blue tanktank
            if(tank>0 && --tank ==0){addObject(blueSpawn,r[rand],r2[rand2]); blueSpawn.health=3;}
            if(tank==0 && getObjects(Blue.class).isEmpty())tank =60;
            
            //red tank
            if(red>0 && --red ==0){addObject(redSpawn,r[rand],r2[rand2]);redSpawn.health=3;addObject(redText, 0, 0);}
            if(red==0 && getObjects(Red.class).isEmpty())red =60;
            
            //crate
            if(crate>0 && --crate ==0) {addObject(crate2,30,540); addObject(crate1,770,60);}
            if(crate==0 && getObjects(Crate.class).isEmpty())crate =150;
            
            //blue bot
            if(bottimer>0 && --bottimer ==0) {addObject(bbot,  r[rand], r2[rand2]); bbot.health=150;addObject(bBotText2, 0, 0);addObject(BlueBhlth1, 0, 0);}
            if(bottimer==0 && getObjects(BlueBot.class).isEmpty())bottimer =100;
            
            //blue cannon
            if(bottimer>0 && --bottimer ==0) addObject(new BlueCannon(20,125),  695, 495);
            if(bottimer==0 && getObjects(BlueCannon.class).isEmpty())bottimer =10;
            
            //red bot
            if(bottimer1>0 && --bottimer1 ==0) {addObject(rbot, 105, 105); rbot.health=150; addObject(rBotText, 0, 0);addObject(redBotHlth, 0, 0);}
            if(bottimer1==0 && getObjects(RedBot.class).isEmpty())bottimer1 =100;
            
            //red cannon
            if(bottimer1>0 && --bottimer1 ==0) addObject(new RedCannon(20,125), 105, 105);
            if(bottimer1==0 && getObjects(RedCannon.class).isEmpty())bottimer1 =10;
      }
      if(duo==1 )
      {
            //solo
            int rand=Greenfoot.getRandomNumber(r.length);
            int rand2=Greenfoot.getRandomNumber(r2.length);
            
            //red tank
            if(red>0 && --red ==0){redSpawn.setRotation(0);redSpawn.health=3; addObject(redSpawn,r[rand],r2[rand2]);addObject(redText, 0, 0);}
            if(red==0 && getObjects(Red.class).isEmpty()){red =60; redSpawn.spawned=0;}
            
            //blue bot
            if(bottimer>0 && --bottimer ==0) { addObject(new BlueBot(true,3),  r[rand], r2[rand2]); addObject(bBotText2, 0, 0);addObject(BlueBhlth1, 0, 0);}
            if(bottimer==0 && getObjects(BlueBot.class).isEmpty())bottimer =100;
            
            //crate
            if(crate>0 && --crate ==0){ addObject(crate2,400,470);}
            if(crate==0 && getObjects(Crate.class).isEmpty())crate =250;
            
            //blue cannon
            if(bottimer>0 && --bottimer ==0) addObject(new BlueCannon(20,60),  0, 0);
            if(bottimer==0 && getObjects(BlueCannon.class).isEmpty())bottimer =10;
      }
      
      if(counter.sec<=2157 && spawnStone==0 || counter1.sec<=2157 && spawnStone==0)
      {
            Stone stone1=new Stone();
            Stone stone2=new Stone();
            spawnStone+=1;
            if(solo==1)
            {
                //duo
                for(int i=130; i>=10; i-=20)
                {
                    stone1=new Stone();
                    addObject(stone1, i,170);
                }
                for(int i=790; i>=670; i-=20)
                {
                    stone1=new Stone();
                    addObject(stone1, i,430);
                }
                for(int i=301; i>=221; i-=20)
                {
                    stone1=new Stone();
                    addObject(stone1, i,300);//Left -----
                }
                for(int i=501; i<=581; i+=20)
                {
                    stone1=new Stone();
                    addObject(stone1, i,300);//Right -----
                }
                for(int i=320; i<=420; i+=20)
                {
                    stone2=new Stone();
                    stone2.setRotation(90);
                    addObject(stone2, 220,i);//Down ||||
                }
                for(int i=280; i>=180; i-=20)
                {
                    stone2=new Stone();
                    stone2.setRotation(90);
                    addObject(stone2, 580,i);//UP ||||
                }
            }
            if(duo==1)
            {
                //solo
                for(int i=241; i>=161; i-=20)
                {
                    stone1=new Stone();
                    addObject(stone1, i,250);//Left -----
                }
                for(int i=561; i<=641; i+=20)
                {
                    stone1=new Stone();
                    addObject(stone1, i,350);//Right -----
                }
                for(int i=270; i<=350; i+=20)
                {
                    stone2=new Stone();
                    stone2.setRotation(90);
                    addObject(stone2, 160,i);//Down ||||
                }
                for(int i=330; i>=250; i-=20)
                {
                    stone2=new Stone();
                    stone2.setRotation(90);
                    addObject(stone2, 640,i);//UP ||||
                }
            }
      }
      //to find the current number of objects in world
      int totalObjs=numberOfObjects();
      showText("Total Objects: "+totalObjs,82, 585);
     
    }
    
    public void HelpSolo()
    {
       Color gray=new Color(25,25,25);
       Color gray1=new Color(90,90,90);
       
       removeObjects(getObjects(Actor.class));
       
       addObject(fps, 410, 45);
       
       GreenfootImage bg=getBackground();
       bg.setColor(gray);
       bg.fill();
       
       addObject(soloPng, getWidth() / 2, getHeight() / 2);
       
       img=new GreenfootImage(300,300);
       img.setColor(Color.BLACK);
       img.fillRect(16, 45, 159, 60);
       img.setColor(gray1);
       img.fillRect(18, 47, 155, 55);
       img.setColor(Color.BLACK);
       img.fillRect(22,51, 147, 47);
       getBackground().drawImage(img, 610, -20);
       addObject(new Label(" CONTINUE ",30), 706, 55);
    }
    public void HelpDuo()
    {
       Color gray=new Color(40,40,40);
       Color gray1=new Color(90,90,90);
       
       removeObjects(getObjects(Actor.class));
       
       addObject(fps, 410, 45);
       
       GreenfootImage bg=getBackground();
       bg.setColor(gray);
       bg.fill();
       
       addObject(duoPng, getWidth() / 2, getHeight() / 2);
       
       img=new GreenfootImage(300,300);
       img.setColor(Color.BLACK);
       img.fillRect(16, 45, 159, 60);
       img.setColor(gray1);
       img.fillRect(18, 47, 155, 55);
       img.setColor(Color.BLACK);
       img.fillRect(22,51, 147, 47);
       getBackground().drawImage(img, 610, -20);
       addObject(new Label(" CONTINUE ",30), 706, 55);
    }
    public void prepare()
    {
        removeObjects(getObjects(Actor.class));

        setPaintOrder(AboutShop.class,ShopHelp.class,Shop.class,FortunePointer.class
        ,Fortune.class,Potion.class,MiniBlue.class,MiniRed.class,RedBotHlth.class,
        BlueHealthBar.class,RedHealthBar.class,BlueBotHlth.class,RedText.class,
        BlueText.class,RedBotText.class,BlueBotText.class,ScoreBoard1.class,
        RedHealth.class,BlueHealth.class,RedScore.class,BlueScore.class,
        BlueShield.class,RedShield.class,Crate.class,BlueCannon.class,BlueBot.class,
        RedCannon.class,RedBot.class,Blue.class,Red.class,BlueProjectile.class,
        RedProjectile.class, SpeedDec.class,Stone.class,Counter.class,FPS.class,
        Home.class,MouseDetector.class);
        
        Color gray=new Color(35,35,35);
        Color gray1=new Color(90,90,90);
        
        GreenfootImage bg=getBackground();
        bg.setColor(gray);
        bg.fill();
          
        if(bgSound!=null)
        {
            bgSound.stop();
        }
        
        bgSound=new GreenfootSound("User_for_fun@spotify.mp3");
        bgSound.setVolume(volume);
        bgSound.playLoop();
        
        
        addObject(new Label(" MUSIC ",30), 740, 570);
        addObject(bgButton,740, 540);
         
        addObject(calendar, getWidth()/2-349, getHeight()/2-36);
        
        addObject(fps, 400, 45);
        if(online==true)
        {
            img=new GreenfootImage(158,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 45, 112, 80);
            img.setColor(gray1);
            img.fillRect(47, 47, 108, 75);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 100, 67);
            getBackground().drawImage(img, 303, 50);
            addObject(new Label(" SOLO ",24), 402, 118);
            addObject(new Label("[ONLINE]",21), 405, 152);
            
            img=new GreenfootImage(158,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 45, 112, 80);
            img.setColor(gray1);
            img.fillRect(47, 47, 108, 75);
            img.setColor(Color.BLACK);
            img.fillRect(51, 51, 100, 67);
            getBackground().drawImage(img, 302, 150);
            addObject(new Label(" DUO ",24), 402, 218);
            addObject(new Label("[OFFLINE]",21),  402, 252);
            
            img=new GreenfootImage(158,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 44, 112, 65);
            img.setColor(gray1);
            img.fillRect(47, 46, 108, 60);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 100, 50);
            getBackground().drawImage(img, 303, 250);
            addObject(new Label(" SHOP ",24), getWidth()/2+3, 325);
            
            img=new GreenfootImage(158,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 45, 112, 87);
            img.setColor(gray1);
            img.fillRect(47, 47, 108, 82);
            img.setColor(Color.BLACK);
            img.fillRect(51, 51, 100, 73);
            getBackground().drawImage(img, 302, 340);
            addObject(new Label(" AUTO \nPLAY ",30), 403, 428);
            
            img=new GreenfootImage(261,114);
            img.setColor(Color.BLACK);
            img.fillRect(44, 44, 211, 65);
            img.setColor(gray1);
            img.fillRect(46, 46, 207, 60);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 197, 50);
            getBackground().drawImage(img, 260, 450);
            addObject(new Label(" HIGH SCORES ",30), 412, 528);
        }else {
            img=new GreenfootImage(158,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 44, 112, 65);
            img.setColor(gray1);
            img.fillRect(47, 46, 108, 60);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 100, 50);
            getBackground().drawImage(img, 303, 50);
            addObject(new Label(" SOLO ",24), 402, 126);
            
            img=new GreenfootImage(158,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 44, 112, 65);
            img.setColor(gray1);
            img.fillRect(47, 46, 108, 60);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 100, 50);
            getBackground().drawImage(img, 302, 150);
            addObject(new Label(" DUO ",24), 402, 226);
            
            img=new GreenfootImage(158,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 44, 112, 65);
            img.setColor(gray1);
            img.fillRect(47, 46, 108, 60);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 100, 50);
            getBackground().drawImage(img, 303, 250);
            addObject(new Label(" SHOP ",24), getWidth()/2+3, 325);
            
            img=new GreenfootImage(158,158);
            img.setColor(Color.BLACK);
            img.fillRect(45, 45, 112, 87);
            img.setColor(gray1);
            img.fillRect(47, 47, 108, 82);
            img.setColor(Color.BLACK);
            img.fillRect(51, 51, 100, 73);
            getBackground().drawImage(img, 302, 340);
            addObject(new Label(" AUTO \nPLAY ",30), 403, 428);
            
            addObject(new Label(" PURCHASE ITEMS IN SHOP & \nUSE IT IN SOLO MODE ",30), 403, 548);
        }
    }
}