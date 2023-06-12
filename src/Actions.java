import java.time.LocalDateTime;
import java.util.*;



public class Actions {

    private  ToyMapper mapper = new ToyMapper();
    private FileOperation fileOperation;

    private ArrayDeque<String> prizes = new ArrayDeque<>();
    public Actions(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }



    public List<Toy> getToys(){
        List<String> lines = fileOperation.readAllLines();
        List<Toy> toys = new ArrayList<>();
        for (String line : lines){
            toys.add(mapper.map(line));
        }
        return toys;

    }

    public void AddToy (Toy toy){
        List<Toy> toys = getToys();
        toys.add(toy);
        List<String> lines = new ArrayList<>();
        for (Toy item: toys) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
     }

    public void ChangeChance (Toy toy, Integer chance){
        List<Toy> toys = getToys();
        for (Toy item: toys){
            if (item.getId().equals(toy.getId())){
                item.setChance(chance);
                System.out.println(item);
            }
        }
        List<String> lines = new ArrayList<>();
        for (Toy item: toys) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }


    public void AddQuantity (Toy toy, Integer quantity){
        List<Toy> toys = getToys();
        for (Toy item: toys){
            if (item.getId().equals(toy.getId())){
                item.setQuantity(item.getQuantity()+quantity);
                System.out.println(item);
            }
        }
        List<String> lines = new ArrayList<>();
        for (Toy item: toys) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }




    public Toy findToy(String toyId) throws Exception {
        List<Toy> toys = getToys();
        for (Toy toy : toys) {
            if (toy.getId().equals(toyId)) {
                return toy;
            }
        }
        throw new Exception("Такой игрушки нет");
    }

    public void printToys(){
        List<Toy> toys = getToys();
        for (Toy toy : toys) {
            System.out.println(toy);
            System.out.println();
        }

    }
    public Toy toyLottery() throws Exception{
        List<Toy> toys = getToys();
        Random rnd = new Random();
        Toy winner = toys.get(0);
        double maxScore = 0;
        for (Toy toy: toys){
            if (toy.getQuantity() > 0){
                double score = toy.getChance() * rnd.nextDouble();
                if (score > maxScore){
                    winner = toy;
                    maxScore = score;
                }
            }
        }
        prizes.addLast(winner.getId());
        return winner;
    }

    public Toy getPrize(){
        List<Toy> toys = getToys();
        String id = prizes.pollFirst();
        if (id != null){
            for (Toy toy: toys){
                if (toy.getId().equals(id)){
                   if(toy.getQuantity() > 0){
                        String log = String.format("%s Выдана игрушка %s", String.format("%1$tb %1$te, %1$tY %1$tI:%1$tM %1$Tp", LocalDateTime.now()), toy.getName());
                        fileOperation.savePrizeLog(log);
                        toy.setQuantity(toy.getQuantity()-1);
                        List<String> lines = new ArrayList<>();
                         for (Toy item: toys) {
                             lines.add(mapper.map(item));
                         }
                         fileOperation.saveAllLines(lines);

                        return toy;
                   }
                }
            }
        }
        return null;
    }
}
