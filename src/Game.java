import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{



    private Window window;
    private int width, height;
    public String title;

    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;


    //States
    private State gameState;
    private State menuState;
    private State settingsState;

    //Input

    private KeyManager keyManager;

    //Camera

    private GameCamera gameCamera;

    public Game(String title, int width, int height){
        this.height = height;
        this.width = width;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init(){
        window = new Window(title, width, height);
        window.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameCamera = new GameCamera(this, 0,0);

        gameState = new GameState(this);
        menuState = new MenuState(this);
        settingsState = new SettingsState(this);
        State.setState(gameState);

    }

    private void tick(){
        keyManager.tick();
        if(State.getState() != null)
            State.getState().tick();

    }




    private void render(){

        bs = window.getCanvas().getBufferStrategy();
        if(bs == null){
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear screen
        g.clearRect(0,0, width, height);
        //Draw Here!

        if(State.getState() != null)
            State.getState().render(g);


        //End Draw!

        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta --;
            }
            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public GameCamera getGameCamera(){return gameCamera;}

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }



    public synchronized void stop(){
            if(!running)
                return;
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


}


