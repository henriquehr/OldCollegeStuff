
all: compile
	
compile:
	clear 
	gcc  	-o  s -O3 sequencial.c
	gcc  	-o  p -O3 pthread.c -lpthread
	gcc  	-o  o -O3 openMP.c -fopenmp
	mpicc -o m -O3 MPI.c 
	nvcc -o c -O3 -arch=sm_20 CUDA.cu

	@echo ""
	@echo "Compilaçao concluida"
	@echo ""
	@echo "( ͡° ͜ʖ ͡°)"
	@echo ""
	@echo "Comandos para Executar:"
	@echo "./s 10000"
	@echo "./p 10000 10"
	@echo "./o 10000 10"
	@echo "mpirun -np 10 ./m 10000"
	@echo "./c 10000 10"
	@echo ""

clean:
	rm -rf ?



