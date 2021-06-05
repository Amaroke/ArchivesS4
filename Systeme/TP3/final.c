#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <time.h>
#include <fcntl.h>

int taille_repertoire(char *nom, int niveau)
{
    struct stat s;
    char ref[1024];
    strcpy(ref, nom);
    char path[1024];
    DIR *directory = opendir(ref);
    int taille_totale = 0;
    if (directory == NULL)
    {
        perror("Erreur lors de l'ouverture !\n");
        exit(1);
    }
    struct dirent *fichierLu;
    while ((fichierLu = readdir(directory)) != NULL)
    {
        sprintf(path, "%s/%s", nom, fichierLu->d_name); //rep ou fichier à analyser avec stat
        strcpy(ref, path);
        if (stat(ref, &s) == -1)
        {
            perror("Le fichier/répertoire n'existe pas !\n");
            exit(1);
        }
        if (S_ISREG(s.st_mode))
        {
            for (int i = 0; i < niveau; ++i)
            {
                printf(" ");
            }
            printf("F %s (%li o)\n", ref, s.st_size);
            fflush(stdout);
            taille_totale += s.st_size;
        }
        else if (S_ISDIR(s.st_mode))
        {
            if (strcmp(fichierLu->d_name, ".") != 0 && strcmp(fichierLu->d_name, "..") != 0)
            {
                for (int i = 0; i < niveau; ++i)
                {
                    printf(" ");
                }
                printf("D %s\n", ref);
                fflush(stdout);
                taille_totale += taille_repertoire(ref, niveau + 1);
            }
        }
    }
    if (closedir(directory) == -1)
    {
        perror("Erreur lors de la fermeture !\n");
        exit(1);
    }
    return taille_totale;
}

int main(int argc, char **argv)
{
    printf("Taille : %i\n", taille_repertoire(argv[1], 0));
}