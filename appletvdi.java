/**
 * Applet para direccionar conexiones RDP a máquinas virtuales en cloud
 * Este applet tiene que estar incluido en un paǵina web que le
 * pase como paramétros el nombre de usuario de la máquina remota,
 * el passowrd del usuario y la ip de la máquina remota (ver ejemplo adjunto)
 * @param login 
 * @param password
 * @param vdesktop_ip
 * @version 0.8 16/05/2015
 * @author Cayetano Reinaldos Duarte
 * @todo ver README adjunto
**/
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class appletvdi extends Applet {

    String osName = System.getProperty("os.name");
    String osVersion = System.getProperty("os.version");

    //INFORMACION DEPURACION
    //String osUser = System.getProperty("user.name");
    //String osHome = System.getProperty("user.home");

public void init() { 
    Process process;
    int response;

    String vdidesktop_ip, login, password;
    String absolutePath = "";

    vdidesktop_ip = getParameter("vdidesktop_ip");
    login = getParameter("login");
    password = getParameter("password");

    //INFORMACION DE DEPURACION
    //response = JOptionPane.showConfirmDialog(null, "Sistema Operativo: " + this.osName + " Versión: " + this.osVersion, "Identificación Cliente", JOptionPane.YES_NO_OPTION);

    //response = JOptionPane.showConfirmDialog(null, "Usuario: " + this.osUser + " Directorio: " + this.osHome, "Identificación cliente", JOptionPane.YES_NO_OPTION);
 
    response = JOptionPane.showConfirmDialog(null, "Va a conectar con " + vdidesktop_ip + " con Login: " + login + " y Password: " + password, "Aceptación de conexión", JOptionPane.YES_NO_OPTION);

    if (response == JOptionPane.YES_OPTION) {
	if (this.osName.equals("Linux")) {
	    try {
		//TODO: Ahora mismo se ignora el certificado que presenta el escritorio remoto
		// Hay que modificarlo para recoger el certificado si se puede, mostrarlo y pasarle un "Y\n" 
		process = Runtime.getRuntime().exec("/usr/bin/xfreerdp --ignore-certificate --disable-wallpaper -u " + login + " -p " + password + " -f " + vdidesktop_ip); 
//		process = Runtime.getRuntime().exec("/usr/bin/xfreerdp --ignore-certificate --disable-wallpaper -u " + login + " -p " + password + " -g 1024x768 " + vdidesktop_ip); 
		//InputStream flujo_entrada_applet = process.getInputStream();
		//OutputStream flujo_salida_applet = process.getOutputStream();
		//BufferedReader lectura = new BufferedReader(new InputStreamReader(flujo_entrada_applet));
		//BufferedWriter escritura = new BufferedWriter(new OutputStreamWriter(flujo_salida_applet));
		//String linea, salida;
		//if ((linea = lectura.readLine()) != null) {
		//    salida = "Y\n";
		//    escritura.write(salida);
		//    escritura.flush();
		}	       
	    catch (IOException e) {
		e.printStackTrace();
	    }
	} else if(this.osName.contains("OS X")) {
	    //estamos en un cliente MAC
	     try {
		 process = Runtime.getRuntime().exec("/Applications/CoRD.app/Contents/MacOS/CoRD -u " + login + " -p " + password + " -host " + vdidesktop_ip); 
	    }

	    catch(IOException e) {
		e.printStackTrace();
	    }
	}
	
    } else {
	    //estamos en un cliente Windows
	     try {
		 process = Runtime.getRuntime().exec("cmdkey /generic:" + vdidesktop_ip + " /user:" + login + " /pass:" + password);
		 process = Runtime.getRuntime().exec("mstsc /v:" + vdidesktop_ip);
	    }

	    catch(IOException e) {
		e.printStackTrace();
	    }
	  }
}
}
