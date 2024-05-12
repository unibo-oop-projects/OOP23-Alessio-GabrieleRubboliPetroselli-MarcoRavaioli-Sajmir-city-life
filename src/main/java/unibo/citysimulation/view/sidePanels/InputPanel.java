package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InputPanel extends StyledPanel {
    private JButton startButton;
    private JSlider peopleSlider;
    private JSlider businessSlider;
    private JSlider richnessSlider;

    public InputPanel(Color bgColor) {
        super(bgColor);
    
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        JLabel label = new JLabel("INPUTPANEL", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2; // Occupa due colonne
        gbc.fill = GridBagConstraints.BOTH;
        add(label, gbc);
    
        gbc.gridwidth = 1; // Resetta a una colonna per gli elementi successivi
    
        // Creazione e aggiunta dello slider per il numero di persone
        peopleSlider = createSlider("Number of People", ConstantAndResourceLoader.MIN_PEOPLE, ConstantAndResourceLoader.MAX_PEOPLE);
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        add(peopleSlider, gbc);
    
        // Creazione e aggiunta dello slider per il numero di business
        businessSlider = createSlider("Number of Business", 0, 100);
        gbc.gridy = 2;
        add(businessSlider, gbc);
    
        // Creazione e aggiunta dello slider per il benessere generale delle persone
        richnessSlider = createSlider("People's Richness", 0, 100);
        gbc.gridy = 3;
        add(richnessSlider, gbc);
    
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
    
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(startButton);
    
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Occupa due colonne
        add(buttonPanel, gbc);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public int getPeopleSliderValue() {
        return peopleSlider.getValue();
    }

    public int getBusinessSliderValue() {
        return businessSlider.getValue();
    }

    public int getRichnessSliderValue() {
        return richnessSlider.getValue();
    }

    public JSlider getPeopleSlider() {
        return peopleSlider;
    }

    public JSlider getBusinessSlider() {
        return businessSlider;
    }

    public JSlider getRichnessSlider() {
        return richnessSlider;
    }

    private JSlider createSlider(String title, int min, int max) {
        JSlider slider = new JSlider(min, max);
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleColor(Color.WHITE);
        slider.setBorder(border);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setForeground(Color.WHITE);
        return slider;
    }

    private JSlider getSlider(int index) {
        switch (index) {
            case 1:
                return peopleSlider;
            case 2:
                return businessSlider;
            case 3:
                return richnessSlider;
            default:
                return null;
        }
    }

    private GridBagConstraints createConstraints(int x, int y, double weightx, double weighty, int fill) {
        return new GridBagConstraints() {{
            gridx = x;
            gridy = y;
            this.weightx = weightx;
            this.weighty = weighty;
            this.fill = fill;
        }};
    }
}
