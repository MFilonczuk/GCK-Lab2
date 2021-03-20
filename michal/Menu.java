package pl.michal;

import java.awt.*;

import static pl.michal.GamePanel.GAME_WIDTH;

public class Menu {

    public Rectangle play1Button = new Rectangle(GAME_WIDTH / 2 - 100, 145, 200, 50);
    public Rectangle play2Button = new Rectangle(GAME_WIDTH / 2 - 100, 245, 200, 50);
    public Rectangle quitButton = new Rectangle(GAME_WIDTH / 2 - 60, 345, 125, 50);

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("arial",Font.BOLD, 50);
        g.setFont(font);
        g.setColor(Color.GREEN);
        g.drawString("PONG THE GAME", GAME_WIDTH/2 - 200, 75);

        Font font1 = new Font("arial",Font.BOLD, 30);
        g.setFont(font1);
        g.drawString("1 PLAYER", play1Button.x + 25, play1Button.y + 35);
        g2d.draw(play1Button);
        g.drawString("2 PLAYERS", play2Button.x + 19, play2Button.y + 35);
        g2d.draw(play2Button);
        g.drawString("Quit :(", quitButton.x + 19, quitButton.y + 35);
        g2d.draw(quitButton);

        Font font2 = new Font("Comic Sans",Font.BOLD, 20);
        g.setFont(font2);
        g.setColor(Color.gray);
        g.drawString("By Michal Filonczuk", GAME_WIDTH/2 + 200, 450);

        Font font3 = new Font("Comic Sans",Font.BOLD, 15);
        g.setFont(font3);
        g.setColor(Color.gray);
        g.drawString("PLAYER 1 - W for UP || S for DOWN", GAME_WIDTH/2 - 450, 450);

        Font font4 = new Font("Comic Sans",Font.BOLD, 15);
        g.setFont(font4);
        g.setColor(Color.gray);
        g.drawString("PLAYER 2 - ArrowUP for UP || ArrowDOWN for DOWN", GAME_WIDTH/2 - 450, 475);
    }

}
