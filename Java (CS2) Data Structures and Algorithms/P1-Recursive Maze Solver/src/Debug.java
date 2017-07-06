/* "Copyright (c) 2012-2016 by Fritz Sieker."
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for any purpose, without fee, and without written
 * agreement is hereby granted, provided that the above copyright notice
 * and the following two paragraphs appear in all copies of this software,
 *
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE TO ANY PARTY FOR DIRECT,
 * INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
 * OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE AUTHOR
 * HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * THE AUTHOR SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS ON AN "AS IS"
 * BASIS, AND THE AUTHOR NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT,
 * UPDATES, ENHANCEMENTS, OR MODIFICATIONS."
 */

import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.lang.StackTraceElement;
import java.util.Arrays;

/**
 * In  writing your code, you may be tempted to use <tt>System.out.printf()
 * </tt> to determine what is happening. As an alternative, the method
 * <tt>Debug.printf()</tt> is provided. The nice thing about <tt>Debug.printf()</tt>
 * in comparison to <tt>System.out.printf()</tt> is that <tt>Debug.printf()
 * </tt> <b>only</b> prints when debug is turn <b>on</b>. You can turn
 * debug on and off by setting the <tt>debug_level</tt> variable to non zero.
 * This is normally done by a command line parameter to the <tt>main()</tt>
 * of your program.  This module allows you to write debug code, but not need
 * to change your program in any
 * way before turning it in. If you use <tt>System.out.printf()</tt> you
 * <b>MUST</b> either comment out the lines or remove them before you submit
 * your code for grading. This is a simple alternative to the many logging
 * packages that have more advanced capabilities. See
 * <a href="http://www.java-logging.com">this</a> page for examples.
 *
 * @author Fritz Sieker
 */

public class Debug  {

  /** A variable controlling how much debug output is produced. A value of
   *  0 means no output. This is normally set by your program's <tt>main()</tt>
   *  by a command line argument. With the <tt>Debug.lDebug()</tt> you can vary
   *  the amount of output from none, to a lot.
   */

  public static int debugLevel = 0;

  /** A variable to allow debug output to go someplace other than
   *  <tt>System.err</tt>. See the <tt>toFile(), close()</tt> methods.
   *  Default to <tt>System.err</tt>.
   */

  /** Define prefix of flag to turn on debuging */

  private static final String prefix = "-debug"; // use "-dbg" ???

  private static java.io.PrintStream ps = System.err;

  /** No instances, a purely static class */
  private Debug() {
  }

  /** This routine will set the <tt>debugLevel</tt> from the arguments
   *  pased to <tt>main()</tt>. If the <b>first</b> argument is <tt>-d</tt> or
   *  <tt>-dDigit</tt>. the value will be used to initialize 
   *  <tt>debugLevel</tt> and that argument will be removed.
   *  @param  args the array of arguments passed to <tt>main</tt>
   *  @return the <tt>args</tt> with the first value removed (if appropriate)
   */
  public static String[] init (String[] args) {
    if (args.length > 0) {
      String arg0 = args[0];

      if (arg0.startsWith(prefix)) {
        debugLevel = 1;
        int len = prefix.length();

        if (arg0.length() > len) { // look for level
          try {
            debugLevel = Integer.parseInt(arg0.substring(len));
          }
          catch (NumberFormatException nfe) {
            System.err.printf("Bad debug specifier '%s'\n", arg0);
            System.exit(-1);
          }
        }

        int newLen = args.length - 1;
        String[] newArgs = new String[newLen];
        System.arraycopy(args, 1, newArgs, 0, newLen);
        args = newArgs;
      }
    }

    return args;
  }

  /** Print the output prefix "DEBUG fileName[lineNumber] methodName"
   * @param format  the format string for the output
   * @param args  argments for the format string (variable number)
   */
  private  static void printIt (String format, Object ... args) {
    StackTraceElement ste = (new Throwable()).getStackTrace()[2];

    ps.printf("DEBUG %s[%d] %s() ", ste.getFileName(),
              ste.getLineNumber(), ste.getMethodName());
    ps.println(Debug.format(format, args));
  }

