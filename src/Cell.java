public class Cell {
    int Ox, Oy;
    TypeOfCell cellType;
    CellElement object;
    boolean visited;
    public Cell()
    {

    }
    public Cell(int Ox, int Oy, TypeOfCell cellType, boolean visited)
    {
        this.Ox = Ox;
        this.Oy = Oy;
        this.cellType = cellType;
        this.visited = visited;
    }

}




