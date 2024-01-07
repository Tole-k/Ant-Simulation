package GUI;

import Ants.Ant;
import Main.AntPopulation;
import Main.Simulation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InfoPanel extends JPanel
{
    private final AntPopulation antPopulation;
    private final DefaultTableModel model;
    private final String resource;

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