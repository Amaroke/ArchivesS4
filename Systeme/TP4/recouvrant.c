#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
    int i = 0;
    while (argv[1][i] != '\0')
    {
        printf("%c", argv[1][i]+32);
        printf("%c", argv[1][i]+32);
        i++;
    }
    printf("\n");
    fflush(stdout);
    return EXIT_SUCCESS;
}