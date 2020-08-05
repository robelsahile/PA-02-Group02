Refactoring done in class
SnapShopGUI.java

1) Inline Variable
Changed the if block with used to assign to variable to an inline statement under the function:
private File showChooser(final boolean theOpenFlag)

2) Rename Variable
Changed the name of variable myImage to myPixelImage as its data type is PixelImage.

3) Removed Middle Man
Removed the variable result from the function:
private File showChooser(final boolean theOpenFlag) 
Replaced the return value directly upon checking the if condition.

4) Extract Variable
added variable explaining the parameters for setLocation method in the function:
private void centerFrame()

5) Rename field
renaming closeImage() to mycloseImage() in the function:
private void mycloseImage()



Refactoring done in class
AbstractFilter.java

1) Substitute Algorithm
Changed the if block under the function: 
private void checkWeights(final int[][] theWeights)
with a for loop.

2) Inline Variable
Changed the if block with used to assign to variable to an inline statement under the function:
protected AbstractFilter()

3) Remove Dead Code
Remove the  checkWeights(theWeights) part from the function: 
protected void weight(final PixelImage theImage, final int[][] theWeights,
                          final int theScale)
As this function is always called the the function protected void weight(final PixelImage theImage, final int[][] theWeights),
and in that function we have checked the weights once, so no need to check again.

4) Rename Variable
Changed the name of variable myDescription to myFilterDescription.

5) Extract Variable
Added variables to explain the normalize in the function:
protected int normalize(final int theColor)