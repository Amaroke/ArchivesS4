#include <stdio.h> 
#include <stdlib.h>

int main(int argc, char **argv)
{
    char *tab = "Test";
    printf("Attention, il va y avoir une erreur de segmentation");
    fflush(stdout);
    tab[15] = 12;
    exit(0);
}