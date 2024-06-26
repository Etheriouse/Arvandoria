package Solide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PerlinNoise {

    static Random r = new Random();

    private static final int PERMUTATION[] = { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95,
            96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21,
            10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219,
            203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125,
            136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146,
            158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55,
            46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209,
            76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86,
            164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5,
            202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16,
            58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44,
            154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253,
            19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246,
            97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241,
            81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106,
            157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236,
            205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215,
            61, 156, 180 };

    private static final int[] p = new int[512];
    private static final double SCALE = 0.1;
    private static final double ISLAND_FACTOR = 1.5;
    private static List<Integer> pl = new ArrayList<>();

    static {
        for (int i : PERMUTATION) {
            pl.add(i);
        }

        Collections.shuffle(pl, r);

        // Dupliquer les valeurs pour les deuxièmes 256 indices
        for (int i = 0; i < 256; i++) {
            p[256 + i] = p[i] = pl.get(i);
        }
    }

    // private static void main(String[] args) {
    // System.out.println(PERMUTATION.length);
    // int width = 22;
    // int height = 22;
    // double scale = 0.15;
    // double[][] noiseGrid = generatePerlinNoise(width, height, scale);
    // double[][] normalizedGrid = normalizeNoise(noiseGrid);
    // int[][] terrainMap = createTerrainMap(normalizedGrid, 0.3); // 0.5
    // continental // 0.7 archipelle // 0.3 pangea

    // // Display the map (optional)
    // displayMap(terrainMap);
    // }

    /**
     *
     * @param width  Longueur de la carte
     * @param height Largeur de la carte
     * @param type   type de la carte
     * @note 0.2 pangea type, 0.4 continental type, 0.6 archipel type
     *
     * @return un tableau de int des continent et oceant selon le type choisi
     */
    public static int[][] generateProcedural(int width, int height, double type) {
        return createTerrainMap(normalizeNoise(generatePerlinNoise(width, height, 0.15)), type);
    }

    private static double[][] generatePerlinNoise(int width, int height, double scale) {
        double[][] noiseGrid = new double[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double nx = x * scale;
                double ny = y * scale;
                noiseGrid[y][x] = perlin(nx, ny);
            }
        }
        return noiseGrid;
    }

    private static double perlin(double x, double y) {
        int X = (int) Math.floor(x) & 255;
        int Y = (int) Math.floor(y) & 255;
        x -= Math.floor(x);
        y -= Math.floor(y);
        double u = fade(x);
        double v = fade(y);
        int A = p[X] + Y;
        int AA = p[A];
        int AB = p[A + 1];
        int B = p[X + 1] + Y;
        int BA = p[B];
        int BB = p[B + 1];

        return lerp(v, lerp(u, grad(p[AA], x, y),
                grad(p[BA], x - 1, y)),
                lerp(u, grad(p[AB], x, y - 1),
                        grad(p[BB], x - 1, y - 1)));
    }

    private static double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private static double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private static double grad(int hash, double x, double y) {
        int h = hash & 15;
        double u = h < 8 ? x : y;
        double v = h < 4 ? y : (h == 12 || h == 14 ? x : 0);
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    private static double[][] normalizeNoise(double[][] noiseGrid) {
        int height = noiseGrid.length;
        int width = noiseGrid[0].length;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

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

        double[][] normalizedGrid = new double[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                normalizedGrid[y][x] = (noiseGrid[y][x] - min) / (max - min);
            }
        }
        return normalizedGrid;
    }

    private static int[][] createTerrainMap(double[][] noiseGrid, double waterLevel) {
        int height = noiseGrid.length;
        int width = noiseGrid[0].length;
        int[][] terrainMap = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                terrainMap[y][x] = getTerrain(noiseGrid[y][x], waterLevel);
            }
        }
        return terrainMap;
    }

    private static int getTerrain(double noiseValue, double waterLevel) {
        if (noiseValue < 0+waterLevel) {
            return 0; // water
        } else if (noiseValue < 0.05+waterLevel) {
            return 2; // sand
        } else if (noiseValue < 0.2+waterLevel) {
            return 1; // grass
        } else if (noiseValue < 0.4+waterLevel) {
            return 3; // forest
        } else if (noiseValue < 0.6+waterLevel) {
             return 4; // moutain
        } else {
            return 4;
        }
    }
}
