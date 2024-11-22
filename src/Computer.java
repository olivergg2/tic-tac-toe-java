public class Computer extends Player {
    public Computer(char character) {
        super(character, "Dator", new ComputerMoveStrategy());
    }
}
