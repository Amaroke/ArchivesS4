#include <unistd.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>

#define ERREUR_CREATION 1
#define ERREUR_RECOUVREMENT 2

int main(int argc, char **argv)
{
    int resultat = EXIT_SUCCESS;
    pid_t ret = fork();
    printf("%d (père : %d)\n", getpid(), getppid());
    if (ret == -1)
    {
        perror("Création impossible !");
        resultat = ERREUR_CREATION;
    }
    else if (ret == 0)
    {
        //On est dans le fils
        execl("./recouvrant", "recouvrant", argv[1], argv[2], NULL);
        perror("Recouvrement impossible !");
        resultat = ERREUR_RECOUVREMENT;
    }
    return resultat;
}
