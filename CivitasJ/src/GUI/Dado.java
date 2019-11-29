
package GUI;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author fvelasco
 */
public class Dado extends JDialog {
  
  private static Dado instance = null;
  private Random random; 
  private Timer timerDado;

  static private int SalidaCarcel = 5;

  private int ultimoResultado;
  private Boolean debug;

    
  private Dado (JFrame parent) {
    super(parent, true);
    
    initComponents();
    timerDado = new Timer (50,diceAction);
    random=new Random();
    ultimoResultado = -1;
    debug = false;
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        System.exit(0);
      }
    });
 
  }

  public static void createInstance (JFrame parent) {
    if (instance == null)
      instance = new Dado (parent);
  }
  
  public static Dado getInstance() {
      return instance;
  }
  
  public void setDebug (Boolean d) {
    debug = d;
  }
  
   public int getUltimoResultado () {
    return ultimoResultado;
  }
   
   public Boolean salgoDeLaCarcel () {
    return (tirar() >= SalidaCarcel);
  }
  
   public int quienEmpieza (int n) {
    return random.nextInt(n); 
  }
  
  private int privateTirar() {
    if (debug)
      ultimoResultado = 1;
    else 
      ultimoResultado = random.nextInt(6)+1;
    
    return ultimoResultado;
  }
  
  private ActionListener diceAction = new ActionListener() {
    @Override
    public void actionPerformed (ActionEvent ev) {
         if (debug)
             jL_texto.setText("El dado está en modo debug y siempre sale 1" );
         else 
             jL_texto.setText("El dado se ha puesto en modo normal. Sale entre 1 y 6 aleatoriamente" );
        ultimoResultado = privateTirar();
        jL_dice.setText(Integer.toString(ultimoResultado));
        pack();
      }
    };
  
 
  public int tirar () {
    
    jB_OK.setVisible(false);
    pack();
    timerDado.start();
    this.setVisible(true);
    return ultimoResultado;
    
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jL_dice = new javax.swing.JLabel();
    jL_texto = new javax.swing.JLabel();
    jB_OK = new javax.swing.JButton();
    mensaje = new javax.swing.JLabel();
    jL_texto = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Dado");
    setMinimumSize(new java.awt.Dimension(400, 280));
    setPreferredSize(new java.awt.Dimension(400, 280));

    jL_dice.setBackground(new java.awt.Color(255, 255, 255));
    jL_dice.setFont(new java.awt.Font("Trebuchet MS", 2, 48)); // NOI18N
    jL_dice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jL_dice.setText("1");
    jL_dice.setOpaque(true);
    jL_dice.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jL_diceMouseClicked(evt);
      }
    });

    jL_texto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jL_texto.setText("jLabel1");

    jB_OK.setText("OK");
    jB_OK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jB_OKActionPerformed(evt);
      }
    });

    mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    mensaje.setText("pincha sobre el dado para detenerlo");


    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(20, 20, 20)
        .addComponent(jL_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGroup(layout.createSequentialGroup()
        .addGap(70, 70, 70)
        .addComponent(mensaje))
      .addGroup(layout.createSequentialGroup()
        .addGap(160, 160, 160)
        .addComponent(jL_dice, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGroup(layout.createSequentialGroup()
        .addGap(170, 170, 170)
        .addComponent(jB_OK))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(10, 10, 10)
        .addComponent(jL_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, 0)
        .addComponent(mensaje)
        .addGap(13, 13, 13)
        .addComponent(jL_dice, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(10, 10, 10)
        .addComponent(jB_OK))
    );

    setSize(new java.awt.Dimension(400, 284));
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void jL_diceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_diceMouseClicked
    timerDado.stop();
    jB_OK.setVisible(true);
    pack();
  }//GEN-LAST:event_jL_diceMouseClicked

  private void jB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_OKActionPerformed
    this.dispose();
    this.setVisible(false);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }//GEN-LAST:event_jB_OKActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jB_OK;
  private javax.swing.JLabel jL_dice;
  private javax.swing.JLabel jL_texto;
  private javax.swing.JLabel mensaje;
  // End of variables declaration//GEN-END:variables
}
