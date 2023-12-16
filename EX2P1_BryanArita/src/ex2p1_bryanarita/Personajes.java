package ex2p1_bryanarita;

public class Personajes {

    String nombre;
    int MP;
    int HP;
    int attackPoints;
    int defensePoints;

    public Personajes(String nombre, int HP, int MP, int attackPoints, int defensePoints) {
        this.nombre = nombre;
        this.MP = MP;
        this.HP = HP;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
    }

    public Personajes() {
    }//constructor vacio

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    @Override
    public String toString() {
        return "Personaje{" + "nombre=" + nombre + ", HP=" + HP + ", MP=" + MP + ", attackPoints=" + attackPoints + ", defensePoints=" + defensePoints + '}';
    }

}
