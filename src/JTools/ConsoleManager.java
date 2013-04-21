package JTools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Manages Console & Log File Output
 * <p>
 * Use '{name}.[required level]' to output into the console & log file.<br>
 * Use '{name}.con' to output to the console only.<br>
 * Use '{name}.log' to output to the log file only.
 * </p>
 * <p>
 * Available logging levels:
 * <table style="width:100px;"><tr><td>FINEST</td><td>(0)</td></tr>
 * <tr><td>FINER</td><td>(1)</td></tr>
 * <tr><td>FINE</td><td>(2)</td></tr>
 * <tr><td>INFO</td><td>(3)</td></tr>
 * <tr><td>WARNING</td><td>(4)</td></tr>
 * <tr><td>SEVERE</td><td>(5)</td></tr></table>
 * </p>
 * 
 * @author Tahoma Menta
 * @version V1.0
 */
public class ConsoleManager {
/**
 * This is the path to where your debug level variable is held.<br>
 * This is defaulted to use the DebugTool.
 * 
 * @since V1.0
 */
    static final int debugPath = DebugManager.getDebugLevel();
    static final Date dt = new Date();
    static final SimpleDateFormat ft = new SimpleDateFormat("dd/MM HH:mm");
    static String date = ("[" + ft.format(dt) + "] ");
/**
 * This retrieves the necessary tag required for logging and/or console output.
 *
 * @param lv int Denotes level for required log tag.
 * @return String Required Log Tag
 * @since V0.1
 */
    private static String getLevel(int lv) {
        String foo;
        if(lv==0){foo="[FINEST] ";}
        else if (lv==1){foo="[FINER] ";}
        else if (lv==2){foo="[FINE] ";}
        else if (lv==3){foo="[INFO] ";}
        else if (lv==4){foo="[WARNING] ";}
        else if (lv==5){foo="[SEVERE] ";}
        else {foo="[SEVERE] Logging Failed!\n";}
        return foo;
    }
/**
 * This manages the printing to the console and/or log file.
 * <p>
 * This will log to the console if the debug level set allows the specific level to be logged. They will also be printed to the log if the bar variable is true.<br>
 * Any messages that aren't allowed to be printed to the console due to debug settings will still be printed to the log file if bar is true.
 *
 * @param lv int Denotes level required for log tagging.
 * @param out String The text to be printed.
 * @param bar boolean Denotes if the text will also be printed to the log file. Will print to log if true.
 * @since V0.1
 */
    private static void print(int lv, String out, boolean bar) {
        String foo;
        if(lv==0&&(debugPath!=0||debugPath!=1)){foo=(date + getLevel(0) + out);}
        else if(lv==1&&(debugPath!=0||debugPath!=1)){foo=(date + getLevel(1) + out);}
        else if(lv==2&&(debugPath!=0||debugPath!=1)){foo=(date + getLevel(2) + out);}
        else if(lv==3){foo=(date + getLevel(3) + out);}
        else if(lv==4){foo=(date + getLevel(4) + out);}
        else if(lv==5){foo=(date + getLevel(5) + out);}
        else{foo=null;}
        if(bar){
            if(!foo.isEmpty()){log(foo);}
            else{log(date + getLevel(lv) + out);}
        }
        if(!foo.isEmpty()){System.out.println(foo);}
    }
/**
 * This prints to the log file.
 *
 * @param foo String Text to be printed to the log file.
 * @since V0.1
 */
    private static void log(String foo) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\JTools\\log", true)))) {
            out.println(foo);
        }
        catch(IOException e) {}
    }

/**
 * Prints to console & log under the "FINEST" tag.
 * 
 * @param foo String Text to be printed.
 * @since V0.1
 */
    public void finest(String foo){print(0, foo, true);}
/**
 * Prints to console & log under the "FINER" tag.
 * 
 * @param foo String Text to be printed.
 * @since V0.1
 */
    public void finer(String foo){print(1, foo, true);}
/**
 * Prints to console & log under the "FINE" tag.
 * 
 * @param foo String Text to be printed.
 * @since V0.1
 */
    public void fine(String foo){print(2, foo, true);}
/**
 * Prints to console & log under the "INFO" tag.
 * 
 * @param foo String Text to be printed.
 * @since V0.1
 */
    public void info(String foo){print(3, foo, true);}
/**
 * Prints to console & log under the "WARNING" tag.
 * 
 * @param foo String Text to be printed.
 * @since V0.1
 */
    public void warn(String foo){print(4, foo, true);}
/**
 * Prints to console & log under the "SEVERE" tag.
 * 
 * @param foo String Text to be printed.
 * @since V0.1
 */
    public void severe(String foo){print(5, foo, true);}
    
/**
 * Prints to log under the tag level specified.
 * 
 * @param lv int Denotes level required for log tagging.
 * @param foo String Text to be printed to the log file.
 * @since V0.1
 */
    public void log(int lv, String foo){log((date + getLevel(lv) + foo));}
/**
 * Prints to console under the tag level specified.
 * 
 * @param lv int Denotes level required for log tagging.
 * @param foo String Text to be printed to the console.
 * @since V0.1
 */
    public void con(int lv, String foo){print(lv, foo, false);}
/**
 * Asks user to input a int.
 * 
 * @return int User input.
 * @since V0.1.2
 */
    public int inputMenuOption() {
        int foo;
        Scanner sc = new Scanner(System.in);
        con(3, "Please Input Option: ");
        System.out.print("> ");
        try{foo = sc.nextInt();}
            catch(Exception e){con(3, "INVALID OPTION!");foo=inputMenuOption();}
        return foo;
    }
}
