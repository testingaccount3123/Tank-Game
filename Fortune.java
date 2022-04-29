import greenfoot.*;
public class Fortune extends Actor
{
    byte turn1=105;//red enforcer
    byte turn2=96;//cyan health kit
    byte turn3=104;//blue shield
    byte turn4=102;//orange potion
    byte turn5=101;//purple bonus point
    byte turn6=99;//green jack pot
    byte mod=0;
    short s[]={0,1,2,3,4,5};
    int rand;
    Shop chances=new Shop(" COST: 10 \n SPINS LEFT: 1 ", 0,255,255,50);
    Shop chances2=new Shop(" COST: 10 \n SPINS LEFT: 0 ", 0,255,255,50);
    Shop clearBag=new Shop(" CLEAR UR BAG STORAGE \n TO SPIN ", 0,255,255,50);
    Shop gotEnforcer=new Shop(" YOU GOT A ENFORCER ", 0,255,255,50);
    Shop gotHealth=new Shop(" YOU GOT A HEALTH KIT ", 0,255,255,50);
    Shop gotShield=new Shop(" YOU GOT A SHIELD ", 0,255,255,50);
    Shop gotPotion=new Shop(" YOU GOT A POTION ", 0,255,255,50);
    Shop gotScore=new Shop(" YOU GOT BONUS POINT ", 0,255,255,50);
    Shop shopJackPot=new Shop(" CONGRATULATION !!! \n YOU WON 100 COINS ", 0,255,255,50);
    
