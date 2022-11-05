public enum TypeOfCell {
    EMPTY,
    ENEMY,
    SHOP,
    FINISH;

    public static TypeOfCell ofString(String str)
    {
        return switch(str)
                {
                    case "EMPTY" -> EMPTY;
                    case "SHOP" -> SHOP;
                    case "FINISH" -> FINISH;
                    default -> ENEMY;
                };
    }

};

