package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;


public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.next();

        // path para o arquivo: src/resources/in.csv
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            Set<Sale> sales = new HashSet<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                Sale obj = new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]),
                        fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4]));

                if (sales.contains(obj)) {
                    sales.stream().filter(s -> s.getSeller().contains(obj.getSeller())).forEach(x -> x.setTotal(x.getTotal() + obj.getTotal()));
                } else {
                    sales.add(obj);
                }
                line = br.readLine();
            }

            System.out.println("\nTotal de vendas por vendedor:");

            sales.forEach(System.out::println);

        }
        catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        sc.close();

    }
}
