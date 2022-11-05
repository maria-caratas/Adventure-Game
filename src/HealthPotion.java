public class HealthPotion implements Potion{
    private int price;
    private int weight;
    private int regenerationValue;
    public HealthPotion(int price, int weight, int regenerationValue)
    {
        this.price = price;
        this.weight = weight;
        this.regenerationValue = regenerationValue;
    }
    @Override
    public int usePotion(int currentLife)
    {
        return currentLife + regenerationValue;
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
