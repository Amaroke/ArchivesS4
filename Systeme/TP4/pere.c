#include <unistd.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>

#define ERREUR_CREATION 1
#define ERREUR_RECOUVREMENT 2

int main(int argc, char **argv)
{
    int p[2];
    int pipe(int[2]);
    if (pipe(p) == -1)
    {
        fprintf(stderr, "erreur ouverture tube.\n)");
        exit(1);
    }
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
        char c;
        char *str = malloc(sizeof(char)*1024);
        int i = 0;
        int nb_lu=0;
        close(p[1]);
        while (read(p[0], &c, 1) != 0)
        {
            if (c >= 'A' && c <= 'Z')
            {
                str[i] = c;
                i++;
            }
            nb_lu++;
        }
        close(p[0]);
        printf("Il y a eu %i caractères lu par le fils.\n", nb_lu);
        execl("./recouvrant", "recouvrant", str, NULL);
        perror("Recouvrement impossible !");
        resultat = ERREUR_RECOUVREMENT;
    }
    else
    {
        // On est dans le père
        int i = 0;
        close(p[0]);
        while (argv[1][i] != '\0')
        {
            write(p[1], &argv[1][i], 1);
            i++;
        }
        close(p[1]);
        printf("Il y a eu %i caractères envoyés par le père.\n", i);
        exit(0);
    }
    return resultat;
}