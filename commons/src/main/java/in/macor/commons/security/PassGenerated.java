/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.macor.commons.security;

import java.util.Random;
import org.joda.time.LocalDate;

/**
 *
 * @author macorin
 */
public class PassGenerated {

    private static final int SIZE_ADMIN_PASSWORD = 6;

    /**
     * Gera a senha de administrador a partir da data atual do servidor.
     * Utilizado para validar a senha do admin no login assim não deixando
     * a senha fixa no banco de dados.
     * @return
     */
    public static String generatedAdminPassword() {
        return generated(new LocalDate(), SIZE_ADMIN_PASSWORD);
    }

    /**
     * Gera uma senha a partir da data passada como parametro.
     * Utilizada para gerar a hash de validação da licença da empresa.
     * @param baseDate
     * @param size
     * @return
     */
    public static String generated(LocalDate baseDate, int size) {
        int a = 'm';
        int b = 'A';
        int c = 'C';
        int d = 'o';
        int e = 'R';

        baseDate = baseDate.plusDays(1);

        StringBuilder strPass = new StringBuilder();

        strPass.append(b);
        strPass.append(baseDate.dayOfYear().get());
        strPass.append(a);
        strPass.append(baseDate.dayOfWeek().get());
        strPass.append(e);
        strPass.append(d);
        strPass.append(baseDate.dayOfMonth().get());
        strPass.append(c);

        char[] chart = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        char[] password = new char[size];

        int chartLenght = chart.length;
        Random rdm = new Random(new Long(strPass.toString()));

        for (int x = 0; x < size; x++) {
            password[x] = chart[rdm.nextInt(chartLenght)];
        }

        return new String(password);
    }
}
