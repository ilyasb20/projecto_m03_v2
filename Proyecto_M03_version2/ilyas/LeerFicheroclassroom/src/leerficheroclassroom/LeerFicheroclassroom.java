/*
 * PROYECTO UF3 M03
 */
package leerficheroclassroom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class LeerFicheroclassroom {

    static usuario[] users = new usuario[10];
    static File fichero = new File("classroom.txt");
    static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {

        crearUsuario();
        listarUsers();
        Login();
        menuAdmin(fichero);
         menuprofesor(fichero);
    }

    private static void menuprofesor(File fichero) {
        boolean salir = false;
        int menu; //Guardaremos la opcion del usuario

        do {
            System.out.println("****************MENÚ TEACHER*******************");
            System.out.println("1.leer fichero");
            System.out.println("2.Escritura Fichero");
            System.out.println("3.actualizar fichero");
            System.out.println("4.eliminar fichero");
            System.out.println("5.Salir");
            System.out.println("Escribe una de las opciones");
            menu = lector.nextInt();

            switch (menu) {
                case 1:
                    LeerFichero(fichero);
                    break;
                case 2:
                    EscrituraFicheroTextoAppend(fichero);
                    break;
                case 3:
                    ActualizarLinea(fichero);
                    break;
                case 4:
                    EliminarLinea(fichero);
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("opción no valida");
            }

        } while (salir != true);
    }

    private static void LeerFichero(File fichero) {

        try {
            // Codificación ISO-8859-1 (ANSI) o UTF-8 dependiendo de cómo esté creado el fichero de texto
            Scanner lectorFichero = new Scanner(fichero, "UTF-8");

            while (lectorFichero.hasNext()) {
                System.out.println(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }

    }

    private static void EscrituraFicheroTextoAppend(File fichero) {
        try {

            // El true al final indica que escribiremos al final del fichero añadiendo texto
            FileWriter writer = new FileWriter(fichero, true);
            String aula, descripcion, capacidad, PC, proyector, aula_insonorizada;

            System.out.println("Introduce el aula:--->");
            aula = lector.nextLine();
            System.out.println("Introduce descripcion:--->");
            descripcion = lector.nextLine();
            System.out.println("Introduce la capacidad:--->");
            capacidad = lector.nextLine();
            System.out.println("Introduce el PC:--->");
            PC = lector.nextLine();
            System.out.println("Introduce el Poryector:--->");
            proyector = lector.nextLine();
            System.out.println("Introduce el aula insonorizada:--->");
            aula_insonorizada = lector.nextLine();

            writer.write(aula + "," + descripcion + "," + capacidad + "," + PC + "," + proyector + "," + aula_insonorizada + "\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al crear/escribir en el fichero");

        }

    }

    private static void ActualizarLinea(File fichero) {
        ArrayList<String> lineas = new ArrayList<>();

        //Abrimos el fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);

            while (lectorFichero.hasNext()) {
                lineas.add(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }

        //Abrimos el fichero de texto para sobreescribirlo
        //Actualizaremos la línea 4
        try {

            String aula, descripcion, capacidad, PC, proyector, aula_insonorizada;

            FileWriter writer = new FileWriter(fichero);
            System.out.println("Introduce el Idaula:--->");
            aula = lector.nextLine();
            for (String linea : lineas) {
                String[] parts = linea.split(",");
                String idAula = parts[0];
                if (aula.equals(idAula)) {
                    System.out.println("Introduce descripcion:--->");
                    descripcion = lector.nextLine();
                    System.out.println("Introduce la capacidad:--->");
                    capacidad = lector.nextLine();
                    System.out.println("Introduce el PC:--->");
                    PC = lector.nextLine();
                    System.out.println("Introduce el Poryector:--->");
                    proyector = lector.nextLine();
                    System.out.println("Introduce el aula insonorizada:--->");
                    aula_insonorizada = lector.nextLine();

                    writer.write(aula + "," + descripcion + "," + capacidad + "," + PC + "," + proyector + "," + aula_insonorizada + "\n");

                } else {
                    writer.write(linea + "\n");
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }

    private static void EliminarLinea(File fichero) {
        String aula;
        System.out.println("Introduce el aula que quieres eliminar:--->");
        aula = lector.nextLine();
        // Array para guardar todas las líneas leídas del fichero
        ArrayList<String> lineas = new ArrayList<>();

        // Abrimos el fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);

            while (lectorFichero.hasNext()) {
                lineas.add(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }

        // Abrimos el fichero de texto para sobreescribirlo
        // Eliminaremos la línea 3
        try {

            FileWriter writer = new FileWriter(fichero);
            for (String linea : lineas) {

                if (!aula.equals(linea.substring(0, 5))) {
                    writer.write(linea + "\n");
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }

    private static void menuAdmin(File fichero) {
        boolean salir = false;
        int menu;
        do {
            System.out.println("****************MENÚ ADMIN*******************");
            System.out.println("****************GESTIÓN DE USUARIOS*******************");
            System.out.println("1.Crear usuario");
            System.out.println("2.Listar usarios");
            System.out.println("3.Modificar usuario");
            System.out.println("4.eliminar usuario");
            System.out.println("5.cerrar sesión");
            System.out.println("6.Salir");
            System.out.println("Escribe una de las opciones");
            menu = lector.nextInt();

            switch (menu) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    listarUsers();
                    break;
                default:
                    System.out.println("opción no valida");
            }

        } while (salir != true);
    }

    private static void listarUsers() {
        try {
            // A partir de aquÃ­ accederemos al fichero a leer mediante la variable fichero
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("fichero.dat"));

            // Creamos un nuevo array de Empleados
            // Y rellenamos con lo recuperado de leer el fichero mediante readObject
            // readObject recibe todo un array de Empleados y por eso lo casteamos (Empleado[])
            usuario[] user_1 = (usuario[]) fichero.readObject();

            // Recorremos todo el array de Empleados
            for (usuario user : user_1) {
                // Tenemos en cuenta que algunas posiciones del array valen null
                // En ese caso no leas la informaciÃ³n del empleado
                if (user != null) {
                    System.out.println("ROl: " + user.rol);
                    System.out.println("password: " + user.password);
                    System.out.println("user: " + user.user);
                    System.out.println("--------------------");
                }
            }

            // Cerramos el fichero
            fichero.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ha ocurrido un error al leer el fichero");
        }
    }

    private static void crearUsuario() {
        try {
            // A partir de aquÃ­ accederemos al fichero a guardar mediante la variable fichero
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("fichero.dat"));

            // Creamos un nuevo array de Empleados
            // Por defecto todas las posiciones del array valen null
            // Creamos un nuevo usuario en la 1a posicion del array
            users[0] = new usuario();
            users[0].rol = "admin";
            users[0].user = "ilyas";
            users[0].password = "ilyas123";

            // Creamos un nuevo empleado en la 2a posiciÃ³n del array
            users[1] = new usuario();
            users[1].rol = "Teacher";
            users[1].user = "David";
            users[1].password = "david123";

            // Con writeObject escribimos directamente todo el array de Empleados
            fichero.writeObject(users);

            // Cerramos el fichero
            fichero.close();

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al guardar el fichero");
        }
    }

    private static void Login() {
        boolean salir = false;
        String usuario = "", password = "", rol = "";

        System.out.println("****Login****");
        do {
            System.out.println("usuario");
            usuario = lector.nextLine();
            for (int i = 0; ((i < users.length) && (salir == false)); i++) {
                usuario user = users[i];

                if (users[i].user.equals(usuario) || (users[i] == null)) {
                    salir = true;
                }
            }
            if (!salir) {
                System.out.println("has introducido un usuario que no existe");
            }
        } while (!salir);
        do {
            System.out.println("password");
            usuario = lector.nextLine();
            for (int i = 0; ((i < users.length) && (salir == false)); i++) {
                usuario passwod = users[i];

                if (users[i].password.equals(password) || (users[i] == null)) {
                    salir = true;
                }else{
                    System.out.println("Contraseña es incorrecta");
                }
            }
            if (!salir) {
                System.out.println("contaseña incorrecta");
            }
        } while (!salir);
        do {
            System.out.println("rol");
            rol = lector.nextLine();
            for (int i = 0; ((i < users.length) && (salir == false)); i++) {
                usuario Rol = users[i];

                if (users[i].rol.equals(rol) || (users[i] == null)) {
                    menuprofesor(fichero);
                    salir = true;
                } else if (users[i].rol.equals(rol) || (users[i] == null)) {
                    salir = true;
                    menuAdmin(fichero);

                }
            }
        } while (!salir);
    }

}
