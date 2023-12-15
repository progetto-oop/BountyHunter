package buontyhunter.input;

public class KeyboardInputController implements InputController {

	private boolean isMoveUp;
	private boolean isMoveDown;
	private boolean isMoveLeft;
	private boolean isMoveRight;
	private boolean aKeyIsPressed = false;

	private boolean isMPressed;

	@Override
	public boolean isMoveUp() {
		return isMoveUp;
	}

	@Override
	public boolean getAKeyIsPressed() {
		return this.aKeyIsPressed;
	}

	public void AKeyIsPressed() {
		this.aKeyIsPressed = true;
	}

	public void AKeyIsPressedIsNotPressed() {
		this.aKeyIsPressed = false;
	}

	@Override
	public boolean isMoveDown() {
		return isMoveDown;
	}

	@Override
	public boolean isMoveLeft() {
		return isMoveLeft;
	}

	@Override
	public boolean isMoveRight() {
		return isMoveRight;
	}

	@Override
	public boolean isMPressed() {
		return isMPressed;
	}

	public void notifyMoveUp() {
		isMoveUp = true;
	}

	public void notifyNoMoreMoveUp() {
		isMoveUp = false;
	}

	public void notifyMoveDown() {
		isMoveDown = true;
	}

	public void notifyNoMoreMoveDown() {
		isMoveDown = false;
	}

	public void notifyMoveLeft() {
		isMoveLeft = true;
	}

	public void notifyNoMoreMoveLeft() {
		isMoveLeft = false;
	}

	public void notifyMoveRight() {
		isMoveRight = true;
	}

	public void notifyNoMoreMoveRight() {
		isMoveRight = false;
	}

	public void notifyMPressed() {
		isMPressed = true;
	}

	public void notifyNoMoreMPressed() {
		isMPressed = false;
	}
}
