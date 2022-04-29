import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LogoText1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LogoText1 extends Actor
{
    public void act() 
    {
        if(isTouching(LogoText1.class))
          getWorld().removeObject(this);
    }    
}
