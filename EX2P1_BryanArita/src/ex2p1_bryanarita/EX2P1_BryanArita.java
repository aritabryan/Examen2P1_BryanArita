//F3A10
package ex2p1_bryanarita;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EX2P1_BryanArita {

    static ArrayList<Personajes> reserva = new ArrayList<>();
    static ArrayList<Items> mochila = new ArrayList<>();
    static ArrayList<Personajes> party = new ArrayList<>();
    
    static int vidaTotalHeartless;

    public static Random rndm = new Random();
    public static Scanner duki = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarEquipos();
        inicializarMochila();
        int cuartoActual = 1;
        while (cuartoActual <= 20 && !party.isEmpty()) {
            int randomEvento = generarRandom(1, 5);

            switch (randomEvento) {
                case 1:
                    System.out.println("Te has encontrado con Heartless en el cuarto " + cuartoActual + "!");
                    vidaTotalHeartless = generarRandom(1, 3) * 75;
                    mostrarEstadoParty();
                    peleaConHeartless();
                    break;
                case 2:
                    abrirCofre("Pocion");
                    break;
                case 3:
                    abrirCofre("Eter");
                    break;
                case 4:
                    abrirCofre("Elixir");
                    break;
                case 5:
                    ayudarAmigos();
                    break;
            }

            cuartoActual++;
        }

        if (cuartoActual > 20) {
            System.out.println("Has completado los 20 cuartos! Ganaste!");
        } else {
            System.out.println("La Party ha sido derrotada, Fin del juego!!");
        }
    }

    private static void abrirCofre(String tipoItem) {
        System.out.println("Te has encontrado un cofre que contiene una " + tipoItem + "!");
        mochila.add(new Items(tipoItem, obtenerHpPoints(tipoItem), obtenerMpPoints(tipoItem)));
        mostrarMochila();
    }

    private static int obtenerHpPoints(String tipoItem) {
        switch (tipoItem.toLowerCase()) {
            case "pocion":
                return 50;
            case "eter":
                return 0;
            case "elixir":
                return 100;
            default:
                return 0;
        }
    }

    private static int obtenerMpPoints(String tipoItem) {
        switch (tipoItem.toLowerCase()) {
            case "pocion":
                return 0;
            case "eter":
                return 50;
            case "elixir":
                return 100;
            default:
                return 0;
        }
    }

    public static void ayudarAmigos() {
        char resp = 's';
        System.out.println("Te has encontrado amigos que necesitan ayuda y les daras algunos items:");

        if (!mochila.isEmpty()) {
            int cantidadItemsAmigos = generarRandom(1, 3);

            for (int i = 0; i < cantidadItemsAmigos && !mochila.isEmpty(); i++) {
                int index = generarRandom(0, mochila.size() - 1);
                Items itemAmigo = mochila.get(index);
                mochila.remove(index);

                System.out.println("Le has dado a tus amigos: " + itemAmigo.nombre);
                System.out.println("Quedan: ");
                mostrarMochila();
                System.out.println("Deseas continuar? [S/N]");
                resp = duki.next().charAt(0);

                if (resp == 's' || resp == 'S') {
                    break;
                }
            }
        } else {
            System.out.println("No tienes items para darles a tus amigos.");
        }
    }

    public static void inicializarEquipos() {
        party.add(new Personajes("Sora", 300, 300, 75, 15));
        party.add(new Personajes("Donald", 150, 450, 45, 10));
        party.add(new Personajes("Goofy", 450, 100, 150, 50));
        reserva.add(new Personajes("Mickey", 100, 500, 150, 35));
        reserva.add(new Personajes("Roxas", 300, 300, 15, 75));
        reserva.add(new Personajes("Kairi", 200, 200, 50, 15));
    }

    public static void inicializarMochila() {
        mochila.add(new Items("Pocion", 50, 0));
        mochila.add(new Items("Eter", 0, 50));
        mochila.add(new Items("Elixir", 100, 100));
    }

    public static void peleaConHeartless() {
        while (!party.isEmpty() && vidaTotalHeartless > 0) {
            mostrarDetallesEstadoParty();
            int opcion = mostrarMenu();
            action(opcion);
        }
    }

    public static void mostrarDetallesEstadoParty() {
        System.out.println("Te enfrentas a Heartless con una vida total de " + vidaTotalHeartless);
        mostrarEstadoParty();
    }

    public static void action(int opcion) {
        Scanner scanner = new Scanner(System.in);
        int indicePersonaje = opcion;

        if (opcion < 0 || opcion >= party.size()) {
            System.out.println("Opcion invalida. Selecciona un personaje valido.");
            return;
        }

        Personajes personajeSeleccionado = party.get(indicePersonaje);

        System.out.println("***Menu***");
        System.out.println("[1] Atacar");
        System.out.println("[2] Usar Magia");
        System.out.println("[3] Usar Items");
        System.out.println("[4] Cambiar Personaje (Party)");

        int opcionAccion = scanner.nextInt();

        switch (opcionAccion) {
            case 1:
                int danio = generarRandom(1, 25);
                System.out.println(personajeSeleccionado.nombre + " ataco y causo " + danio + " de dano a los Heartless" + "\n" + personajeSeleccionado.nombre + " Recibio el golpe");
                reducirVidaHeartless(danio);
                if (vidaTotalHeartless <= 0) {
                    System.out.println("¡Has derrotado a los Heartless!");
                }
                recibirDanioParty();
                if (party.isEmpty()) {
                    System.out.println("¡La Party ha sido derrotada! Fin del juego.");
                }
                break;

            case 2:
                System.out.println("[1] Blizzard (50 MP - 50 DMG)");
                System.out.println("[2] Firaga (25 MP - 25 DMG)");
                System.out.println("[3] Gravity (75 MP - 100 DMG)");

                int tipoMagia = scanner.nextInt();

                switch (tipoMagia) {
                    case 1:
                        usarMagia(personajeSeleccionado, "Blizzard", 50, 50);
                        break;

                    case 2:
                        usarMagia(personajeSeleccionado, "Firaga", 25, 25);
                        break;

                    case 3:
                        usarMagia(personajeSeleccionado, "Gravity", 75, 100);
                        break;

                    default:
                        System.out.println("Opcion de magia invalida.");
                        break;
                }
                break;

            case 3:
                mostrarMochila();
                System.out.println("Elige un item:");
                int indiceItem = scanner.nextInt();

                if (indiceItem >= 0 && indiceItem < mochila.size()) {
                    Items itemSeleccionado = mochila.get(indiceItem);
                    usarItem(personajeSeleccionado, itemSeleccionado);

                    mochila.remove(indiceItem);

                    System.out.println(personajeSeleccionado.nombre + " uso " + itemSeleccionado.nombre
                            + " y recupero " + itemSeleccionado.Hp_points + " HP y " + itemSeleccionado.Mp_points + " MP.");
                } else {
                    System.out.println("Opcion de item invalida.");
                }
                break;

            case 4:
                mostrarPersonajesReserva();
                System.out.println("Elige a quien cambiar:");
                int indiceCambio = scanner.nextInt();

                if (indiceCambio >= 0 && indiceCambio < reserva.size()) {
                    cambiarPersonajeEnParty(indicePersonaje, indiceCambio);
                    System.out.println(personajeSeleccionado.nombre + " ha sido cambiado antes del ataque!");
                } else {
                    System.out.println("Opcion de cambio invalida.");
                }
                break;

            default:
                System.out.println("Opcion invalida.");
                break;
        }
    }

    public static void usarMagia(Personajes personaje, String nombreMagia, int costoMP, int danioMagia) {
        if (personaje.MP >= costoMP) {
            personaje.MP -= costoMP;
            System.out.println(personaje.nombre + " lanzo " + nombreMagia + " y causo " + danioMagia + " de dano a los Heartless.");
            reducirVidaHeartless(danioMagia);
        } else {
            System.out.println("No tienes MP suficiente para lanzar " + nombreMagia + ".");
        }
    }

    public static void usarItem(Personajes personaje, Items item) {
        personaje.HP += item.Hp_points;
        personaje.MP += item.Mp_points;
    }

    public static void cambiarPersonajeEnParty(int indicePersonaje, int indiceCambio) {
        Personajes personajeReserva = reserva.get(indiceCambio);
        reserva.remove(indiceCambio);
        reserva.add(party.get(indicePersonaje));
        party.remove(indicePersonaje);
        party.add(personajeReserva);
    }

    public static void reducirVidaHeartless(int danio) {
        vidaTotalHeartless -= danio;
        System.out.println("La vida total de los Heartless es: " + vidaTotalHeartless);
    }

    public static void recibirDanioParty() {
        for (int i = 0; i < party.size(); i++) {
            Personajes personaje = party.get(i);
            personaje.HP -= generarRandom(1, 10);
        }
        mostrarEstadoParty();
    }

    public static void mostrarEstadoParty() {
        System.out.println("Estado actual de la Party:");
        for (int i = 0; i < party.size(); i++) {
            Personajes personaje = party.get(i);
            System.out.println(i + "-" + personaje.nombre + "\nHP: " + personaje.HP + "\nMP: " + personaje.MP);
        }
    }

    public static void mostrarPersonajesReserva() {
        System.out.println("Personajes en reserva:");
        for (int i = 0; i < reserva.size(); i++) {
            Personajes personaje = reserva.get(i);
            System.out.println(i + "- " + personaje.nombre + "\n\tHP=" + personaje.HP + "\n\tMP=" + personaje.MP + "\n\tAttack P. =" + personaje.attackPoints + "\n\tDefense P. =" + personaje.defensePoints);
        }
    }

    public static int generarRandom(int min, int max) {
        return rndm.nextInt(max - min + 1) + min;
    }

    public static Items obtenerItem(String tipoItem) {
        for (int i = 0; i < mochila.size(); i++) {
            Items item = mochila.get(i);
            if (item.nombre.equalsIgnoreCase(tipoItem)) {
                return item;
            }
        }
        return null;
    }

    public static int mostrarMenu() {
        System.out.print("Elije el personaje: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void mostrarMochila() {
        System.out.println("Mochila actual:");
        for (int i = 0; i < mochila.size(); i++) {
            Items item = mochila.get(i);
            System.out.println(i + "- " + item.nombre + " HPpoints=" + item.Hp_points + ", MPpoints=" + item.Mp_points);
        }
    }
}
