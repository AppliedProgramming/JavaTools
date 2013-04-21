package JTools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Manages Debug Settings
 * <p>
 * Use
 * <p>
 * Available Debug Levels:
 * -1 = No Debug Tools, All Logging (Finest, Finer, Fine, Info, Warning and Severe)
 * 0  = No Debug Tools, Normal Logging (Info, Warning and Severe) - DEFAULT
 * 1  = Debug Tools, Normal Logging (Info, Warning and Severe)
 * 2  = Debug Tools, All Logging (Finest, Finer, Fine, Info, Warning and Severe)
 * 
 * @author Tahoma Menta
 * @version V0.1.2
 */
public class DebugManager {
static ConsoleManager con = new ConsoleManager();
/**
 * This is the current debug level.<br>
 * This is defaulted to 0.
 * 
 * @since V1.0
 */
    static int debug;
    static boolean forceReset;
    
    public static void main(String[] args){init();}
    
/**
 * This returns the current debug level.
 * 
 * @return Debug Level Number 
 * @since V0.1
 */
    public static int getDebugLevel() {return debug;}
/**
 * This returns true or false in relation to if the debug tools are active or not.
 * 
 * @return If the debug tools are active or not.
 * @since V0.1
 */
    public static boolean getDebugStatus() {if(debug>=1){return true;}else{return false;}}

    public static void setDebugLevel(int foo) {
        if(foo>=-1&&foo<=2){debug=foo;con.con(3, "New Debug Level: " + debug);saveVars();}
        else{con.severe("Invalid Debug Level!");con.severe("Level Supplied: " + foo);con.severe("Levels Valid: -1, 0, 1 & 2");}
    }
    
/**
 * This runs the menu to perform various debug commands.
 * <p>
 * This includes:<br>
 * setDebugLevel
 * </p>
 * 
 * @since V0.1.2
 */
    public static void menu() {
        con.con(3, "Running Debug Menu:");
        con.con(3, "1. Change Debug Level");
        con.con(3, "0. Exit");
        con.con(3, "-1. Reset To Default Settings");
        if(con.inputMenuOption()==1){con.con(3,"Current Debug Level: " + debug);con.con(3, "Please Enter New Debug Level.");setDebugLevel(con.inputMenuOption());menu();}
        else if(con.inputMenuOption()==-1){
            Scanner sc = new Scanner(System.in);
            boolean bar = false;
            while(!bar){
                con.con(3, "ARE YOU SURE? [y/N]");
                String foo = sc.nextLine();
                if(foo.equalsIgnoreCase("Y")){reset();bar=true;}
                else if(foo.equalsIgnoreCase("N")||foo.isEmpty()){bar=true;}
                else{con.con(3, "INVALID OPTION!");}
            }
            menu();
        }
    }
    
    private static void reset() {
        con.fine("Resetting Debug Variables!");
        con.finer("Resetting Debug Level");
        debug=0;
        con.finer("Debug Level Reset To Default (0)");
        con.finer("Disabling Force Reset");
        forceReset = false;
        con.finer("Force Reset Disabled");
        con.finer("Saving Vars");
        saveVars();
        con.finer("Finished Saving Vars");
        con.fine("Finished Variable Reset");
    }
    
    private static void saveVars(){
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\JTools\\debugSettings", false)))) {
            out.println(debug);
            out.println(forceReset);
            out.close();
        } catch (IOException ex) {
        }
    }
    private static void readVars(){
        try(BufferedReader in = new BufferedReader(new FileReader("C:\\JTools\\debugSettings"))) {
            debug = Integer.parseInt(in.readLine());
            forceReset = Boolean.parseBoolean(in.readLine());
            in.close();
        } catch (IOException ex) {
        }
    }
    public static void init(){
        readVars();
        if(forceReset){con.warn("Settings Demands A Force Reset!!!!!");reset();}
        menu();
    }
}