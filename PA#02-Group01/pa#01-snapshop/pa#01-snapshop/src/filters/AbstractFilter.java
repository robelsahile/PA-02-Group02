/*
 * TCSS 305 - SnapShop
 */

package filters;

import java.util.stream.IntStream;

import image.Pixel;
import image.PixelImage;

/**
 * Abstract superclass for all image filters.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 1.1
 */
public abstract class AbstractFilter implements Filter {
   /**
    * The "Filter" suffix.
    */
   private static final String FILTER_SUFFIX = "Filter";

   /**
    * The description of this filter (will be used on buttons).
    */
//    private String myDescription;
   
   //REFACTORED CODE using Rename variable
   private String myFilterDescription;
   
   private int DIMS = 3;
   
   // Constructor

   /**
    * Constructs a filter and uses a modified version of the class's name as its description.
    * Any package hierarchy in the name is removed, and the word "Filter" is removed from the
    * end (if present). For example, the class "snapshot.filters.EdgeDetectFilter" would end
    * up with "EdgeDetect" as its description.
    */
   protected AbstractFilter() {
      final String name = getClass().getName();
      final int dot = name.lastIndexOf('.');
      // if (dot >= 0 && name.endsWith(FILTER_SUFFIX)) {
      // // truncate the word "Filter"
      // myDescription = name.substring(dot + 1, name.length() - FILTER_SUFFIX.length());
      // } else {
      // myDescription = name.substring(dot + 1, name.length());
      // }
   
      // REFACTORED CODE using inline variable
      // Changed the if block with used to assign to variable to an inline statement
      // Surafel Seboka #3
      myFilterDescription = (dot >= 0 && name.endsWith(FILTER_SUFFIX))
                                     ? name.substring(dot + 1,
                                                      name.length()
                                                               - FILTER_SUFFIX.length())
                                     : name.substring(dot + 1, name.length());
   }

   /**
    * Constructs a filter with the specified description.
    * 
    * @param theDescription The description.
    */
   public AbstractFilter(final String theDescription) {
      myFilterDescription = theDescription;
   }

   // Instance Methods

   /**
    * Returns the text description of this filter.
    * 
    * @return the text description of this filter
    */
   @Override
   public String getDescription() {
      return myFilterDescription;
   }

   /**
    * Applies a "weighting" to each pixel, where its new value is produced by doing a weighted
    * average of the 3x3 grid of pixels around it. For example, A Gaussian blur/softening
    * effect can be achieved by applying the following weights to each pixel:
    * 
    * <pre>
    *    1  2  1
    *    2  4  2
    *    1  2  1
    * </pre>
    * 
    * Since the weights increase the pixel's color value, likely beyond the legal maximum
    * color value of 255, a scale-down is applied based on the sum of the weights.
    * 
    * @param theImage The image.
    * @param theWeights The weights matrix. This must be a non-null 3x3 matrix or an
    *            IllegalArgumentException is thrown.
    * @exception IllegalArgumentException if the weights are invalid.
    */
   protected void weight(final PixelImage theImage, final int[][] theWeights)
                                   throws IllegalArgumentException {
   
      checkWeights(theWeights);
   
      int sum = 0;
   
      for (final int[] row : theWeights) {
      // for (final int col : row) {
      //  sum = sum + col;
      // }
         
      // #1, Robel Sahile PA#02 REFACTORED using Replace Inline Code with Function Call
      // removed the for loop and replace it with a inline expression
         
         sum += IntStream.of(row).sum();
      }
   
      if (sum == 0) {
         sum = sum + 1;
      }
   
      weight(theImage, theWeights, sum);
   }

