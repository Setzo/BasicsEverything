package nameOccupy;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {

    private static final long serialVersionUID = -8935448900550211127L;

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameTextField;
    private JTextField occupationTextField;
    private JButton submitInfo;
    private FormListener formListener;

    public FormPanel() {

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");

        nameTextField = new JTextField(10);
        occupationTextField = new JTextField(10);

        submitInfo = new JButton("Submit");

        submitInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameTextField.getText();
                String occupation = occupationTextField.getText();
                FormEvent ev = new FormEvent(this, name, occupation);

                if (formListener != null) {
                    formListener.formEventOccured(ev);
                }
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("FormPanel");
        Border outerBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();


        Insets blankInset = new Insets(0, 0, 0, 0);
        Insets fiveFromRightInset = new Insets(0, 0, 0, 5);

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = fiveFromRightInset;
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInset;
        add(nameTextField, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = fiveFromRightInset;
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInset;
        add(occupationTextField, gc);

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(submitInfo, gc);
    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }
}
