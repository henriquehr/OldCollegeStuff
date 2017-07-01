
#include "mpi.h"
#include "pilhaAlcalina.h"
#include "tempo.h"

int main(int argc,char *argv[]){
	srand(time(NULL));
	int NUMTHREAD, TAMVETOR;

	int myid, numprocs, TAMMENORES ;
	double mypi;

	int i;
	
	MPI_Status status;
	MPI_Init(&argc,&argv);
	
	MPI_Comm_size(MPI_COMM_WORLD,&numprocs);
	MPI_Comm_rank(MPI_COMM_WORLD,&myid);
	
	struct Pilha pilha;
	
	TAMVETOR = atoi(argv[1]);
	NUMTHREAD = numprocs;
	TAMMENORES= TAMVETOR/NUMTHREAD;
	int topoMenor = TAMMENORES-1;
	int capaMenor = TAMMENORES;

	criarPilha(&pilha, TAMVETOR);
	
	struct Pilha p[NUMTHREAD][TAMMENORES];
	
	for(i=0;i<NUMTHREAD;i++){
		criarPilha(p[i], TAMMENORES);
	}

	if(myid == 0){
		if(argc != 2){
		 	printf("NOME DO EXECUTAVEL + TAMANHO DA PILHA \n");
		 	return 1;
		}
	
		if(NUMTHREAD%2!=0 || TAMVETOR%2!=0){
			printf(" COISEIA UM NUMERO PAR DE THREADS E TAMANHO DA PILHA  \n");
			return 1;
		}
		double a1  = NUMTHREAD,a2 = TAMVETOR;
		if((a2/a1) != TAMMENORES){
			printf(" NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO NÃO \n");
			printf(" TAMVETOR/NUMTHREAD DEVE DAR UM NUMERO INTEIRO \n");
			return 1;
		}
		tempo1();
		for(i=0; i < TAMVETOR;i++){
			empilhar(&pilha, rand()%TAMVETOR);
		}
		
		for(i=0;i<NUMTHREAD;i++){
			copiarDeParaDeAte(&pilha, p[i], 0, TAMMENORES);
		}

		for(i=0;i<NUMTHREAD-1;i++){
			MPI_Send(p[i+1]->pElem, (TAMMENORES), MPI_INT, i+1, 4, MPI_COMM_WORLD);
		}

		ordenarCres(p[myid]);

		for(i=0;i<NUMTHREAD-1;i++){
        		MPI_Recv(p[i+1]->pElem, (TAMMENORES), MPI_INT, i+1, 4, MPI_COMM_WORLD, &status);
		}

		int maior = -1, u, temp;
		struct Pilha pilhaFinal;
		criarPilha(&pilhaFinal, TAMVETOR);
		
		while(estaCheia(&pilhaFinal) == 0){
			for (i=0; i < NUMTHREAD; i ++)    { 
				if(estaVazia(p[i]) == 0){
					maior = desempilhar(p[i]);
				}else{
					maior = -1;
				}
				for(u=0; u < NUMTHREAD; u++)    {
					if(estaVazia(p[u]) == 0){
						temp = desempilhar(p[u]);
						if(maior > temp){
							if(maior != -1){
								empilhar(p[i],maior);
							}
							maior = temp;
						}else{
							empilhar(p[u],temp);
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

	tempoFinal("mili segundos", "", NULL);
	}else{
		MPI_Recv(p[myid]->pElem, (TAMMENORES), MPI_INT, 0, 4, MPI_COMM_WORLD, &status);
		
		p[myid]->capa = capaMenor;
		p[myid]->topo = topoMenor;		

		ordenarCres(p[myid]);

		MPI_Send(p[myid]->pElem, (TAMMENORES), MPI_INT, 0, 4, MPI_COMM_WORLD);
	}
	
	MPI_Finalize();
}

