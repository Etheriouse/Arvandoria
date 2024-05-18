public class PathFinding {

  public static void main(String[] args) {
    int t[][] = generationTerrain(10, 10);
    t[1][4] = 7;
    t[8][6] = 4;
    show(t);
    while (t != null) {
      t = move(calculateDistance(t), t);
      show(t);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.out.println(e);
      }
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }
  }

  public static int[][] move(int t[][], int map[][]) {
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

  public static void show(int t[][]) {
    for (int[] is : t) {
      for (int i : is) {
        if (i < 9) {
          System.out.print(i + "  ");
        } else {
          System.out.print(i + " ");
        }
      }
      System.out.println();
    }
  }

  public static int[][] generationTerrain(int x, int y) {
    int t[][] = {
        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 1, 0, 7, 0, 0, 1, 0, 0},
        {1, 1, 1, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
        {1, 1, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 1, 0, 0, 1, 0, 1, 1, 1, 0},
        {0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    return t;
  }

  public static int[][] calculateDistance(int t[][]) {
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

  public static int[] find(int t[][], int... value) {
    int valeur = 7;
    if (value.length != 0) {
      valeur = value[0];
    }
    for (int i = 0; i < t.length; i++) {
      for (int j = 0; j < t[i].length; j++) {
        if (t[i][j] == valeur) {
          return new int[] {i, j};
        }
      }
    }
    return new int[] {0, 0};
  }
}
