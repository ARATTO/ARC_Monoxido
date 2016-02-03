
//Las librerias que se utilizan en el presente programa seran:
//Por parte de Arduino --->> Arduino v2.5.0.jar y  RXTXcomm.jar
//Por parte de la Gráfica --->> jfreechart-1.0.14.jar y jcommon-1.0.17.jar

package graphics;

import Arduino.Arduino;
import com.sun.xml.internal.ws.resources.SoapMessages;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Window extends javax.swing.JFrame {
    
    int tiempo=0;
    
    //la variable Serie que almacena los datos que se van recibiendo de Arduino del sensor que yo llame "Monoxido de Carbono"
    final XYSeries Serie = new XYSeries("Monoxido Carbono");
    //la variable Coleccion es en la que se agregan una coleccion de series que son las que ocupan en el grafico
    final XYSeriesCollection Coleccion = new XYSeriesCollection();
    //la variable Grafica va contener el Grafico que se creara en FreeChart
    JFreeChart Grafica;
    //La variable Arduino sirve para invocar los metodos de la libreria
    //Arduino que seran los que ocuparan para el manejo de arduino en netbeans
    Arduino Arduino = new Arduino();
    
    
    //la variable evento es la que esta pendiente de los sucesos que ocurran en el puerto serie
    //especificamente en la recepcion de datos
    SerialPortEventListener evento = new SerialPortEventListener() {

        
        @Override
        //Metodo que se ejecuta cada vez que se reciben datos de Arduino atravez del puerto serial
        public void serialEvent(SerialPortEvent spe) {
            //si hay un mensaje disponible osea que se ha producido un salto de linea y que java puede
            //leer un dato que le ha enviado Arduino
            if(Arduino.MessageAvailable()==true)
            {
                double vProc, voltajeArduino;
                tiempo++;
                voltajeArduino = Double.parseDouble(Arduino.PrintMessage());
                
                vProc = (voltajeArduino * 5000)/1023;
                vProc = vProc/2.5;
                vProc = Math.rint(vProc*100)/100;
                //Se agregan los datos a la serie los cuales han sido parseados de String
                Serie.add(tiempo, vProc);
                //Datos[i] = Integer.parseInt(Arduino.PrintMessage()) ;
                /*int mensaje=Integer.parseInt(Arduino.PrintMessage());
                if(Integer.parseInt(Arduino.PrintMessage())<=100)
                {
                    System.out.print("AMBIENTE LIBRE DE MONOXIDO DE CARBONO");
                }*/
                
                txtAreaSerial.append("Lectura:[tiempo(seg),monoxido(ppm)]" + Serie.getDataItem(tiempo)+"\n");
                //System.out.println("Lectura : [tiempo, monoxido]" + Serie.getDataItem(i)+"\n");
                //System.out.println(Datos[i]);
                //txtArea.append("Lectura : [tiempo, monoxido]" + Serie.getDataItem(i)+ "\n");
            }
            
        }

        private String toString(int parseInt) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    /**
     * Creates new form Window
     */
    public Window() {
        initComponents();
        try {
            //Inicio de la Conexion en Arduino en el pueto COM3 para la cual se utiliza la variable evento
            Arduino.ArduinoRX("COM3", 1000, 9600, evento);
        } catch (Exception ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Se define mediante el Serial que la Grafica parta en 0,0
        Serie.add(0, 0);
        //se agregan los datos de la serie a la coleccion de datos
        Coleccion.addSeries(Serie);
        //se inicia la variable Grafica como una Grafica del especificado
        //con titulo Monoxido de carbono vs Tiempo, en el eje x tiempo, en el eje y monoxido de carbono, la coleccion son los datos a graficar y por ultimo se define la orientacion del grafico
        Grafica = ChartFactory.createXYLineChart("Monoxido Carbono vs Tiempo", "Tiempo", "Monoxido Carbono", Coleccion, PlotOrientation.VERTICAL, true, true, false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_grafico = new javax.swing.JButton();
        btn_promedio = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaSerial = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaResultado = new javax.swing.JTextArea();
        btn_moda = new javax.swing.JButton();
        btn_max = new javax.swing.JButton();
        btn_min = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COMineria");

        btn_grafico.setText("GRAFICAR");
        btn_grafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_graficoActionPerformed(evt);
            }
        });

        btn_promedio.setText("PROMEDIO");
        btn_promedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_promedioActionPerformed(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);

        txtAreaSerial.setColumns(20);
        txtAreaSerial.setRows(5);
        jScrollPane1.setViewportView(txtAreaSerial);

        txtAreaResultado.setColumns(20);
        txtAreaResultado.setRows(5);
        jScrollPane2.setViewportView(txtAreaResultado);

        btn_moda.setText("MODA");
        btn_moda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modaActionPerformed(evt);
            }
        });

        btn_max.setText("Valor Maximo");
        btn_max.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_maxActionPerformed(evt);
            }
        });

        btn_min.setText("Valor Minimo");
        btn_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("LECTURAS");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel2.setText("DATOS Y RESULTADOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_promedio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_moda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_max, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(btn_min, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_grafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(51, 51, 51))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btn_promedio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_moda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_max, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_min, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_grafico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_graficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_graficoActionPerformed
        // TODO add your handling code here:
        //se agrega un ChartPanel para contener el grafico
        ChartPanel Panel = new ChartPanel(Grafica);
        //Se crea una ventana que contendra el grafico con los datos de Arduino y se le coloca el titulo
        JFrame ventana = new JFrame("Gráfico en Tiempo Real");
        ventana.getContentPane().add(Panel);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_btn_graficoActionPerformed

    private void btn_promedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_promedioActionPerformed
        
        double Promedio, TotalDatos=0;
        double[] Datos = obtenerLecturas();
        
        for (int k = 0; k < Datos.length; k++) {
            TotalDatos = TotalDatos + Datos[k];
        }
        
        Promedio = TotalDatos/Datos.length;
        Promedio = Math.rint(Promedio*100)/100; //Redondear a dos decimales
        
        txtAreaResultado.append("Promedio CO es aprox. "+Promedio+"ppm\n");    
    }//GEN-LAST:event_btn_promedioActionPerformed

    private void btn_modaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modaActionPerformed
        double Moda=0;
        double[] Datos = obtenerLecturas();
        int maxRepeticion = 0; 

        for(int k=0; k<Datos.length; k++){ 
            int repeticiones = 0; 
            for(int j=0; j<Datos.length; j++){ 
                if(Datos[k] == Datos[j]) 
                    repeticiones++; 
            } 
            if(repeticiones > maxRepeticion){ 
                    Moda = Datos[k]; 
                    maxRepeticion = repeticiones; 
            } 
        }
        
        txtAreaResultado.append("Moda: "+Moda+"ppm, con un total de "+maxRepeticion+" rep\n");
        
        
    }//GEN-LAST:event_btn_modaActionPerformed

    private void btn_maxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_maxActionPerformed
        double Max=0;
        double[] Datos = obtenerLecturas();
        
        for (int k = 0; k < Datos.length; k++) {
            if(Datos[k]>Max){
                Max = Datos[k];
            }
        }
        
        txtAreaResultado.append("Maximo valor: "+Max+"ppm\n");
    }//GEN-LAST:event_btn_maxActionPerformed

    private void btn_minActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minActionPerformed
        double Min=2500;
        double[] Datos = obtenerLecturas();
        
        for (int k = 0; k < Datos.length; k++) {
            if(Datos[k]<Min & Datos[k]>0){
                Min = Datos[k];
            }
        }
        
        txtAreaResultado.append("Minimo valor: "+Min+"ppm\n");
    }//GEN-LAST:event_btn_minActionPerformed

    
    public double[] obtenerLecturas(){
        double[] Datos = new double[txtAreaSerial.getLineCount()];
        String resultado; //String Total
        String valor; //Numero en String
        String [] lineas, lectura;
        
        resultado = txtAreaSerial.getText();
        lineas = resultado.split("\n");

        for(int j=0;j<lineas.length;j++){
            lectura = lineas[j].split(" ");
            valor = lectura[1].substring(0, lectura[1].length()-1);
            Datos[j] = Double.parseDouble(valor);
        }
        
        return Datos;
    }
    
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
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_grafico;
    private javax.swing.JButton btn_max;
    private javax.swing.JButton btn_min;
    private javax.swing.JButton btn_moda;
    private javax.swing.JButton btn_promedio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTextArea txtAreaResultado;
    public static javax.swing.JTextArea txtAreaSerial;
    // End of variables declaration//GEN-END:variables
}
