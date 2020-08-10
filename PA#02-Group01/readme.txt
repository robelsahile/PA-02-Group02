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