import java.util.Scanner;

public class testes{

    public static boolean achaproduto(String Carrinho[],String produto){
        for(int j = 0;j<Carrinho.length;j++){
            if(Carrinho[j].equals(produto)){
                return true;
            }
        }
        return false;
    }
    public static void main(String args[]){

        Scanner entrada = new Scanner(System.in);

       int qtd = entrada.nextInt();
       entrada.nextLine();

       String nomes[] = new String[qtd];
       int quantidades[] = new int[qtd];

       for(int i=0; i < qtd; i++){
        String linha = entrada.nextLine();
        String[] partes = linha.split(" ");

        nomes[i] = partes[0];
        quantidades[i] = Integer.parseInt(partes[1]);
       }

       while(true){
        String produto = entrada.nextLine();

        if(produto.equals("fim")){
            for(int k=0;k<quantidades.length;k++){
                System.out.printf("%s %d\n",nomes[k],quantidades[k]);
            }
            break;
        }

        boolean touf = achaproduto(nomes,produto);

        if(touf == true){
          int quantidade = entrada.nextInt();
          entrada.nextLine();

            for(int k=0;k<quantidades.length;k++){
                if(nomes[k].equals(produto)){
                    quantidades[k] -=quantidade;
                    break;
                }
            }
            System.out.printf("%s %d\n",produto,quantidade);
            continue;

        }else{

            System.out.printf("%s produto não encontrado\n",produto);

            }
        }
    }
}
