// CallbackInterface.java - interface for recursive maze assignment
// Author: Chris Wilcox
// Date:   12/27/2016
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

public interface CallbackInterface {

	// Status of search
	public enum SearchStatus {
		PATH_VALID,			// Valid path
		PATH_INVALID,		// Invalid path (cannot move)
		PATH_COMPLETE,		// Path complete (found gold)
		PATH_ILLEGAL,		// Illegal path (out of bounds)
		PATH_IMPOSSIBLE		// No path (cannot solve)
	}
	
	// Callback method invoked for each recursive call
	public void sendStatus(SearchStatus eStatus, int row, int col);
	
}
