package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		String caminho = "C:\\Users\\Gilliano\\eclipse-projetos\\StreamExercicios\\funcionario.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			List<Funcionario> lista = new ArrayList<>();
			String linha = br.readLine();
			while (linha != null) {
				String[] campos = linha.split(",");
				lista.add(new Funcionario(campos[0], campos[1], Double.parseDouble(campos[2])));
				linha = br.readLine();
			}

			System.out.println("Entre com o salário: ");
			Double salario = sc.nextDouble();
			System.out.println("Email de pessoas com salário maior que " + String.format("%.2f", salario));
			List<String> listaemail = lista.stream().filter(f -> f.getSalario() > salario).map(f -> f.getEmail())
					.sorted().collect(Collectors.toList());

			listaemail.forEach(System.out::println);

			Double somaSalario = lista.stream().filter(f -> f.getNome().charAt(0) == 'M').map(f -> f.getSalario())
					.reduce(0.0, (x, y) -> x + y);
			System.out.println("A soma dos salários de pessoas que começam com a letra M: " + String.format("%.2f", somaSalario));

		} catch (IOException e) {
			System.out.println("Erro" + e.getMessage());

		}
		sc.close();

	}

}
