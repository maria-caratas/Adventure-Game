public class Warrior extends Character{
    int weight;
    public Warrior(String name, int level, int experience)
    {
        this.name = name;
        this.experience = experience;
        this.level = level;
        weight = 995;
        fire = true;
        ice = false;
        earth = false;
        strength = level*2;
        dexterity = level;
        charisma = level/2;
        maxLife = 900;
        currentLife = 200;
        maxManna = 900;
        currentManna = 9000;
    }
    @Override
    public void receiveDamage(int damage) {
        double var = Math.random();
        int chance = (int)Math.floor(var*2);
        if(chance<=0.6)
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
        int damage = level*10+(dexterity+charisma)/2+charisma*10;
        double var = Math.random();
        int chance = (int)Math.floor(var*2);
        if(chance<=0.5)
            return 2*damage;
        return damage;
    }
}
