public class Mage extends Character{
    int weight;
    public Mage(String name, int level, int experience)
    {
        this.name = name;
        this.level = level;
        this.experience = experience;
        weight = 80;
        ice = true;
        earth = false;
        fire = false;
        strength = level/2;
        dexterity = level;
        charisma = level*2;
        maxLife = 1000;
        currentLife = 1000;
        maxManna = 1000;
        currentManna = 1000;
    }
    @Override
    public void receiveDamage(int damage)
    {
        double var = Math.random();
        int chance = (int)Math.floor(var*2);
        if(chance<=0.7)
        {
            currentLife = currentLife - damage;
        }
        else
        {
            currentLife = currentLife - damage/2;
        }

    }
    @Override
    public int getDamage()
    {
        int damage = level*10+(strength+dexterity)/2+charisma*10;
        double var = Math.random();
        int chance = (int)Math.floor(var*2);
        if(chance<=0.6)
            return 2*damage;
        return damage;
    }

}
