/*
 * TCSS 305 - SnapShop
 */

package filters;

import image.Pixel;
import image.PixelImage;

/**
 * A filter that flips the image vertically.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 1.1
 */
public class FlipVerticalFilter extends AbstractFilter {
    /**
     * Constructs a new flip vertical filter.
     */
    public FlipVerticalFilter() {
        super("Flip Vertical");
    }

    /**
     * Filters the specified image.
     * 
     * @param theImage The image.
     */
    @Override
    //Timmy refactor #1 separate query from modifier 
    public void filter(final PixelImage theImage) {
        final Pixel[][] data = theImage.getPixelData();
        for (int row = 0; row < theImage.getHeight() / 2; row++) {
            for (int col = 0; col < theImage.getWidth(); col++) {
                swap(data, row, col, theImage.getHeight() - row - 1, col);
            }
        }
        filterModifier(theImage,data);
    }
    //@author Timmy Roma
    private void filterModifier(final PixelImage theImage, final Pixel[][] data)
    {
    	theImage.setPixelData(data);
    }
}
