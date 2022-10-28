
import javax.swing.JOptionPane;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy
 */
public class InterfazHill extends javax.swing.JFrame {

    /**
     * Creates new form InterfazHill
     */
    public InterfazHill() {
        initComponents();
        llave.setBackground(new java.awt.Color(0,0,0,1));
           //Clave sera NxN 
       // msj = new HillMessage("libni que bonita estas",4,"agua");
        //encriptrar(msj);
        //Nuestro alfabeto es el ASCII
        //DIVIDIR textoClaro en bloques de longitud N=2
        String mensaje = "Hola mis amigos como estan?";
        //String mensaje = "hola amigos";
        System.out.print(mensaje.length());
        //String llave = "wed932as5";
        //String llave = "a 6W";        String llave = "nprt";
        //sllave = "☻♦◘►";
        String llave = "bfjn";
        //int divMsj = (int)Math.sqrt(llave.length());

    }

    
    public static String encriptrar(HillMessage msj){
       // System.out.println("\nLLave K:");
        int keyNxN[][] = getKey(msj.llave);
       // System.out.println("\nMatriz M:");
        int matrizM[][] = getMessage(msj.textoClaro,msj.longitudN);
        int n = msj.longitudN;
        int n3 = (int) Math.sqrt(msj.llave.length());
        int n2 = (int)Math.ceil((float)msj.textoClaro.length()/n);
        int matrizC[][] = new int [n][(int)Math.ceil((float)msj.textoClaro.length()/n)];
        String mensaje = "";
        int contadorRelleno = 0 ;
        int lenMensaje = msj.textoClaro.length();

        int matrizCifrada[] = new int [n*n2];
        int suma = 0;
        int contadork = 0;
        for(int i = 0; i < n2; i++){ 
            for(contadork = 0; contadork < n; contadork++){
                suma = 0;
                for(int j=0; j < n; j++){                    
                    //System.out.print(matrizM[j][i] + " ->");
                    //System.out.println(keyNxN[contadork][j]+ "*"+matrizM[j][i] + "="+ keyNxN[contadork][j] * matrizM[j][i]);
                    suma += keyNxN[contadork][j] * matrizM[j][i] ;
                } 
                //System.out.println("Suma:" + suma + " Modulo-255= " + (suma % 255) + " Simbolo:" +(char)(suma % 255));
                mensaje =  mensaje + (char)(suma % 255);
                //System.out.println(mensaje);
            }
            
            suma = 0;
        }
        return mensaje;
    }

    public static String desencriptrar(String llave, String mensaje){
        //Matriz = la llave inversa por C  modulo |A|
        //System.out.println("");
        int [][] matrizMensaje = getMessage(mensaje,2);
        //System.out.println("");
        int [][] key = getKey(llave);
        int [][] keyInversa = inversa(key);
        String texto = "";
        for (int i = 0; i < mensaje.length()/2; i++) {
            texto = texto + ((char)((keyInversa[0][0] * matrizMensaje[0][i] + keyInversa[0][1] * matrizMensaje[1][i])%255));
            texto = texto + ((char)((keyInversa[1][0] * matrizMensaje[0][i] + keyInversa[1][1] * matrizMensaje[1][i])%255));
        }
        return texto;
    }

