
package vista;

import controlador.Controlador;

/**
 *
 * @author POLO
 */
public class App {
    public static void main(String[] args) {
        LoginFrm ob = new LoginFrm();
        Controlador c = new Controlador(ob);
        ob.setVisible(true);
        
        
    }
}
