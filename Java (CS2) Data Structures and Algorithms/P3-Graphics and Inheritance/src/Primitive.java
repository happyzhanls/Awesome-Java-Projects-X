
public abstract class Primitive {
	
	public int color;
	public boolean isFilled;
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}
	
	public abstract void draw(UserInterface ui);
	
}