    public static int[][] inversa(int[][] matriz){
        int [][] resultado = new int[2][2];
        resultado[0][0] = matriz[1][1];
        resultado[0][1] = matriz[0][1] * -1;
        resultado[1][0] = matriz[1][0] * -1;
        resultado[1][1] = matriz[0][0];
        int determinante = (matriz[0][0]*matriz[1][1]) - (matriz[0][1]*matriz[1][0]);
        int escalar = 256/determinante;
        //System.out.println("Determinante:"+determinante);

        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado.length; j++) {
                resultado[i][j] = resultado[i][j]*escalar;
            }
        }
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado.length; j++) {
              //  System.out.print(resultado[i][j] + " ");
            }
           // System.out.println("");
        }


        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado.length; j++) {
                int mod = resultado[i][j];
                if(mod >= 0){
                    resultado[i][j] = resultado[i][j]%255;
                }else{
                    int div = resultado[i][j]/255;
                    int mul = (div*-1) * 255;
                    resultado[i][j] =( resultado[i][j] + mul ) + 255;
                }
            }
        }

        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado.length; j++) {
                //System.out.print(resultado[i][j] + " ");
            }
           // System.out.println("");
        }

        return resultado;
    }

    public static int [][] getMessage(String mensaje,int n) {
        //CREACION DE LA MATRIZ M 
        int n2 = (int)Math.ceil((float)mensaje.length()/n);
        //System.out.println("n2 = "+n2);
        int lenMensaje = mensaje.length();
        int contadorRelleno = 0 ;
        int matrizMensaje[][] = new int [n][n2];
        //RELLENAR LA MATRIZ CON ESPACIOS
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n2; j++) 
                matrizMensaje[i][j] = (int)' ';
        //RELLENAR LA MATRIZ CON EL MENSAJE
        for (int j = 0; j < n2; j++) {
            for (int i = 0; i < n; i++) {
                if(contadorRelleno < lenMensaje){
                    matrizMensaje[i][j] = mensaje.charAt(contadorRelleno);
                    contadorRelleno++;  
                }
            }
        }
        //Visualizar la matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n2; j++) {
                //System.out.print(matrizMensaje[i][j]+" ");
            }
            //System.out.println("");
        }
        //System.out.println("");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n2; j++) {
                //System.out.print((char)matrizMensaje[i][j]+" ");
            }
            //System.out.println("");
        }
        return matrizMensaje;
    }
    //La llave tiene que tener NxN si no no acepta la clave
    // 2x2 = 4, 1x1 = 1, 3x3 = 9, 4x4 = 16, 5x5 = 25
    public static int[][] getKey(String llave){
        int n =(int) Math.sqrt(llave.length());
        int llaveMatriz[][] = new int[n][n];
        int contador = 0;
        //Convertir Matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                llaveMatriz[i][j] = llave.charAt(contador);              
                contador++;
            }

        }
        //Visualizar matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.print(llaveMatriz[i][j] +" ");
            }
            //System.out.println("");
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               // System.out.print((char)llaveMatriz[i][j] +" ");
            }
            //System.out.println("");
        }
        return llaveMatriz;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textoLlano1 = new javax.swing.JScrollPane();
        descifrado = new javax.swing.JTextArea();
        textoLlano2 = new javax.swing.JScrollPane();
        cifrado = new javax.swing.JTextArea();
        textoLlano = new javax.swing.JScrollPane();
        llano = new javax.swing.JTextArea();
        btnCifrar = new javax.swing.JButton();
        btnDescifrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        llave = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cifrado de Hill");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textoLlano1.setBorder(null);

        descifrado.setBackground(new java.awt.Color(75, 88, 107));
        descifrado.setColumns(20);
        descifrado.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        descifrado.setForeground(new java.awt.Color(255, 255, 255));
        descifrado.setLineWrap(true);
        descifrado.setRows(5);
        textoLlano1.setViewportView(descifrado);

        getContentPane().add(textoLlano1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 230, 320, 360));

        textoLlano2.setBorder(null);
        textoLlano2.setOpaque(false);

        cifrado.setBackground(new java.awt.Color(75, 88, 107));
        cifrado.setColumns(20);
        cifrado.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        cifrado.setForeground(new java.awt.Color(255, 255, 255));
        cifrado.setLineWrap(true);
        cifrado.setRows(5);
        cifrado.setBorder(null);
        textoLlano2.setViewportView(cifrado);

        getContentPane().add(textoLlano2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 320, 360));

        textoLlano.setBackground(new java.awt.Color(75, 88, 107));
        textoLlano.setBorder(null);

        llano.setBackground(new java.awt.Color(75, 88, 107));
        llano.setColumns(20);
        llano.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        llano.setForeground(new java.awt.Color(255, 255, 255));
        llano.setLineWrap(true);
        llano.setRows(5);
        textoLlano.setViewportView(llano);

        getContentPane().add(textoLlano, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 320, 360));

        btnCifrar.setBorder(null);
        btnCifrar.setContentAreaFilled(false);
        btnCifrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCifrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCifrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCifrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 86, 140, 60));

        btnDescifrar.setBorder(null);
        btnDescifrar.setContentAreaFilled(false);
        btnDescifrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescifrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescifrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnDescifrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 86, 140, 60));

        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1088, 86, 140, 60));

        llave.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        llave.setForeground(new java.awt.Color(255, 255, 255));
        llave.setBorder(null);
        llave.setOpaque(false);
        getContentPane().add(llave, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 320, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mesa de trabajo – 1.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDescifrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescifrarActionPerformed
        descifrado.setText(desencriptrar(llave.getText(),cifrado.getText()));
    }//GEN-LAST:event_btnDescifrarActionPerformed

    private void btnCifrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCifrarActionPerformed
        int divMsj = 2;
        HillMessage msj = new HillMessage(
            llano.getText(),
            divMsj,
            llave.getText()
        );
        String texto = "";
        
        if(msj.llave.length() % (Math.sqrt(msj.llave.length())) == 0){
            texto = encriptrar(msj);
            cifrado.setText(texto);
            //System.out.println("\nMensaje Cifrado:\n"+texto+"\n");    
        //System.out.println(texto.length());
            //System.out.println("Texto Descifrado: "+desencriptrar(llave, texto));
        }
        else 
            JOptionPane.showMessageDialog(null, "Llave no Aceptada");
       
    }//GEN-LAST:event_btnCifrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        llano.setText("");
        cifrado.setText("");
        descifrado.setText("");
        llave.setText("");
        
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
                    javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazHill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazHill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazHill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazHill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazHill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCifrar;
    public javax.swing.JButton btnDescifrar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JTextArea cifrado;
    public javax.swing.JTextArea descifrado;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JTextArea llano;
    public javax.swing.JTextField llave;
    public javax.swing.JScrollPane textoLlano;
    private javax.swing.JScrollPane textoLlano1;
    public javax.swing.JScrollPane textoLlano2;
    // End of variables declaration//GEN-END:variables
}

class HillMessage{
    String textoClaro;
    int longitudN;
    String llave;
    public HillMessage(String m1,int m2,String m3){
        textoClaro = m1;
        longitudN = m2;
        llave = m3;
    }
}