/*
  ID: ved.man1
  LANG: C
  TASK: ride
*/
#include <stdio.h>
#include <string.h>

int main()
{
        FILE *fin = fopen("ride.in", "r");
        FILE *fout = fopen("ride.out", "w");
        char comet[7] = {0}, group[7] = {0};
        int comet_product = 1, group_product = 1;
        int i = 0, n = 0;

        n = fscanf(fin, "%s\n%s", comet, group);
        for(i = 0; i < strlen (comet); i++) {
                comet_product *= ((int) comet [i] - 64);
        }
        for(i = 0; i < strlen (group); i++) {
                group_product *= ((int) group [i] - 64);
        }
        if((comet_product % 47) == (group_product % 47)) {
                fprintf(fout, "GO\n");
        } else {
                fprintf(fout, "STAY\n");
        }
        fclose(fin);
        fclose(fout);
        return 0;
}
