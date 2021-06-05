#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int arg = atoi(argv[1]);
    printf("J'ai %i %s\n", arg*2, argv[2]);
    return EXIT_SUCCESS;
}