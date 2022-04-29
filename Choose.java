import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class Choose extends World
{
    Color gray=new Color(40,40,40);
    FPS fps = new FPS();
    GreenfootImage img=new GreenfootImage(244,108);
    public static UserInfo user;
    UserInfo userData = UserInfo.getMyInfo();
    public static List<Integer> banned;
    public static Map<Integer, UserInfo> allUsers;
    public static boolean isBanned = false;
    public static UserInfo myData;
    int timer=0;
    String col;
    CounterShop counterShop=new CounterShop();
    boolean online;
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
    
    public Choose()
    {    
        super(800, 600, 1); 
        getName();
        Greenfoot.start();
        Greenfoot.setSpeed(100);
        
        addObject(fps, 410, 45);
        
        GreenfootImage bg=getBackground();
        bg.setColor(gray);
        bg.fill();
          
        if(!reload()) return;
        Greenfoot.start();
    }
    
    public boolean reload()
    {
      if(online==true)
      {
          try
          {
              UserInfo user = UserInfo.getMyInfo();
              if(Choose.user != null && Choose.user.getUserName().equals(user.getUserName()))
                 user = Choose.user;
               user.store();
              if(user == null)
              {
                 Greenfoot.setWorld(new Start());
                 return false;
              }
                
              Map<Integer, UserInfo> allUsers = new HashMap<>();
              for(UserInfo u : (List<UserInfo>)UserInfo.getTop(-1))
                 allUsers.put(hash(u.getUserName()), u);  
                
              List<Integer> banned = new ArrayList<>();
              for(UserInfo u : allUsers.values()) {
                for(int i=0; i<UserInfo.NUM_INTS; i++)
                    if(u.getInt(i) != 0) banned.add(u.getInt(i));
                for(int i=0; i<UserInfo.NUM_STRINGS; i++)
                    if(u.getString(i).length() != 0) {
                        banned.add(hash(u.getString(i)));
                        //System.out.println(u.getString(i));
                     }
              }  
              Choose.user = user;
              Choose.allUsers = allUsers;
              Choose.banned = banned;
              Choose.isBanned = banned.contains(hash(user.getUserName()));  
              
              String yourBans = "You banned:\n";
              for(int i=0; i<UserInfo.NUM_STRINGS; i++)
                if(user.getString(i).length() > 0) 
                  yourBans += '\n' + user.getString(i);
              for(int i=0; i<UserInfo.NUM_INTS; i++) 
              {
                if(user.getInt(i) != 0) 
                {
                   UserInfo u = allUsers.get(user.getInt(i));
                   yourBans += '\n' + (u != null ? u.getUserName() : "User could not be loaded");
                }
              }
              if(yourBans.length() == 12) 
                yourBans += "\n None";
              GreenfootImage textImage1 = new GreenfootImage(yourBans, 18, Color.CYAN, new Color(0,0,0,0));
              if(getName().equals("Roshan123") && user.getInt(5)<=1000)
              {
                 counterShop.score+=50;
                 user.setInt(5,counterShop.score);
                 user.store();
              }
              if(getName().equals("Roshan123"))
                addObject(new Actor() {{setImage(textImage1);}}, getWidth()/2+200, getHeight()/2);
              if(!getName().equals("Roshan123") && !isBanned )
              {
                 addObject(new Label(" LOADING... ",60), 400, 300);  
              }
              
              return true;            
          }catch(Exception e)
           {
              e.printStackTrace();
              return false;
           }
      }else return false;
    }
    
    public void ban(String name) 
    {
        if(online==true)
        {
            if(user.getUserName().equals(name)) {
                showText("You cannot ban yourself",getWidth()/2,500);
                return;
            }
            
            if(banned.contains(hash(name))) {
                showText("User " + name + " is already banned",getWidth()/2,500);
                return;
            }
            
            for(int i=UserInfo.NUM_INTS-1; i>0; i--)
               user.setInt(i, user.getInt(i-1));
               
            user.setInt(0, parse(user.getString(UserInfo.NUM_STRINGS - 1)));
            
            for(int i=UserInfo.NUM_STRINGS-1; i>0; i--)
              user.setString(i, user.getString(i-1));
              
            userData.setString(0, col);
            userData.store();
            reload();
        }
    }
    
    public void unban(String name) {
        if(online==true)
        {
            for(int i=0; i<UserInfo.NUM_STRINGS; i++) {
                if(user.getString(i).equalsIgnoreCase(name)) {
                    user.setString(i, "");
                    user.store();
                    reload();
                    return;
                }
            }
            int hash = hash(name);
            for(int i=0; i<UserInfo.NUM_INTS; i++) {
                if(user.getInt(i) == hash) {
                    user.setInt(i, 0);
                    user.store();
                    reload();
                    return;
                }
            }
            System.err.println("You have not banned the user " + name);
        }
    }
    
    private static int parse(String string) {
        return string.length() != 0 ? hash(string) : 0;
    }
    
    public static int hash(String string) {
        string = string.toLowerCase();
        int hash = 1;
        for(int i=0; i<string.length(); i++)
          hash += 1 + (string.charAt(i) - 100) * (i+1) * (i+2);
        return hash;
    }
    
    public void act()
    {
      timer++;
      if(online==false)Greenfoot.setWorld(new Start());
      if(getName().equals("Roshan123"))
      {
        img.setColor(Color.CYAN);
        img.fillRect(47, 47, 194, 58);
        img.setColor(Color.BLACK);
        img.fillRect(51,51, 186, 50);
        getBackground().drawImage(img, 0, 175);
        addObject(new Label(" CLICK TO BAN ",25), 143, 250);
        
        img=new GreenfootImage(200,158);
        img.setColor(Color.CYAN);
        img.fillRect(47, 47, 108, 58);
        img.setColor(Color.BLACK);
        img.fillRect(51,51, 100, 50);
        getBackground().drawImage(img, 635, -35);
        addObject(new Label(" BACK ",30), 735, 40);
        
        img=new GreenfootImage(244,108);
        img.setColor(Color.CYAN);
        img.fillRect(70, 47, 144, 58);
        img.setColor(Color.BLACK);
        img.fillRect(74,51, 136, 50);
        getBackground().drawImage(img, 0, 245);
        addObject(new Label(" UNBAN ",25), 143,320);
        
        img=new GreenfootImage(244,108);
        img.setColor(Color.CYAN);
        img.fillRect(70, 47, 144, 58);
        img.setColor(Color.BLACK);
        img.fillRect(74,51, 136, 50);
        getBackground().drawImage(img, 0, 325);
        addObject(new Label(" RELOAD ",25), 143,400);
        
        addObject(new Label("HELLO "+getName()+" !!!",25), getWidth()/2, 75);
      }
      if(isBanned)
      {
         addObject(new Banned(), getWidth()/2, getHeight()/2);
         Greenfoot.stop();
      }
      else if(!isBanned && !getName().equals("Roshan123"))
         Greenfoot.setWorld(new Start());  
         
      MouseInfo mouse=Greenfoot.getMouseInfo();
      if(mouse!=null && Greenfoot.mousePressed(null))
      {
        int x = mouse.getX();  
        int y = mouse.getY();
        
        if(x>70 && x<214 && y>291 && y<348 && !isBanned)
        {
           unban(Greenfoot.ask("Enter the name of the user to unban:"));
        }
        
        if(x>680 && x<789 && y>10  && y<70 && !isBanned)
        {
            String s=Greenfoot.ask("error");
            if(getName().equals("Roshan123") && s.equals("rsb"))Greenfoot.setWorld(new Start());   
            else if(getName().equals("Roshan123") && !s.equals("rsb"))Greenfoot.stop();
            
        }
        
        if(!isBanned && x>46 && x<242 && y>221  && y<280 && !isBanned)
        {
            if(userData!=null)
            {
                do
                {
                    col = Greenfoot.ask("TYPE THE USER NAME TO BAN");
                }while(col.length()>50 || col.length()==0);
                if (UserInfo.isStorageAvailable())
                {
                    if (col != null && !" ".equals(col))
                    {
                      ban(col);
                    }
                }
            }else Greenfoot.setWorld(new Start());
        }
        
        if(x>70 && x<214 && y>372 && y<430 && !isBanned)
        {
           //to reload choose world
           Greenfoot.setWorld(new Choose());
        }
        //showText(x+"  "+y,getWidth()/2,getHeight()/2); 
      }
    }
}