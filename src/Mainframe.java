import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Mainframe extends JFrame implements ActionListener {
	private JTextField pathRequest;
	private JButton runFunctionBTN;
	private JLabel[] labels;
	private LogHandler functions;
	private JComboBox<String> optionsDropList;
	private String[] options2Run = { "Copy from file to database", "Copy from database to file", "Read from file", "Read from database" };;
	
	public Mainframe(String title) throws HeadlessException {
		super(title);
		Container c = getContentPane();
		setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		labels = new JLabel[] {new JLabel(" file directory: "), new JLabel(" option: ")};
		functions = new LogHandler();
		optionsDropList = new JComboBox<>(options2Run);
		pathRequest = new JTextField(20);
		runFunctionBTN = new JButton("Go");
		c.add(labels[0]);
		c.add(pathRequest);
		c.add(labels[1]);
		c.add(optionsDropList);
		c.add(runFunctionBTN);
		runFunctionBTN.addActionListener((ActionListener) this);
	}//O(N)
	
	public void checkWhichFunc2Run(boolean flag) {
		if (optionsDropList.getSelectedItem() == options2Run[0]) {
			flag = functions.log2Db(pathRequest.getText(), "logs.db");
			if (!flag)
				JOptionPane.showMessageDialog(null, "done copy from log to database.", "Answer:", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (optionsDropList.getSelectedItem() == options2Run[1]) {
			flag = functions.db2Log(pathRequest.getText(), "logs.db");
			if (!flag)
				JOptionPane.showMessageDialog(null, "done copy from database to log.", "Answer:", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (optionsDropList.getSelectedItem() == options2Run[2]) {
			String answer = functions.printLog(pathRequest.getText());
			if (answer != null)
				JOptionPane.showMessageDialog(null, answer, "Answer:", JOptionPane.INFORMATION_MESSAGE);
			else
				flag = true;
		}
		else if (optionsDropList.getSelectedItem() == options2Run[3]) {
			String answer = functions.printDB("logs.db");
			if (answer != null)
				JOptionPane.showMessageDialog(null, answer, "Answer:", JOptionPane.INFORMATION_MESSAGE);
			else
				flag = true;
		}
		if (flag)
			JOptionPane.showMessageDialog(null, "Invalid path.", "Answer:", JOptionPane.INFORMATION_MESSAGE);
	}//O(1)
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == runFunctionBTN)
			checkWhichFunc2Run(false);
	}//O(1)
}