import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.RescaleOp;


//منابع استفاده شده برای این پروژه
//faradars
//geeksforgeeks
//javatpoint
//w3shools
//https://cloudinary.com/guides/bulk-image-resize/3-ways-to-resize-images-in-java
//https://stackoverflow.com/questions/5895829/resizing-image-in-java
//https://blog.aspose.com/imaging/adjust-image-contrast-brightness-gamma-in-java/
//https://stackoverflow.com/questions/12980780/how-to-change-the-brightness-of-an-image
//https://stackoverflow.com/questions/46540646/converting-a-colored-image-to-grayscale-image-in-java
//http://codehustler.org/blog/java-to-create-grayscale-images-icons/


public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath = "/home/apaf/photos";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);

        mainPanel();
    }

    public void mainPanel(){
        // Create main panel for adding to Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 3));

        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));

         selectFileButton = new JButton("Choose Image");
         showImageButton = new JButton("Show Image");
         brightnessButton = new JButton("Brightness");
         grayscaleButton = new JButton("Gray Scale");
         resizeButton = new JButton("Resize");
         closeButton = new JButton("Exit");
         resizeButton.addActionListener(this);
         selectFileButton.addActionListener(this);
         showImageButton.addActionListener(this);
         brightnessButton.addActionListener(this);
         grayscaleButton.addActionListener(this);
         closeButton.addActionListener(this);


        // Adding all buttons to Grid panel
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);

        // add main panel to our frame
        this.add(mainPanel);
    }

    public void resizePanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);

        JLabel l1,l2,l3;
        l1=new JLabel("resize section");
        l1.setBounds(310,30, 400,50);
        resizePanel.add(l1);

        l2=new JLabel("Width:");
        l2.setBounds(250,70, 400,50);
        resizePanel.add(l2);

        l3=new JLabel("Height:");
        l3.setBounds(250,120, 400,50);
        resizePanel.add(l3);

        widthTextField = new JTextField();
        widthTextField.setBounds(300, 80, 100, 30);
        resizePanel.add(widthTextField);

        heightTextField = new JTextField();
        heightTextField.setBounds(300, 130, 100, 30);
        resizePanel.add(heightTextField);

        showResizeButton = new JButton("Result");
        showResizeButton.setBounds(125, 200, 100, 30);
        showResizeButton.addActionListener(this);
        resizePanel.add(showResizeButton);

        backButton = new JButton("Back");
        backButton.setBounds(450, 200, 100, 30);
        backButton.addActionListener(this);
        resizePanel.add(backButton);

        this.add(resizePanel);
    }
    public void brightnessPanel(){
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);

        showBrightnessButton= new JButton("result");
        showBrightnessButton.addActionListener(this);


        backButton = new JButton("Back");
        backButton.addActionListener(this);

        JLabel brightnessLabel = new JLabel("Enter brightness value (0-1):");
        brightnessTextField = new JTextField();

        showBrightnessButton.setBounds(120, 200, 120, 30);
        backButton.setBounds(400, 200, 120, 30);
        brightnessLabel.setBounds(120, 95, 180, 30);
        brightnessTextField.setBounds(300, 100, 50, 30);

        brightnessPanel.add(showBrightnessButton);
        brightnessPanel.add(backButton);
        brightnessPanel.add(brightnessLabel);
        brightnessPanel.add(brightnessTextField);

        this.add(brightnessPanel);
    }

    public void chooseFileImage(){

        fileChooser.setAcceptAllFileFilterUsed(false);
        int option = fileChooser.showOpenDialog(ImageViewerGUI.this);
        if(option == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
        }

    }
    public void showOriginalImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        JLabel label = new JLabel(icon);
        tempPanel.add(label);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void grayScaleImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        try {
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

            Graphics g = grayImage.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            JLabel label = new JLabel(new ImageIcon(grayImage));
            tempPanel.add(label);

            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showResizeImage(int w, int h,String apafFile){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        ImageIcon icon = new ImageIcon(apafFile);
        Image scaledImage = icon.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        tempPanel.add(imageLabel);


        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showBrightnessImage(float f){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        Image image = icon.getImage();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);

        RescaleOp op = new RescaleOp(f, 0, null);
        bufferedImage = op.filter(bufferedImage, null);

        ImageIcon brightenedIcon = new ImageIcon(bufferedImage);
        JLabel imageLabel = new JLabel(brightenedIcon);
        tempPanel.add(imageLabel);



        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            this.getContentPane().removeAll();
            this.resizePanel();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()== showImageButton){
            this.getContentPane();
            this.showOriginalImage();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()==grayscaleButton){
            this.getContentPane();
            this.grayScaleImage();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()== showResizeButton){
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());
            showResizeImage(width, height, file.getAbsolutePath());
            widthTextField.setText(" ");
            heightTextField.setText(" ");
            this.revalidate();
            this.repaint();

        }else if(e.getSource()==brightnessButton){
            this.getContentPane().removeAll();
            this.brightnessPanel();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()== showBrightnessButton){
            float brightnessValue = Float.parseFloat(brightnessTextField.getText());
            showBrightnessImage(brightnessValue);
            brightnessTextField.setText("");

            this.revalidate();
            this.repaint();

        }else if(e.getSource()== selectFileButton){
            this.getContentPane();
            this.chooseFileImage();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}