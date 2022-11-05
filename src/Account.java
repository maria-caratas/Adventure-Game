import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class Account {
    public Account() throws  InvalidCommandException
    {
        Characters = new ArrayList<Character>();
    }
    Information information;
    List<Character> Characters;
    Integer playedGames;
    public void setCharacters(ArrayList<Character> Characters)
    {
        this.Characters = Characters;
    }
    public List<Character> getCharacters()
    {
        return Characters;
    }
    static class Information{
        private Credentials credentials; //required
        private SortedSet<String> favoriteGames;
        private String name; //required
        private String country;
        private String maps;

        public Information(String name, Credentials credentials, SortedSet<String> favoriteGames, String country, String maps) throws  InvalidCommandException
        {
            this.name  = name;
            this.credentials = credentials;
            this.favoriteGames = favoriteGames;
            this.country = country;
            this.maps = maps;
        }

        public void setName(String name)
       {
           this.name = name;
       }
       public void setCredentials(Credentials credentials)
       {
           this.credentials = credentials;
       }
       public void setFavoriteGames(SortedSet<String> favoriteGames)
       {
           this.favoriteGames = favoriteGames;
       }
       public void setCountry(String country)
       {
           this.country = country;
       }
       public void setMaps(String maps)
       {
           this.maps = maps;
       }
       public String getName()
        {
            return name;
        }
        public String getCountry()
        {
            return country;
        }
        public SortedSet getFavoriteGames()
        {
            return favoriteGames;
        }
        public Credentials getCredentials()
        {
            return credentials;
        }
        public String getMaps()
        {
            return maps;
        }
        public static class InformationBuilder
        {
            private Credentials credentials; //required
            private SortedSet<String> favoriteGames;
            private String name; //required
            private String country;
            private String maps;
            public InformationBuilder(String name, Credentials credentials)
            {
                this.name = name;
                this.credentials = credentials;
            }
            public InformationBuilder setFavoriteGames (SortedSet favoriteGames)
            {
                this.favoriteGames = favoriteGames;
                return this;
            }
            public InformationBuilder setCountry(String country)
            {
                this.country = country;
                return this;
            }
            public InformationBuilder setMaps(String maps)
            {
                this.maps = maps;
                return this;
            }
            //Returneaza obiectul final, de tip Information
            public Information build() throws InvalidCommandException
            {
                return new Information(name,credentials,favoriteGames,country,maps);
            }
        }
    }
}
