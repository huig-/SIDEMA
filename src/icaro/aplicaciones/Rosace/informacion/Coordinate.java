package icaro.aplicaciones.Rosace.informacion;

import java.io.Serializable;

public class Coordinate implements Serializable{

  public double x, y, z;
  
  public Coordinate(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public String toString() {
    return "Coordinate: (" + x + "," + y + "," + z +")";
  }
  
} 
