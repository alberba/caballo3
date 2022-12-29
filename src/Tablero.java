import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel {
    int[][] board;
    boolean [][] visited;
    int size;
    int soluciones;
    boolean solucionEncontrada;
    static int maxSoluciones = 3;
    private static final int[][] MOVIMIENTOS = {{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};

    public Tablero(int n, int x, int y){
        board = new int[n][n];
        visited = new boolean[n][n];
        size = n;
        solucionEncontrada = false;
    }

    private boolean zonaSegura(int x, int y){
        // Comprobamos que la casilla esta dentro del tablero y aun no ha sido visitada
        return (x >= 0 && y >= 0 && x < size && y < size && !visited[x][y]);
    }

    public void encontrarSolucion(JPanel panel, int x, int y, int numMove){
        //Comprobamos si hemos encontrado una solucion
        if (numMove == size*size){
            soluciones++;
            panel.repaint();
            solucionEncontrada = true;
        }

        for (int i = 0; i < 8; i++){
            int tempX = x + MOVIMIENTOS[i][0];
            int tempY = y + MOVIMIENTOS[i][1];
            if (zonaSegura(tempX, tempY)){
                visited[tempX][tempY] = true;
                board[tempX][tempY] = numMove + 1;
                encontrarSolucion(panel, tempX, tempY, numMove + 1);
                // se comprueba si no se ha encontrado solucion
                if (!solucionEncontrada){
                    visited[tempX][tempY] = false;
                    board[tempX][tempY] = 0;
                }else{
                    return;
                }
            }

        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int tile = 800 / size;
        boolean temp = false;
        boolean temp2;
        //dibujamos el tablero
        for (int i = 0; i < size; i++){
            temp = !temp;
            temp2 = temp;
            for (int j = 0; j < size; j++){
                if (temp2){
                    g.setColor(Color.WHITE);
                }else{
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j*tile, i*tile, tile, tile);
                temp2 = !temp2;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (visited[i][j]) {
                    g.setColor(Color.RED);
                    g.drawString(String.valueOf(board[i][j]), j * tile + tile / 2, i * tile + tile / 2);
                }
            }
        }
    }
}
