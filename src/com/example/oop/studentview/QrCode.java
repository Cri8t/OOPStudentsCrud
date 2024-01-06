package com.example.oop.studentview;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;



public class QrCode extends javax.swing.JFrame {

     private WebcamPanel panel = null;
    private Webcam webcam = null;

   
    public QrCode() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize the webcam
        initWebcam();

        // Create a panel for the webcam feed
        panel = new WebcamPanel(webcam);
        add(panel, BorderLayout.CENTER);

        pack();

        // Create a JLabel for displaying webcam feed
        jLabel1 = new JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 33, 193, 149));
        add(jPanel1, BorderLayout.NORTH);


        pack();
    }

    private void initWebcam() {
        // Get the default webcam
        webcam = Webcam.getDefault();

        if (webcam != null) {
            // Set up the webcam view size
            Dimension customSize = new Dimension(176, 144);
            webcam.setViewSize(customSize);

            // Start the webcam
            webcam.open(true);

            // Create a thread to continuously scan for QR codes
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        // Capture an image from the webcam
                        BufferedImage image = webcam.getImage();

                        // Decode the QR code
                        Result result = decodeQRCode(image);

                        if (result != null) {
                            // Display the QR code result
                            // Handle QR code result as needed
                        }

                        // Sleep for a short duration
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            // Start the thread
            thread.start();
        } else {
            // Display an error message if the webcam is not available
            JOptionPane.showMessageDialog(this, "Webcam not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

  private Result decodeQRCode(BufferedImage image) {
        try {
            // Convert the image to a binary bitmap
            LuminanceSource source;
            source = new QrCode.BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // Set up the QR code reader
            Reader reader = new MultiFormatReader();

            // Try to decode the QR code
            try {
                return reader.decode(bitmap);
            } catch (NotFoundException e) {
                // No QR code found in the image
                System.out.println("No QR code found in the image");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

  

}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(215, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(() -> {
            new QrCode().setVisible(true);
        });
      try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QrCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new QrCode().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

 public class BufferedImageLuminanceSource extends LuminanceSource {

    private final BufferedImage image;

    public BufferedImageLuminanceSource(BufferedImage image) {
        super(image.getWidth(), image.getHeight());
        this.image = image;
    }

    @Override
    public byte[] getRow(int y, byte[] row) {
        if (y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image boundaries");
        }

        int width = getWidth();
        if (row == null || row.length < width) {
            row = new byte[width];
        }

        for (int x = 0; x < width; x++) {
            row[x] = (byte) getLuminance(x, y);
        }

        return row;
    }

    @Override
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        byte[] matrix = new byte[width * height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                matrix[y * width + x] = (byte) getLuminance(x, y);
            }
        }

        return matrix;
    }

    public byte[] getMatrix(int left, int top, int width, int height, byte[] row) {
        if (left < 0 || top < 0 || width < 0 || height < 0
                || left + width > getWidth() || top + height > getHeight()) {
            throw new IllegalArgumentException("Requested region is outside the image boundaries");
        }

        if (row == null || row.length < width) {
            row = new byte[width];
        }

        for (int y = top; y < top + height; y++) {
            for (int x = left; x < left + width; x++) {
                row[x - left] = (byte) getLuminance(x, y);
            }
        }

        return row;
    }

    private int getLuminance(int x, int y) {
        int pixel = image.getRGB(x, y);
        return (int) ((0.2126 * ((pixel >> 16) & 0xFF)) + (0.7152 * ((pixel >> 8) & 0xFF)) + (0.0722 * (pixel & 0xFF)));
    }
 }
}
  



