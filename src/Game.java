import json.JSONArray;
import json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static java.lang.System.exit;


public class Game {
    private static Game instance = null;
    private Game()
    {
        accounts = new ArrayList<Account>();
        stories = new HashMap<TypeOfCell,List<String>>();
    }
    List<Account> accounts;
    HashMap<TypeOfCell,List<String>> stories;
    Shop shop = new Shop();

    public static Game getInstance()
    {
        if(instance == null)
        {
            instance = new Game();
        }
        return instance;
    }

    public static String readFile(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    //Creeaza o lista cu toate conturile
    public void createAccounts(String file) throws Exception
    {
        String json = readFile(file);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray accounts = jsonObject.getJSONArray("accounts");
        //Citeste fiecare cont
        for (int i = 0; i<accounts.length();i++)
        {
            Account account = new Account();
            JSONObject jsonObject1 = (JSONObject) accounts.get(i);
            JSONArray characters = jsonObject1.getJSONArray("characters");
            ArrayList<Character> listOfCharacters = new ArrayList<Character>();

            //Citeste personajele
            for(int j = 0; j< characters.length();j++)
            {
                JSONObject object = (JSONObject) characters.get(j);
                String type = object.getString("profession");
                String name = object.getString("name");
                int level = object.getInt("level");
                int experience = object.getInt("experience");
                CharactersFactory aux = new CharactersFactory();
                if(type.equals("Warrior"))
                {
                    Character ch = aux.createCharacter(CharactersFactory.CharacterType.Warrior,name,experience,level);
                    listOfCharacters.add(ch);
                }
                if(type.equals("Mage"))
                {
                    Character ch = aux.createCharacter(CharactersFactory.CharacterType.Mage,name,experience,level);
                    listOfCharacters.add(ch);
                }
                if(type.equals("Rouge"))
                {
                    Character ch = aux.createCharacter(CharactersFactory.CharacterType.Rouge,name,experience,level);
                    listOfCharacters.add(ch);
                }
            }
            account.setCharacters(listOfCharacters);

            //Se seteaza credentialele
            JSONObject credentialsObject = jsonObject1.getJSONObject("credentials");
            Credentials credentials = new Credentials();
            credentials.setEmail(credentialsObject.getString("email"));
            credentials.setPassword(credentialsObject.getString("password"));

            //Se seteaza lista jocurilor preferate
            JSONArray arr = jsonObject1.getJSONArray("favorite_games");
            SortedSet<String> favoriteGames = new TreeSet<>();
            for(int j = 0; j < arr.length(); j++)
                favoriteGames.add((String) arr.get(j));
            Account.Information.InformationBuilder builder = new Account.Information.InformationBuilder(jsonObject1.getString("name"),credentials);
            builder.setFavoriteGames(favoriteGames);
            builder.setCountry(jsonObject1.getString("country"));
            builder.setMaps(jsonObject1.getString("maps_completed"));
            account.information = builder.build();

            //Adaugam contul in lista
            this.accounts.add(account);
        }
    }

    //Afiseaza toate conturile disponibile pentru autentificare
    public void listAccounts()
    {
        for(Account account:accounts)
        {
            System.out.println(account.information.getName());
        }
    }

    //Creeaza un HashMap cu povesti
    public void createStories(String file) throws Exception
    {
        String json = readFile(file);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray storiesArray = jsonObject.getJSONArray("stories");
        List<String> element = new ArrayList<String>();
        for(int i=0; i<storiesArray.length();i++)
        {
            JSONObject jsonObject1 = (JSONObject) storiesArray.get(i);
            String content = jsonObject1.getString("value");
            String type = jsonObject1.getString("type");

            element.add(content);
            stories.put(TypeOfCell.ofString(type),element);
        }
    }

    public void run() throws  Exception
    {
        createAccounts("src/accounts.json");
        System.out.println("Accounts:");
        listAccounts();
        createStories("src/stories.json");
        System.out.println("Select an account: ");
        Scanner scanner = new Scanner(System.in);
        String personaj = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter the password: ");
        String password = scanner.nextLine();

        Character player = null;
        //Verificarea procesului de autentificare
        String character = "";
        for(Account a: accounts) {
            if (personaj.equals(a.information.getName()))
                if (email.equals(a.information.getCredentials().getEmail()))
                    if (password.equals(a.information.getCredentials().getPassword())) {
                        System.out.println("Successful login!");
                        System.out.println("Choose your character:");
                        for (Character c : a.Characters)
                            System.out.println(c.name);
                        System.out.println("Enter the character: ");
                        character = scanner.nextLine();

                        for (Character c : a.Characters) {
                            if (c.name.equals(character)) ;
                            player = c;
                        }
                    } else
                        System.out.println("Authentication failed! Please reintroduce the password.");
                else
                    System.out.println("Authentication failed! Please reintroduce the email.");
        }
        System.out.println("Game map:");
        Grid grid = new Grid();
        ArrayList<ArrayList<Cell>> map = grid.generateMap(5,5);
        grid.showMapAtTime(map);
        System.out.println("Start!");
        System.out.println("Press the P key to move trough the map");
        ArrayList<String> moves = new ArrayList<String>();
        moves.add("goEast");
        moves.add("goEast");
        moves.add("goEast");
        moves.add("buyManaPotion");
        moves.add("buyHealthPotion");
        moves.add("goEast");
        moves.add("goSouth");
        moves.add("goSouth");
        moves.add("goSouth");
        moves.add("Battle");
        moves.add("goSouth");
        int curentmove = 0;
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        while(move(map.get(grid.current.Ox).get(grid.current.Oy),sc,inventory).equals("P") && curentmove<11)
        {
            String action = moves.get(curentmove);
            if(action.equals("goEast"))
                grid.goEast();
            if(action.equals("goWest"))
                grid.goWest();
            if(action.equals("goNorth"))
                grid.goNorth();
            if(action.equals("goSouth"))
                grid.goSouth();
            if(action.equals("buyManaPotion"))
            {
                int index = 0;
                ManaPotion buy = (ManaPotion) shop.getPotion(index);
                if(player.buyPotion(inventory,buy)==true)
                {
                    inventory.addPotion(buy);
                    inventory.money = inventory.money - buy.getPrice();
                }
                else
                    System.out.println("You can't buy it!");
                inventory.showInventory();

            };
            if(action.equals("buyHealthPotion"))
            {
                int index = 0;
                HealthPotion buy = (HealthPotion) shop.getPotion(index);
                if(player.buyPotion(inventory,buy)==true)
                {
                    inventory.addPotion(buy);
                    inventory.money = inventory.money - buy.getPrice();
                }
                else
                    System.out.println("You can't buy it!");
                inventory.showInventory();

            }
            if(action.equals("Battle"))
            {
                map.get(grid.current.Ox).get(grid.current.Oy).object = new Enemy();
                Enemy enemy = (Enemy) map.get(grid.current.Ox).get(grid.current.Oy).object;
                boolean ok = true;
                while(battle(enemy,player,ok,inventory)==0)
                {
                    ok = !ok;
                }

            }
            map.get(grid.current.Ox).get(grid.current.Oy).visited = true;
            grid.showMapAtTime(map);
            curentmove++;
            System.out.println("Press the P key to move trough the map");
        }
        System.out.println("Game over!");}



    //Arata lista de optiuni disponibile si preia urmatoarea comanda
    public String move(Cell cell, Scanner sc, Inventory inventory)
    {
        if(cell.cellType.equals(TypeOfCell.EMPTY))
        {
            //20% sanse de a gasi bani
            int min = 0, max = 100;
            int random = (int)Math.floor(Math.random()*(max-min+1)+min);
            if(random<=20)
                inventory.money = inventory.money + 100;
           System.out.println(stories.get(TypeOfCell.EMPTY).get(0));
           stories.get(TypeOfCell.EMPTY).remove(0);
        }
        else
        if(cell.cellType.equals(TypeOfCell.SHOP))
        {
            System.out.println(stories.get(TypeOfCell.SHOP).get(31));
            shop.listShopOptions();
        }
        else
        if(cell.cellType.equals(TypeOfCell.ENEMY))
        {
            System.out.println(stories.get(TypeOfCell.ENEMY).get(27));

        }
        else
        {
            System.out.println(stories.get(TypeOfCell.FINISH).get(37));
            System.out.println("Game over!");
            exit(0);
        }
        String nextCommand = sc.nextLine();
        return nextCommand;
    }

    //Exista o sansa de 75% sansa de atac normal si 25% sansa sa foloseasca o abilitatea la o runda
    public int battle(Enemy enemy, Character player, boolean ok, Inventory inventory)
    {
        int finish = 0;
        if(ok==true)
        {
            //Vedem daca exista mana pt a utiliza abilitati si in caz afirmativ le folosim
            if(player.earth == true && player.useAbility("earth",enemy)==0);
            else
            if(player.fire == true &&player.useAbility("fire",enemy)==0);

            else
            if(player.ice == true && player.useAbility("ice",enemy)==0);
            //Se folosesc potiunile din inventar
            else if(inventory.countManaPotions() != 0)
            {
                Potion p = new ManaPotion(40,50,80);
                inventory.removePotion(p);
                player.currentLife = player.currentLife + p.usePotion(player.currentLife);
            }
            else
                //Atacuri normale
            if(inventory.countHealthPotions()!=0)
            {
                Potion p = new HealthPotion(70,80,100);
                inventory.removePotion(p);
                player.currentLife = player.currentLife + p.usePotion(player.currentLife);
            }
            else
                enemy.receiveDamage(player.getDamage());
        }
        //Inamicul ataca
        if(ok==false)
        {
            int min = 0, max = 100;
            int random = (int)Math.floor(Math.random()*(max-min+1)+min);
            //75% sanse de atac normal
            if(random<=75)
            player.receiveDamage(enemy.getDamage());
            //25% sanse sa se foloseasca o abilitate
            else {
                if (enemy.earth == true && enemy.useAbility("earth", player) == 0) ;
                else if (enemy.fire == true && enemy.useAbility("fire", player) == 0) ;
                else if (enemy.ice == true && enemy.useAbility("ice", player) == 0) ;
            }
        }
        if(player.currentLife<=0)
        {
            finish = 1;
            System.out.println("You lost");
        }
        if(enemy.currentLife <= 0)
        {
            int min = 0, max = 100;
            int random = (int)Math.floor(Math.random()*(max-min+1)+min);
            if(random<=80)
                inventory.money = inventory.money + 100;
            finish = 1;
            System.out.println("You won");
        }
        return finish;
    }
}
