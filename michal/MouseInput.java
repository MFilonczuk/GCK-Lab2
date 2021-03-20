package pl.michal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static pl.michal.GamePanel.GAME_WIDTH;
import static pl.michal.GamePanel.State;
import static pl.michal.GamePanel.Diff;

public class MouseInput implements MouseListener {

    private boolean mouseListenerIsActive;



    @Override
    public void mouseClicked(MouseEvent e) {


    }


    @Override
    public void mousePressed(MouseEvent e) {


       if(!mouseListenerIsActive) {
           int mx = e.getX();
           int my = e.getY();


           if (State == GamePanel.STATE.PLAYERS1) {

               if (mx >= GAME_WIDTH / 2 - 100 && mx <= GAME_WIDTH / 2 + 100) {
                   if (my >= 145 && my <= 200)
                       Diff = GamePanel.DIFF.EASY;
               }
               if (mx >= GAME_WIDTH / 2 - 150 && mx <= GAME_WIDTH / 2 + 100) {
                   if (my >= 245 && my <= 300)
                       Diff = GamePanel.DIFF.NORMAL;
               }
               if (mx >= GAME_WIDTH / 2 - 100 && mx <= GAME_WIDTH / 2 + 100) {
                   if (my >= 345 && my <= 400)
                       Diff = GamePanel.DIFF.HARD;
               }
               return;
           }

           if (mx >= GAME_WIDTH / 2 - 100 && mx <= GAME_WIDTH / 2 + 100) {
               if (my >= 145 && my <= 200)
                   State = GamePanel.STATE.PLAYERS1;
           }
           if (mx >= GAME_WIDTH / 2 - 100 && mx <= GAME_WIDTH / 2 + 100) {
               if (my >= 245 && my <= 300) {
                   State = GamePanel.STATE.PLAYERS2;
                   mouseListenerIsActive = true;
               }

           }
           if (mx >= GAME_WIDTH / 2 - 50 && mx <= GAME_WIDTH / 2 + 50) {
               if (my >= 345 && my <= 400)
                   System.exit(1);
           }
       }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
