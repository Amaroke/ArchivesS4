#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>

#define ERREUR_CREATION 1
#define ERREUR_RECOUVREMENT 2

int main(int argc, char **argv)
{
    int resultat = EXIT_SUCCESS;
    pid_t ret = fork();
    printf("fils 1 : %d (père : %d)\n", getpid(), getppid());
    if (ret == -1)
    {
        perror("Création impossible !");
        resultat = ERREUR_CREATION;
    }
    else if (ret == 0)
    {
        //On est dans le fils
        execl("./recouvrant", "recouvrant", "2", NULL);
        perror("Recouvrement impossible ");
        resultat = ERREUR_RECOUVREMENT;
        return 0;
    }

    pid_t ret2 = fork();
    printf("fils 2 : %d (père : %d)\n", getpid(), getppid());
    if (ret2 == -1)
    {
        perror("Création impossible !");
        resultat = ERREUR_CREATION;
    }
    else if (ret2 == 0)
    {
        //On est dans le fils 2
        execl("./recouvrant", "recouvrant", "5", NULL);
        perror("Recouvrement impossible ");
        resultat = ERREUR_RECOUVREMENT;
    }

    int status;
    waitpid(getpid(), &status, 0);
    int exit_status = WEXITSTATUS(status);
    printf("Le fils avec le pid %d se termine avec : %d\n", getpid(), exit_status);
    return resultat;
}
