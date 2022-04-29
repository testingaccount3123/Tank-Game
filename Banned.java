import greenfoot.*;
import java.util.List;
public class Banned extends Actor {
    static String getName;
    public  String banned(String name)
    {
      for(UserInfo user:(List<UserInfo>)UserInfo.getTop(-1))
      {
         if(user.getString(1).equals(name))
         {
            return getName=user.getUserName();
        }
      }return null;//if u r not yet banned by anyone
    }
    @Override
    public void addedToWorld(World w) 
    {
        GreenfootImage image = new GreenfootImage(w.getWidth(), w.getHeight());
        image.setColor(new Color(0, 0, 0, 150));
        image.fill();
        GreenfootImage text = new GreenfootImage(" You have been \n banned by the Host ", 40, Color.WHITE, new Color(0,0,0,170));
        image.drawImage(text, (w.getWidth() - text.getWidth()) / 2, (w.getHeight() - text.getHeight()) / 2);
        setImage(image);
    }
}
