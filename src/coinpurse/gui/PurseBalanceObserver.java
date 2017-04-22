package coinpurse.gui;

import coinpurse.Purse;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Pawat Nakpiphatkul
 */
public class PurseBalanceObserver extends JFrame implements Observer {
    
    private JLabel info;
    private int FONTSIZE = 32;
    public PurseBalanceObserver() {
        initFrame();
    }
    
    public void initFrame() {
        super.setTitle("Purse Balance");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new FlowLayout());
        initUI();
        super.setAlwaysOnTop(true);
        super.pack();
        super.setVisible(true);
    }
    
    public void initUI() {
        info = new JLabel(" Please deposit money . . . ");
        info.setFont(new Font(Font.MONOSPACED,Font.BOLD,FONTSIZE));
        super.add(info);
    }
    
    @Override
    public void update(Observable subject, Object info) {
        if(subject instanceof Purse) {
            Purse purse = (Purse) subject;
            double balance = purse.getBalance();
            String currency = purse.getCurrency();
            if(balance == 0) this.info.setText(" Please deposit money . . . ");
            else this.info.setText(" "+balance+" "+currency);
        }
    }
    
}
