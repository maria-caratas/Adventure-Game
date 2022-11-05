public class Rouge extends Character{
    int weight;
    public Rouge(String name, int level, int experience)
    {
        this.name = name;
        this.level = level;
        this.experience = experience;
        weight = 400;
        earth = true;
        fire = false;
        ice = false;
        strength = level;
        dexterity = level*2;
        charisma = level/2;
        maxLife = 700;
        currentLife = 700;
        maxManna = 800;
        currentManna = 800;
    }
    @Override
    public void receiveDamage(int damage)
    {
        double var = Math.random();
        int chance = (int)Math.floor(var*2);
        if(chance<=0.5)
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
        int damage = level*10+(strength+charisma)/2+dexterity*10;
        double var = Math.random();
        int chance = (int)Math.floor(var*2);
        if(chance<=0.7)
            return 2*damage;
        return damage;
    }
}
