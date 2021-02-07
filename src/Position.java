public enum Position{
    start('-'), way('x'), end('+'), empty(' ');

    private final char letter;

    private Position(char letter) {
        this.letter = letter;
    }

    public char getLetter(){
        return letter;
    }
}
