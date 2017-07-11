
public class Text extends Primitive {
	
	public int x, y, fontSize;
	public String text, fontName;
	
	public Text(int x, int y, String text) {
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	public void setFont(String fontName, int fontSize) {
		this.fontName = fontName;
		this.fontSize = fontSize;
	}
	
	public void draw(UserInterface ui) {
		ui.drawText(x, y, text);
		ui.textColor(color);
	}
}
