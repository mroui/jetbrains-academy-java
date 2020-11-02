import java.util.*;

/**
 * Observable
 */
interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

/**
 * Concrete Observable
 */
class RockstarGames implements Observable {

    public String releaseGame;
    private final List<Observer> observers = new ArrayList<>();

    public void release(String game) {
        this.releaseGame = game;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            System.out.println("Inform message to : " + observer);
            observer.update(releaseGame);
        }
    }
}

/**
 * Observer
 */
interface Observer {

    void update(String domain);
}

/**
 * Concrete Observer
 */
class Gamer implements Observer {

    private final String name;
    private final String reaction;
    private final Set<String> games = new HashSet<>();

    public Gamer(String name, String reaction) {
        this.reaction = reaction;
        this.name = name;
    }

    public void buyGame(String game) {
        games.add(game);
        System.out.println(name + " says: " + reaction);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void update(String game) {
        buyGame(game);
    }
}

/**
 * Main Class
 */

public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        String game;

        RockstarGames rockstarGames = new RockstarGames();

        Gamer garry = new Gamer("Garry Rose", "I want to pre-order");
        Gamer peter = new Gamer("Peter Johnston", "Pinch me...");
        Gamer helen = new Gamer("Helen Jack", "Jesus, it's new game from Rockstar!");

        rockstarGames.addObserver(garry);
        rockstarGames.addObserver(peter);
        rockstarGames.addObserver(helen);

        game = scanner.nextLine();
        System.out.println("It's happened! RockstarGames releases new game - " + game + "!");

        rockstarGames.release(game);

        scanner.close();
    }
}