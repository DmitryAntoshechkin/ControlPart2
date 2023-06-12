

import java.util.Scanner;

public class View {

    private Actions actions;

    public View(Actions actions) {
        this.actions = actions;
    }


    public void run() throws Exception {
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            String id;
            switch (com) {
                case ADD: // Добавление игрушки
                    System.out.println("Добавлене игрушки");
                    id = prompt("Идентификатор: ");
                    String name = prompt("Название: ");
                    Integer quantity = new Integer(prompt("Количество: "));
                    Integer chance = new Integer(prompt("Шанс выпадания(%): "));
                    actions.AddToy(new Toy(id, name, quantity, chance));
                    break;
                case READ: // Поиск игрушки
                    id = prompt("Идентификатор игрушки: ");
                    try {
                        Toy toy = actions.findToy(id);
                        System.out.println(toy);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case CHANCE: //Изменение шанса выдачи
                    id = prompt("Идентификатор игрушки: ");
                    try {
                        Toy toy = actions.findToy(id);
                        System.out.println(toy);
                        actions.ChangeChance(toy, new Integer(prompt("Введиите новый шанс выпадания: ")));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST: //Список всех игрушек
                    actions.printToys();
                    break;
                case LOTTERY: //Определение выигрышной игрушки
                    System.out.println("Выигрышная игрушка: ");
                    System.out.println(actions.toyLottery().getName());
                    break;
                case GET: //Выдача игрушки
                    Toy prize = actions.getPrize();
                    if (prize != null){
                        System.out.println("Выдана игрушка: "+prize.getName()+". Осталось: "+prize.getQuantity()+" штук.\n" );
                    }else System.out.println("Извините, игрушек для выдачи нет");
                    break;
                case QUANTITY:
                    id = prompt("Идентификатор игрушки: ");
                    try {
                        Toy toy = actions.findToy(id);
                        System.out.println(toy);
                        actions.AddQuantity(toy, new Integer(prompt("Сколько добавить игрушек?: ")));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;



            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
