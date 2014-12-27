package swingUt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = -7611703358605238817L;
	private JLabel percentage = new JLabel("0");
	private JLabel statusLabel = new JLabel("Not started");
	private JButton startButton = new JButton("Start");
	
	public MainFrame(String title) {
		
		super(title);
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		add(percentage, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		add(statusLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		add(startButton, gc);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		
		setSize(220, 260);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void start() {
		SwingWorker<Boolean, Integer> sw = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				
				for(int i=0; i<100; i++) {
					Thread.sleep(100);
					System.out.println(i + " Hello");
					publish(i+1);
				}
				return true;
			}

			@Override
			protected void process(List<Integer> chunks) {
				
				Boolean stat = false;
				int val = chunks.get(chunks.size()-1);
				percentage.setText(String.valueOf(val) + "% Done");
				statusLabel.setText("Not completed, status: " + stat.toString());
			}

			@Override
			protected void done() {
				
				try {
					Boolean stat = get();
					statusLabel.setText("Completed, status: " + stat.toString());
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			
		};
		sw.execute();
	}
}
