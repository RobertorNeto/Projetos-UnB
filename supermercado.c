/* projeto apc supermercado */

#include<stdio.h>
#include<string.h>

struct produto{
char nome[101];
int quantidade;
double preco;
};

typedef struct produto Produto;
Produto carrinho[10000];
int qtdprodutos;
double orcamento;

void mostrarcarrinho(){
  int i,j,k;
  double valorprod,total;
  Produto aux;
  total = 0;
  
  for(i=0;i<qtdprodutos-1;i++){
    for(j=i+1;j<qtdprodutos;j++){
      if(strcmp(carrinho[i].nome,carrinho[j].nome) > 0){  
        aux = carrinho[j];
        carrinho[j] = carrinho[i];
        carrinho[i] = aux;
      }
    }
  }
  
  for(k=0;k<qtdprodutos;k++){
    if(carrinho[k].quantidade == 0){
      continue;
    }
    valorprod = 0; 
    valorprod = carrinho[k].quantidade * carrinho[k].preco;
    total = total + valorprod;
    printf("%s %d x %.2lf = %.2lf\n",carrinho[k].nome,carrinho[k].quantidade,carrinho[k].preco,valorprod);
  }
  printf("TOTAL: %.2lf\n",total);
}

void comprar(char nome[],double preco,int quantidade){
  int i,indice;
  double custo;
  indice = -1;

  if(orcamento<preco){
    return;
  }
  
  for(i=0;i<qtdprodutos;i++){
    if(strcmp(carrinho[i].nome,nome) == 0){
      indice = i;
    }
  }
  
  if(indice == -1){
    indice = qtdprodutos;
    qtdprodutos ++;
  }

  strcpy(carrinho[indice].nome,nome);
  carrinho[indice].preco = preco;

  custo = quantidade * preco;
  while(custo>orcamento){
    quantidade--;
    custo = quantidade * preco;
  }
  
  if(quantidade>0){
    carrinho[indice].quantidade = quantidade;
    orcamento = orcamento - custo;
  }
}

void remover(char nome[],int quantidade){
  int i;
  int indice;
  indice = -1;

  for(i=0;i<qtdprodutos;i++){
    if(strcmp(carrinho[i].nome,nome) == 0){
      indice = i;
    }
  }

  if(indice == -1|| carrinho[indice].quantidade == 0){
    printf("ERRO: O produto %s nao esta no carrinho\n",nome);
    return;
  }

  if(quantidade > carrinho[indice].quantidade){
    quantidade = carrinho[indice].quantidade;
  }

  if(carrinho[indice].quantidade == 0){
      for(i=indice;i<qtdprodutos-1;i++){
        carrinho[i] = carrinho[i+1];
      }
    qtdprodutos--;
  }

  carrinho[indice].quantidade -= quantidade;
  orcamento += quantidade * carrinho[indice].preco;
}

void atualizarpreco(char nome[],double novopreco){
  int i;
  int indice;
  double novocusto,custoatual,diferenca;
  indice = -1;

  for(i=0;i<qtdprodutos;i++){
    if(strcmp(carrinho[i].nome,nome) == 0){
      indice = i;
    }
  }

  if(indice == -1||carrinho[indice].quantidade == 0){
    printf("ERRO: O produto %s nao esta no carrinho\n",nome);
    return;
  }

  custoatual = carrinho[indice].quantidade * carrinho[indice].preco;
  novocusto = carrinho[indice].quantidade * novopreco;
  carrinho[indice].preco = novopreco;

  if(novocusto > custoatual){
    diferenca = novocusto - custoatual;
    while(diferenca > orcamento){
      carrinho[indice].quantidade--;
      novocusto = carrinho[indice].quantidade * novopreco;
      diferenca = novocusto - custoatual;
    }
  }
  orcamento += custoatual - novocusto;
  
  if(carrinho[indice].quantidade == 0){
    for(i=indice;i<qtdprodutos-1;i++){
      carrinho[i] = carrinho[i+1];
    }
    qtdprodutos--;
  }
}

int main(){
  int operacoes,quantidade,i;
  double preco,novopreco;
  char OP,nome[100];
  
  scanf("%d %lf",&operacoes,&orcamento);
  qtdprodutos = 0;
  for(i=0;i<operacoes;i++){
    getchar();
    scanf("%c",&OP);

    if(OP == 'C'){
      scanf("%s %lf %d",nome,&preco,&quantidade);
      comprar(nome,preco,quantidade);
    }else if(OP == 'R'){
      scanf("%s %d",nome,&quantidade);
      remover(nome,quantidade);
    }else if(OP == 'A'){
      scanf("%s %lf",nome,&novopreco);
      atualizarpreco(nome,novopreco);
    }else if(OP == 'M'){
      mostrarcarrinho();
    }
  }
  return 0;
}
