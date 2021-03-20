package pl.michal;

import java.awt.*;

import static pl.michal.GamePanel.GAME_WIDTH;

public class Difficulty {

    public Rectangle easy = new Rectangle(GAME_WIDTH / 2 - 100, 145, 200, 50);
    public Rectangle normal = new Rectangle(GAME_WIDTH / 2 - 100, 245, 200, 50);
    public Rectangle hard = new Rectangle(GAME_WIDTH / 2 - 100, 345, 200, 50);

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("arial",Font.BOLD, 50);
        g.setFont(font);
        g.setColor(Color.GREEN);
        g.drawString("Select difficulty level", GAME_WIDTH/2 - 240, 75);

        Font font1 = new Font("arial",Font.BOLD, 30);
        g.setFont(font1);
        g.drawString("Easy", easy.x + 65, easy.y + 35);
        g2d.draw(easy);
        g.drawString("Normal", normal.x + 50, normal.y + 35);
        g2d.draw(normal);
        g.drawString("Hard", hard.x + 65, hard.y + 35);
        g2d.draw(hard);


    }

}
