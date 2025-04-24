import java.util.Scanner;

class Conta{
    private int numeroConta;
    private String nomeCliente;
    private double saldo;
    private int senha;

    public Conta(int numeroConta, int senha, String nomeCliente,double saldo){
        this.numeroConta = numeroConta;
        this.senha = senha;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }

    public int getNumConta(){
        return numeroConta;
    }

    public String getNome(){
        return nomeCliente;
    }

    public double getSaldo(int senha){
        if(this.senha == senha){
            return this.saldo;
        }else{
            return -1;
        }
    }

    public boolean sacar(double saque, int senha){
        if(this.senha != senha || saque <= 0 || saque > this.saldo){
            return false;
        }else{
            this.saldo -= saque;
            return true;
        }
    }

    public boolean depositar(double deposito, int senha){
        if(this.senha != senha || deposito <= 0){
            return false;
        }else{
            this.saldo += deposito;
            return true;
        }
    }

    public boolean transferencia(double valor, int senha, Conta conta2){
        if(this.senha != senha || valor <= 0 || valor > this.saldo){
            return false;
        }else{
            this.saldo -= valor;
            conta2.depositar(valor,conta2.senha);
            return true;
        }
    }
}


public class testes{
    public static void main(String args[]){
        Scanner entrada = new Scanner(System.in);
        int opcao = 0;
        int senha;

        String input1 = entrada.nextLine();
        String input2 = entrada.nextLine();

        String[] partes1 = input1.split(" ");
        String[] partes2 = input2.split(" ");

        Conta conta1 = new Conta(Integer.parseInt(partes1[0]),Integer.parseInt(partes1[1]),partes1[2], Double.parseDouble(partes1[3]));
        Conta conta2 = new Conta(Integer.parseInt(partes2[0]),Integer.parseInt(partes2[1]),partes2[2], Double.parseDouble(partes2[3]));

        while(opcao != 5){
            opcao = entrada.nextInt();
            entrada.nextLine();
            if(opcao == 1){
                senha = entrada.nextInt();
                entrada.nextLine();
                double valor = conta1.getSaldo(senha);
                if(valor == -1){
                    System.out.printf("senha incorreta\n");
                }else{
                    System.out.printf("%.2f\n",valor);
                }

            }else if(opcao == 2){
                String valores = entrada.nextLine();
                String[] partes = valores.split(" ");
                boolean bool = conta1.sacar(Double.parseDouble(partes[0]),Integer.parseInt(partes[1]));
                if(bool == false){
                    System.out.printf("saque não realizado\n");
                }else{
                    System.out.printf("saque realizado\n");
                }

            }else if(opcao == 3){
                String valores = entrada.nextLine();
                String[] partes = valores.split(" ");
                boolean bool = conta1.depositar(Double.parseDouble(partes[0]),Integer.parseInt(partes[1]));
                if(bool == false){
                    System.out.printf("depósito não realizado\n");
                }else{
                    System.out.printf("depósito realizado\n");
                }
            }else if(opcao == 4){
                String nome = entrada.nextLine();
                if(nome.equals(conta2.getNome()) == false){
                    System.out.printf("nenhum usuário encontrado\n");

                }else{
                    String valores = entrada.nextLine();
                    String[] partes = valores.split(" ");
                    boolean bool = conta1.transferencia(Double.parseDouble(partes[0]),Integer.parseInt(partes[1]),conta2);
                    if(bool == false){
                        System.out.printf("transferência não realizada\n");
                    }else{
                        System.out.printf("transferência realizada\n");
                    }
                }
            }else if(opcao == 5){
                break;
            }
        }
        entrada.close();
 }
}
