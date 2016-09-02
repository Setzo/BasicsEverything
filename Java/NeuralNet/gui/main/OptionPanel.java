package gui.main;

import gui.events.OptionsChangedEvent;
import gui.listeners.SubmitButtonListener;
import gui.listeners.ValidationFailedListener;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.Controller;
import controller.Validator;

public class OptionPanel extends JPanel {

    private static final long serialVersionUID = 3389021541615862354L;

    private static double ETA_STEP_SIZE = 0.01;
    private static double ALPHA_STEP_SIZE = 0.01;

    private JSpinner etaSpinner;
    private SpinnerNumberModel etaSpinnerModel;
    private JLabel etaSpinnerLabel;

    private JSpinner alphaSpinner;
    private SpinnerNumberModel alphaSpinnerModel;
    private JLabel alphaSpinnerLabel;

    private JLabel inputFilePathLabel;
    private JTextField inputFilePathField;

    private JLabel connectionWeightsFilePathLabel;
    private JTextField connectionWeightsFilePathField;

    private JButton submitButton;
    private JButton submitRunButton;

    //TODO Reset to defaults in gridx = 2, make all textfields gridwidth = 2

    private SubmitButtonListener submitListener;
    private ValidationFailedListener validationListener;

    public OptionPanel() {

        submitButton = new JButton("Submit");
        submitRunButton = new JButton("Submit & Run");

        etaSpinnerModel = new SpinnerNumberModel(0.15, 0, 1, ETA_STEP_SIZE);
        etaSpinner = new JSpinner(etaSpinnerModel);
        etaSpinnerLabel = new JLabel("ETA:");
        etaSpinnerLabel.setLabelFor(etaSpinner);

        alphaSpinnerModel = new SpinnerNumberModel(0.5, 0, 1, ALPHA_STEP_SIZE);
        alphaSpinner = new JSpinner(alphaSpinnerModel);
        alphaSpinnerLabel = new JLabel("Momentum:");
        alphaSpinnerLabel.setLabelFor(alphaSpinner);

        inputFilePathField = new JTextField();
        inputFilePathLabel = new JLabel("Input:");
        inputFilePathLabel.setLabelFor(inputFilePathField);

        connectionWeightsFilePathField = new JTextField();
        connectionWeightsFilePathLabel = new JLabel("Weights:");
        connectionWeightsFilePathLabel.setLabelFor(connectionWeightsFilePathField);

        addListeners();

        setBorder(BorderFactory.createTitledBorder("OptionPanel"));

        doLayoutStuff();

        //setPreferredSize(new Dimension(250, 500));
        setVisible(true);
    }

    public void addListeners() {

        etaSpinner.addMouseWheelListener((e) -> {

            etaSpinner.setValue(
                    ((Double) etaSpinner.getValue()).doubleValue() < 0.0 ? 0.0 :
                            ((Double) etaSpinner.getValue()).doubleValue() + ETA_STEP_SIZE * -e.getWheelRotation());

            if (((Double) etaSpinner.getValue()).doubleValue() < 0) {
                etaSpinner.setValue(0.0);

            } else if (((Double) etaSpinner.getValue()).doubleValue() > 1) {
                etaSpinner.setValue(1.0);
            }
        });

        alphaSpinner.addMouseWheelListener((e) -> {

            alphaSpinner.setValue(
                    ((Double) alphaSpinner.getValue()).doubleValue() < 0.0 ? 0.0 :
                            ((Double) alphaSpinner.getValue()).doubleValue() + ALPHA_STEP_SIZE * -e.getWheelRotation());

            if (((Double) alphaSpinner.getValue()).doubleValue() < 0) {
                alphaSpinner.setValue(0.0);

            } else if (((Double) alphaSpinner.getValue()).doubleValue() > 1) {
                alphaSpinner.setValue(1.0);
            }
        });

        submitButton.addActionListener((e) -> {

            if (Validator.isEtaAlphaValid(etaSpinner.getValue())) {

                if (Validator.isEtaAlphaValid(alphaSpinner.getValue())) {

                    if (Validator.isInputValid(inputFilePathField.getText())) {

                        if (Validator.areConnectionWeightsValid(connectionWeightsFilePathField.getText())) {

                            submitListener.submitButtonClicked(
                                    new OptionsChangedEvent(

                                            OptionPanel.this,
                                            (Double) etaSpinner.getValue(),
                                            (Double) alphaSpinner.getValue(),
                                            new File(inputFilePathField.getText()),
                                            Controller.loadConnectionWeightsFromFile(new File(connectionWeightsFilePathField.getText())))
                            );

                        } else {
                            validationListener.validationFailed("Connection Weights Validation Failed",
                                    "Connection weights file path is incorrect, or file is corrupted.");
                        }

                    } else {
                        validationListener.validationFailed("Input Validation Failed",
                                "Input file path is incorrect, or file is corrupted.");
                    }

                } else {
                    validationListener.validationFailed("Momentum Validation Failed",
                            "Momentum value is incorrect.");
                }

            } else {
                validationListener.validationFailed("ETA Validation Failed",
                        "ETA value is incorrect.");
            }
        });
    }

    public void doLayoutStuff() {

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //Insets blank = new Insets(0, 0, 0, 0);
        //Insets fiveToLeft = new Insets(0, -5, 0, 0);

        //**************		ROW 0		**************

        gc.weightx = 0;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.BOTH;

        gc.gridx = 0;
        gc.gridy = 0;
        //gc.insets = fiveToLeft;

        //**************		ROW++		**************

        gc.gridx = 0;
        gc.gridy++;

        add(etaSpinnerLabel, gc);

        gc.gridx++;

        add(etaSpinner, gc);

        //**************		ROW++		**************

        gc.gridx = 0;
        gc.gridy++;

        add(alphaSpinnerLabel, gc);

        gc.gridx++;

        add(alphaSpinner, gc);

        //**************		ROW++		**************

        gc.gridx = 0;
        gc.gridy++;

        add(inputFilePathLabel, gc);

        gc.gridx++;

        add(inputFilePathField, gc);

        //**************		ROW++		**************

        gc.gridx = 0;
        gc.gridy++;

        add(connectionWeightsFilePathLabel, gc);

        gc.gridx++;

        add(connectionWeightsFilePathField, gc);

        //**************		ROW++		**************

        gc.gridx = 0;
        gc.gridy++;

        add(submitButton, gc);

        gc.gridx++;

        add(submitRunButton, gc);
    }

    public void setSubmitButtonListener(SubmitButtonListener submitListener) {

        this.submitListener = submitListener;
    }

    public void setValidationFailedListener(ValidationFailedListener validationListener) {

        this.validationListener = validationListener;
    }
}