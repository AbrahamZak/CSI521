#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char * argv[]) {
    //Get our input string
    char* input = argv[1];
    int len = ((int)strlen(input));
    //Establish our character set
    char characters[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    //Resulting string
    char *result = (char *) malloc(sizeof(char) * 1000);
    int index, bits = 0, pad = 0, process = 0, count = 0, temp;
    //Variables for loops
    int i, j, k = 0;
    //Take 3 characters at a time and store in process
    for (i = 0; i < len; i += 3)
    {
        //Set variables back to 0
        process = 0;
        count = 0;
        bits = 0;
        for (j = i; j < len && j <= i + 2; j++)
        {
            //Stores binary data in process
            process = process << 8;
            //Stores character in process
            process = process | input[j];
            //Incrmement count
            count++;
        }
        bits = count * 8;
        //Calculate the '=' amount
        pad = bits % 3;
        // Process 6 bits at a time from process and find value at each block
        while (bits != 0)
        {
            if (bits >= 6)
            {
                temp = bits - 6;
                index = (process >> temp) & 63;
                bits -= 6;
            }
            else
            {
                temp = 6 - bits;
                // append zeros to right if bits are less than 6
                index = (process << temp) & 63;
                bits = 0;
            }
            result[k++] = characters[index];
        }
    }
    //Add '='
    for (i = 1; i <= pad; i++)
    {
        result[k++] = '=';
    }
    //Null terminate
    result[k] = '\0';
    //Print the decoded string
    printf("%s\n", result);
    return 0;
}
