import java.util.ArrayList;

public class Inventory {
  ArrayList<Potion> potionsList = new ArrayList<Potion>();
  int maxWeight;
  int money;
  public Inventory()
  {
    maxWeight = 1000;
    money = 1000;
  }

  public void addPotion(Potion p)
  {
    potionsList.add(p);
  }

  public void removePotion(Potion p)
  {
    potionsList.remove(p);
  }


  public int weightCalcul()
  {
    int weight = 0;
    for(Potion p:potionsList)
    {
          weight = weight + p.getPrice();
    }
    return maxWeight - weight;
  }

  public int countManaPotions()
  {
    int count = 0;
    for(Potion potion:potionsList)
      if(potion.toString().contains("ManaPotion"))
        count ++;
      return count;
  }
  public int countHealthPotions()
  {
    int count = 0;
    for(Potion potion:potionsList)
      if(potion.toString().contains("HealthPotion"))
        count ++;
    return count;
  }

  public void showInventory()
  {
    System.out.println("Inventory:");
    System.out.println("Maximum wieght: " + maxWeight);
    System.out.println("Money: " + money);
    System.out.println("Mana Potions: " + countManaPotions() + " Health Potions: " + countHealthPotions());
  }
}
