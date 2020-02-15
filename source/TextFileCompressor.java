import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;

public class TextFileCompressor extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args)
    {
        new TextFileCompressor();
    }

    public TextFileCompressor()
    {
        super("Text File Compressor");


        JFileChooser fc = new JFileChooser();


        setSize(100,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}