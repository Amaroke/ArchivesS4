#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>

#define ERREUR_CREATION 1
#define ERREUR_RECOUVREMENT 2
#define N 1000000
#define MAXFORK 21

void repart_tab(int *tab, int n, int val)
{
  int cpt = 0;
  for (int i = 0; i < n; i++)
  {
    if (tab[i] == val)
    {
      cpt++;
    }
  }
  int pourcent = (int)cpt * 100 / n;
  printf("%i : %i%%\n", val, pourcent);
}

int main()
{
  srand(time(NULL));
  int *tab = (int *)malloc(N * sizeof(int));
  for (int i = 0; i < N; i++)
  {
    tab[i] = rand() % 21;
  }
  int pid[MAXFORK];
  int i, j;
  int fini;
  for (i = 0; i < MAXFORK; i++)
  {
    pid[i] = fork();
    if (pid[i] == -1)
    {
      perror("Création impossible !");
      return EXIT_FAILURE;
    }
    else if (pid[i] == 0)
    {
      // Fils
      sleep(1);
      repart_tab(tab, N, i);
      free(tab);
      exit(0);
    }
    else
    {
      // Père
    }
  }
  free(tab);
  printf("\n");
  return EXIT_SUCCESS;
}