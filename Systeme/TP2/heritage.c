#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>

#define ERREUR_CREATION 1
#define ERREUR_RECOUVREMENT 2

void print_tab(int *tab)
{
    printf("[ ");
    for (int i = 0; i < 3; i++)
    {
        printf("%d, ", tab[i]);
    }
    printf("%d ]\n", tab[3]);
}

int main(int argc, char **argv)
{
    int tab[4] = {1, 1, 1, 1};
    int resultat = EXIT_SUCCESS;
    pid_t ret = fork();
    if (ret == -1)
    {
        perror("Création impossible !");
        resultat = ERREUR_CREATION;
    }
    else if (ret == 0)
    {
        //On est dans le fils
        print_tab(tab);
        for (int i = 0; i < 4; i++)
        {
            tab[i] = 4;
        }
        sleep(2);
        printf("Adresse : %p ", &tab);
        print_tab(tab);
        resultat = ERREUR_RECOUVREMENT;
        return EXIT_SUCCESS;
    }
    else
    {
        // On est dans le père
        print_tab(tab);
        for (int i = 0; i < 4; i++)
        {
            tab[i] = 2;
        }
        wait(&ret);
        printf("Adresse : %p ", &tab);
        print_tab(tab);
    }
}