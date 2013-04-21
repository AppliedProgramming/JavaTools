package JTools;
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
 * @version V0.1
 */
public class DebugManager {
static ConsoleManager con = new ConsoleManager();
/**
 * This is the current debug level.<br>
 * This is defaulted to 0.
 * 
 * @since V1.0
 */
    static int debug = 2;
    
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
        if(foo>=-1&&foo<=2){debug=foo;con.info("New Debug Level: " + debug);}
        else{con.severe("Invalid Debug Level!");con.severe("Level Supplied: " + foo);con.severe("Levels Valid: -1, 0, 1 & 2");}
    }
    
    public static void menu() {
        con.con(3, "Running Debug Menu:");
        con.con(3, "1. Change Debug Level");
        if(con.inputMenuOption()==1){setDebugLevel(con.inputMenuOption());}
    }
public static void main(String[] args){menu();}
}