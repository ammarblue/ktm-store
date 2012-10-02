
package org.ktm.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Native2Ascii {

    private static BufferedReader reader;


    private Native2Ascii() {
        super();
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: java de.enough.polish.util.Native2Ascii [-reverse] filepath [encoding]");
            return;
        }
        String arg = args[0];
        boolean reverse = false;
        if ("-reverse".equals(arg)) {
            reverse = true;
            if (args.length == 1) {
                System.out.println("Usage: java de.enough.polish.util.Native2Ascii [-reverse] filepath [encoding]");
                return;
            }
            arg = args[1];
        }
        File file = new File( arg );
        String encoding = null;
        if ( (args.length > 1 && !reverse) || args.length > 2 ) {
            if (reverse) {
                encoding = args[2];
            } else {
                encoding = args[1];
            }
        }
        
        //InputStream in = translateToAscii( new FileInputStream( file ), encoding );
        
        reader = encoding == null ?
                new BufferedReader( new InputStreamReader( new FileInputStream( file ) ) ) :
                new BufferedReader( new InputStreamReader( new FileInputStream( file ), encoding ) );
        String line;
//      int i = 1;
        while ((line = reader.readLine()) != null) {
//          System.out.print( i + ": [");
            if (reverse) {
                System.out.println( asciiToNative(line) );              
            } else {
                System.out.println( nativeToAscii(line) );
            }
//          System.out.println("]");
//          i++;
        }
        //in.close();   
    }
    
//  /**
//   * Translates the given input stream to ascii code
//   * @param in the input stream
//   * @param encoding the encoding, when null the default encoding is used
//   * @return the input stream that contains the ascii representation of the given input stream
//   * @throws IOException when the native2ascii tool could not be found.
//   */
//  public static InputStream translateToAscii( InputStream in, String encoding ) 
//  throws IOException 
//  {
//      String javaHomePath = System.getProperty("java.home");
//      File javaHome = new File( javaHomePath );
//      if (!javaHome.exists()) {
//          throw new IOException("The system property java.home points to the invalid destination [" + javaHome.getAbsolutePath() + "].");
//      }
//      File executable;
//      if ( File.separatorChar == '\\') { // windows
//          executable = new File( javaHome, "bin\\native2ascii.exe");
//      } else {
//          executable = new File( javaHome, "bin/native2ascii");
//      }
//      if (!executable.exists()) {
//          if ( javaHomePath.endsWith("jre") || javaHomePath.endsWith("JRE") ) {
//              javaHomePath = javaHomePath.substring( 0, javaHomePath.length() - "/jre".length() );
//              javaHome = new File( javaHomePath );
//              if ( File.separatorChar == '\\') { // windows
//                  executable = new File( javaHome, "bin\\native2ascii.exe");
//              } else {
//                  executable = new File( javaHome, "bin/native2ascii");
//              }               
//          }
//          if (!executable.exists()) {
//              throw new IOException("Unable to find the native2ascii tool at [" + executable.getAbsolutePath() + "].");
//          }
//      }
//      String[] arguments;
//      if (encoding == null) {
//          arguments = new String[]{ executable.getAbsolutePath() };           
//      } else {
//          arguments = new String[]{
//                  executable.getAbsolutePath(),
//                  "-encoding",
//                  encoding
//              };          
//      }
//      Runtime runtime = Runtime.getRuntime();
//      Process process = runtime.exec( arguments );
//      StreamWrapper wrapper = new StreamWrapper( in, process.getOutputStream() );
//      wrapper.start();
//      return process.getInputStream();
//  }
    
    /**
     * Translates the given String into ASCII code.
     * 
     * @param input the input which contains native characters like umlauts etc
     * @return the input in which native characters are replaced through ASCII code
     */
    public static String nativeToAscii( String input ) {
        if (input == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer( input.length() + 60 );
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c <= 0x7E) { 
                buffer.append(c);
            }
            else {
                buffer.append("\\u");
                String hex = Integer.toHexString(c);
                for (int j = hex.length(); j < 4; j++ ) {
                    buffer.append( '0' );
                }
                buffer.append( hex );
            }
        }
        return buffer.toString();
    }
    
    
    /**
     * Translates the given String into ASCII code.
     * 
     * @param input the input which contains native characters like umlauts etc
     * @return the input in which native characters are replaced through ASCII code
     */
    public static String asciiToNative( String input ) {
        if (input == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer( input.length() );
        boolean precedingBackslash = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (precedingBackslash) {
                switch (c) {
                case 'f': c = '\f'; break;
                case 'n': c = '\n'; break;
                case 'r': c = '\r'; break;
                case 't': c = '\t'; break;
                case 'u':
                    String hex = input.substring( i + 1, i + 5 );
                    c = (char) Integer.parseInt(hex, 16 );
                    i += 4;
                }
                precedingBackslash = false;
            } else {
                precedingBackslash = (c == '\\');
            }
            if (!precedingBackslash) {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }


}