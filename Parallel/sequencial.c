#include "pilhaAlcalina.h"
#include "tempo.h"


int NUMTHREAD, TAMVETOR;

int main(int argc,char *argv[]){
	if(argc != 2){
	 	printf("NOME DO EXECUTAVEL + TAMANHO DA PILHA\n");
	 	return 1;
	}
	tempo1();
	
	srand(time(NULL));
	
	TAMVETOR = atoi(argv[1]);
	if(TAMVETOR%2!=0){
		printf(" COISEIA UM NUMERO PAR DE THREADS E TAMANHO DA PILHA  \n");
		return 1;
	}
	struct Pilha pilha;

	criarPilha(&pilha, TAMVETOR);
	
	int i;
	for(i=0;i<TAMVETOR;i++){
		empilhar (&pilha, rand()%TAMVETOR);
	}
	
	ordenarCres(&pilha);
	tempo2();

	//mostrar(&pilha);

	tempoFinal("mili segundos", "", MSGLOG);
}
