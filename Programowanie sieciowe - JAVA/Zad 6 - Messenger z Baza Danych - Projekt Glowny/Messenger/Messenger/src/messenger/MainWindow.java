package messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class MainWindow extends javax.swing.JFrame {
    private MainWindow() {
        initComponents();
        setLocationRelativeTo(null);
        
        ArrayList<User> contacts = DatabaseHelper.GetContects();
        DefaultListModel listModel = new DefaultListModel();
        
        for(User user : contacts){
            listModel.addElement(user);
        }
        
        jList1.setModel(listModel);
    }
    
    
    private static MainWindow instance;
    
    public static MainWindow getInstance(){
        if(instance==null){
            instance = new MainWindow();
        }
        
        return instance;
    }
    
    
    public void refreshMessage(){
        jTextArea1.setText(getMessages(SessionContext.getSelectedContactId()));
    }
    
    
    
    private String getMessages(int contactId){
        String result = "";
        
        ArrayList<Message> messages = DatabaseHelper.GetMessage(contactId);
        for(Message message : messages){
            result += getMessageText(message);
        }
        
        return result;
    }
    
    private String getMessageText(Message message){
        return message.getFromDisplayName()+ ", " + message.getDate() + "\n----------------------\n" + message.getMessage() + "\n\n";
    }
    
    private String readRequest(Socket client) throws IOException{
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));

        return stdIn.readLine();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jButton1.setText("Wyślij");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Zmień hasło");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Wyloguj");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        User user = (User)jList1.getSelectedValue();
        SessionContext.setSelectedContactId(user.getId());
        
        refreshMessage();
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        User user = (User)jList1.getSelectedValue();
        Date date= new Date();
        Message message = new Message(SessionContext.getCurrentUserId(), user.getId(), new Timestamp(date.getTime()), jTextArea2.getText());
        message.setFromDisplayName("Ja");
        
        try{
            Client client = new Client();
            client.connect();
            
            boolean isMessageCreated = client.sendMessage(message);
            
            if(isMessageCreated){
                jTextArea1.append(getMessageText(message));
                jTextArea2.setText("");
            }
            else{
                JOptionPane.showMessageDialog(
                        this,
                        "Wysyłanie wiadomości nie powiodło się",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception exception){
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try{
            //Creating a SocketClient object
            Client client = new Client();
            client.connect();

            boolean isLoggedOut = client.sendLogout(SessionContext.getCurrentUserId());
            if(isLoggedOut){
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(
                        this,
                        "Wylogowanie nie powioadło się",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception exception){
            JOptionPane.showMessageDialog(
                    this,
                    "Nieudana próba połączenia ze serwerem",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ChangePassword window = new ChangePassword();
        window.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try{
            //Creating a SocketClient object
            Client client = new Client();
            client.connect();

            boolean isLoggedOut = client.sendLogout(SessionContext.getCurrentUserId());
            if(isLoggedOut){
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(
                        this,
                        "Wylogowanie nie powioadło się",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception exception){
            JOptionPane.showMessageDialog(
                    this,
                    "Nieudana próba połączenia ze serwerem",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
