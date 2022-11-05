import java.util.ArrayList;

public class Grid extends ArrayList{
    private static Grid instance = null;
    int length, width;
    Character character;
    Cell current ;
    public Grid()
    {
       current = new Cell();
    }

    public static Grid getInstance()
    {
        if(instance == null)
            return new Grid();
        return instance;
    }

    public ArrayList<ArrayList<Cell >> generateMap(int length, int width)
    {
        ArrayList<ArrayList<Cell>> map = new ArrayList<ArrayList<Cell>>(5);
        for(int i=0; i<5; i++)
            map.add(new ArrayList<Cell>());
        map.get(0).add(new Cell(0,0,TypeOfCell.ofString("EMPTY"), true));
        map.get(0).add(new Cell(0,1,TypeOfCell.ofString("EMPTY"),false));
        map.get(0).add(new Cell(0,2,TypeOfCell.ofString("EMPTY"),false));
        map.get(0).add(new Cell(0,3,TypeOfCell.ofString("SHOP"),false));
        map.get(0).add(new Cell(0,4,TypeOfCell.ofString("EMPTY"),false));
        map.get(1).add(new Cell(1,0,TypeOfCell.ofString("EMPTY"),false));
        map.get(1).add(new Cell(1,1,TypeOfCell.ofString("EMPTY"),false));
        map.get(1).add(new Cell(1,2,TypeOfCell.ofString("EMPTY"),false));
        map.get(1).add(new Cell(1,3,TypeOfCell.ofString("SHOP"),false));
        map.get(1).add(new Cell(1,4,TypeOfCell.ofString("EMPTY"),false));
        map.get(2).add(new Cell(2,0,TypeOfCell.ofString("SHOP"),false));
        map.get(2).add(new Cell(2,1,TypeOfCell.ofString("EMPTY"),false));
        map.get(2).add(new Cell(2,2,TypeOfCell.ofString("EMPTY"),false));
        map.get(2).add(new Cell(2,3,TypeOfCell.ofString("EMPTY"),false));
        map.get(2).add(new Cell(2,4,TypeOfCell.ofString("EMPTY"),false));
        map.get(3).add(new Cell(3,0,TypeOfCell.ofString("EMPTY"),false));
        map.get(3).add(new Cell(3,1,TypeOfCell.ofString("EMPTY"),false));
        map.get(3).add(new Cell(3,2,TypeOfCell.ofString("EMPTY"),false));
        map.get(3).add(new Cell(3,3,TypeOfCell.ofString("EMPTY"),false));
        map.get(3).add(new Cell(3,4,TypeOfCell.ofString("ENEMY"),false));
        map.get(4).add(new Cell(4,0,TypeOfCell.ofString("EMPTY"),false));
        map.get(4).add(new Cell(4,1,TypeOfCell.ofString("EMPTY"),false));
        map.get(4).add(new Cell(4,2,TypeOfCell.ofString("EMPTY"),false));
        map.get(4).add(new Cell(4,3,TypeOfCell.ofString("EMPTY"),false));
        map.get(4).add(new Cell(4,4,TypeOfCell.ofString("FINISH"),false));
        return map;
}

    public void showMapAtTime(ArrayList<ArrayList<Cell>> map)
    {
        String ans = "";
        for (ArrayList<Cell> row : map)
        {
            ans = "";
            for(Cell col:row)
            {
                if(col.visited == true)
                {
                    if(col.cellType.equals(TypeOfCell.EMPTY))
                        ans = ans +  "N ";
                    if(col.cellType.equals(TypeOfCell.ENEMY))
                        ans = ans +  "E ";
                    if(col.cellType.equals(TypeOfCell.SHOP))
                        ans = ans +  "S ";
                }
                else
                    ans = ans + "? ";
            }
            System.out.println(ans);
        }
    }

    public void goNorth()
    {
        if((current.Ox - 1) < width)
            current.Ox --;
        else
            System.out.println("Wrong!");
        current.visited = true;
    }

    public void goSouth()
    {
        if((current.Ox +1) > width)
            current.Ox ++;
        else
            System.out.println("Wrong!");
        current.visited = true;
    }

    public void goWest()
    {
        if((current.Oy - 1) < length)
            current.Oy --;
        else
            System.out.println("Wrong!");
        current.visited = true;
    }

    public void goEast()
    {
        if((current.Oy + 1) > length)
            current.Oy ++;
        else
            System.out.println("Wrong!");
     current.visited = true;
    }
}
