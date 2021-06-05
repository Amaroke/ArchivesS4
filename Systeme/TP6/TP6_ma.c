#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>
#include <fcntl.h>
#include <stdbool.h>

int main(int argc, char **argv)
{
    int p_joe[2];
    int p_jack[2];
    int p_william[2];
    int p_averell[2];
    if (pipe(p_joe) == -1 || pipe(p_jack) == -1 || pipe(p_william) == -1 || pipe(p_averell) == -1) // On ouvre les tubes.
    {
        fprintf(stderr, "Erreur lors de l'ouverture du tube.\n)");
        return EXIT_FAILURE;
    }
    pid_t joe = fork(); // On crée un fils.
    pid_t jack;
    pid_t william;
    pid_t averell;
    if (joe == -1)
    {
        perror("Error create");
        return EXIT_FAILURE;
    }
    else if (joe == 0)
    {
        // On est dans Joe.
        char nomTube0[10];
        sprintf(nomTube0, "%i", p_joe[0]);
        char nomTube1[10];
        sprintf(nomTube1, "%i", p_joe[1]);
        execl("./covering", "Joe", nomTube0, nomTube1, NULL);
        perror("Error execl");
        return EXIT_FAILURE;
    }
    else
    {
        jack = fork();
        if (jack == -1)
        {
            perror("Création impossible !");
            return EXIT_FAILURE;
        }
        else if (jack == 0)
        {
            // On est dans Jack.
            char nomTube0[10];
            sprintf(nomTube0, "%i", p_jack[0]);
            char nomTube1[10];
            sprintf(nomTube1, "%i", p_jack[1]);
            wait(&joe);
            execl("./covering", "Jack", nomTube0, nomTube1, NULL);
            perror("Error execl");
            return EXIT_FAILURE;
        }
        else
        {
            william = fork();
            if (william == -1)
            {
                perror("Création impossible !");
                return EXIT_FAILURE;
            }
            else if (william == 0)
            {
                // On est dans William.
                char nomTube0[10];
                sprintf(nomTube0, "%i", p_william[0]);
                char nomTube1[10];
                sprintf(nomTube1, "%i", p_william[1]);
                wait(&jack);
                execl("./covering", "William", nomTube0, nomTube1, NULL);
                perror("Error execl");
                return EXIT_FAILURE;
            }
            else
            {
                averell = fork();
                if (averell == -1)
                {
                    perror("Création impossible !");
                    return EXIT_FAILURE;
                }
                else if (averell == 0)
                {
                    // On est dans Averell.
                    char nomTube0[10];
                    sprintf(nomTube0, "%i", p_averell[0]);
                    char nomTube1[10];
                    sprintf(nomTube1, "%i", p_averell[1]);
                    wait(&william);
                    execl("./covering", "Averell", nomTube0, nomTube1, NULL);
                    perror("Error execl");
                    return EXIT_FAILURE;
                }
                else
                {
                    // On est dans Ma.
                    // On ferme la lecture des tubes
                    close(p_joe[0]);
                    close(p_jack[0]);
                    close(p_william[0]);
                    close(p_averell[0]);
                    // On ouvre le fichier à traiter.
                    int fichier = open("TP6_cailloux.txt", O_RDONLY);
                    if (fichier == -1)
                    {
                        perror("Error open src");
                        return EXIT_FAILURE;
                    }
                    // On écrit ce qui nous intéresse dans les tubes.
                    char temp_char;
                    while (read(fichier, &temp_char, sizeof(char)) > 0)
                    {
                        switch (temp_char)
                        {
                        case 'r':
                            if (write(p_joe[1], &temp_char, sizeof(char)) != sizeof(char))
                            {
                                perror("Error copy");
                                return EXIT_FAILURE;
                            }
                            break;
                        case 'g':
                            if (write(p_william[1], &temp_char, sizeof(char)) != sizeof(char))
                            {
                                perror("Error copy");
                                return EXIT_FAILURE;
                            }
                            break;
                        case 'n':
                            if (write(p_jack[1], &temp_char, sizeof(char)) != sizeof(char))
                            {
                                perror("Error copy");
                                return EXIT_FAILURE;
                            }
                            break;
                        case 'v':
                            if (write(p_averell[1], &temp_char, sizeof(char)) != sizeof(char))
                            {
                                perror("Error copy");
                                return EXIT_FAILURE;
                            }
                            break;
                        default:
                            printf("Il y un caractère innaproprié dans le txt.");
                            break;
                        }
                    }
                    temp_char = 'e';
                    if (write(p_joe[1], &temp_char, sizeof(char)) != sizeof(char) || write(p_william[1], &temp_char, sizeof(char)) != sizeof(char) || write(p_jack[1], &temp_char, sizeof(char)) != sizeof(char) || write(p_averell[1], &temp_char, sizeof(char)) != sizeof(char))
                    {
                        perror("Error final_copy");
                        return EXIT_FAILURE;
                    }
                    // On ferme l'écriture des tubes.
                    close(p_joe[1]);
                    close(p_william[1]);
                    close(p_jack[1]);
                    close(p_averell[1]);

                    // On ferme les fichiers.
                    close(fichier);

                    // On attends les fils.
                    wait(&joe);
                    wait(&jack);
                    wait(&william);
                    wait(&averell);
                    fflush(stdout);
                    printf("Fin...\n");
                }
            }
        }
    }
    return EXIT_SUCCESS;
}