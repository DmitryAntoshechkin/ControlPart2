public class Toy {

    private String id;

    private String name;

    private Integer quantity;

    private  Integer chance;

    public Toy(String id, String name, Integer quantity, Integer chance) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.chance = chance;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getChance() {
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
    }

    @Override
    public String toString() {
        return String.format("Идентификатор: %s\nНазвание: %s,\nКоличество: %s,\nВероятность: %s%%", id, name, quantity, chance);
    }
}
