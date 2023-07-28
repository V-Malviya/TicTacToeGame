package Model;

public class Cell {
    private int row;
    private int col;
    private CellStatus cellStatus;
    private Player player;
    Cell(int r,int c)
    {
        this(r,c,null,CellStatus.EMPTY);
    }
    Cell(int r,int c, Player p,CellStatus status)
    {
        this.row=r;
        this.col=c;
        this.player=p;
        this.cellStatus=status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
