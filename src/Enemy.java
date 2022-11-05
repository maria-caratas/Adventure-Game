import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends Entity implements CellElement{
    ArrayList<Spell> spells = new ArrayList<Spell>();

    public Enemy()
    {
        //Setam viata in [100,1000] si mana in [50,900]
        int minLife = 100, maxLife = 1000, minManna = 50, maxManna = 900;
        double var = Math.random();
        currentLife = (int)Math.floor(var*(maxLife-minLife+1)+minLife);
        currentManna = (int)Math.floor(var*(maxManna-minManna+1)+minManna);
        fire = ThreadLocalRandom.current().nextBoolean();
        ice = ThreadLocalRandom.current().nextBoolean();
        earth = ThreadLocalRandom.current().nextBoolean();
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
    }
    @Override
    public int getDamage()
    {
        int min = 100, max = 150;
        double aux = Math.random();
        int damage = (int)Math.floor(aux*(max-min+1)+min);
        double var = Math.random();
        int chance = (int)Math.floor(var*2);
        if(chance<=0.5)
            return 2*damage;
        return damage;
    }

    @Override
    public java.lang.Character toCharatacter() {
        return 'E';
    }
}
