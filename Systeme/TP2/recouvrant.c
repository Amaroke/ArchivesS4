#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv) {
    int arg = atoi(argv[1]);
    printf("Avant sleep %d\n", arg);
    sleep(arg);
    printf("apres sleep\n");
    return EXIT_SUCCESS;
}