import greenfoot.*; 
public class Explosion extends Actor
{
    private final static int IMAGE_COUNT= 8;
    private static GreenfootImage[] images;
    private int size=0;
    private int increment=1;    
    
    public Explosion() 
    {
        initialiseImages();
        setImage(images[0]);        
    }    
    
    public synchronized static void initialiseImages() 
    {
        if(images == null) 
        {
            GreenfootImage baseImage = new GreenfootImage("explosion.png");
            baseImage.setTransparency(130);
            int maxSize = baseImage.getWidth()*4;
            int delta = maxSize / (IMAGE_COUNT+1);
            int size = 0;
            images = new GreenfootImage[IMAGE_COUNT];
            for(int i=0; i < IMAGE_COUNT; i++) 
            {
                size = size + delta;
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
            }
        }
    }
   
    public void act()
    { 
        setImage(images[size]);

        size += increment;
        if(size>=IMAGE_COUNT) 
        {
            increment = -increment;
            size += increment;
        }
        
        //explodeOthers();
        if(size <= 0) 
        {
            getWorld().removeObject(this);
        }
    }
}