package Strategy.GameWinning;

import Model.Move;

public interface checkWinnerStrategy {
    public boolean winChecker(Move move);
    public void updateMaps(char ch,int r,int c);
    public void updateMapUndo(char ch,int r,int c);
}
