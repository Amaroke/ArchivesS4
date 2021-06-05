#include <stdio.h>
#include <stdlib.h>
#include <string.h>
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
    if (stat(ref, &s) == -1)
    {
        perror("Le fichier/répertoire n'existe pas !\n");
        exit(1);
    }
    char *dern_modif = ctime(&s.st_mtime);
    char *dern_acces = ctime(&s.st_atime);
    if (S_ISREG(s.st_mode))
    {
        // FICHIER
        printf("C'est un fichier ! i-node : %li\nTaille (en octets) : %li\nDernière modification : %sDernier accès : %s", s.st_ino, s.st_size, dern_modif, dern_acces);
        int fichier = open(ref, O_RDONLY);
        if (fichier == -1)
        {
            perror("Le fichier ne peur s'ouvrir !\n");
            exit(1);
        }
        char tab[1];
        for (int i = 0; i < 100; ++i)
        {
            read(fichier, &tab, 1);
            printf("%c", tab[0]);
        }
        close(fichier);
    }
    else if (S_ISDIR(s.st_mode))
    {
        // DOSSIER
        printf("C'est un dossier ! i-node : %li\nDernière modification : %sDernier accès : %s", s.st_ino, dern_modif, dern_acces);
    }
}