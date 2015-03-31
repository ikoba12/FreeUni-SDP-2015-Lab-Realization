package ge.edu.freeuni.sdp.snake.model;
//ჩვეულებრივი პოპულატორი რომელიც ამატებს ერთ ცალ მოძრავ თაგვს.
public class MovingMousePopulator extends RandomPositionPopulator {
	private MovingMouseBeing _mouse;
	
	@Override
	public void populate(Universe universe) {
				Being g=new Snake(new Point(1,1));
				
				if (_mouse == null || !_mouse.isAlive()) {
				Point point = getRandomUnocupied(universe);
				_mouse = new MovingMouseBeing(point);
				universe.addBeing(_mouse);
			}
		
		
		
	}

}
