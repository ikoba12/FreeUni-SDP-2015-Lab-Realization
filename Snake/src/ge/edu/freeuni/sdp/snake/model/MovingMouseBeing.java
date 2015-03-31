package ge.edu.freeuni.sdp.snake.model;
/*ეს თაგვი არის being კლასის შვილობილი. ალბათ უფრო ლოგიკური იქნებოდა თუ იქნებოდა movingBeing-ს შვილობილი რადგან თვითონაც მოძრაობს მაგრამ ბოლოს ნახსენები კლასის 
ობიექტი ყოველ ჯერზე უნდა მოძრაობდეს, ჩვენი თაგვი კი მხოლოდ მაშინ მოძრაობს, როდესაც მას 5 უჯრის მანძილზე მიუახლოვდება ვინმე. ვაკეთებ შემდეგს: ვიმახსოვრებ ყველაზე ახლოს რომელი
არსებები მყავს ოთხი მიმართულებიდან და შემდეგ გადასაადგილებლად ვირჩევ ისეთ მიმართულებას, რომელიც მიმაახლოვებს ყველაზე შორეულს.(თუ რომელიმე მიმართულებით არ არის არსება მაშინ იგულისხმება, რომ 
დაშორება იქით უსასრულობაა, და გადაადგილებაც მაგ მხარეს მოხდება). მანძილის დასადგენად ვიყენებ Manhattan Distance-ს.
*/
public class MovingMouseBeing extends Being {
	private Point point;
	private int closestTop;
	private int closestBottom;
	private int closestLeft;
	private int closestRight;
	private boolean toMove;
	int speed;
	private Direction dir;
	public MovingMouseBeing(Point p){
		this.point=p;
		closestTop=Integer.MAX_VALUE;
		closestBottom=Integer.MAX_VALUE;
		closestLeft=Integer.MAX_VALUE;
		closestRight=Integer.MAX_VALUE;
		toMove=false;
		speed=2;
	}
	@Override
	public BeingKind getKind() {
		// TODO Auto-generated method stub
		return BeingKind.FoodMouse;
	}
	private void updateDistances(int x, int y){
		int xDiff=Math.abs(x);
		int yDiff=Math.abs(y);
		if(x<=0 && closestRight>xDiff+yDiff) closestRight=xDiff+yDiff;
	    if(x>=0 && closestLeft>xDiff+yDiff) closestLeft=xDiff+yDiff;
	    if(y<=0 && closestBottom>xDiff+yDiff) closestBottom=xDiff+yDiff;
	    if(y>=0 && closestTop>xDiff+yDiff) closestTop=xDiff+yDiff;

	}
	
	private void setDirection(){
		int max=Math.max(closestRight, Math.max(closestLeft, Math.max(closestTop, closestBottom)));
		Direction toGo=null;
		if(max==closestRight){ 
				toGo=Direction.RIGHT;
				
		}
		else if(max==closestLeft){
				toGo=Direction.LEFT;
				
		}
		else if(max==closestTop){ 
			toGo=Direction.UP;
			
		}
		else if(max==closestBottom){
				toGo=Direction.DOWN;
				
		}
		setDirection(toGo);
	}
	@Override
	public void interactWith(Being other) {
		Point o=other.getHead();
		int xDiff=point.X-o.X;
		//System.out.println("x- "+xDiff);
		int yDiff=point.Y-o.Y;
		int manDist=Math.abs(xDiff)+Math.abs(yDiff);
		if(manDist<=5){
			updateDistances(xDiff,yDiff);
			setDirection();
			toMove=true;
			/*System.out.println("R- "+closestRight);
			System.out.println("L- "+closestLeft);
			System.out.println("T- "+closestTop);
			System.out.println("B- "+closestBottom);
			System.out.println();*/
		}
	}

	@Override
	public boolean contains(Point point) {
		// TODO Auto-generated method stub
		return this.point.equals(point);
	}

	@Override
	public Point getHead() {
		// TODO Auto-generated method stub
		return this.point;
	}
	@Override
	public void move(Topology topology) {
		System.out.println(toMove);
		if(toMove){
			Point point=topology.getNextTo(this.point, dir);
			if(speed==1){
				this.point=point;
				speed=2;
				toMove=false;
			}
			else speed--;
			
		}
		closestTop=Integer.MAX_VALUE;
		closestBottom=Integer.MAX_VALUE;
		closestLeft=Integer.MAX_VALUE;
		closestRight=Integer.MAX_VALUE;
		
			
		
	}
		
	

	@Override
	public void setDirection(Direction direction) {
		this.dir=direction;
		
	}

}
