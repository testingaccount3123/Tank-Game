import greenfoot.*;
public class GameOver1 extends Actor
{
    ScoreBoard1 scoreBoard1=new ScoreBoard1(460, 390);
    GreenfootImage img=new GreenfootImage(250,158);
    Label rank =new Label(" RANK ",25); 
    Label restart =new Label(" BACK ",25);
    GreenfootImage bg=getImage();
    Label saveText=new  Label("LOADING SCORES",30);
    int timer=0,overTimer=0;
    public void act() 
    {
      MyWorld w = (MyWorld) getWorld();
      MouseInfo mouse=Greenfoot.getMouseInfo();
      try
      {
          if(mouse!=null && Greenfoot.mousePressed(null))
          {
            int x = mouse.getX();  
            int y = mouse.getY();///210 428
            if(x>37 && x<146 && y>500 && y<560 && w.online==true)
            {
                getWorld().addObject(saveText, getWorld().getWidth()/2, getWorld().getHeight()/2+100);
                if(saveText==null)
                  return;
                timer++;
            }
            if(x>645 && x<756 && y>500 && y<561)
            {
                w.click=0;
                w.spawnStone=0;
                w.autoPlay=0;
                w.shop=0;
                w.prepare();
            }
            //getWorld().showText(x+"  "+y,400,70);    
          }
          if(timer>=1)
            overTimer++;
          if(overTimer>=2)
          {
            Color gray=new Color(25,25,25);
            GreenfootImage bg=getImage();
            bg.setColor(gray);
            bg.fill();
            if(scoreBoard1!=null)
              getWorld().removeObject(saveText);
            gameOver();
          }
          Color gray1=new Color(90,90,90);
          
          if(w.online==true)
          {
              img=new GreenfootImage(261,114);
              img.setColor(Color.BLACK);
              img.fillRect(45, 45, 112, 63);
              img.setColor(gray1);
              img.fillRect(47, 47, 108, 58);
              img.setColor(Color.BLACK);
              img.fillRect(51,51, 100, 50);
              bg.drawImage(img, -9, 455);
              getWorld().addObject(rank, 90, 530);
          }      
          img=new GreenfootImage(261,114);
          img.setColor(Color.BLACK);
          img.fillRect(45, 45, 112, 63);
          img.setColor(gray1);
          img.fillRect(47, 47, 108, 58);
          img.setColor(Color.BLACK);
          img.fillRect(51,51, 100, 50);
          bg.drawImage(img, 600, 455);
          getWorld().addObject(restart, 697, 532);
      }catch(Exception e){}
    }    
    public void gameOver()
    {
        getWorld().removeObject(rank);
        
        getWorld().addObject(scoreBoard1, getWorld().getWidth() / 2, getWorld().getHeight() / 2-25);
        
        Color gray=new Color(25,25,25);
        Color gray1=new Color(90,90,90);
        
        GreenfootImage bg=getWorld().getBackground();
        bg.setColor(gray);
        bg.fill();
        
        img=new GreenfootImage(261,114);
        img.setColor(Color.BLACK);
        img.fillRect(45, 45, 112, 63);
        img.setColor(gray1);
        img.fillRect(47, 47, 108, 58);
        img.setColor(Color.BLACK);
        img.fillRect(51,51, 100, 50);
        bg.drawImage(img, 600, 455);
        getWorld().addObject(restart, 697, 532);
    }
}
