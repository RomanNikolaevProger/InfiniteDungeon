import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GameWindow extends JFrame implements KeyListener {

    GameProcessor GP;

    MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int MouseXR = e.getPoint().x-8;
            int MouseYR = e.getPoint().y-32;

            if (GP.StageID==0) {
                if (MouseXR > 864 && MouseXR < 1056 && MouseYR > 305 && MouseYR < 381) {
                    GP.StageID = 1;
                    repaint();
                }
                /*if (MouseXR > 735 && MouseXR < 1125 && MouseYR > 505 && MouseYR < 581) {
                    StageID = 2;
                    repaint();
                }*/
                if (MouseXR > 864 && MouseXR < 1056 && MouseYR > 705 && MouseYR < 781) {
                    System.exit(0);
                }
            } else {
                if (GP.StageID == 1) {
                    if (MouseXR > 700 && MouseXR < 892 && MouseYR > 705 && MouseYR < 781) {
                        GP.StageID = 0;
                        repaint();
                    }
                    if (MouseXR > 1028 && MouseXR < 1220 && MouseYR > 705 && MouseYR < 781) {
                        GP.StageID = -1;
                        repaint();
                    }
                    if (MouseXR > 664 && MouseXR < 856 && MouseYR > 505 && MouseYR < 581) {
                        GP.Difficulty = 0;
                        repaint();
                    }
                    if (MouseXR > 864 && MouseXR < 1056 && MouseYR > 505 && MouseYR < 581) {
                        GP.Difficulty = 1;
                        repaint();
                    }
                    if (MouseXR > 1064 && MouseXR < 1256 && MouseYR > 505 && MouseYR < 581) {
                        GP.Difficulty = 2;
                        repaint();
                    }
                    if (MouseXR > 600 && MouseXR < 820 && MouseYR > 305 && MouseYR < 381) {
                        GP.Class = 0;
                        repaint();
                    }
                    if (MouseXR > 850 && MouseXR < 1070 && MouseYR > 305 && MouseYR < 381) {
                        GP.Class = 1;
                        repaint();
                    }
                    if (MouseXR > 1100 && MouseXR < 1320 && MouseYR > 305 && MouseYR < 381) {
                        GP.Class = 2;
                        repaint();
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
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
    };

    public GameWindow(GameProcessor GPlink) throws IOException {
        setSize(1920,1080);
        setTitle("EVERCELL");
        setVisible(true);
        this.GP = GPlink;
        Painter cp = new Painter(GPlink,this);
        add(cp);
        addMouseListener(ml);
        setFocusable(true);
        addKeyListener(this);
        requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            GP.PressedA = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            GP.PressedD = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            GP.PressedS = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            GP.PressedW = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            GP.PressedA = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            GP.PressedS = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            GP.PressedD = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            GP.PressedW = false;
        }
    }


    public void paint (Graphics g) {
        super.paintComponents(g);
    }
}
