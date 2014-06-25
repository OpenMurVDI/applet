import java.io.InputStream;
import java.io.IOException;
import java.applet.Applet;
import java.awt.*;
public class execute extends Applet{
String output="";
public void init(){ 
try {
// Execute command
String usuario = this.getParameter("usuario");
String clave = this.getParameter("clave");
String ip = this.getParameter("ip");

File f = new File(/usr/bin/xfreerdp);
if(f.exists() && !f.isDirectory()) { 
  String command = "xfreerdp -u " + usuario + " -p " + clave + " " ip ;
  Process child = Runtime.getRuntime().exec(command);
}

// Get the input stream and read from it
//InputStream in = child.getInputStream();
//int c= in.read();
//while ((c = in.read()) != -1) {
//output =output+((char)c);
//}
//in.close();
  System.out.println("GUAY");
}   
catch (IOException e) {
  System.out.println("MIERDA");
  e.printStackTrace();
}
System.out.println(output);
}
public void paint(Graphics g){
g.drawString(output,60,100);
}
}
