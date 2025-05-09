import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static  final int  UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT )/ UNIT_SIZE;
    Random random;
    Apple apple = new Apple();
    Snake snake = new Snake();
    StartStop startStop;
    GamePanel(){
        random =new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new StartStop.MyKeyAdapter());
        Snake.x[0] = 0;
        Snake.y[0] = 300;
        startStop = new StartStop(this);
        startStop.startGame();
    }
    public void paintComponent(Graphics grafica) {
        super.paintComponent(grafica);
        if (StartStop.running) {

            drawTerrain(grafica);
            snake.paintComponent(grafica);
            apple.paintComponent(grafica);
            Score(grafica);
        }
        else{
            gameOver(grafica);
        }
    }
    private void drawTerrain(Graphics grafica) {
        if (StartStop.running) {// daca jocul ruleaza se indeplinesc celelalte conditii
            //am creat o grila pentru fundal
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                grafica.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                grafica.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);

            }
        }
        else {
            gameOver(grafica);
        }
    }
    public void Score(Graphics grafica){
        grafica.setColor(Color.red);// am setat culoarea textului
        grafica.setFont(new Font("Ink Free",Font.BOLD,40)); // dimensiunea fontului si stilul
        FontMetrics metrics = getFontMetrics(grafica.getFont());// am creat un obiect care returneaza aspectele unui font
        grafica.drawString("Score: "+Snake.applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: "+Snake.applesEaten))/2,grafica.getFont().getSize());
    }
    public void gameOver(Graphics grafica){
        // afisam scorul si la game over
        grafica.setColor(Color.red);// am setat culoarea textului
        grafica.setFont(new Font("Ink Free",Font.BOLD,40)); // dimensiunea fontului si stilul
        FontMetrics metrics1 = getFontMetrics(grafica.getFont());// am creat un obiect care returneaza aspectele unui font
        grafica.drawString("Score: "+Snake.applesEaten,(SCREEN_WIDTH - metrics1.stringWidth("Score: "+Snake.applesEaten))/2,grafica.getFont().getSize());
        // afisam text pe ecran
        if(Snake.applesEaten < 25) {// conditie facuta de mine
            grafica.setColor(Color.red);// am setat culoarea textului
            grafica.setFont(new Font("Ink Free", Font.BOLD, 75)); // dimensiunea fontului si stilul
            FontMetrics metrics2 = getFontMetrics(grafica.getFont());// am creat un obiect care returneaza aspectele unui font
            grafica.drawString("Skill Issue", (SCREEN_WIDTH - metrics2.stringWidth("Skill Issue")) / 2, SCREEN_HEIGHT / 2);// am aliniat textul sa fie pozitionat in centru
        }
        else{
            grafica.setColor(Color.red);// am setat culoarea textului
            grafica.setFont(new Font("Ink Free", Font.BOLD, 75)); // dimensiunea fontului si stilul
            FontMetrics metrics3 = getFontMetrics(grafica.getFont());// am creat un obiect care returneaza aspectele unui font
            grafica.drawString("Game Over", (SCREEN_WIDTH - metrics3.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);// am aliniat textul sa fie pozitionat in centru
        }
    }

}
