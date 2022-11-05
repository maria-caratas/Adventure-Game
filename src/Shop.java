import java.util.ArrayList;

public class Shop implements CellElement{
    ArrayList<Potion> potions = new ArrayList<Potion>();

    public Shop()
    {
        potions.add(new ManaPotion(40,50,80));
        potions.add(new HealthPotion(70,80,100));
    }
    void generatePotions()
    {
        potions.add(new HealthPotion(100,100,100));
        potions.add(new ManaPotion(20,20,20));
    }

    Potion selectPotion(int index)
    {
        Potion x= potions.get(index);
        potions.remove(index);
        return x;
    }

    public Potion getPotion(int index)
    {
        Potion x = selectPotion(index);
        potions.remove(x);
        return x;
    }

    public int getManaPotions()
    {
        int count = 0;
        for(Potion potion:potions)
            if(potion.toString().contains("ManaPotion"))
                count++;
        return count;
    }

    public int getHealthPotions()
    {
        int count = 0;
        for(Potion potion:potions)
            if(potion.toString().contains("HealthPotion"))
                count++;
        return count;
    }

    public void listShopOptions()
    {
        System.out.println("Shop:");
            System.out.println("Mana Potions: " + getManaPotions()+ " Health Potions: " + getHealthPotions());

    }
    @Override
    public java.lang.Character toCharatacter()
    {
        return 'S';
    }
}
