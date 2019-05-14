#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char * argv[]) {
    //Get our input string
    char* input = argv[1];
    int len = ((int)strlen(input));
    //Resulting string
    char *result = (char *) malloc(sizeof(char) * 1000);
    
    //loop varibles, store the bitstream, and count the bits
    int i, j, k = 0, bitstream=0, count=0;
    
    //Process 4 characters at a time
    for (i = 0; i < len; i += 4) {
        //Reset our variables
        bitstream = 0;
        count = 0;
        for (j = 0; j < 4; j++) {
            //Store 6 bits
            if (input[i + j] != '=') {
                bitstream = bitstream << 6;
                count += 6;
            }
            
            //Find each encoded character and store in the bitstream

            //Capital letters
            if (input[i + j] >= 'A' && input[i + j] <= 'Z')
                bitstream = bitstream | (input[i + j] - 'A');
            
            //Lowercase letters
            else if (input[i + j] >= 'a' && input[i + j] <= 'z')
                bitstream = bitstream | (input[i + j] - 'a' + 26);
            
            //Numbers
            else if (input[i + j] >= '0' && input[i + j] <= '9')
                bitstream = bitstream | (input[i + j] - '0' + 52);
            
            //+
            else if (input[i + j] == '+')
                bitstream = bitstream | 62;
            
            ///
            else if (input[i + j] == '/')
                bitstream = bitstream | 63;
            
            //If we find '=' make sure to delete it
            else {
                bitstream = bitstream >> 2;
                count -= 2;
            }
        }
        
        //Store the result into result
        while (count != 0) {
            count -= 8;
            result[k++] = (bitstream >> count) & 255;
        }
    }
    
    //Null terminate
    result[k] = '\0';
    
    //Print the decoded string
    printf("%s\n", result);
    return 0;
}
