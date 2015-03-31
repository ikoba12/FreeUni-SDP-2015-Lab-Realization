package ge.edu.freeuni.sdp.snake.model;

public abstract class EndlessTopology implements Topology {

	@Override
	public Point getNextTo(Point point, Direction direction) {
		
		return direction.addTo(point);
	}
}
