package ge.edu.freeuni.sdp.snake.model;

//ჩვეულებრივი პოპულატორი რომელიც ამატებს ერთ ცალ მოძრავ თაგვს.
public class EscapingMousePopulator extends RandomPositionPopulator {
	private EscapingMouseBeing _mouse;
	
	@Override
	public void populate(Universe universe) {
				if (_mouse == null || !_mouse.isAlive()) {
				Point point = getRandomUnocupied(universe);
				_mouse = new EscapingMouseBeing(point);
				universe.addBeing(_mouse);
			}
	}
}
