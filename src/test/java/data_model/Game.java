package data_model;

import java.util.Objects;

/**
 * @author Pavel Romanov 20.12.2022
 */
public class Game {
    private String name;
    private String releaseDate;
    private String price;

    public Game(String name, String releaseDate, String price) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name) &&
                Objects.equals(releaseDate, game.releaseDate) &&
                Objects.equals(price, game.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseDate, price);
    }
}
