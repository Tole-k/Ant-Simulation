package GUI;

import Ants.Ant;
import Main.AntPopulation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InfoPanel extends JPanel
{
    private final JTable infoTable;
    private final AntPopulation antPopulation;
    private final DefaultTableModel model;

    public InfoPanel()
    {
        antPopulation = AntPopulation.access();
        model = new DefaultTableModel(antPopulation.getSize() + 1, 5);
        infoTable = new JTable();
        infoTable.setModel(model);
        infoTable.setBackground(Color.BLACK);
        infoTable.setForeground(Color.WHITE);
        add(infoTable);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        updateInfo();
    }

    public void updateInfo()
    {
        antPopulation.ant_semaphore.acquireUninterruptibly();
        model.setRowCount(antPopulation.getSize() + 1);
        infoTable.getModel().setValueAt("Name", 0, 0);
        infoTable.getModel().setValueAt("Color", 0, 1);
        infoTable.getModel().setValueAt("Strength", 0, 2);
        infoTable.getModel().setValueAt("Health", 0, 3);
        infoTable.getModel().setValueAt("Collected Larvae", 0, 4);
        int i = 0;
        for (Ant ant : antPopulation.getAnts())
        {
            infoTable.setValueAt(ant.get_Name(), ++i, 0);
            infoTable.setValueAt(ant.getColor(), i, 1);
            infoTable.setValueAt(ant.getStrength(), i, 2);
            infoTable.setValueAt(ant.getHealth(), i, 3);
            infoTable.setValueAt(ant.getCollected_larvae(), i, 4);
        }
        antPopulation.ant_semaphore.release();
    }
}