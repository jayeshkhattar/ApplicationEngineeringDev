/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.CustomerRole;

import Business.Customer.Customer;
import Business.Customer.CustomerDirectory;
import Business.DB4OUtil.DB4OUtil;
import Business.DeliveryMan.DeliveryManDirectory;
import Business.EcoSystem;
import Business.Menu.Menu;
import Business.Menu.MenuDirectory;
import Business.Order.Order;
import Business.Order.OrderDirectory;
import Business.Restaurant.Restaurant;
import Business.Restaurant.RestaurantDirectory;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raunak
 */
public class CustomerAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem ecoSystem;
    private UserAccount account;
    private CustomerDirectory customerDirectory;
    private RestaurantDirectory restaurantDirectory;
    private DeliveryManDirectory deliveryManDirectory;
    private MenuDirectory menuDirectory;
    private OrderDirectory orderDirectory;
    private static int count = 1;
    private DB4OUtil dB4OUtil;
    
    /**
     * Creates new form DoctorWorkAreaJPanel
     */
    public CustomerAreaJPanel(JPanel userProcessContainer, UserAccount account, EcoSystem ecoSystem, 
            CustomerDirectory customerDirectory, RestaurantDirectory restaurantDirectory, 
            DeliveryManDirectory deliveryManDirectory, MenuDirectory menuDirectory, OrderDirectory orderDirectory, DB4OUtil dB4OUtil) {
        initComponents();
        
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.ecoSystem = ecoSystem;
        this.customerDirectory = ecoSystem.getCustomerDirectory();
        this.restaurantDirectory = ecoSystem.getRestaurantDirectory();
        this.menuDirectory = ecoSystem.getMenuDirectory();
        this.deliveryManDirectory = ecoSystem.getDeliveryManDirectory();
        this.orderDirectory = ecoSystem.getOrderDirectory();
        valueLabel.setText(account.getUsername());
        populateRequestTable();
        populateRestaurantCombo();
        this.dB4OUtil = dB4OUtil;
    }
    
    public void populateTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblItem.getModel();
        dtm.setRowCount(0);
        if(boxRestaurant.getSelectedItem() != null) {
        String restaurantName = boxRestaurant.getSelectedItem().toString();
            Restaurant restaurant = ecoSystem.getRestaurantDirectory().getRestaurant(restaurantName);
            for(Menu menu : ecoSystem.getMenuDirectory().getMenuDirectory()){
                if(restaurant.getRestaurantName().equals(menu.getRestaurantName())) {
                    Object [] row = new Object[2];
                    row[0] = menu;
                    row[1] = menu.getItemPrice();
                    dtm.addRow(row);
                }
            }            
        }
    }
    
    public void populateRestaurantCombo() {
        boxRestaurant.removeAllItems();
        boxRestaurant.addItem("Select Restaurant");
        for(Restaurant res : ecoSystem.getRestaurantDirectory().getRestaurantDirectory()) {
            //System.out.println("res" + res);
            boxRestaurant.addItem(res.getRestaurantName());
        }
    }
    
    public void populateRequestTable(){
        DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();
        model.setRowCount(0);
        for (Order order : ecoSystem.getOrderDirectory().getOrderDirectory()){
            //System.out.println("Order" + order.getOrderId());
            if(account.getEmployee().getName().equals(order.getCustomer().getName())) {
                Object[] row = new Object[8];
                row[0] = order;
                row[1] = order.getMenu().getItemName();
                row[2] = order.getQuantity() * order.getMenu().getItemPrice();
                row[3] = order.getRestaurant().getRestaurantName();
                row[4] = order.getMessage();
                row[5] = order.getCustomer().getName();
                row[6] = order.getOrderStatus();
                row[7] = order.getQuantity();
                model.addRow(row);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        requestTestJButton = new javax.swing.JButton();
        refreshTestJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();
        valueLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        boxRestaurant = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtComment = new javax.swing.JTextField();
        btnMenuShow = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 51));

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Order ID", "ItemName", "Price", "Restaurant", "Message", "Receiver", "Status", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(workRequestJTable);
        if (workRequestJTable.getColumnModel().getColumnCount() > 0) {
            workRequestJTable.getColumnModel().getColumn(4).setResizable(false);
        }

        requestTestJButton.setText("Add Comment");
        requestTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestTestJButtonActionPerformed(evt);
            }
        });

        refreshTestJButton.setText("Refresh");
        refreshTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTestJButtonActionPerformed(evt);
            }
        });

        enterpriseLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        enterpriseLabel.setText("Welcome, ");

        valueLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valueLabel.setText("<value>");

        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Item Name", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblItem);

        boxRestaurant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        boxRestaurant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxRestaurantActionPerformed(evt);
            }
        });

        jLabel1.setText("Quantity:");

        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        jLabel2.setText("Comment:");

        btnMenuShow.setText("Show Menu");
        btnMenuShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuShowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(boxRestaurant, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(btnMenuShow))))
            .addGroup(layout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirm)
                .addGap(276, 276, 276)
                .addComponent(refreshTestJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(requestTestJButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxRestaurant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnMenuShow)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConfirm)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(refreshTestJButton)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(requestTestJButton))
                .addContainerGap(312, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void requestTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestTestJButtonActionPerformed

        if(txtComment.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Field cannot be empty");
            return;
        }
        
        int selectedRow = workRequestJTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warining", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Order order = (Order)workRequestJTable.getValueAt(selectedRow, 0);
        order.setMessage(txtComment.getText());
        populateRequestTable();
    }//GEN-LAST:event_requestTestJButtonActionPerformed

    private void refreshTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshTestJButtonActionPerformed

        populateRequestTable();
        
    }//GEN-LAST:event_refreshTestJButtonActionPerformed

    private void boxRestaurantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxRestaurantActionPerformed
        btnMenuShowActionPerformed(evt);
    }//GEN-LAST:event_boxRestaurantActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
         int selectedRow = tblItem.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warining", JOptionPane.WARNING_MESSAGE);
            return;
        }
        System.out.println("--txtQuantity.getText()--"+txtQuantity.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        String restaurantName = boxRestaurant.getSelectedItem().toString();
        Restaurant restaurant = ecoSystem.getRestaurantDirectory().getRestaurant(restaurantName);
        Customer customer = ecoSystem.getCustomerDirectory().getCustomer(account.getEmployee().getName());
        Menu menu = (Menu) tblItem.getValueAt(selectedRow, 0);
        String status = "Order Placed";
        
        //Order order = new Order();
        Order order = ecoSystem.getOrderDirectory().newOrder();
        order.setCustomer(customer);
        int cnt = ecoSystem.getOrderDirectory().getOrderDirectory().size();
        cnt++;
        order.setOrderId(String.valueOf(cnt));
        order.setQuantity(quantity);
        order.setMenu(menu);
        order.setRestaurant(restaurant);
        order.setOrderStatus(status);
        order.setAssign(false);
        
//        ecoSystem.getOrderDirectory().newOrder(order);
        
        JOptionPane.showMessageDialog(null,"You have placed an order");
        dB4OUtil.storeSystem(ecoSystem);
        populateRequestTable();
                
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnMenuShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuShowActionPerformed
        // TODO add your handling code here:
        if(boxRestaurant.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a restaurant to continue");
            return;
        }
        populateTable();
    }//GEN-LAST:event_btnMenuShowActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxRestaurant;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnMenuShow;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refreshTestJButton;
    private javax.swing.JButton requestTestJButton;
    private javax.swing.JTable tblItem;
    private javax.swing.JTextField txtComment;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}
