package ge.edu.freeuni.sdp.snake.model;

/* Note: Comment is not up to date
 * ეს თაგვი არის being კლასის შვილობილი. ალბათ უფრო ლოგიკური იქნებოდა თუ იქნებოდა movingBeing-ს შვილობილი რადგან თვითონაც მოძრაობს მაგრამ ბოლოს ნახსენები კლასის 
 ობიექტი ყოველ ჯერზე უნდა მოძრაობდეს, ჩვენი თაგვი კი მხოლოდ მაშინ მოძრაობს, როდესაც მას 5 უჯრის მანძილზე მიუახლოვდება ვინმე. ვაკეთებ შემდეგს: ვიმახსოვრებ ყველაზე ახლოს რომელი
 არსებები მყავს ოთხი მიმართულებიდან და შემდეგ გადასაადგილებლად ვირჩევ ისეთ მიმართულებას, რომელიც მიმაახლოვებს ყველაზე შორეულს.(თუ რომელიმე მიმართულებით არ არის არსება მაშინ იგულისხმება, რომ 
 დაშორება იქით უსასრულობაა, და გადაადგილებაც მაგ მხარეს მოხდება). მანძილის დასადგენად ვიყენებ Manhattan Distance-ს.
 */
public class EscapingMouseBeing extends MovingBeing {
	private Point point;
	private int closestTop;
	private int closestBottom;
	private int closestLeft;
	private int closestRight;
	private boolean toMove;
	private boolean speedDropFlip;
	
	public EscapingMouseBeing(Point p) {
		this.point = p;
		toMove = false;
		setDirection(Direction.DOWN);
		resetClosest();
	}

	@Override
	public void interactWith(Being other) {
		Point o = other.getHead();
		int xDiff = point.X - o.X;
		int yDiff = point.Y - o.Y;
		int manDist = Math.abs(xDiff) + Math.abs(yDiff);
		if (manDist <= 5) {
			updateDistances(xDiff, yDiff);
			setDirection();
			toMove = true;
		}
	}
	
	@Override
	protected void moveTo(Point point) {
		if (!toMove) return;
		speedDropFlip = !speedDropFlip;
		if (speedDropFlip) return;
		toMove = false;
		this.point = point;
	}

	private void resetClosest() {
		closestTop = Integer.MAX_VALUE;
		closestBottom = Integer.MAX_VALUE;
		closestLeft = Integer.MAX_VALUE;
		closestRight = Integer.MAX_VALUE;
	}
	
	private void updateDistances(int x, int y) {
		int xDiff = Math.abs(x);
		int yDiff = Math.abs(y);
		if (x <= 0 && closestRight > xDiff + yDiff)
			closestRight = xDiff + yDiff;
		if (x >= 0 && closestLeft > xDiff + yDiff)
			closestLeft = xDiff + yDiff;
		if (y <= 0 && closestBottom > xDiff + yDiff)
			closestBottom = xDiff + yDiff;
		if (y >= 0 && closestTop > xDiff + yDiff)
			closestTop = xDiff + yDiff;
	}

	private void setDirection() {
		int max = Math.max(closestRight,
				Math.max(closestLeft, Math.max(closestTop, closestBottom)));
		Direction toGo = null;
		if (max == closestRight) {
			toGo = Direction.RIGHT;

		} else if (max == closestLeft) {
			toGo = Direction.LEFT;

		} else if (max == closestTop) {
			toGo = Direction.UP;

		} else if (max == closestBottom) {
			toGo = Direction.DOWN;

		}
		setDirection(toGo);
	}

	@Override
	public boolean contains(Point point) {
		return this.point.equals(point);
	}

	@Override
	public Point getHead() {
		return this.point;
	}

	@Override
	public BeingKind getKind() {
		return BeingKind.FoodMouse;
	}
}
