#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <time.h>
#include <fcntl.h>
#include <stdbool.h>

int main(int argc, char **argv)
{
    int tube0 = atoi(argv[1]); // p[0]
    int tube1 = atoi(argv[2]); // p[1]
    close(tube1);
    char temp_char;
    int cpt = 0;
    fflush(stdout);
    printf("Bonjour %s !\n", argv[0]);
    while(read(tube0, &temp_char, sizeof(char)) > 0)
    {
        if (temp_char != 'e')
        {
            cpt++;
        }
        else
        {
            if(argv[0][0] == 'A'){
                printf("Averell mange %i cailloux...\n", cpt);
            }
            else {
                printf("%s a cassé %i cailloux...\n", argv[0], cpt);
            }
            return EXIT_SUCCESS;
        }
    }
    return EXIT_SUCCESS;
}