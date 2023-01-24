package com.example.alexfanimageviewer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    FileChooser fileChooser = new FileChooser(); // file chooser

    private int width;
    private int height;

    @FXML
    public ImageView regularImage, grayImageView, redImage, greenImage, blueImage, adjustableImage;

    @FXML
    public Label description, imageWidth, imageHeight;

    @FXML
    private Button Load, Exit;

    @FXML
    private MenuButton colourChoices;

    @FXML
    private MenuItem Gray, Red, Green, Blue;

    @FXML
    private Slider hueSlider, brightnessSlider, saturationSlider, contrastSlider;

    @FXML
    public void getImage(ActionEvent actionEvent){
        File file = fileChooser.showOpenDialog(new Stage()); // open file explorer
        fileChooser.setInitialDirectory(new File("C:\\Users\\tinyf\\OneDrive - Waterford Institute of Technology\\Applied Computing Course\\year 2\\semester 2\\data structures & algorithms 2\\worksheet 1\\Alex Fan Image Viewer\\images"));

        if(file != null) {
            Image image = new Image(file.toURI().toString()); // make file into a URI
            regularImage.setImage(image); // open image in imageviewer
            adjustableImage.setImage(image);
            imageDetails(); // run file detail class

        }
        else{
            System.out.println("No Image Chosen");
        }

    }

    public WritableImage toGrayScale(ActionEvent actionEvent) {

        Image image = regularImage.getImage(); // get the chosen image from file explorer (regular imageview)
        grayImageView.setImage(image); // set grayscale image to this
        PixelReader pixelReader = image.getPixelReader(); // initialize the pixel reader for the original image

        int width = (int) image.getWidth(); // get width of image
        int height = (int) image.getHeight(); // get height of image

        WritableImage grayImage = new WritableImage(width, height); // create a new WritableImage object with the same dimensions as the original image

        // loop through each pixel of the original image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // Get the red, green, and blue values of the current pixel
                double red = pixelReader.getColor(x,y).getRed();
                double green = pixelReader.getColor(x,y).getGreen();
                double blue = pixelReader.getColor(x,y).getBlue();

                double grayLevel = (red + green + blue)/3; // average the red, green, and blue values to get the gray level for the current pixel
                grayImage.getPixelWriter().setColor(x,y, Color.gray(grayLevel)); // set the current pixel in the new grayImage to the calculated gray level
            }
        }
        grayImageView.setImage(grayImage); // set the grayImageView to display the new grayscale image
        return grayImage; // return the new grayscale image
    }

    public WritableImage toRedScale(ActionEvent actionEvent) {
        Image image = regularImage.getImage(); // get the chosen image from file explorer (regular imageview)
        redImage.setImage(image); // set the redImage imageView to the original image chosen from file explorer
        PixelReader pixelReader = image.getPixelReader(); // initialize the pixel reader for the chosen image

        int width = (int) image.getWidth(); // get and declare width and height of image
        int height = (int) image.getHeight();

        WritableImage toRedImage = new WritableImage(width, height); // Create a new WritableImage object with the same dimensions as the original image

        // loop through each pixel of the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the red value of the current pixel
                double red = pixelReader.getColor(x,y).getRed();
                double redder = (red);

                // Set the red,green and blue values of the current pixel in the new toRedImage to the calculated red value, 0 for green and 0 for blue.
                toRedImage.getPixelWriter().setColor(x,y, Color.color(redder,0,0,1));
            }
        } redImage.setImage(toRedImage); // Set the redImage object to display the new red scale image
        return toRedImage; // return the new red scale image
    }

    public WritableImage toGreenScale(ActionEvent actionEvent) {
        Image image = regularImage.getImage(); // get the chosen image from file explorer (regular imageview)
        greenImage.setImage(image); // set the greenImage imageView to the original image chosen from file explorer
        PixelReader pixelReader = image.getPixelReader(); // initialize the pixel reader for the chosen image

        int width = (int) image.getWidth(); // get and declare width and height of image
        int height = (int) image.getHeight();

        WritableImage toGreenImage = new WritableImage(width, height); // Create a new WritableImage object with the same dimensions as the original image

        // loop through the images pixels
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // get the green value of the pixels
                double green = pixelReader.getColor(x,y).getGreen();
                double greener = (green);
                // Set the red,green and blue values of the current pixel in the new toGreenImage to the calculated 0 for red, greenValue and 0 for blue.
                toGreenImage.getPixelWriter().setColor(x,y, Color.color(0,greener,0,1));
            }
        } greenImage.setImage(toGreenImage); // set the greenImage object to display the new green scaled image
        return toGreenImage; // return the new green scaled image
    }

    public WritableImage toBlueScale(ActionEvent actionEvent) {
        Image image = regularImage.getImage();
        blueImage.setImage(image);
        PixelReader pixelReader = image.getPixelReader();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage toBlueImage = new WritableImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double blue = pixelReader.getColor(x,y).getBlue();
                double bluer = (blue);
                toBlueImage.getPixelWriter().setColor(x,y, Color.color(0,0,bluer,1));
            }
        } blueImage.setImage(toBlueImage);
        return toBlueImage;
    }

    public void hueSlider(MouseEvent mouseEvent){
        ColorAdjust colorAdjust = new ColorAdjust(); // set up colour adjuster
        colorAdjust.setHue(hueSlider.getValue()); // set hue to the value of hueSlider (between 0 and 1)
        adjustableImage.setEffect(colorAdjust); // allow image to be effcted by slider
    }

    public void saturationSlider(MouseEvent mouseEvent){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(saturationSlider.getValue());
        adjustableImage.setEffect(colorAdjust);
    }

    public void brightnessSlider(MouseEvent mouseEvent){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(brightnessSlider.getValue());
        adjustableImage.setEffect(colorAdjust);
    }

    public void contrastSlider(MouseEvent mouseEvent){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(contrastSlider.getValue());
        adjustableImage.setEffect(colorAdjust);
    }

    public void imageDetails() {
        Image image = regularImage.getImage(); // declare image as the chosen image
        String details = "Image Details: " + regularImage.getImage().getUrl(); // create strings for details, width and height
        String width = "Width: " + regularImage.getImage().getWidth() + " px";
        String height = "Height: " + regularImage.getImage().getHeight() + " px";

        // add strings to labels
        description.setText(details);
        imageWidth.setText(width);
        imageHeight.setText(height);

    }

    public void exit(ActionEvent actionEvent){
        //exits app
        Platform.exit();
    }

