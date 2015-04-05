package ge.edu.freeuni.sdp.snake;

import ge.edu.freeuni.sdp.snake.model.CompositePopulator;
import ge.edu.freeuni.sdp.snake.model.Configuration;
import ge.edu.freeuni.sdp.snake.model.EscapingMouseBeing;
import ge.edu.freeuni.sdp.snake.model.EscapingMousePopulator;
import ge.edu.freeuni.sdp.snake.model.Level;
import ge.edu.freeuni.sdp.snake.model.SingleMousePopulator;
import ge.edu.freeuni.sdp.snake.model.Size;
import ge.edu.freeuni.sdp.snake.model.SphericTopology;
import ge.edu.freeuni.sdp.snake.view.ViewController;
import ge.edu.freeuni.sdp.snake.view.terminal.TerminalViewFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

public class App {

	public static void main(String[] args) {

		Terminal terminal = getTerminal();
		Size size = getSize(terminal);

		List<Level> levels = new ArrayList<Level>();

		Level level1 = new Level(
				"Very Simple Level", 
				new SphericTopology(),
				new SingleMousePopulator());
		//კომპოზიტის საშუალებით ვუზრუნველყოფ 5 მოძრავი თაგვის დამატებას.
		EscapingMousePopulator[] mice=new EscapingMousePopulator[5];
		for(int i=0;i<5;i++) mice[i]=new EscapingMousePopulator();
		
		Level level2 =new Level("Tom And Jerry", new SphericTopology(), new CompositePopulator(mice));
		//TODO Add other levels here
		levels.add(level1);
		levels.add(level2);

		Configuration.init(size, levels);
		
		TerminalViewFactory viewFactory = new TerminalViewFactory(terminal);
		ViewController controller = new ViewController(viewFactory);
		controller.run();
		
		terminal.exitPrivateMode();
	}

	private static Terminal getTerminal() {
		Terminal terminal = TerminalFacade.createTerminal(System.in,
				System.out, Charset.forName("UTF8"));
		terminal.enterPrivateMode();
		terminal.clearScreen();
		terminal.setCursorVisible(false);
		return terminal;
	}

	private static Size getSize(Terminal terminal) {
		TerminalSize terminalSize = terminal.getTerminalSize();
		int width = terminalSize.getColumns();
		int height = terminalSize.getRows();
		
		Size size = new Size(width, height);
		return size;
	}
}