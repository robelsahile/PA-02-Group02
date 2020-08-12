PA #02 - snapshop
Robel Sahile Refactoring

1) In AbstractFilter.java
in the function private void checkWeights(final int[][] theWeights), 
used Refactoring method of Replace Magic Literal to replace 3, with 
a global DIMS variable denoting dimensions.

2) In AbstractFilter.java
in the function protected void weight(final PixelImage theImage, final int[][] theWeights),
used Refactoring method of Replace Inline Code with Function Call to convert the for loop
to a inline expression

3) In AbstractFilter.java
in the function protected void weight(final PixelImage theImage, final int[][] theWeights,
                          final int theScale),
used Refactoring method Extract function to execute a piece of code, on a separate function

4) In AbstractFilter.java
in the function protected void weight(final PixelImage theImage, final int[][] theWeights,
                          final int theScale),
used Refactoring method Remove Middle Man to removed the normalize function and added it here

5) In PixelImage.java
in the function public void setPixelData(final Pixel[][] theData),
used Refactoring using Slide Statement, to switch two statement in their order.


PA #02 - snapshop
Surafel Seboka Reafactoring

6. using method extract
      myFilterDescription(name, dot);
      
7. using method extract
   private void theData(Pixel[][] theData, Pixel[] theDatum, int col1, int row2, int col2) {
      final Pixel temp = theDatum[col1];
      theDatum[col1] = theData[row2][col2];
      theData[row2][col2] = temp;
      
8. using inline variable
    public void setRed(final int theRed) throws IllegalArgumentException {
        if (theRed < MIN_COLOR_VALUE || theRed > MAX_COLOR_VALUE) {
            throw new IllegalArgumentException("Invalid color value: " + theRed);
        }
        myRed = theRed;
    }

9. using inline variable 
    public void setGreen(final int theGreen) throws IllegalArgumentException {
        if (theGreen < MIN_COLOR_VALUE || theGreen > MAX_COLOR_VALUE) {
            throw new IllegalArgumentException("Invalid color value: " + theGreen);
        }
        myGreen = theGreen;
    }

10.using inline variable 
    public void setBlue(final int theBlue) throws IllegalArgumentException {
        if (theBlue < MIN_COLOR_VALUE || theBlue > MAX_COLOR_VALUE) {
            throw new IllegalArgumentException("Invalid color value: " + theBlue);
        }
        myBlue = theBlue;
    }