  /** An "extension" of <tt>String.format()</tt> to automatically convert
   *  arrays to Strings using the <tt>Arrays.toString()</tt> methods.
   *  Arrays of arrays are also handled. The argument list is exactly the same
   *  as that of <tt>String.format()</tt>, so any of the features of that
   *  code may be used. If there is only a <b>single</b> argument after the
   *  format and if that argument is an array, cast it to an Object using
   *  (<tt>(Object)</tt>)
   *  @param format the format string for the output (use <tt>%s</tt> for arrays)
   *  @param args argments for the format string (variable number)
   *  @return the formatted string with all array objects expanded
   */
  public static String format (String format, Object ... args) {
    for (int i = 0; i < args.length; i++) {
      if ((args[i] != null) && args[i].getClass().isArray()) {
        if (args[i] instanceof boolean[])
          args[i] = Arrays.toString((boolean[]) args[i]);
        else if (args[i] instanceof byte[])
          args[i] = Arrays.toString((byte[]) args[i]);
        else if (args[i] instanceof char[])
          args[i] = Arrays.toString((char[]) args[i]);
        else if (args[i] instanceof double[])
          args[i] = Arrays.toString((double[]) args[i]);
        else if (args[i] instanceof float[])
          args[i] = Arrays.toString((float[]) args[i]);
        else if (args[i] instanceof int[])
          args[i] = Arrays.toString((int[]) args[i]);
        else if (args[i] instanceof long[])
          args[i] = Arrays.toString((long[]) args[i]);
        else if (args[i] instanceof Object[])
          args[i] = Arrays.deepToString((Object[]) args[i]);
        else if (args[i] instanceof short[])
          args[i] = Arrays.toString((short[]) args[i]);
      }
    }
    return String.format(format, args);
  }

  /** Simple routine to print the fileName, lineNumber and methodName. Used
   *  for quick debugging to see if your code even gets to a particular place.
   * @return always returns <tt>true</tt>. Allows you to "abuse" the
   * <tt>assert</tt> statement for high performance.
   */
  public static boolean HERE () {
    if (debugLevel != 0)
      printIt("HERE");

    return true;
  }

  /** Print a message if the variable <tt>Debug.debugLevel</tt> is non-zero.
   *  The argument list is exactly the same as that of <tt>String.format()</tt>,
   *  so any of the features of that code may be used. See the documentation of
   * <tt>Debug.format()</tt> for details on printing arrays.
   * @param format  the format string for the output
   * @param args  argments for the format string (variable number)
   * @return always returns <tt>true</tt>. Allows you to "abuse" the
   * <tt>assert</tt> statement for high performance.
   */
  public static boolean printf (String format, Object ... args) {
    if (debugLevel != 0)
      printIt(format, args);

    return true;
  }

  /** Print a message if the parameter <tt>level</tt> is less than or equal to
   *  <tt>debugLevel</tt>. The argument list following <tt>level</tt>
   * is exactly the same as that of <tt>String.format()</tt>, so any of
   * the features of that code may be used. See the documentation of
   * <tt>Debug.format()</tt> for details on printing arrays.
   * @param level  controls whether message is printed or not
   * @param format  the format string for the output
   * @param args  argments for the format string (variable number)
   * @return always returns <tt>true</tt>. Allows you to "abuse" the
   * <tt>assert</tt> statement for high performance.
   */
  public static boolean printf (int level, String format, Object ... args) {
    if (level <= debugLevel)
      printIt(format, args);

    return true;
  }

  /** Send debugging output to a file.
   * @param fileName name of the file to send output to
   */

  public static void toFile (String fileName) throws FileNotFoundException {
    close();
    ps = new PrintStream(fileName);
  }

  /** Close the output stream if it is not <tt>System.err</tt>. For use in
   *  conjuntion with <tt>toFile()</tt>
   */
  public static void close () {
    if (ps != System.err) {
      ps.close();
      ps = System.err;
    }
  }
}
