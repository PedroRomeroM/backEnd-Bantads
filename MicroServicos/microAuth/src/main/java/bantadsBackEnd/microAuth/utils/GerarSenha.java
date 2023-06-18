package bantadsBackEnd.microAuth.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.MessageDigest;

public class GerarSenha {

    public static String generateRandomPassword(int size) {

        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static String encrypt(String senha) throws NoSuchAlgorithmException {

        String password = senha;
        String salt = generateRandomPassword(8);

        String saltedPassword = password + salt;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(saltedPassword.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte hashedByte : hashedBytes) {
            sb.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString()+"?"+salt;
    }

//    public static String extractPassword() throws NoSuchAlgorithmException {
//        String texto = encrypt();
//
//        int posicaoInterrogacao = texto.indexOf("?"); // Encontra a posição do caractere "?"
//        String extractedPassword = texto.substring(0, posicaoInterrogacao); // Extrai a substring até o caractere "?"
//
//        return extractedPassword;
//    }

    public static String extractSalt(String senhaSaltada) throws NoSuchAlgorithmException {
        String texto = senhaSaltada;

        int posicaoInterrogacao = texto.indexOf("?"); // Encontra a posição do caractere "?"
        String extractedSalt = texto.substring(posicaoInterrogacao + 1); // Extrai a substring após o caractere "?"
        extractedSalt = extractedSalt.trim(); // Remove espaços em branco adicionais no início

        return extractedSalt;
    }

    public static boolean verifyUserPassword(String password, String salt, String hash) throws NoSuchAlgorithmException {


        String saltedPassword = password + salt;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(saltedPassword.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte hashedByte : hashedBytes) {
            sb.append(String.format("%02x", hashedByte));
        }

        if ((sb.toString()+"?"+salt).equals(hash)){
            return true;
        }
        return false;

    }
}