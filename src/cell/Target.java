package cell;

public class Target extends ContainerCell {
	private boolean visible;
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible() {
		visible = true;
		
	}
	public String toString() {
		return "Destino + " + this.getContent();
	}

}
