import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JApplet;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class AppletFirmado extends JApplet
{
    public void init()
    {
        DefaultListModel modelo = new DefaultListModel();
        JList lista = new JList(modelo);
        JScrollPane scroll = new JScrollPane(lista);
        add(scroll);
        rellenaListaConFicheroDeDirectorioRaiz(modelo);
    }

    private void rellenaListaConFicheroDeDirectorioRaiz(DefaultListModel modelo)
    {
        File directorioRaiz = new File("/");
        String [] ficheros = directorioRaiz.list();
        for (int i=0;i<ficheros.length;i++)
            modelo.addElement(ficheros[i]);
    }
}