   /**
    * Applies a "weighting" to each pixel, with the given scale-down factor.
    * 
    * @param theImage The image.
    * @param theWeights The weights matrix. This must be a non-null 3x3 matrix or an
    *            IllegalArgumentException is thrown.
    * @param theScale The scale-down factor.
    * @exception IllegalArgumentException if the weights are invalid.
    * @see #weight(PixelImage, int[][])
    */
   protected void weight(final PixelImage theImage, final int[][] theWeights,
                         final int theScale) throws IllegalArgumentException {
   //        checkWeights(theWeights);
      
      //REFACTORED CODE using Remove Dead Code
    	// Surafel Seboka #4
      //No need for above code as this function is always called the the function protected void weight(final PixelImage theImage, final int[][] theWeights),
      //and in that function we have checked the weights once, so no need to check again.
    	
    	// Refactored rename variables
    	// Surafel Seboka #2
   
      final int Width = theImage.getWidth(null);
      final int Height = theImage.getHeight(null);
      final Pixel[][] oldPixels = theImage.getPixelData();
      final Pixel[][] newPixels = new Pixel[Height][Width];
   
      for (int y = 0; y < Height; y++) {
         for (int x = 0; x < Width; x++) {
            // add up 9 neighboring pixels
            int red = 0;
            int green = 0;
            int blue = 0;
            for (int j = Math.max(0, y - 1); j <= Math.min(y + 1, Height - 1); j++) {
               for (int i = Math.max(0, x - 1); i <= Math.min(x + 1, Width - 1); i++) {
                  // Pixel p = oldPixels[i][j];
                  final Pixel p = oldPixels[j][i];
                  final int weight = theWeights[y - j + 1][x - i + 1];
                  red = red + p.getRed() * weight;
                  green = green + p.getGreen() * weight;
                  blue = blue + p.getBlue() * weight;
               }
            }
         
            // account for negative / too high color values
            red = normalize(red / theScale);
            green = normalize(green / theScale);
            
            // blue = normalize(blue / theScale);
            // #2, Robel Sahile PA#02 REFACTORED using Remove Middle Man
            // removed the normalize function and added it here
            blue = Math.max(Pixel.MIN_COLOR_VALUE, Math.min(Pixel.MAX_COLOR_VALUE, blue/theScale));
            
            newPixels[y][x] = new Pixel(red, green, blue);
         }
      }
   
   // theImage.setPixelData(newPixels);
      
   // #3, Robel Sahile PA#02 REFACTORED about code using Extract Function
   // added a function to perform the above code.
      
      addToImage(theImage, newPixels);
   }
   
   private void addToImage(final PixelImage theImage, final Pixel[][]newPixels) {
      theImage.setPixelData(newPixels);
   }

   /**
    * Checks to see if the specified weights matrix is valid (that is, is non-null and a
    * Pixel.NUM_CHANNELS-square grid).
    * 
    * @param theWeights The weights matrix.
    * @exception IllegalArgumentException if the weights matrix is invalid.
    */
   private void checkWeights(final int[][] theWeights) throws IllegalArgumentException {
      // if (theWeights == null || theWeights.length != Pixel.NUM_CHANNELS
      // || theWeights[0] == null || theWeights[0].length != Pixel.NUM_CHANNELS
      // || theWeights[1] == null || theWeights[1].length != Pixel.NUM_CHANNELS
      // || theWeights[2] == null || theWeights[2].length != Pixel.NUM_CHANNELS) {
      // throw new IllegalArgumentException("must pass correctly-sized grid");
      // }
   
      // REFACTORED CODE using Substitute Algorithm
      // In case of more theWeights, we just need to change the bounding condition in the for
      // loop
    	  // Robel Sahile #6 - PA#01
    	
      if (theWeights == null || theWeights.length != Pixel.NUM_CHANNELS) {
         throw new IllegalArgumentException("must pass correctly-sized grid");
      }
    	
    	// #4, Robel Sahile PA#02 REFACTORED USING Replace Magic Literal
    	// replace 3 with the Dimensions of pixel as a global variable
      // for (int i = 0; i < 3; i++) {
          
      for (int i = 0; i < DIMS; i++) {
         if (theWeights[i] == null || theWeights[i].length != Pixel.NUM_CHANNELS) {
            throw new IllegalArgumentException("must pass correctly-sized grid");
         }
      }
   }

   /**
    * Normalizes the specified color value to the range 0-255.
    * 
    * @param theColor The color value.
    * @return the normalized color value.
    */
   protected int normalize(final int theColor) {
   //        return Math.max(Pixel.MIN_COLOR_VALUE, Math.min(Pixel.MAX_COLOR_VALUE, theColor));
      
      //REFACTORED CODE using Extract Variable
      //Added variables to explain the working of normalize function
    	// Surafel Seboka #5
      
      int minValue = Math.min(Pixel.MAX_COLOR_VALUE, theColor);
      int maxValue = Math.max(Pixel.MIN_COLOR_VALUE, minValue);
      return maxValue;
   }

   /**
    * Swaps the specified pixels in the image.
    * 
    * @param theData The image data.
    * @param row1 The row of the first pixel to swap.
    * @param col1 The column of the first pixel to swap.
    * @param row2 The row of the second pixel to swap.
    * @param col2 The column of the second pixel to swap.
    */
   protected void swap(final Pixel[][] theData, final int row1, final int col1,
                       final int row2, final int col2) {
      final Pixel temp = theData[row1][col1];
      theData[row1][col1] = theData[row2][col2];
      theData[row2][col2] = temp;
   }
}
