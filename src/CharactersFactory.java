public class CharactersFactory {
    public enum CharacterType
    {
        Warrior,
        Mage,
        Rouge;

        public static CharacterType ofString(String str)
        {
            return switch(str)
                    {
                        case "Warrior" -> Warrior;
                        case "Mage" -> Mage;
                        default -> Rouge;
                    };
        }
    }
    public static Character createCharacter(CharacterType characterType, String name, int experience, int level)
    {
        switch(characterType)
        {
            case Warrior:
                return new Warrior(name,level,experience);
            case Mage:
                return new Mage(name,level,experience);
            case Rouge:
                return new Rouge(name,level,experience);
        }
        return null;
    }
}
