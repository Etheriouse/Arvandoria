import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PathFinding {

    public static void main(String[] args) {

        int t[][] = generationTerrain(10, 10);
        t[1][4] = 7;
        t[8][6] = 4;
        while (t != null) {
            t = move(generateDistanceGrid(t, 1, 4), t);
            show(t);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    static class Cell {
        int x, y, distance;

        Cell(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    private static final int[] dX = { -1, 1, 0, 0 };
    private static final int[] dY = { 0, 0, -1, 1 };
    private static final int OBSTACLE = 1;

    private static int[][] generateDistanceGrid(int[][] grid, int startX, int startY) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] distances = new int[rows][cols];
        for (int[] row : distances) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distances[startX][startY] = 0;

        PriorityQueue<Cell> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.distance));
        priorityQueue.add(new Cell(startX, startY, 0));

        while (!priorityQueue.isEmpty()) {
            Cell current = priorityQueue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dX[i];
                int newY = current.y + dY[i];

                if (isValid(newX, newY, rows, cols) && grid[newX][newY] != OBSTACLE) {
                    int newDist = current.distance + 1; // assuming uniform weight for simplicity
                    if (newDist < distances[newX][newY]) {
                        distances[newX][newY] = newDist;
                        priorityQueue.add(new Cell(newX, newY, newDist));
                    }
                }
            }
        }

        return distances;
    }

    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    private static int[][] move(int t[][], int map[][]) {
        int x = find(map, 4)[1];
        int y = find(map, 4)[0];
        int deplace_x = 0;
        int deplace_y = 0;
        int min = Integer.MAX_VALUE;
        for (int i = y - 1; i < y + 2; i++) {
            for (int j = x - 1; j < x + 2; j++) {
                if (!(i < 0 || j < 0 || i > map.length - 1 || j > map[i].length - 1)) {
                    if (map[i][j] == 0) {
                        if (min > t[i][j]) {
                            min = t[i][j];
                            deplace_x = j;
                            deplace_y = i;
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println();

        int temp = map[deplace_y][deplace_x];
        map[deplace_y][deplace_x] = map[y][x];
        map[y][x] = temp;

        return map;
    }

    private static void show(int t[][]) {
        for (int[] is : t) {
            for (int i : is) {
                if (i < 10) {
                    System.out.print(i + "  ");
                } else {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
    }

    private static int[][] generationTerrain(int x, int y) {
        int t[][] = {
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 7, 0, 0, 1, 0, 0 },
                { 1, 1, 1, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 1, 1, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 0, 0, 1, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        return t;
    }

    // Fonctionne sans obstacle, sinon ia debile
    @SuppressWarnings("unused")
    private static int[][] calculateDistance(int t[][]) {
        int x = find(t)[1];
        int y = find(t)[0];
        int tprime[][] = new int[t.length][t[0].length];
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                tprime[i][j] = Math.abs((Math.abs(j - x)) + (Math.abs(i - y)));
            }
        }
        return tprime;
    }

    private static int[] find(int t[][], int... value) {
        int valeur = 7;
        if (value.length != 0) {
            valeur = value[0];
        }
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                if (t[i][j] == valeur) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { 0, 0 };
    }
}
