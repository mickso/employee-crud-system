package employeecrud.ui.button;

import java.util.EventListener;

public interface ITableButtonListener extends EventListener {
	  public void tableButtonClicked( int row, int col );
}