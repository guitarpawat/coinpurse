package coinpurse.gui;

import coinpurse.Purse;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Pawat Nakpiphatkul
 */
public class PurseStatusObserver extends JFrame implements Observer {

    private JLabel info;
    private JProgressBar bar;
    private int FONTSIZE = 32;
    public PurseStatusObserver() {
        initFrame();
    }
    
    public void initFrame() {
        super.setTitle("Purse Status");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        initUI();
        super.setAlwaysOnTop(true);
        super.pack();
        super.setVisible(true);
    }
    
    public void initUI() {
        info = new JLabel(" EMPTY              ");
        info.setFont(new Font(Font.MONOSPACED,Font.BOLD,FONTSIZE));
        super.add(info,BorderLayout.CENTER);
        bar = new JProgressBar();
        super.add(bar,BorderLayout.SOUTH);
    }
    
    @Override
    public void update(Observable subject, Object info) {
        if(subject instanceof Purse) {
            Purse purse = (Purse) subject;
            int current = purse.count();
            int capacity = purse.getCapacity();
            bar.setMinimum(0);
            bar.setMaximum(capacity);
            bar.setValue(current);
            if(current == capacity) this.info.setText(" FULL");
            else if(current == 0) this.info.setText(" EMPTY");
            else this.info.setText(" "+current+" ITEMS");
        }
    }
    
}
