



import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * <code>ButtonKeyTest</code>.
 */
public class ButtonKeyTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ButtonKeyTest()::startUp);
    }

    private void startUp() {
        JButton b = new JButton("Use Enter Luke!");
        b.addActionListener(e -> JOptionPane.showMessageDialog(b, "Yeah!"));
        // rebind "space" action to "enter" for key pressed event
        Object actId = b.getInputMap().get(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0));
        b.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), actId);
        // rebind "space" action to "enter" for key released event
        actId = b.getInputMap().get(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true));
        b.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), actId);
        // end rebind

        // alternative
        // b.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "pressed");
        // b.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");

        JPanel p = new JPanel();
        p.add(b);
        JFrame frm = new JFrame("Button");
        frm.add(p);
        frm.setSize(200, 100);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
}