#include <unistd.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("%d (père : %d) DEBUT !\n", getpid(), getppid());
    int tab[1000000] = {0};

    for (int i = 0; i < 10000; ++i)
    {
        for (int j = 0; j < 1000000; ++i)
        {
            tab[j] = tab[j] + 1;
        }
        if (i % 1000 == 0)
        {
            printf("Nb de parcours : %d.\n", i);
        }
    }
    printf("%d (père : %d) FIN !\n", getpid(), getppid());
    exit(0);
}
