public class ToyMapper {

        public String map(Toy toy) {
            return String.format("%s,%s,%s,%s", toy.getId(), toy.getName(), toy.getQuantity(), toy.getChance() );
        }

        public Toy map(String line) {
            String[] lines = line.split(",");
            return new Toy(lines[0], lines[1], new Integer(lines[2]), new Integer(lines[3]));
        }
    }

