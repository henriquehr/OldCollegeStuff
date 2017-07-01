
#include <omp.h>
#include "pilhaAlcalina.h"
#include "tempo.h"

void * criarPilhasThreads(int inicio);
void * copiarPilhasMenores(int i);

int NUMTHREAD, TAMVETOR;

struct Pilha pilha;
struct Pilha * p;

int main(int argc,char *argv[]){
	if(argc != 3){
	 	printf("NOME DO EXECUTAVEL + TAMANHO DA PILHA + NUMERO DE THREADS\n");
	 	return 1;
	}
	
	
	
	srand(time(NULL));
	
	TAMVETOR = atoi(argv[1]);
	NUMTHREAD = atoi(argv[2]);
	
	pthread_t  pidt[NUMTHREAD];
	
	if(NUMTHREAD%2!=0 || TAMVETOR%2!=0){
		printf(" COISEIA UM NUMERO PAR DE THREADS E TAMANHO DA PILHA  \n");
		return 1;
	}
	double a1  = NUMTHREAD,a2 = TAMVETOR;
	if((a2/a1) != TAMVETOR/NUMTHREAD){
		printf(" NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO \n");
		printf(" TAMVETOR/NUMTHREAD DEVE DAR UM NUMERO INTEIRO \n");
		return 1;
	}
	
	omp_set_num_threads (NUMTHREAD);
	
	tempo1();
	
	criarPilha(&pilha, TAMVETOR);
	struct Pilha p1[NUMTHREAD][TAMVETOR];
	p = *p1;
	
	int i;
	
	#pragma omp parallel 
	{
		#pragma omp for private (i)
		for(i=0;i<TAMVETOR;i++){
			#pragma omp critical
			empilhar (&pilha, rand()%TAMVETOR);
		}	
		
		#pragma omp for private (i) 
		for(i=0;i<NUMTHREAD;i++){
			criarPilhasThreads(i);
		}
	
		#pragma omp for private (i)
		for(i=0;i<NUMTHREAD;i++){
			#pragma omp critical
			copiarPilhasMenores(i);
		}
	
		#pragma omp for private (i)
		for(i=0;i<NUMTHREAD;i++){
			ordenarCres(&p[i]);
		}
	
	}
	int maior = -1, u, temp;
	struct Pilha pilhaFinal;
	criarPilha(&pilhaFinal, TAMVETOR);
	
	while(estaCheia(&pilhaFinal) == 0){//malditomalditomalditomalditomalditomalditomalditomalditomalditomaldito
		for (i=0; i < NUMTHREAD; i ++)    { 
			if(estaVazia(&p[i]) == 0){
				maior = desempilhar(&p[i]);
			}else{
				maior = -1;
			}
			for(u=0; u < NUMTHREAD; u++)    {
				if(estaVazia(&p[u]) == 0){
					temp = desempilhar(&p[u]);
					if(maior < temp){
						if(maior != -1){
							empilhar(&p[i],maior);
						}
						maior = temp;
					}else{
						empilhar(&p[u],temp);
					}
				}else{
					temp = -1;
				}
			}
			if(maior != -1){
				empilhar(&pilhaFinal, maior);
			}
		}
	}
	
	tempo2();
	
	//mostrar(&pilhaFinal);

	tempoFinal("mili segundos", "", MSGLOG);
}

void * criarPilhasThreads(int i){
	criarPilha(&p[(i)],(TAMVETOR/NUMTHREAD));
}

void * copiarPilhasMenores(int i){
	copiarDeParaDeAte(&pilha, &p[i], 0, TAMVETOR/NUMTHREAD);
}
