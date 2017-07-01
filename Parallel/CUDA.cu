#include <stdio.h>     
#include <stdlib.h> 
#include <unistd.h>
#include <cuda.h>
#include "tempo.h"

void mostrar(int *p, int capa);

__global__ void ordenarPilhaCres(int *p, int *pAux0, int *pAux1, int topo, int NT){	
	int idx = blockIdx.x * blockDim.x + threadIdx.x;
	int midx = idx*NT;
	int topoA0 = (midx)-1;
	int topoA1 = (midx)-1;
	topo = (topo*idx+NT)-1;
	if (topo != (midx)-1) {
            int maior, temp;
            while (topo != (midx)-1) {
                maior = p[topo];
		topo--;
                while (topo != (midx)-1) {	
                    temp = p[topo];
		    topo--;
                    if (temp > maior) {
			topoA0++;
			pAux0[topoA0] = maior;
                        maior = temp;
                    } else {
                        topoA0++;
			pAux0[topoA0] = temp;
                    }
                }
                topoA1++;
		pAux1[topoA1] = maior;
                while (topoA0 != (midx)-1) {
			topo++;
			p[topo] = pAux0[topoA0];
   			topoA0--;
                }
            }
            while (topoA1 != (midx)-1) {
			topo++;
			p[topo] = pAux1[topoA1];
   			topoA1--;
            }
        }
}

int main(int argc,char *argv[]){
	if(argc != 3){
		 printf("NOME DO EXECUTAVEL + TAMANHO DA PILHA + NUMERO DE THREADS \n");
		 return 1;
	}

	int TAMVETOR, NUMTHREAD, TAMMENORES;
	TAMVETOR = atoi(argv[1]);
	NUMTHREAD = atoi(argv[2]);
	TAMMENORES = TAMVETOR/NUMTHREAD;
	int gridSize, blockSize;
	blockSize = NUMTHREAD/2;
	gridSize = 2;


	if(NUMTHREAD>1000){
		printf(" MAX 1000 THREADS  \n");
		return 1;
	}
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

	tempo1();

	srand(time(NULL));
	int i;

	int *h_Pilha;
	int *h_Topo;
	int *h_Min;

	int *d_PilhaAux0;
	int *d_PilhaAux1;
	int *d_Pilha;

	int TAMVETOR_bytes = (TAMVETOR)*sizeof(int);
	int NUMTHREAD_bytes = NUMTHREAD*sizeof(int);

	h_Pilha = (int *) malloc(TAMVETOR_bytes);
	h_Topo = (int *) malloc(NUMTHREAD_bytes);
	h_Min = (int *) malloc(NUMTHREAD_bytes);

	cudaMalloc( (void**)&d_Pilha, TAMVETOR_bytes);
	cudaMalloc( (void**)&d_PilhaAux0, TAMVETOR_bytes);
	cudaMalloc( (void**)&d_PilhaAux1, TAMVETOR_bytes);


	for(i = 0; i < TAMVETOR; i++){
		h_Pilha[i] = (rand()%TAMVETOR);
	}

	cudaMemcpy(d_Pilha, h_Pilha, TAMVETOR_bytes, cudaMemcpyHostToDevice);

	ordenarPilhaCres<<<gridSize,blockSize>>>(d_Pilha, d_PilhaAux0, d_PilhaAux1, TAMMENORES, TAMMENORES);

	cudaMemcpy(h_Pilha, d_Pilha, TAMVETOR_bytes, cudaMemcpyDeviceToHost);

	for(i = 0; i < NUMTHREAD; i++){
		h_Topo[i] = ((TAMMENORES)*(i+1))-1;
		h_Min[i] = (TAMMENORES*i);
	}

	int maior = -1, u, temp;
	int *pilhaFinal;
	pilhaFinal = (int *) malloc(TAMVETOR_bytes);
	int topoFin = -1;
	while(topoFin < TAMVETOR){
		if(h_Topo[i] >= h_Min[i]){
			maior = h_Pilha[h_Topo[i]];
			h_Topo[i]-=1;
		}else{
			maior = -1;
		}
		for(u=0; u < NUMTHREAD; u++){
			if(h_Topo[u] >= h_Min[u]){
				temp = h_Pilha[h_Topo[u]];
				h_Topo[u]-=1;
				if(maior < temp){
					if(maior != -1){
						h_Topo[i]+=1;
						h_Pilha[h_Topo[i]] = maior;
					}
					maior = temp;
				}else{
					h_Topo[u]+=1;
					h_Pilha[h_Topo[u]] = temp;
				}
			}
		}
		if(maior > -1){
			topoFin+=1;
			pilhaFinal[topoFin] = maior;
		}
	}
	
	tempo2();

	//mostrar(pilhaFinal, TAMVETOR);

	tempoFinal("mili segundos", "", NULL);

    	cudaFree(d_Pilha);
    	cudaFree(d_PilhaAux0);
    	cudaFree(d_PilhaAux1);
	free(pilhaFinal);
	free(h_Topo);
	free(h_Min);
}

void mostrar(int *p, int capa){
	int t;	
	for(t = 0; t < capa; t++){
		printf("\tpos-%d:\t%d\n",t , p[t]);
	}
}
