package ex2p1_bryanarita;

public class Items {

    String nombre;
    int Mp_points;
    int Hp_points;

    public Items(String nombre, int Hp_points, int Mp_points) {
        this.nombre = nombre;
        this.Mp_points = Mp_points;

        this.Hp_points = Hp_points;
    }

    public Items() {
    }//constructor vacio

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMp_points() {
        return Mp_points;
    }

    public void setMp_points(int Mp_points) {
        this.Mp_points = Mp_points;
    }

    public int getHp_points() {
        return Hp_points;
    }

    public void setHp_points(int Hp_points) {
        this.Hp_points = Hp_points;
    }

    @Override
    public String toString() {
        return "Item{" + "nombre=" + nombre + ", Hp_points=" + Hp_points + ", Mp_points=" + Mp_points + '}';
    }

}
