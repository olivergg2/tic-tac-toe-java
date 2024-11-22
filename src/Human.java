public class Human extends Player {
    public Human(char character, String name) {
        super(character, name, new HumanMoveStrategy());
    }
}

