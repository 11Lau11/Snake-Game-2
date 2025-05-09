import javax.swing.*;
import java.awt.event.*;

public class StartStop extends JPanel implements ActionListener {
    static boolean running = false;
    static Timer timer;
    static int DELAY = 100;
    GamePanel panel;
    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();

    }
    public StartStop(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (StartStop.running) {//daca jocul ruleaza
            Snake.move();// sarpele se va misca
            Snake.checkApple();// verificam daca am luat marul
            Snake.checkColission();// verificam coliziunea cu peretele
        }
        panel.repaint();
    }

    public enum Movement {
        UP,
        LEFT,
        DOWN,
        RIGHT;
    }

    public static class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    if (Snake.direction != StartStop.Movement.RIGHT) {
                        Snake.direction = StartStop.Movement.LEFT;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    if (Snake.direction != StartStop.Movement.LEFT) {
                        Snake.direction = StartStop.Movement.RIGHT;
                    }
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    if (Snake.direction != StartStop.Movement.DOWN) {
                        Snake.direction = StartStop.Movement.UP;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    if (Snake.direction != StartStop.Movement.UP) {
                        Snake.direction = StartStop.Movement.DOWN;
                    }
                    break;
                case KeyEvent.VK_ESCAPE:// daca apas esc programul se inchide
                    System.exit(0);
                    break;
                case KeyEvent.VK_SPACE: // prin apasarea tastei space jocul se reseteaza
                    Snake.x = new int[GamePanel.GAME_UNITS];
                    Snake.y = new int[GamePanel.GAME_UNITS];
                    Snake.x[0] = 0;
                    Snake.y[0] = 300;
                    Snake.bodyParts = 6;
                    Snake.applesEaten = 0;
                    Snake.direction = StartStop.Movement.RIGHT;
                    running = true;
                    Apple.appleX = Apple.random.nextInt((int) (GamePanel.SCREEN_WIDTH / GamePanel.UNIT_SIZE)) * GamePanel.UNIT_SIZE;
                    Apple.appleY = Apple.random.nextInt((int) (GamePanel.SCREEN_HEIGHT / GamePanel.UNIT_SIZE)) * GamePanel.UNIT_SIZE;
                    timer.start();
                    break;
                case KeyEvent.VK_P:// prin apasarea tastei P jocul se pune pe pauza sau varula dinou de unde a ramas
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                    break;
            }
        }
    }
}
