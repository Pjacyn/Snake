/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author PJ
 */
public class Apples {
    int x;
    int y;
    static final int SIZEX=10;
    static final int SIZEY=10;
    static int quan = -1;
    
    Apples(){
        this.x=((int)(ThreadLocalRandom.current().nextInt(1, Game.FRAMEX-SIZEX*2)/10))*10;
        this.y=((int)(ThreadLocalRandom.current().nextInt(1, Game.FRAMEX-SIZEY*4)/10))*10;
        quan++;
    }
    public void print(Graphics2D g){
        g.fillRect(x,y,SIZEX,SIZEY);
    }
    
}
