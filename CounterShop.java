import greenfoot.*;  
public class CounterShop extends Actor
{
    int score;
    int highScore;
    protected void addedToWorld(World world)
    {
       UserInfo myInfo = UserInfo.getMyInfo();
       MyWorld w=(MyWorld)getWorld();
       if(w.online==true)
       {
           highScore=myInfo.getInt(5);
           score+=highScore;
       }
    }
    public CounterShop()
    {
      setImage(new GreenfootImage(" COINS:"+score+" ",35,Color.WHITE,new Color(255,0,255,30)));
    }
    public void act() 
    {
       MyWorld w=(MyWorld)getWorld();
       if(w.online==true)
         score=highScore;
         
       setImage(new GreenfootImage(" COINS:"+score+" ",35,Color.WHITE,new Color(255,0,255,30)));
       
       getScore();
       
       if(isTouching(CounterShop.class))w.removeObject(this);
    } 
    public int getScore()
    {
        return score;
    }
}