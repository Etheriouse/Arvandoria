import java.util.Random;

public class SimpleNoiseMap {

    public static void main(String[] args) {
        int width = 20;
        int height = 20;
        double[][] noiseGrid = generateSimpleNoise(width, height);
        double[][] normalizedGrid = normalizeNoise(noiseGrid);
        int[][] terrainMap = createTerrainMap(normalizedGrid, 0.4);

        // Display the map (optional)
        displayMap(terrainMap);
    }

    // Générer une grille de bruit simple
    public static double[][] generateSimpleNoise(int width, int height) {
        Random random = new Random();
        double[][] noiseGrid = new double[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                noiseGrid[y][x] = random.nextDouble();
            }
        }
        return noiseGrid;
    }

    // Normaliser les valeurs de bruit
    public static double[][] normalizeNoise(double[][] noiseGrid) {
        int height = noiseGrid.length;
        int width = noiseGrid[0].length;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        // Trouver les valeurs min et max
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (noiseGrid[y][x] < min) {
                    min = noiseGrid[y][x];
                }
                if (noiseGrid[y][x] > max) {
                    max = noiseGrid[y][x];
                }
            }
        }

        // Normaliser les valeurs entre 0 et 1
        double[][] normalizedGrid = new double[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                normalizedGrid[y][x] = (noiseGrid[y][x] - min) / (max - min);
            }
        }
        return normalizedGrid;
    }

    // Créer la carte du terrain
    public static int[][] createTerrainMap(double[][] noiseGrid, double waterLevel) {
        int height = noiseGrid.length;
        int width = noiseGrid[0].length;
        int[][] terrainMap = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (noiseGrid[y][x] < waterLevel) {
                    terrainMap[y][x] = 0; // 0 représente l'eau
                } else {
                    terrainMap[y][x] = 1; // 1 représente la terre
                }
            }
        }
        return terrainMap;
    }

    // Afficher la carte (optionnel)
    public static void displayMap(int[][] terrainMap) {
        int height = terrainMap.length;
        int width = terrainMap[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (terrainMap[y][x] == 0) {
                    System.out.print("~ "); // Eau
                } else {
                    System.out.print("# "); // Terre
                }
            }
            System.out.println();
        }
    }
}