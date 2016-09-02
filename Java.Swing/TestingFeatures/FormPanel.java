package nameOccupy;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class FormPanel extends JPanel {

    private static final long serialVersionUID = -8935448900550211127L;

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameTextField;
    private JTextField occupationTextField;
    private JButton submitInfo;
    private FormListener formListener;
    private JList<AgeCategory> ageList;
    private JComboBox<String> empComboBox;
    private JCheckBox citizenCheck;
    private JTextField nrDowodu;
    private JLabel nrDowoduLabel;

    public FormPanel() {

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");

        nameTextField = new JTextField(10);
        occupationTextField = new JTextField(10);

        ageList = new JList<AgeCategory>();

        //JList set up
        DefaultListModel<AgeCategory> ageModel = new DefaultListModel<AgeCategory>();
        ageModel.addElement(new AgeCategory(0, "0-10"));
        ageModel.addElement(new AgeCategory(1, "10-18"));
        ageModel.addElement(new AgeCategory(2, "18+"));

        ageList.setPreferredSize(new Dimension(120, 66));
        ageList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        ageList.setModel(ageModel);
        ageList.setSelectedIndex(0);

        empComboBox = new JComboBox<String>();

        //JComboBox set up
        DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<String>();
        empModel.addElement("Employed");
        empModel.addElement("Self-employed");
        empModel.addElement("Unemployed");
        empComboBox.setModel(empModel);
        empComboBox.setSelectedIndex(0);

        citizenCheck = new JCheckBox();
        nrDowodu = new JTextField(9);
        nrDowoduLabel = new JLabel("Document ID: ");

        //set up ^
        nrDowoduLabel.setEnabled(false);
        nrDowodu.setEnabled(false);

        citizenCheck.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //boolean isTicked = citizenCheck.isSelected();
                nrDowodu.setEnabled(citizenCheck.isSelected());
                nrDowoduLabel.setEnabled(citizenCheck.isSelected());
            }
        });

        submitInfo = new JButton("Submit");

        submitInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameTextField.getText();
                String occupation = occupationTextField.getText();
                AgeCategory age = ageList.getSelectedValue();

                String employment = (String) empComboBox.getSelectedItem();

                boolean plCitizen = citizenCheck.isSelected();
                String nrDow = nrDowodu.getText();

                FormEvent ev = new FormEvent(this, name, occupation, age.getId(), employment, plCitizen, nrDow);

                if (formListener != null) {
                    formListener.formEventOccured(ev);
                }
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("FormPanel");
        Border outerBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    public void layoutComponents() {

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();


        //Insets blankInset = new Insets(0, 0, 0, 0);
        //Insets fiveFromRightInset = new Insets(0, 0, 0, 5);

        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.05;

        //////////////ROW 1
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameTextField, gc);

        //////////////ROW++
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationTextField, gc);

        //////////////ROW++
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.02;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Age: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageList, gc);

        // ////////////ROW++
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.02;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Employment: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(empComboBox, gc);

        // ////////////ROW++
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.02;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Polish Citizen: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(citizenCheck, gc);

        // ////////////ROW++
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.02;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(nrDowoduLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(nrDowodu, gc);

        //////////////ROW++
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(submitInfo, gc);
    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }
}

class AgeCategory {

    private int id;
    private String text;

    public AgeCategory(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public String toString() {
        return text;
    }

    public int getId() {
        return id;
    }
}
