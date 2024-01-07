package GUI;

import Ants.Ant;
import Main.AntPopulation;
import Main.Simulation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The InfoPanel class extends the JPanel class and represents the information panel in the GUI.
 * It includes methods for updating the information panel.
 */
public class InfoPanel extends JPanel
{
    // The ant population whose information is to be displayed
    private final AntPopulation antPopulation;
    // The table model for the information table
    private final DefaultTableModel model;
    // The resource collected by the ants
    private final String resource;

    /**
     * Constructor for the InfoPanel class.
     * It initializes the information panel and sets up the information table.
     */
    public InfoPanel()
    {
        antPopulation = AntPopulation.access();
        model = new DefaultTableModel(antPopulation.getSize() + 1, 6);
        JTable infoTable = new JTable();
        infoTable.setModel(model);
        infoTable.getColumnModel().getColumn(0).setMinWidth(150);
        infoTable.getColumnModel().getColumn(5).setMinWidth(125);
        infoTable.setBackground(Color.BLACK);
        infoTable.setForeground(Color.WHITE);
        add(infoTable);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        if (Simulation.FREEDOM_MODE)
            this.resource = "dollars";
        else
            this.resource = "larvae";
    }

    /**
     * This method updates the information panel.
     * It updates the information table with the current state of the ant population.
     */
    public void updateInfo()
    {
        antPopulation.getAnt_semaphore().acquireUninterruptibly();
        model.setRowCount(0);
        model.addRow(new Object[]{"Name", "Color", "Class", "Strength", "Health", "Collected " + resource});
        for (Ant ant : antPopulation.getAnts())
        {
            model.addRow(new Object[]{ant.get_Name(), ant.getColor(), ant.getClass().getName().substring(5), ant.getStrength(), ant.getHealth(), ant.getCollected_larvae()});
        }

        antPopulation.getAnt_semaphore().release();
    }
}