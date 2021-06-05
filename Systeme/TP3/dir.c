#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <time.h>
#include <fcntl.h>

int main(int argc, char **argv)
{
    struct stat s;
    char ref[1024];
    strcpy(ref, argv[1]);
    char path [1024];
    DIR *directory = opendir(ref);
    int taille_totale;
    if (directory == NULL)
    {
        perror("Erreur lors de l'ouverture !\n");
        exit(1);
    }
    struct dirent *fichierLu;
    while ((fichierLu = readdir(directory)) != NULL)
    {
        sprintf(path,"%s/%s",argv[1], fichierLu->d_name); //rep ou fichier à analyser avec stat 
        strcpy(ref, path); 
        if (stat(ref, &s) == -1)
        {
            perror("Le fichier/répertoire n'existe pas !\n");
            exit(1);
        }        
        if (S_ISREG(s.st_mode))
        {
            // FICHIER
            printf("Le fichier lu s'appelle '%s', son inode est : %li.\n", fichierLu->d_name, fichierLu->d_ino);
            taille_totale+=s.st_size;
        }
        else if (S_ISDIR(s.st_mode))
        {
            // DOSSIER
            printf("Le dossier lu s'appelle '%s', son inode est : %li.\n", fichierLu->d_name, fichierLu->d_ino);
        }
    }
    printf("La taille totale est de : %i\n", taille_totale);
    if (closedir(directory) == -1)
    {
        perror("Erreur lors de la fermeture !\n");
        exit(1);
    }
}