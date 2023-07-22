import java.util.Scanner;

public class TresEnRaya {

    private char jugadorActual;
    private char[][] tablero;

    //Siempre empezara la X SI SE QUIERE EMPEZAR CON LA O , cambiar el jugadorActual en el constructor
    public TresEnRaya() {
        tablero = new char[3][3];
        jugadorActual = 'X';
        iniciarTablero();
    }

    private void iniciarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    private void imprimirTablero() {
      ;
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println();

        }
    }

    private boolean tableroLleno() {
        //Verifica si el tablero esta lleno de X y O
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean verificarVictoria() {
        //Verifica si hay algun ganador ya sea por el criterio de fila , columna o diagonal
        return verificarFila() || verificarColumna() || verificarDiagonal();
    }

    private boolean verificarFila() {
        //Verifica fila por fila si hay 3 caracteres seguidos del mismo caracter
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == jugadorActual && tablero[i][1] == jugadorActual && tablero[i][2] == jugadorActual) {
                return true;
            }
        }
        return false;
    }

    private boolean verificarColumna() {
        //Lo mismo que verificar fila pero en columna
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] == jugadorActual && tablero[1][i] == jugadorActual && tablero[2][i] == jugadorActual) {
                return true;
            }
        }
        return false;
    }

    private boolean verificarDiagonal() {
        //Lo mismo que verificar fila y columna pero en diagonal
        return (tablero[0][0] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][2] == jugadorActual) ||
                (tablero[0][2] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][0] == jugadorActual);
    }

    //Validar movimiento
    private boolean movValido(int row, int column) {
        return row >= 0 && row < 3 && column >= 0 && column < 3 && tablero[row][column] == '-';
    }

    private void cambiarJugador() {
        jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
    }

    private void Jugar() {
        imprimirTablero();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jugador " + jugadorActual + ", ingresa una fila (1-3) y una columna (1-3) separados por un espacio: ");
        int fila = scanner.nextInt() - 1;
        int columna = scanner.nextInt() - 1;

        if (movValido(fila, columna)) {
            tablero[fila][columna] = jugadorActual;
            if (verificarVictoria()) {
                imprimirTablero();
                System.out.println("Ganador : " + jugadorActual );
            } else if (tableroLleno()) {
                System.out.println("Empate!");
            } else {
                cambiarJugador();
                Jugar();
            }
        } else {
            System.out.println("Movimiento Invalido.");
            Jugar();
        }
    }

    public static void main(String[] args) {
        TresEnRaya game = new TresEnRaya();
        game.Jugar();
    }
}