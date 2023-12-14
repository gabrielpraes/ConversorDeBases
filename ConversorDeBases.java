/**
 * Algoritmo de troca de bases (decimal para binário e binário para decimal) aceitando numeros com vírgula
 * @author Gabriel Praes, Lucas Gabriel, Brayan Mendes
 */

import java.util.Locale;
import java.util.Scanner;

public class ConversorDeBases {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        boasVindas();
        //menu
        System.out.println("\nDigite 1 para conversão de decimal para binário");
        System.out.println("Digite 2 para conversão de binário para decimal");
        System.out.println("Digite 0 para encerrar");

        int opcao = sc.nextInt();

        while(opcao != 0) {
            //decimal para binário
            if(opcao == 1) {
                System.out.println("Digite o numero decimal a ser convertido (em caso de numero fracionário, utilize ponto): ");
                double num = sc.nextDouble();
                System.out.println("Digite o numero de casas decimais: ");
                int casas = sc.nextInt();

                System.out.println("\nNumero em binário: " + decToBin(num, casas));
            }
            //binario para decimal
            else if(opcao == 2) {
                System.out.println("Digite o numero binário a ser convertido (em caso de numero fracionário, utilize ponto): ");
                String num = sc.next();
                if(isBin(num)) {
                    System.out.println("\nNumero em decimal: " + binToDec(num));
                }
                else {
                    System.out.println("O numero digitado não é binário ou tem mais de uma vírgula(ponto)");
                }
            }
            else {
                System.out.println("Opção inválida, tente novamente!");
            }

            //repete menu
            System.out.println("\nDigite 1 para conversão de decimal para binário");
            System.out.println("Digite 2 para conversão de binário para decimal");
            System.out.println("Digite 0 para encerrar");
            opcao = sc.nextInt();
        }

        sc.close();
    }

    /**
     * Metodo de String estático que faz a corversão de um número em decimal para binário
     * @param num numero a ser convertido
     * @param casas quantidade de casas decimais desejadas na respostas
     * @return numero convertido para binário
     */
    public static String decToBin(double num, int casas) {
        String bin = "";

        //Separar a parte inteira da parte decimal:
        int inteiro = (int) num;
        double decimal = num - inteiro;

        //montando as strings de forma separada (parte inteira e parte flutuante)
        bin += decToBinInteiro(inteiro);
        bin += '.';
        bin += decToBinDecimal(decimal, casas);

        return bin;
    }

    /**
     * Função estática que recebe um número inteiro e retorna em binário por uma String
     * @param num Número a ser convertido para binário
     * @return String: número em binário
     */
    public static String decToBinInteiro(int num) {
        int aux;
        String bin = "";

        while(num != 0) {
            aux = num % 2;            
            bin += aux;
            num = num / 2;
        }
        bin = invert(bin);
        return bin;
    }

    /**
     * Função estática que converte a parte decimal de um numero para binário
     * @param num parte decimal do numero a ser convertido para binario
     * @param casas numero de casas decimais que o numero convertido para binário terá
     * @return uma String com o numero já convertido em binário (parte depois da vírgula)
     */
    public static String decToBinDecimal(double num, int casas) {
        int count = 0;
        String bin = "";

        while(count < casas) {
            num = num*2;
            if(num >= 1) {
                bin += '1';
                num = num - 1;
            }
            else {
                bin += '0';
            }
            count++;
        }

        return bin;
    }

    /**
     * Inverte String
     * @param str String a ser invertida
     * @return String invertida
     */
    public static String invert(String str) {
        String inverted = "";
        int tam = str.length();

        for(int i = tam-1; i >= 0; i--) {
            inverted += str.charAt(i);
        }

        return inverted;
    }

    /**
     * Metodo estático double que recebe um número em binário e retorna esse número em decimal
     * @param num número a ser convertido
     * @return número convertido para binário
     */
    public static double binToDec(String num) {

        int tam = num.length();
        double dec = 0;
        int count = numAntesDaVirgula(num);

        count--;

        //numeros antes da vírgula
        for(int i = 0; i < tam; i++) {
            if(num.charAt(i) != '.') {
                String tmp = "";
                tmp += num.charAt(i);
                int aux2 = Integer.parseInt(tmp);
                dec += aux2 * Math.pow(2, (count));
                count--;
            }
        }

        return dec;
    }

    /**
     * Conta a quantidade de números antes da vírgula
     * @param num a ser contado
     * @return inteiro com a quantidade de números antes da vírgula
     */
    public static int numAntesDaVirgula(String num) {
        int resp = 0;
        int tam = num.length();

        for(int i = 0; i < tam; i++) {
            if(num.charAt(i) == '.') {
                i = tam;
            }
            else {
                resp++;
            }
        }

        return resp;
    }

    /**
     * Verifica se o numero digitado é um número binário
     * @param num Numero digitado
     * @return true se for um numero binário, false se não for um número binário
     */
    public static boolean isBin(String num) {
        boolean resp = true;
        int tam = num.length();
        int count = 0;

        for(int i = 0; i < tam; i++) {
            if(num.charAt(i) == '.') {
                count++;
            }
            else if((num.charAt(i) != '1' && num.charAt(i) != '0') || count > 1) {
                resp = false;
                i = tam;
            }
        }

        return resp;
    }

    public static void boasVindas() {
        System.out.println("-------------Bem vindo ao conversor de bases!-------------\n");
        System.out.println("Aqui você pode escolher entre conversão de decimal para binário ou de binário para decimal.");
    }  
}
