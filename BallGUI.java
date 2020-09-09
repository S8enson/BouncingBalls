package BallGUI;

/**
 * A class which demonstrates how a Swing application is multithreaded with the
 * main thread and the event dispatch thread
 *
 * @author Andrew Ensor
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BallGUI extends JPanel implements ActionListener {

    private JButton addBallButton;
    private JButton add100Button;
    private Timer timer;
    private Random random;
    DrawPanel drawPanel;
    int z = 0;
    ArrayList<Ball> Balls;
    private Ball ball;

    public BallGUI() {
        super(new BorderLayout());
        addBallButton = new JButton("Add Ball");
        add100Button = new JButton("Add 100 Balls");
        addBallButton.addActionListener((ActionListener) this);
        add100Button.addActionListener((ActionListener) this);
        JPanel southPanel = new JPanel();
        southPanel.add(addBallButton);
        southPanel.add(add100Button);
        add(southPanel, BorderLayout.SOUTH);
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
        Balls = new ArrayList<Ball>();
        timer = new Timer(5, this);

        timer.start();

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addBallButton) {
            ball = new Ball(getHeight(), getWidth());

            Balls.add(ball);

        }
        if (source == add100Button) {
            for (int i = 1; i < 100; i++) {
                ball = new Ball(getHeight(), getWidth());

                Balls.add(ball);
            }
        }
        if (source == timer) {

            drawPanel.repaint();

        }

    }

    private class DrawPanel extends JPanel {

        public DrawPanel() {
            super();
            setPreferredSize(new Dimension(400, 400));
            setBackground(Color.white);

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
//        Ball ball = new Ball(400, 400);
//        ball.drawBall(g);
            for (int i = 0; i < Balls.size(); i++) {
                Ball.worldHeight = getHeight();
                Ball.worldWidth = getWidth();
                Balls.get(i).drawBall(g);

            }

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Balls");
        // kill all threads when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BallGUI());

        frame.pack();
        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width - frameDimension.width) / 2,
                (screenDimension.height - frameDimension.height) / 2);
        frame.setVisible(true);

        // now display something while the main thread is still alive
    }
}
