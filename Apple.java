import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Apple extends JPanel {
    static Random random;
    static int appleX;
    static int appleY;

    public Apple() {
        random = new Random();
        newApple();
    }

    public void paintComponent(Graphics grafica) {
        super.paintComponent(grafica);
        draw(grafica);
    }

    private void draw(Graphics grafica) {


        boolean appleCovered = false;

        for (int i = 0; i < Snake.bodyParts; i++) {
            if (Snake.x[i] == appleX && Snake.y[i] == appleY) {
                appleCovered = true;
                break;
            }
        }

        if (!appleCovered) {
            grafica.setColor(Color.red);
            grafica.fillOval(appleX, appleY, GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
        }


    }

    public static void newApple() {
        appleX = random.nextInt((int) (GamePanel.SCREEN_WIDTH / GamePanel.UNIT_SIZE)) * GamePanel.UNIT_SIZE;
        appleY = random.nextInt((int) (GamePanel.SCREEN_HEIGHT / GamePanel.UNIT_SIZE)) * GamePanel.UNIT_SIZE;
    }
}

