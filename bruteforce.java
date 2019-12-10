import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;


class jeh{
    
    public String match(String match0, String match1){
        if (match0 == match1){
            return "matched";
        }else{
            return "not Matched";
        }

    }

    public static String crypto(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(str.getBytes("UTF-8"));
           
            //converting byte array to Hexadecimal String
           StringBuilder sb = new StringBuilder(2*hash.length);
           for(byte b : hash){
               sb.append(String.format("%02x", b&0xff));
           }
          
            String hashtext = sb.toString();
            return hashtext;

        }catch (UnsupportedEncodingException e){
            throw new RuntimeException(e);
        } 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static void file_writer(String data){
        File file = new File("EH.txt");

        try {
            FileWriter ap = new FileWriter(file, true);
            ap.write(data + "\n");
            ap.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public static void combo(int max, char[] chr, String data, String hashed){
        if (data.length() == max){
            //System.out.println(crypto(data) + ": " + hashed + ":" + data);

            if (hashed.trim().equals(crypto(data)) | hashed.trim().equals(crypto(data).toUpperCase())){
                System.out.println("Cracked: "+ data);
                System.exit(1);
            }
        }else{
            for (int x=0; x < chr.length; x++){
                String oldData = data;
                data += chr[x];
                combo(max, chr, data, hashed);
                data = oldData;
                

            }   
        }
    }
    public static void main(String[] args)throws NoSuchAlgorithmException {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Hash: ");
        String hashed = input.nextLine();

        System.out.print("Length: ");
        int len = input.nextInt();
        input.close();

        char[] wdlist = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h','i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'z', 'y',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Z', 'Y', '/', '.',
         ';', '"', ']', '[', '+', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ':', '|', ',', '=', '-', '_', '!', '@', '#', '$', '%', '^', 
         '&', '*', '(' ,')' ,'~', '`', 92, 39};         
         //'\ ', "'",  '`'

        combo(len, wdlist, "", hashed);
        
    }
}