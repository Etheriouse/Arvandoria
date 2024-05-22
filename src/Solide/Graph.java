package Solide;

import java.awt.Graphics2D;

public interface Graph {

    /**
     * Fait le rendu sur l'offscreen des texture correspondant au monde associer
     * @param Ts la taille la texture
     * @param posCameraX la position x de la camera
     * @param posCameraY la position y de la camera
     * @param width la largeur de la camera
     * @param height la hauteur de la camera
     * @param offscreen le buffer qui permet d'afficher les texture a l'ecran
     */
    public void rendere(int Ts, int posCameraX, int posCameraY, int width, int height, Graphics2D offscreen);

}