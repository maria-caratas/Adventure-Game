public abstract class Entity{

    int currentLife, maxLife;
    int currentManna, maxManna;
    boolean fire, ice, earth;

    public void lifeRegeneration(int value)
    {
        if(currentLife + value > maxLife)
            currentLife = maxLife;
        else
            currentLife = currentLife + value;
    }

    public void mannaRegeneration(int value)
    {
        if(currentManna + value > maxManna)
            currentManna = maxManna;
        else
            currentManna = currentManna + value;
    }

    public int useAbility(String abbility, Entity opponent)
    {
        Ice ice = new Ice();
        Fire fire = new Fire();
        Earth earth = new Earth();
        if(abbility.equals("ice"))
        {
            if(currentManna - ice.costManna <=0)
            {
                return 1;
            }
            else
            {
                currentManna = currentManna - ice.costManna;
                opponent.receiveDamage(ice.valueOfDamage);
            }


        }
        if(abbility.equals("fire"))
        {
            if(currentManna - fire.costManna <=0)
            {
                return 1;
            }
            else
            {
                opponent.receiveDamage(fire.valueOfDamage);
                currentManna = currentManna- fire.costManna;
            }

        }
        if(abbility.equals("earth"))
        {
            if(currentManna - earth.costManna >=0)
            {
                return 1;
            }
            else {
                currentManna = currentManna - earth.costManna;
                opponent.receiveDamage(earth.valueOfDamage);
            }
        }
        return 0;
    }
    public abstract void receiveDamage(int damage);
    public abstract int getDamage();


}
