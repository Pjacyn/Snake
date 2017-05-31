/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author PJ
 */
public class Game extends JPanel {

    static int FRAMEX = 600;
    static int FRAMEY = 600;
    static boolean GAMEOVER = false;
    static int SPEED = 100;
    static Snake snake;
    Apples apple;

    Game() {
        snake = new Snake();
        apple = new Apples();
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                snake.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setSize(FRAMEX + 200, FRAMEY);
        Game game = new Game();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);
        while (true) {
            game.repaint();
            game.move();
            game.colison();
            Thread.sleep(Game.SPEED);
            if (Game.GAMEOVER) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                Game.snake = new Snake();
                Game.GAMEOVER = false;
            }

        }
    }
    public void newSnake(){
        snake = new Snake();
    }

    public void move() {
        snake.tail();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("Score:" + Apples.quan, FRAMEX + 5, 20);
        g.drawRect(0, 0, FRAMEX, FRAMEY);
        snake.print(g2d);
        apple.print(g2d);
        if (Game.GAMEOVER) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.drawString("GAME OVER", 10, 300);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Score:" + Apples.quan, 250, 350);
            g.drawString("Wait 5sec", 250, 370);

        }
    }

    public void colison() {
        if (Math.abs(snake.x - apple.x) < 10 && Math.abs(snake.y - apple.y) < 10) {
            snake.add();
            apple = new Apples();
        }
        for (int i = 1; i < snake.tail.size(); i++) {
            if (Math.abs(snake.x - (snake.getTailX(snake.tail.get(i)))) < 10
                    && Math.abs(snake.y - (snake.getTailY(snake.tail.get(i)))) < 10) {
                Game.GAMEOVER = true;

            }
        };
    }
}