//        public WritableImage changeToGray(ActionEvent actionEvent) {
//
//        Image image = regularImage.getImage();
//        grayImageView.setImage(image);
//        PixelReader pixelReader = image.getPixelReader();
//
//        int width = (int) image.getWidth();
//        int height = (int) image.getHeight();
//
//        WritableImage grayImage = new WritableImage(width, height);
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                int pixel = pixelReader.getArgb(x, y);
//
//                int alpha = ((pixel >> 24) & 0xff);
//                int red = ((pixel >> 16) & 0xff);
//                int green = ((pixel >> 8) & 0xff);
//                int blue = (pixel & 0xff);
//
//                int grayLevel = (int) (0.2162 * red + 0.7152 * green + 0.0722 * blue);
//                int gray = (alpha << 24) + (grayLevel << 16) + (grayLevel << 8) + grayLevel;
//
//                grayImage.getPixelWriter().setArgb(x, y, gray);
//            }
//        } grayImageView.setImage(grayImage);
//        return grayImage;
//    }
//
//        public WritableImage changeToRed(ActionEvent actionEvent) {
//
//        Image image = regularImage.getImage();
//        redImage.setImage(image);
//        PixelReader pixelReader = image.getPixelReader();
//
//        int width = (int) image.getWidth();
//        int height = (int) image.getHeight();
//
//        WritableImage toRedImage = new WritableImage(width, height);
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                int pixel = pixelReader.getArgb(x, y);
//
//                int alpha = ((pixel >> 24) & 0xff);
//                int red = ((pixel >> 16) & 0xff);
//
//
//                int redLevel = (int) (0.2162 * red + 0.7152);
//                int toRed = (alpha << 24) + (redLevel << 16);
//
//                toRedImage.getPixelWriter().setArgb(x, y, red);
//            }
//        } redImage.setImage(toRedImage);
//        return toRedImage;
//    }

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("C:\\Users\\tinyf\\OneDrive - Waterford Institute of Technology\\Applied Computing Course\\year 2\\semester 2\\data structures & algorithms 2\\worksheet 1\\Alex Fan Image Viewer\\images"));
    }
}