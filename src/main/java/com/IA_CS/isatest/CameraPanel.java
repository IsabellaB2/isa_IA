/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.IA_CS.isatest;
 
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import com.github.sarxos.webcam.Webcam;

public class CameraPanel extends JPanel {

    public static Webcam webcam;
    private JLabel webcamView;
    private JComboBox<Webcam> webcamSelector;
    private boolean isWebcamActive = false;

    private int width;
    private int height;

    public CameraPanel () {
    }

    public CameraPanel ( JComboBox<Webcam> webcamSelector , JLabel webcamView , int width , int height ) {
        this.webcamView = webcamView;
        this.width = width;
        this.height = height;
        this.webcamSelector = webcamSelector;
        setLayout ( new BorderLayout () );

        // Initialize the webcam to the default one
        webcam = Webcam.getDefault ();

        // Setup webcam view
        webcamView = new JLabel ();
        add ( webcamView , BorderLayout.CENTER );
    }

    public void stopCamera () {
        webcam.close ();
    }

    public boolean getCameraState () {
        return isWebcamActive;
    }

    public void setCameraState ( boolean state ) {
        isWebcamActive = state;
    }

    public void toggleWebcam () {
        if ( isWebcamActive ) {
            if ( webcam.isOpen () ) {
                webcam.close ();
            }
        }
        else {
            if ( !webcam.isOpen () ) {
                webcam = ( Webcam ) webcamSelector.getSelectedItem ();
                webcam.open ();
            }

            new Thread ( () -> {
                while ( webcam.isOpen () ) {
                    BufferedImage image = webcam.getImage ();

                    Image scaledImage = scaleAndCenterImage ( image , width , height );
                    webcamView.setIcon ( new ImageIcon ( scaledImage ) );

                    try {
                        Thread.sleep ( 2 );  // Adjust for better performance vs refresh rate
                    }
                    catch ( InterruptedException e ) {
                        e.printStackTrace ();
                    }
                }
            } ).start ();
        }
        isWebcamActive = !isWebcamActive;
    }

    private Image scaleAndCenterImage ( BufferedImage original , int targetWidth , int targetHeight ) {
        int imageWidth = original.getWidth ();
        int imageHeight = original.getHeight ();

        // Calculate scales
        double scaleX = ( double ) targetWidth / imageWidth;
        double scaleY = ( double ) targetHeight / imageHeight;

        // Preserve aspect ratio by taking the minimum scaling factor
        double scale = Math.min ( scaleX , scaleY );

        int scaledWidth = ( int ) ( scale * imageWidth );
        int scaledHeight = ( int ) ( scale * imageHeight );

        // Create new image
        BufferedImage scaledImage = new BufferedImage ( targetWidth , targetHeight , BufferedImage.TYPE_INT_RGB );
        Graphics2D g2d = scaledImage.createGraphics ();

        // Set rendering hints for quality
        g2d.setRenderingHint ( RenderingHints.KEY_INTERPOLATION , RenderingHints.VALUE_INTERPOLATION_BICUBIC );
        g2d.setRenderingHint ( RenderingHints.KEY_RENDERING , RenderingHints.VALUE_RENDER_QUALITY );
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );

        // Center image
        int x = ( targetWidth - scaledWidth ) / 2;
        int y = ( targetHeight - scaledHeight ) / 2;

        g2d.drawImage ( original , x , y , scaledWidth , scaledHeight , null );
        g2d.dispose ();

        return scaledImage;
    }

}
