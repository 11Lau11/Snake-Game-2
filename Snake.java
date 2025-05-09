import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Snake extends JPanel {
   static int x[] = new int[GamePanel.GAME_UNITS];
   static int y[] = new int[GamePanel.GAME_UNITS];
    static int bodyParts = 6;
    static int applesEaten;
public static StartStop.Movement direction = StartStop.Movement.RIGHT;
    Apple apple = new Apple();

    public static void checkApple() {
        // cream o conditie ca sa verificam daca marul a fost luat
        if ((x[0] == Apple.appleX) && (y[0] == Apple.appleY)) {
            bodyParts++;// daca marul a fost luat creste dimensiunea corpului cu cate 1
            applesEaten++;// se numara cate mere au fost mancate "Score", incepe de la 0
            StartStop.DELAY = Math.max(StartStop.DELAY - 1, 50);//jocul va rula din ce in ce mai rapid, limita timerului fiind de 50(ms) (conditiea mea)
            StartStop.timer.setDelay(StartStop.DELAY);

            Apple.newApple();
        }

    }
    public static void checkColission(){
        // facem verificarea daca capul sarpelui se loveste de corp
        for(int i = bodyParts;i>0;i--){
            if((x[0] == x[i])&& (y[0] == y[i])){
                /*
                x[0] si y[0] sunt pozitiile capului, x[i] si y[i] sunt pozitile fiecarui
                segment al corpului, daca pozitile capului au aceasi valoare cu oricare segment
                (adica pozitile lor) atunci se indeplineste conditia si programul isi opreste rularea
                 */
                StartStop.running = false;
            }
        }
        // verificam daca capul se atinge de peretele din stanga a peretelui
        if(x[0] < 0){
            StartStop.running = false;
        }
        // verificam daca capul se atinge de peretele din dreapta a peretelui
        if(x[0] > GamePanel.SCREEN_WIDTH){
            StartStop.running = false;
        }
        // verificam daca capul se atinge de peretele de sus a peretelui
        if(y[0] < 0){
            StartStop.running = false;
        }
        // verificam daca capul se atinge de peretele de jos a peretelui
        if(y[0] > GamePanel.SCREEN_HEIGHT){
            StartStop.running = false;
        }
        if(!StartStop.running){ // daca nu ruleaza timerul se opreste
            StartStop.timer.stop();
        }
    }
    public void paintComponent (Graphics grafica){
        super.paintComponent(grafica);
        drawSnake(grafica);
        apple.paintComponent(grafica);
    }

    private void drawSnake(Graphics grafica) {
        for (int i = 0; i < bodyParts; i++) {// se creaza corpul in bucla aceasta si va fi incrementat pana ajunge la marimea prestabilita de noi (bodyparts = 6)
            if (i == 0) {//la primul segment/parte a corpului se creaza capul
                grafica.setColor(Color.green);//culoare cap
                grafica.fillRect(x[i], y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);//forma capului
            } else {//aici se creaza restul corpului
                grafica.setColor(new Color(45, 180, 0));//culoare corp
                grafica.fillRect(x[i], y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);//forma corpului
            }
        }
    }
    public static void move() {
        //miscarea sarpelui
        for (int i = bodyParts; i > 0; i--) { // mutam partiile corpului ca sa simulam miscarea
            x[i] = x[i - 1]; // partiile corpului se muta cu cate o casuta la coordonata x
            y[i] = y[i - 1]; // partiile corpului se muta cu cate o casuta la coordonata y
        }

        switch(direction){ // decide ce actiune sa intreprinda in functie de valoarea variabilei
            case UP:
                y[0] = y[0] - GamePanel.UNIT_SIZE; //y[0] si x[0] este coordonata capului sarpelui si y[0]/x[0]+/-UNIT_SIZE face in asa fel incat sa mearga la urmatoarea pozitie
                break;
            case DOWN:
                y[0] = y[0] + GamePanel.UNIT_SIZE;
                break;
            case LEFT:
                x[0] = x[0] - GamePanel.UNIT_SIZE;
                break;
            case RIGHT:
                x[0] = x[0] + GamePanel.UNIT_SIZE;
                break;
        }
    }
}
