
public class Transforms implements ImageInterface {

	private PictureLibrary pl = null;
	private int imageWidth = 0;
	private int imageHeight = 0;
	private int[][] imageData;
	
	public Transforms() {
		//Instantiate PictureLibrary object
		pl = new PictureLibrary();
	}
	
	@Override
	public void readImage(String inFile) {
		try {
			pl.readPGM(inFile);
			imageHeight = pl.getHeight();
			imageWidth = pl.getWidth();
			imageData = pl.getData();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void writeImage(String outFile) {
		pl.setData(imageData);
		try {
			pl.writePGM(outFile);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int[][] imageData() {
		return imageData;
	}

	@Override
	public void decode() {
		for (int i = 0; i < imageHeight; i++) {
			for (int j = 0; j < imageWidth; j++) {
				int originalPixel = imageData[i][j];
				int upperBits = originalPixel / 16;
				int lowerBits = originalPixel % 16;
				int negatedUpperBits = 15 - upperBits;
				int newPixel = (negatedUpperBits * 16) + lowerBits;
				imageData[i][j] = newPixel;
			}
		}
	}

	@Override
	public void swap() {
		for (int i = 0; i < imageHeight; i++) {
			for (int j = 0; j < imageWidth; j++) {
				int originalPixel = imageData[i][j];
				int upperTwoBits = originalPixel & 0b11000000;
				int middleFourBits = originalPixel & 0b00111100;
				int lowerTwoBits = originalPixel & 0b00000011;
				int newPixel = (lowerTwoBits << 6) | middleFourBits | (upperTwoBits >> 6);
				imageData[i][j] = newPixel;
			}
		}
	}

	@Override
	public void mirror() {
		for (int i = 0; i < imageHeight / 2; i++) {
			int[] temp = imageData[i];
			imageData[i] = imageData[imageHeight - i - 1];
			imageData[imageHeight - i - 1] = temp;
		}
	}

	@Override
	public void exchange() {
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 150; j++) {
				int temp = imageData[i+10][j+10];
				imageData[i+10][j+10] = imageData[i+10][j+310];
				imageData[i+10][j+310] = temp;
			}
		}
	}
}
