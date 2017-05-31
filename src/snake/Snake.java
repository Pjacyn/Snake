/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author PJ
 */
public class Snake {

    int x;
    int y;
    int turn;
    ArrayList<Pair> tail = new ArrayList<Pair>();
    static final int SPEED = 10;

    Snake() {
        x = Game.FRAMEX / 2;
        y = Game.FRAMEY / 2;
        turn = 1;
        tail.add(new Pair(this));
        tail.add(new Pair(this));
        tail.add(new Pair(this));
        tail.add(new Pair(this));
        tail.add(new Pair(this));

    }

    public void move() {
        if (turn == 1) {
            y -= SPEED;
        }
        if (turn == 2) {
            x += SPEED;
        }
        if (turn == 3) {
            y += SPEED;
        }
        if (turn == 4) {
            x -= SPEED;
        }
        if(x > Game.FRAMEX-10)
            x=0;
        if(x<0)
            x=Game.FRAMEX-10;
        if(y> Game.FRAMEY-10)
            y=0;
        if(y<0)
            y=Game.FRAMEY-10;

    }
    public void add(){
        tail.add(new Pair(this.tail.get(1)));
    }

    public void print(Graphics2D g) {
        g.fillRect(x, y, 10, 10);
        for(int i=0;i<tail.size();i++){
            g.fillRect(tail.get(i).x,tail.get(i).y, 10, 10);
            g.setColor(Color.WHITE);
            g.drawRect(tail.get(i).x,tail.get(i).y, 10, 10);
            g.setColor(Color.BLACK);
        }
    }

    void keyPressed(KeyEvent e) {
        if (turn == 1) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                turn = 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                turn = 2;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                turn = 4;
            }

        }
        if (turn == 2) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                turn = 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                turn = 2;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                turn = 3;
            }
        }
        if (turn == 3) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                turn = 2;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                turn = 3;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                turn = 4;
            }
        }
        if (turn == 4) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                turn = 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                turn = 3;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                turn = 4;
            }
        }
    }

    void keyReleased(KeyEvent e) {
       
    }

    public void tail() {
        tail.get(0).x=this.x;
        tail.get(0).y=this.y;
        for (int i = tail.size()-1; i > 0; i--) {
            tail.get(i).x = tail.get(i - 1).x;
            tail.get(i).y = tail.get(i - 1).y; 
        }
        this.move();
        
    }
    public int getTailX(Pair p){
        return p.getX(); 
    }
    public int getTailY(Pair p){
        return p.getY(); 
    }

    private static class Pair {

        int x;
        int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Pair(Snake outer) {
            this.x = outer.x;
            this.y = outer.y;
        }
        public Pair(Pair p){
            this.x = p.x;
            this.y = p.y;
        }

        
    }

}
