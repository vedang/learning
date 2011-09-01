#include <stdio.h>
#include <string.h>


char *test()
{
        static char *stringy;
        char test[] = "test";
        static char *a;
        int add;
        stringy = test;
        printf("\nAddresses = %u %u %u", stringy, a, &add);
        printf("\n stringy = %s", stringy);
        return stringy;
}


int test2()
{
        int add = 2;
        int add1 = 1, add2 = 2, add3 = 3;
        printf("\n test2 add = %u", &add);
        printf("\n In test2");
        printf("\n out");
        return 0;
}


int main()
{
        char *s = test();
        printf("\n %s\n", s);
        return 0;
}

