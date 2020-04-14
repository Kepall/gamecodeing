import javax.swing.*;

public class Launcher {


    public static void main(String[] args) {

        Object[] options = {"Start Game",
                "Options", "Quit"};
        int n = JOptionPane.showOptionDialog(null, "Select Option:", "Game Launcher",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);


        if (n == JOptionPane.YES_OPTION) {
            System.out.println("Started!");
            Game game = new Game("JayLex alpha 0.3", 400, 400);
            game.start();

        }
        else if(n == JOptionPane.CANCEL_OPTION){
            System.exit(0);
        }

        else{
            JOptionPane.showMessageDialog(null, "This option is currently not avaliable");
        }

    }
}