import greenfoot.*;
import java.util.Calendar;
public class CalendarActor extends Actor
{
    private Calendar calendar;
    private DayActor[] das = new DayActor[31];
    private Aktor pHeader;
    private Actor[] flds = new Actor[3];
    private Actor dayOfWeek;
    private int focus = -1;
    
    public CalendarActor()
    {
        calendar = Calendar.getInstance();
    }
    
    protected void addedToWorld(World world)
    {
        dayOfWeek = new Aktor();
        world.addObject(dayOfWeek, getX()-90, getY()+getImage().getHeight()/2-264);
        
        for (int i=0; i<3; i++)
        {
            flds[i] = new Aktor();
            world.addObject(flds[i], getX()-30+44*i+14*(i==2?1:0), getY()+getImage().getHeight()/2-264);
            updateField(i); // initializes field value and its display
        }
        
    }
    
    public void act()
    {
          getImage().setTransparency(0);
    }
        
    private void updateField(int fnum)
    {
        GreenfootImage img = new GreenfootImage(40, 30);
        int val = 0;
        switch (fnum)
        {
            case 0: val = getDayOfMonth(); break;
            case 1: val = getMonth(); break;
            case 2: img.scale(68, 30); val = getYear(); break;
        };
        img.setColor(fnum == focus ? new Color(255, 255, 128) : Color.BLACK);
        img.fill();
        img.setColor(Color.CYAN);
        img.drawRect(0, 0, img.getWidth()-1, img.getHeight()-1);
        GreenfootImage text = new GreenfootImage(""+val, 30, Color.CYAN, new Color(0, 0, 0, 0));
        img.drawImage(text, img.getWidth()-6-text.getWidth(), 0);
        flds[fnum].setImage(img);
    }
    
    public int getDayOfMonth() 
    { 
        return calendar.get(Calendar.DAY_OF_MONTH); 
    }
    
    public int getMonth() 
    { 
        return calendar.get(Calendar.MONTH)+1; 
    }
    
    public int getYear() 
    { 
        return calendar.get(Calendar.YEAR); 
    }
    
    private class DayActor extends Actor
    {
        int val; // day of month
        public DayActor(int dom)
        {
            val = dom;
        }
        
        public void setLocation(int x, int y) {}
        
        protected void setPosition(int x, int y)
        {
            super.setLocation(x, y);
        }
    }
    
    protected class Aktor extends Actor
    {
        public void setLocation(int x, int y) {}
    }
    
    public int storeDay()
    {
        return getDayOfMonth();
    }
    public int storeMonth()
    {
        return getMonth();
    }
    public int storeYear()
    {
        return getYear();
    }
}