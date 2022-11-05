public class ManaPotion implements Potion{
    int price;
    int weight;
    int regenerationValue;

    public ManaPotion(int price, int weight, int regenerationValue)
    {
        this.price = price;
        this.weight = weight;
        this.regenerationValue = regenerationValue;
    }
    @Override
    public int usePotion(int currentManna)
    {
        return currentManna + regenerationValue;

    }
    @Override
    public int getPrice()
    {
        return price;
    }
    @Override
    public int getRegenerationValue()
    {
        return regenerationValue;

    }
    @Override
    public int getWeight()
    {
        return weight;

    }
}
