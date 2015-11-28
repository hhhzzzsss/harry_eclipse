
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class controlPanel extends JPanel implements ActionListener{
	public JButton reloadButton = new JButton("Reload Mandlebrot");
	public JTextField tfx = new JTextField("-2.0");
	public JTextField tfy = new JTextField("-2.0");
	public JTextField tfd = new JTextField("4");
	public controlPanel() {
		reloadButton.setActionCommand("reload");
		reloadButton.addActionListener(this);
		tfx.setPreferredSize(new Dimension(100, 25));
		tfy.setPreferredSize(new Dimension(100, 25));
		tfd.setPreferredSize(new Dimension(100, 25));
		this.add(reloadButton);
		this.add(tfx);
		this.add(tfy);
		this.add(tfd);
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("reload".equals(e.getActionCommand())) {
			Main.selectionArea[0] = Double.parseDouble(tfx.getText());
			Main.selectionArea[1] = Double.parseDouble(tfy.getText());
			Main.selectionArea[2] = Double.parseDouble(tfd.getText());
			Main.reloadPanel();
		}
	}
}
