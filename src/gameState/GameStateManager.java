package gameState;

public class GameStateManager {
	
	public static GameState[] gameStates;
	public static int currentState;
	
	public static final int NUMGAMESTATES = 5;
	public static final int LOADINGSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int OPTIONSSTATE = 2;
	public static final int CREDITSSTATE = 3;
	public static final int LEVELSTATE = 4;
	
	public GameStateManager() {
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = LOADINGSTATE;
		loadState(currentState);
		
	}
	
	private void loadState(int state) {
		if(state == LOADINGSTATE)
			gameStates[state] = new LoadingState(this);
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if(state == CREDITSSTATE)
			gameStates[state] = new CreditsState(this);
		if(state == OPTIONSSTATE)
			gameStates[state] = new OptionsState(this);
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		//gameStates[currentState].init();
	}
	
	public void update() {
		try {
			gameStates[currentState].update();
		} catch(Exception e) {}
	}
	
	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch(Exception e) {}
	}
	
	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}
	
}