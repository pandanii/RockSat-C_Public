package userinterface;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class rsc_jframe extends javax.swing.JFrame
{

    private static final long serialVersionUID = 1L;
    private java.io.File rawFlightfile;
    private java.io.File flightFile;
    private javax.swing.JFileChooser tempFileChooser;

    /**
     * Creates new form rsc_jframe
     */
    public rsc_jframe()
    {
        initComponents();
        //rscJTabbedPane.addTab("PreLoadedFlight", null, new FlightPanel());// INITIALIZED WITH A NEW FLIGHT PANEL
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        rscJTabbedPane = new javax.swing.JTabbedPane();
        WelcomePanel = new javax.swing.JPanel();
        WelcomeLabel = new javax.swing.JLabel();
        rscJMenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        LoadRawMenuItem = new javax.swing.JMenuItem();
        LoadFlightMenutItem = new javax.swing.JMenuItem();
        SaveFlightMenuItem = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" RockSat-C");
        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.darcula.color1"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        setForeground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        setName("Welcome"); // NOI18N

        WelcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WelcomeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/WelcomeScreen.jpg"))); // NOI18N
        WelcomeLabel.setToolTipText("");

        javax.swing.GroupLayout WelcomePanelLayout = new javax.swing.GroupLayout(WelcomePanel);
        WelcomePanel.setLayout(WelcomePanelLayout);
        WelcomePanelLayout.setHorizontalGroup(
            WelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(WelcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1287, Short.MAX_VALUE)
        );
        WelcomePanelLayout.setVerticalGroup(
            WelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(WelcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
        );

        rscJTabbedPane.addTab("Welcome Tab", WelcomePanel);

        rscJMenuBar.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.darcula.color2"));
        rscJMenuBar.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.darcula.color1"));
        rscJMenuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        FileMenu.setText("File");

        LoadRawMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        LoadRawMenuItem.setText("New Flight (load Raw)");
        LoadRawMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                LoadRawMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(LoadRawMenuItem);

        LoadFlightMenutItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        LoadFlightMenutItem.setText("Open Flight");
        LoadFlightMenutItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                LoadFlightMenutItemActionPerformed(evt);
            }
        });
        FileMenu.add(LoadFlightMenutItem);

        SaveFlightMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SaveFlightMenuItem.setText("Save Flight");
        SaveFlightMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SaveFlightMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(SaveFlightMenuItem);

        rscJMenuBar.add(FileMenu);

        EditMenu.setText("Edit");

        jMenu8.setText("jMenu8");
        EditMenu.add(jMenu8);

        rscJMenuBar.add(EditMenu);

        setJMenuBar(rscJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1289, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rscJTabbedPane))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 819, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rscJTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoadRawMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_LoadRawMenuItemActionPerformed
    {//GEN-HEADEREND:event_LoadRawMenuItemActionPerformed
        rawFlightfile = new java.io.File(".");
        tempFileChooser = new javax.swing.JFileChooser(rawFlightfile);
        FileNameExtensionFilter tempFileNameExtensionFilter = new FileNameExtensionFilter("Raw Flight Format (txt, or csv)", "csv", "txt");
        tempFileChooser.addChoosableFileFilter(tempFileNameExtensionFilter);
        tempFileChooser.setFileFilter(tempFileNameExtensionFilter);
        int returnVal = tempFileChooser.showOpenDialog(this);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION)
        {
            rawFlightfile = tempFileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "File selected:" + rawFlightfile);
            rscJTabbedPane.addTab(rawFlightfile.toString(), null, new FlightPanel(rawFlightfile, FlightPanel.LOAD_RAW));
        }
    }//GEN-LAST:event_LoadRawMenuItemActionPerformed

    private void LoadFlightMenutItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_LoadFlightMenutItemActionPerformed
    {//GEN-HEADEREND:event_LoadFlightMenutItemActionPerformed
        flightFile = new java.io.File(".");
        tempFileChooser = new javax.swing.JFileChooser(flightFile);
        tempFileChooser.setMultiSelectionEnabled(false);
        FileNameExtensionFilter tempFileNameExtensionFilter = new FileNameExtensionFilter("Flight File Format (rsc)", "rsc");
        tempFileChooser.addChoosableFileFilter(tempFileNameExtensionFilter);
        tempFileChooser.setFileFilter(tempFileNameExtensionFilter);
        int returnVal = tempFileChooser.showOpenDialog(this);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION)
        {
            flightFile = tempFileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "File selected: " + flightFile);
            rscJTabbedPane.addTab(flightFile.toString(), null, new FlightPanel(flightFile, FlightPanel.LOAD_FLIGHT));
        }
    }//GEN-LAST:event_LoadFlightMenutItemActionPerformed

    private void SaveFlightMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SaveFlightMenuItemActionPerformed
    {//GEN-HEADEREND:event_SaveFlightMenuItemActionPerformed
        if (flightFile == null)
        {
            JOptionPane.showMessageDialog(null, "There has not been a flight file loaded yet!");
        }
        else
        {
            //FlightPanel tempFP = (FlightPanel) rscJTabbedPane.getSelectedComponent();
            //TODO: ADD THIS ONCE SAVEFLIGHT IS COMPLETED tempFP.saveFlight();
            //tempFP.saveFlight();
//            tempFileChooser = new javax.swing.JFileChooser(flightFile);
//            int returnVal = tempFileChooser.showOpenDialog(this);
//            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION)
//            {
//                flightFile = tempFileChooser.getSelectedFile();
//                JOptionPane.showMessageDialog(null, "File selected:" + flightFile);
//            }
        }
    }//GEN-LAST:event_SaveFlightMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        try
        {
            // imported from https://github.com/bulenkov/Darcula, to compile this you will need to include it as a library. (note to run the JAR it will need to be near by as well)
            javax.swing.plaf.basic.BasicLookAndFeel darcula = new com.bulenkov.darcula.DarculaLaf();
            javax.swing.UIManager.setLookAndFeel(darcula);

            /*// old leftover incase we dont want to use this imported theme.
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                //Set the Nimbus look and feel
                //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
                // If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
                // For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
            }*/
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(rsc_jframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (Exception e)
        {
            java.util.logging.Logger.getLogger(rsc_jframe.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new rsc_jframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu EditMenu;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuItem LoadFlightMenutItem;
    private javax.swing.JMenuItem LoadRawMenuItem;
    private javax.swing.JMenuItem SaveFlightMenuItem;
    private javax.swing.JLabel WelcomeLabel;
    private javax.swing.JPanel WelcomePanel;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar rscJMenuBar;
    private javax.swing.JTabbedPane rscJTabbedPane;
    // End of variables declaration//GEN-END:variables
}