    public void act() 
    {
       MyWorld w = (MyWorld) getWorld();
       if(getWorld()!=null && getWorld().getObjects(CounterShop.class).size()>0)
       {
         CounterShop counterShop = getWorld().getObjects(CounterShop.class).get(0);
         if(Greenfoot.mouseClicked(chances)  && counterShop.score>=10)
         {
             getImage().setTransparency(90);
             Greenfoot.delay(5);
             getImage().setTransparency(255);
         }
         if(w.click3<=1 || w.click2<=4 || w.click4<=1 
         || w.click5<=1  || w.click1<=4)
         {
             if(Greenfoot.mouseClicked(chances) && counterShop.score>=1 && w.g==0 ) 
             {
               w.g+=1;
               rand=Greenfoot.getRandomNumber(s.length);
               //rand=4;
               UserInfo myData2 = UserInfo.getMyInfo();
               counterShop.score-=10;
               if (myData2 != null)
               {
                   if(w.online==true)
                   {
                       myData2.setInt(5,counterShop.score);
                       myData2.store();
                   }
               }
               if(w.online==true)
               {
                   getWorld().removeObject(counterShop);
                   getWorld().addObject(new CounterShop(), getWorld().getWidth()/2-20, 18);
               }
             }
         }
       }
       
       if(rand==0 && w.click3>1)
       {
           w.removeObject(w.shopEnforcer);
           Shop shopEnforcer=new Shop(" BAG IS FULL ", 255,225,0,50);
           w.addObject(shopEnforcer, 400, 450);//orange
       }else if(rand==1 && w.click2>4)
       {
            w.removeObject(w.shopHealth);
            Shop shopHealth=new Shop(" BAG IS FULL ", 255,0,255,70);
            w.addObject(shopHealth, 245, 450);//purple
       }else if(rand==2 && w.click4>1)
       {
           w.removeObject(w.shopShield);
           Shop shopShield=new Shop(" BAG IS FULL ", 86,12,255,60);
           w.addObject(shopShield, 550, 450);//blue
       }else if(rand==3 && w.click5>1)
       {
            w.removeObject(w.shopPotion);
            Shop shopPotion=new Shop(" BAG IS FULL ", 255,0,0,60);
            w.addObject(shopPotion, 695, 450);//red
       }else if(rand==4 && w.click1>4)
       {
           w.removeObject(w.shopScore);
           Shop shopScore=new Shop(" BAG IS FULL ", 0,255,255,50);
           w.addObject(shopScore, 95, 450);//cyan
       }
       
       if(w.g==1)
       {
           if(rand==0 && w.click3<=1)
           {
               if(++mod%4==0)
               {
                 turn(--turn1);
               }
               if(turn1<=1)
               {
                   turn1=1;
                   w.click3++;
                   w.removeObject(w.shopEnforcer);
                   getWorld().addObject(gotEnforcer, getX(), getY()+100);
                   Shop shopEnforcer=new Shop(" ENFORCER "+"\n COST: 15 \n LEFT:"+w.available3+"\n BAG STORAGE: "+w.click3+"/2 ", 255,225,0,50);
                   w.addObject(shopEnforcer, 400, 450);//orange
                   w.g++;
               }
           } 
           
           if(rand==1 && w.click2<=4)
           {
               if(++mod%4==0)
               {
                 turn(--turn2);
               }
               if(turn2<=1)
               {
                   turn2=1;
                   w.click2++;
                   w.removeObject(w.shopHealth);
                   getWorld().addObject(gotHealth, getX(), getY()+100);
                   Shop shopHealth=new Shop(" HEALTH KIT "+"\n COST: 3 \n LEFT:"+w.available2+"\n BAG STORAGE: "+w.click2+"/5 ", 255,0,255,70);
                   w.addObject(shopHealth, 245, 450);//purple
                   w.g++;
               }
           }
           
           if(rand==2 && w.click4<=1)
           {
               if(++mod%4==0)
               {
                 turn(--turn3);
               }
               if(turn3<=1)
               {
                   turn3=1;
                   w.click4++;
                   w.removeObject(w.shopShield);
                   getWorld().addObject(gotShield, getX(), getY()+100);
                   Shop shopShield=new Shop(" SHILED "+"\n COST: 10 \n LEFT:"+w.available4+"\n BAG STORAGE: "+w.click4+"/2 ", 86,12,255,60);
                   w.addObject(shopShield, 550, 450);//blue
                   w.g++;
               }
           }
           
           if(rand==3 && w.click5<=1)
           {
               if(++mod%4==0)
               {
                 turn(--turn4);
               }
               if(turn4<=1)
               {
                   turn4=1;
                   w.click5++;
                   w.removeObject(w.shopPotion);
                   getWorld().addObject(gotPotion, getX(), getY()+100);//cyan
                   Shop shopPotion=new Shop(" POTION "+"\n COST: 15 \n LEFT:"+w.available5+"\n BAG STORAGE: "+w.click5+"/2 ", 255,0,0,60);
                   w.addObject(shopPotion, 695, 450);//red
                   w.g++;
               }
           }
           
           if(rand==4 && w.click1<=4)
           {
               if(++mod%4==0)
               {
                 turn(--turn5);
               }
               if(turn5<=1)
               {
                   turn5=1;
                   w.click1++;
                   w.removeObject(w.shopScore);
                   getWorld().addObject(gotScore, getX(), getY()+100);//cyan
                   Shop shopScore=new Shop(" BONUS POINT "+"\n COST: 5 \n LEFT:"+w.available1+"\n BAG STORAGE: "+w.click1+"/5 ", 0,255,255,50);
                   w.addObject(shopScore, 95, 450);//red
                   w.g++;
               }
           }
           
           if(rand==5)
           {
               if(++mod%4==0)
               {
                 turn(--turn6);
               }
               if(turn6<=1 )
               {
                   turn6=1;
                   if(getWorld()!=null && getWorld().getObjects(CounterShop.class).size()>0)
                   {
                      CounterShop counterShop2 = getWorld().getObjects(CounterShop.class).get(0);
                      UserInfo myData = UserInfo.getMyInfo();
                      counterShop2.score+=100;
                      if (myData != null) 
                      {
                         if(w.online==true)
                         {
                             myData.setInt(5,counterShop2.score);
                             myData.store();
                         }
                      }
                      getWorld().removeObject(counterShop2);
                      getWorld().addObject(shopJackPot, getX(), getY()+105);//cyan
                      w.g++;
                   }
                   getWorld().addObject(new CounterShop(), getWorld().getWidth()/2-20, 18);
               }
           }
           
           //getWorld().showText("red "+turn1+"\ncyan "+turn2+"\nbllue "+turn3+"\norange "+turn4+
           //"\npurp "+turn5+"\ngreen "+turn6+"\n rand"+rand+"\n click"+w.g, 222, 222);
       }
       
       if(w.click3<=1 || w.click2<=4 || w.click4<=1 
       || w.click5<=1  || w.click1<=4)
       {
         if(w.g>0) 
         {
             getWorld().removeObject(chances);
             getWorld().removeObject(clearBag);
             getWorld().addObject(chances2, getWorld().getWidth()/2-20, 70);  
         }else if(w.g==0) 
         {
             getWorld().removeObject(chances2);
             getWorld().removeObject(clearBag);
             getWorld().addObject(chances, getWorld().getWidth()/2-20, 70);
         }
       }
       if(w.click3>1 || w.click2>4 || w.click4>1 
       || w.click5>1  || w.click1>4)
       {
          getWorld().removeObject(chances);
          getWorld().removeObject(chances2);
          getWorld().addObject(clearBag, getWorld().getWidth()/2-20, 70);
       }
    }
}