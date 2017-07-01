  
#include <stdio.h>     
#include <stdlib.h> 

struct Pilha {
	int topo;
	int capa;
	int *pElem;
};

void criarPilha( struct Pilha *p, int c ){
   p->topo = -1;
   p->capa = c;
   p->pElem = (int*) malloc (c * sizeof(int));
}

int estaVazia ( struct Pilha *p ){
   if( p-> topo == -1 )
      return 1;
   else
      return 0;
}

int estaCheia ( struct Pilha *p ){
	if (p->topo == p->capa - 1)
		return 1;
	else
		return 0;
}

void empilhar ( struct Pilha *p, int v){
	p->topo++;
	p->pElem [p->topo] = v;
}

int desempilhar ( struct Pilha *p ){
   int aux = p->pElem [p->topo];
   p->topo--;
   return aux;
}

int retornaTopo ( struct Pilha *p ){
   return p->pElem [p->topo];
}

void copiarDeParaDeAte(struct Pilha *de, struct Pilha *para, int deI, int ateI){
	int i = 0;
	struct Pilha pAux0;
	struct Pilha pAux1;
        criarPilha(&pAux0, de->capa);
        criarPilha(&pAux1, (ateI-deI)+1);
	while(estaVazia(de) == 0){
		if(i>=deI && i<ateI){
			empilhar(&pAux1,desempilhar(de));
		}else{
			empilhar(&pAux0,desempilhar(de));
		}
		if(i==ateI){
			break;
		}
		i++;
	}
	while(estaVazia(&pAux1) == 0){
		empilhar(para,desempilhar(&pAux1));
	}
	while(estaVazia(&pAux0) == 0){
		empilhar(de,desempilhar(&pAux0));
	}
}

void ordenarCres( struct Pilha *p ) {
        if (estaVazia(p) == 0) {
            struct Pilha pAux0;
            struct Pilha pAux1;
            criarPilha(&pAux0, p->capa);
            criarPilha(&pAux1, p->capa);
            int maior, temp;
            while (estaVazia(p) == 0) {
                maior = desempilhar(p);
                while (estaVazia(p) == 0) {
                    temp = desempilhar(p);
                    if (temp > maior) {
                        empilhar(&pAux0, maior);
                        maior = temp;
                    } else {
                        empilhar(&pAux0, temp);
                    }
                }
                empilhar(&pAux1, maior);
                while (estaVazia(&pAux0) == 0) {
                    empilhar(p, desempilhar(&pAux0));
                }
            }
            while (estaVazia(&pAux1) == 0) {
                empilhar(p, desempilhar(&pAux1));
            }
        }
}

void mostrar(struct Pilha *p){
	int i = 0;
	struct Pilha pAux;
        criarPilha(&pAux, p->capa);
	while(estaVazia(p)==0){
		i = desempilhar(p);
		printf("\t pos-%d: \t %d\n",p->topo+1,i);
		empilhar(&pAux,i);
	}
	while(estaVazia(&pAux)==0){
		empilhar(p,desempilhar(&pAux));
	}
}
