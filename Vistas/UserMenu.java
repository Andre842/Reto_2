
package Vistas;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UserMenu extends javax.swing.JFrame {
    //1. Instancia de la clase conexion
    Conexion conexion = new Conexion();
    Connection connection;
    //2. La librería statement permite ejecutar los query SQL
    Statement st;
    ResultSet rs;
    //3. Creamos una instancia de la tabla de la interfaz
    DefaultTableModel contenidoTablaEmpleados; 
    

    public UserMenu() {
        initComponents();
        setLocationRelativeTo(null);
        listarEmpleados();
    }

   //4. Método que trae todos los empleados existenten a la base de datos
    private void listarEmpleados(){
        String filtroBusqueda = txtSearch.getText();
        if(filtroBusqueda.isEmpty()){
          String queryConsulta = "SELECT * FROM empleados";
        //5. Intentar ejecutar el query y obtener una respuesta de la base de datos
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryConsulta);
            //6. Crear un objeto donde se almacene el resultado de la consulta
            Object[] empleados = new Object[6];
            //7. Actualizar la propiedad Model de la tabla
            contenidoTablaEmpleados = (DefaultTableModel)tblEmpleados.getModel();
            //8. Recorremos el resultado de la consulta del query
            while(rs.next()){
                empleados[0] = rs.getInt("idEmp");
                empleados[1] = rs.getString("nombreEmp"); 
                empleados[2] = rs.getString("apellidos");
                empleados[3] = rs.getString("tipoDocumento");
                empleados[4] = rs.getString("documento");
                empleados[5] = rs.getString("correo");
                //9. Creamos una fila dentro de la tabla, por cada empleado que devuelve el query
                contenidoTablaEmpleados.addRow(empleados);
                System.out.println("id: " + empleados[0] + ", nombre: " + empleados[1] + ", apellidos: " + empleados[2] + ", documento: " + empleados[3] + " " + empleados[4] + ", correo: " + empleados[5]);
                tblEmpleados.setModel(contenidoTablaEmpleados);
            }
        }catch(SQLException e){
            System.out.println("Error");  
        }
        
        }else{
            String queryConsulta = "SELECT * FROM empleados WHERE nombreEmp LIKE '%"+filtroBusqueda+"%' OR apellidos LIKE '%"+filtroBusqueda+"%'";
            System.out.println(queryConsulta);
            try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryConsulta);
            //6. Crear un objeto donde se almacene el resultado de la consulta
            Object[] empleados = new Object[6];
            //7. Actualizar la propiedad Model de la tabla
            contenidoTablaEmpleados = (DefaultTableModel)tblEmpleados.getModel();
            //8. Recorremos el resultado de la consulta del query
            while(rs.next()){
                empleados[0] = rs.getInt("idEmp");
                empleados[1] = rs.getString("nombreEmp"); 
                empleados[2] = rs.getString("apellidos");
                empleados[3] = rs.getString("tipoDocumento");
                empleados[4] = rs.getString("documento");
                empleados[5] = rs.getString("correo");
                //9. Creamos una fila dentro de la tabla, por cada empleado que devuelve el query
                contenidoTablaEmpleados.addRow(empleados);
                System.out.println("id: " + empleados[0] + ", nombre: " + empleados[1] + ", apellidos: " + empleados[2] + ", documento: " + empleados[3] + " " + empleados[4] + ", correo: " + empleados[5]);
                tblEmpleados.setModel(contenidoTablaEmpleados);
            }
        }catch(SQLException e){
            System.out.println("Error");  
        }
        }
    }
    
    //Cuando añado un nuevo empleado el proceso background es el siguiente:
    //1. Se almacena el nuevo empleado en la base de datos
    //2. Se eliminan los datos que tiene la tabla tblEmpleados
    //3. Se llama el método listarEmpleados para consultar nuevamente la base de datos
    //4. Se cargan los empleados de la tabla incluyendo el recién creado
    
    private void borrarDatosTabla(){
        //el método getRowCount() devuelve la cantidad de registros existentes
        for (int i = 0; i <tblEmpleados.getRowCount(); i++) {
            contenidoTablaEmpleados.removeRow(i);
            i -= 1;
        }
    }
    
    private void eliminarEmpleado(){
        //1. Identificamos la fila seleccionada en la tabla de empleados
        int row = tblEmpleados.getSelectedRow();
        //2. Capturamos el id del empleado que corresponde a la fila seleccionada 
        int idEmp = Integer.parseInt(tblEmpleados.getValueAt(row, 0).toString());
        //3. Validar si el usuario no ha seleccionado un empleado
        if(row<0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un empleado.", "", JOptionPane.WARNING_MESSAGE );
        }else{
            String query = "DELETE FROM empleados WHERE idEmp = " + idEmp + ";";
            System.out.println(query);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnAddUser = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(255, 204, 255));

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 734, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Home", jPanel1);

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellidos", "Tipo Documento", "Documento", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleados);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/MinTic.png"))); // NOI18N

        jLabel2.setText("INFORMACION EMPLEADOS");

        jLabel3.setText("Nombre");

        btnAddUser.setBackground(new java.awt.Color(204, 255, 255));
        btnAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/avatar.png"))); // NOI18N
        btnAddUser.setText("Crear Empleado");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/showUser.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddUser)
                .addGap(62, 62, 62))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Empleados", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // Creamos una instancia de diálogo
        AddUserForm addUserF = new AddUserForm(this, rootPaneCheckingEnabled);
        addUserF.setVisible(rootPaneCheckingEnabled);
        borrarDatosTabla();
        listarEmpleados();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
        int row = tblEmpleados.getSelectedRow();
        System.out.println("Fila seleccionada n.: " + row);
        //Validar si el usuario no ha seleccionado un empleado
        if(row <0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un empleado.", "", JOptionPane.WARNING_MESSAGE );
        }else{
            //Partiendo del hecho de que cada fila es un registro de empleado 
            int idEmp = Integer.parseInt(tblEmpleados.getValueAt(row, 0).toString());
            String nombreEmp = tblEmpleados.getValueAt(row, 1).toString();
            String apellidos = tblEmpleados.getValueAt(row, 2).toString();
            String tipoDocumento = tblEmpleados.getValueAt(row, 3).toString();
            String documento = tblEmpleados.getValueAt(row, 4).toString();
            String correo = tblEmpleados.getValueAt(row, 5).toString();
            
            System.out.println("idEmp: " + idEmp + ", nombre: " + nombreEmp + " " + apellidos + ", documento: " + tipoDocumento + " " + documento + ", correo: " + correo);
            ShowUserForm showUserForm = new ShowUserForm(this, true);
            //Mediante la instancia del JDialog enviamos los datos del empleado de esta vista actual a la vista que muestra la info en detalle
            showUserForm.recibeDatos(idEmp, nombreEmp, apellidos, tipoDocumento, documento, correo);
            showUserForm.setVisible(true);
            //Para que la tabla actualice la información del empleado se debe de borrar toda la información de la tabla y volverlos a cargar.
            borrarDatosTabla();
            listarEmpleados();
            
        }
    }//GEN-LAST:event_tblEmpleadosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        borrarDatosTabla();
        listarEmpleados();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
