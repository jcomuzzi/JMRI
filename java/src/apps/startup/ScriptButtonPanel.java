package apps.startup;

import java.awt.Component;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Randall Wood
 */
public class ScriptButtonPanel extends javax.swing.JPanel {

    private final Component parent;
    private final JFileChooser chooser;
    private final static Logger log = LoggerFactory.getLogger(ScriptButtonPanel.class);

    /**
     * Creates new form ScriptButtonPanel
     *
     * @param chooser {@link JFileChooser} to use for Browse action
     * @param parent  parent {@link Component} within which this is contained
     */
    public ScriptButtonPanel(JFileChooser chooser, Component parent) {
        initComponents();
        this.parent = parent;
        this.chooser = chooser;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonName = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        scriptLabel = new javax.swing.JLabel();
        script = new javax.swing.JTextField();
        scriptButton = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("apps/startup/Bundle"); // NOI18N
        buttonName.setText(bundle.getString("ScriptButtonPanel.buttonName.text")); // NOI18N

        nameLabel.setText(bundle.getString("ScriptButtonPanel.nameLabel.text")); // NOI18N

        scriptLabel.setText(bundle.getString("ScriptButtonPanel.scriptLabel.text")); // NOI18N

        script.setText(bundle.getString("ScriptButtonPanel.script.text")); // NOI18N

        scriptButton.setText(bundle.getString("ScriptButtonPanel.scriptButton.text")); // NOI18N
        scriptButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scriptButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel)
                    .addComponent(scriptLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(script, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(buttonName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scriptButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(script, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scriptLabel)
                    .addComponent(scriptButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scriptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scriptButtonActionPerformed
        if (this.chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            try {
                this.script.setText(this.chooser.getSelectedFile().getCanonicalPath());
            } catch (IOException ex) {
                log.error("File {} does not exist.", this.chooser.getSelectedFile());
            }
        }
    }//GEN-LAST:event_scriptButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buttonName;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField script;
    private javax.swing.JButton scriptButton;
    private javax.swing.JLabel scriptLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the name
     */
    protected String getButtonName() {
        return buttonName.getText();
    }

    /**
     * @param name the name to set
     */
    protected void setButtonName(String name) {
        this.buttonName.setText(name);
    }

    /**
     * @return the script
     */
    protected String getScript() {
        return script.getText();
    }

    /**
     * @param script the script to set
     */
    protected void setScript(String script) {
        this.script.setText(script);
    }
}
