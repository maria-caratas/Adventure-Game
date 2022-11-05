public abstract class Character extends Entity {
    String name;
    int Ox, Oy;
    int experience;
    int level;
    int strength;
    int charisma;
    int dexterity;
    //Metoda care vreifica daca se poate cumpara potiunea primita ca parametru
    public boolean buyPotion(Inventory inventory, Potion p)
    {
        if(inventory.weightCalcul()>=p.getWeight() && inventory.money>=p.getPrice())
            return true;
        return false;
    }
}
