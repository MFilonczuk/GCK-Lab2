package pl.michal;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class GamePanel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    Menu menu = new Menu();
    Difficulty diff = new Difficulty();
    public boolean paused;

    public static enum DIFF{
        EASY,
        NORMAL,
        HARD
    };
    public static DIFF Diff;

    public static enum STATE{
        MENU,
        PLAYERS1,
        PLAYERS2,
    };
    public static STATE State = STATE.MENU;


    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActionLisener());
        this.addMouseListener(new MouseInput());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER); //koordynaty na spawn pilki
    }

    public void newPaddles(){
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HIGHT/2),PADDLE_WIDTH,PADDLE_HIGHT,1); //aby byla idealnie na srodku musimy odjac rozmiar palety
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HIGHT/2),PADDLE_WIDTH,PADDLE_HIGHT,2);
    }

    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }
    public void draw(Graphics g){
        if(State == STATE.PLAYERS2) {
            paddle1.draw(g);
            paddle2.draw(g);
            ball.draw(g);
            score.draw(g);
        }else if(State == STATE.MENU){
            menu.render(g);
        }else if(State == STATE.PLAYERS1){
            if(Diff == DIFF.EASY || Diff == DIFF.NORMAL || Diff == DIFF.HARD){
                paddle1.draw(g);
                paddle2.draw(g);
                ball.draw(g);
                score.draw(g);
            }else
                diff.render(g);
        }

    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();

    }
    public void checkCollision(){

        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);

        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(paddle1.y<=0){
            paddle1.y=0;
        }
        if(paddle1.y >= (GAME_HEIGHT - PADDLE_HIGHT)) {
            paddle1.y = GAME_HEIGHT - PADDLE_HIGHT;
        }
        if(paddle2.y<=0){
            paddle2.y=0;
        }
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HIGHT)) {
            paddle2.y = GAME_HEIGHT - PADDLE_HIGHT;
        }

        if(ball.x <= 0){
            score.player2++;
            newPaddles();
            newBall();
        }
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER){
            score.player1++;
            newPaddles();
            newBall();
        }

    }
    public void run(){
        if(State == STATE.MENU) {
            long lastTime = System.nanoTime();
            double amountOfTicks = 60.0;
            double ns = 1000000000 / amountOfTicks;
            double delta = 0;
            boolean running = true;
                while (running) {
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;
                    if (delta >= 1) {
                        if (State == STATE.PLAYERS1 && (Diff == DIFF.EASY || Diff == DIFF.NORMAL || Diff == DIFF.HARD)) {
                            p1();
                        } else if (State == STATE.PLAYERS2)
                            p2();
                        repaint();
                        delta--;
                    }
                }
             }
        }
    public void p1(){
        move();
        checkCollision();
        if(Diff == DIFF.EASY)
            CPU(50,5);
        if(Diff == DIFF.NORMAL)
            CPU(30,7);
        if(Diff == DIFF.HARD)
            CPU(5,9);
    }
    public void p2(){
        move();
        checkCollision();
    }

    public void CPU(int precision, int speed){
        paddle2.setYDirection((ball.y - paddle2.y)-precision);
        if(paddle2.yVelocity >= speed)
            paddle2.yVelocity = speed;
        else
            paddle2.yVelocity = -speed;
    }


    public class ActionLisener extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            if (State == STATE.PLAYERS1){
                paddle1.keyPressed(e);
            }
            if (State == STATE.PLAYERS2) {
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
            }
        }
        public void keyReleased(KeyEvent e){
            if (State == STATE.PLAYERS1){
                paddle1.keyReleased(e);
            }
            if(State == STATE.PLAYERS2) {
                paddle1.keyReleased(e);
                paddle2.keyReleased(e);
            }

        }
    }
}
