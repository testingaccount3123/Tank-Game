import greenfoot.*;
import java.util.List;
public class ScoreBoard1 extends Actor
{
    private static final int GAP = 10;
    private static final int HEADER_TEXT_HEIGHT = 25;
    private static final Color MAIN_COLOR = Color.CYAN;
    private static final Color MAIN_COLO = Color.CYAN;
    private static final Color SCORE_COLOR = Color.PINK;
    Color gray=new Color(25,25,25);
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
    
    public ScoreBoard1(int width, int height)
    {    
        setImage(new GreenfootImage(Math.max(600, width), height));
        if(getName().length()==1)online=false;
        if(online==true)
          drawScores();
    }
    private void drawString(String text, int x, int y, Color color, int height)
    {
        getImage().drawImage(new GreenfootImage(text, height, color, new Color(0,0,0,0)), x, y);
    }
    private void drawScores()
    { 
        MyWorld w = (MyWorld) getWorld();
        final int pixelsPerUser = 50 + 2*GAP;
        final int numUsers = ((getImage().getHeight() - (HEADER_TEXT_HEIGHT + 10)) / pixelsPerUser);
        final int topSpace = getImage().getHeight() - (numUsers * pixelsPerUser) - GAP;
        drawString("Top Scores", 100, topSpace - HEADER_TEXT_HEIGHT - 5, MAIN_COLO, HEADER_TEXT_HEIGHT);
        drawString("Near You", 100 + getImage().getWidth() / 2, topSpace - HEADER_TEXT_HEIGHT - 5, MAIN_COLO, HEADER_TEXT_HEIGHT);        
        drawUserPanel(GAP, topSpace, (getImage().getWidth() / 2) - GAP, topSpace + numUsers * pixelsPerUser, UserInfo.getTop(numUsers+2));
        drawUserPanel(GAP + getImage().getWidth() / 2, topSpace+140, getImage().getWidth() - GAP, topSpace + numUsers * pixelsPerUser, UserInfo.getNearby(numUsers));
    }
    private void drawUserPanel(int left, int top, int right, int bottom, List users)
    {
        MyWorld w = (MyWorld) getWorld();
        if(getName().length()==1)online=false;
        if(online==true)
        {
            getImage().setColor(MAIN_COLOR);
            getImage().drawRect(left, top-140, right - left, bottom - top+140);
            int y = top-140 + GAP;
            if (users == null) {
                getImage().setColor(Color.BLACK);
                getImage().fillRect(left + 5, y - GAP + 1, right - left - 10, 50 + 2*GAP - 1);
                int x = left + 10;
                drawString("Log in or replay to save your score", x, y+18, MAIN_COLOR, 14);
                return;
            }
            if (users.isEmpty()) {
                getImage().setColor(Color.BLACK);
                getImage().fillRect(left + 5, y - GAP + 1, right - left - 10, 50 + 2*GAP - 1);
                int x = left + 10;
                drawString("Log in or replay to save your score", x, y+18, MAIN_COLOR, 14);
                return;
            }
            for (Object obj : users)
            {
                UserInfo playerData = (UserInfo)obj;            
                Color c;
                if (playerData.getUserName().equals(UserInfo.getMyInfo().getUserName())) 
                {
                  c = Color.BLUE;
                }
                else 
                {
                  c = new Color(0,0,0);
                }
                getImage().setColor(c);
                getImage().fillRect(left + 5, y - GAP + 1, right - left - 10, 50 + 2*GAP - 1);
                int x = left + 10;
                
                drawString("#" + Integer.toString(playerData.getRank()-2), x, y+18, Color.GREEN, 14);
                x += 50;
                drawString(Integer.toString(playerData.getScore()), x, y+18, SCORE_COLOR, 14);
                x += 80;
                getImage().drawImage(playerData.getUserImage(), x, y);
                x += 55;
                drawString(playerData.getUserName(), x, y + 18, Color.YELLOW, 14);
                y += 50 + 2*GAP;
            }
            getImage().setColor(gray);
            getImage().fillRect(10, 0, 282, 30);
            
            getImage().setColor(MAIN_COLOR);
            getImage().drawRect(left, top, right - left, bottom - top);
            
            final int pixelsPerUser = 50 + 2*GAP;
            final int numUsers = ((getImage().getHeight() - (HEADER_TEXT_HEIGHT + 10)) / pixelsPerUser);
            final int topSpace = getImage().getHeight() - (numUsers * pixelsPerUser) - GAP;
            drawString("Top Scores", 100, topSpace - HEADER_TEXT_HEIGHT - 5, MAIN_COLO, HEADER_TEXT_HEIGHT);
        }
    }
}