#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef unsigned char UDFParaEnCodeLen;

struct UDFParaEncodeMap{
    const char * mapKey[1];
    const char * mapValue[1] ; 
    size_t size;
};
extern struct UDFParaEncodeMap udfParaEncodeMap = {
        // The values must be 4-bytes shorter than their keys
        // Because we will do inplace encoding, and 3 bytes for signature
        .mapKey = { "com/teradata/fnc" },
        // Teradata fnc package delimited by slash.
        .mapValue = { "TFPS" },
        .size = 1
};

static unsigned int enCodeUDFParameterSignature(char * toBeEncoded,
        unsigned char encodingMethod)
{
    switch (encodingMethod)
    {
    case 0:
    {
        char * stp = 0, *cp = 0;
        int mapIndex = 0;
        size_t originalLength = strlen(toBeEncoded);
        // Static string mapping method
        for (; mapIndex < udfParaEncodeMap.size; ++mapIndex)
        {
            stp = strstr(toBeEncoded, udfParaEncodeMap.mapKey[mapIndex]);
            if (stp)
            {
                // found a substring in toBeEncoded matches an element in the coding map
                cp = stp;
                *cp = 2;
                *(++cp) = (UDFParaEnCodeLen) strlen(
                        udfParaEncodeMap.mapValue[mapIndex]);
                memcpy(++cp, udfParaEncodeMap.mapValue[mapIndex],
                        strlen(udfParaEncodeMap.mapValue[mapIndex]));
                cp += strlen(udfParaEncodeMap.mapValue[mapIndex]);
                *cp = 2;
                cp++;
                int sufLen = toBeEncoded + originalLength
                        - (stp + strlen(udfParaEncodeMap.mapKey[mapIndex]));
                memcpy(cp, stp + strlen(udfParaEncodeMap.mapKey[mapIndex]), sufLen);
                cp += sufLen;
                *cp = '\0';
                cp ++;
                return cp - toBeEncoded;
            }
        }
        break;
    }
        // Other encoding methods could be added here, such as self/foreigner-order, huffman coding.
    }
    return 0;
}

unsigned int deCodeUDFParameterSignature(char * toBedeCoded, size_t maxLen,
        unsigned char encodingMethod)
{
    switch (encodingMethod)
    {
    case 0:
    {
        // Static string mapping method
        char * stp = 0, *cp = 0;
        char sign = 2;
        int mapIndex = 0;
        // check the decoding signature
        if (!(stp = strstr(toBedeCoded, &sign)))
            return 0;
        UDFParaEnCodeLen lenOfValue = *(stp + 1);
        if (lenOfValue <= 0 || *(stp + 2 + lenOfValue) != 2)
            return 0;
        
        cp = stp + 2;
        // search the coding map using
        for (; mapIndex < udfParaEncodeMap.size; ++mapIndex)
        {
            if (!(memcmp(cp, udfParaEncodeMap.mapValue[mapIndex], lenOfValue)))
            {
                size_t endPos = strlen(toBedeCoded) - strlen(udfParaEncodeMap.mapValue[mapIndex]
                     - 3 + strlen(udfParaEncodeMap.mapKey[mapIndex]));
                char * sufPos = stp + 3 + lenOfValue;
                if(endPos > maxLen)
                    // Not enough reserved space for the decoded string
                    return 0;

                memmove(stp + strlen(udfParaEncodeMap.mapKey[mapIndex]), sufPos,
                        strlen(toBedeCoded) - (sufPos-toBedeCoded));
                memcpy(stp, udfParaEncodeMap.mapKey[mapIndex], 
                        strlen(udfParaEncodeMap.mapKey[mapIndex]));
                return endPos;
            }
        }
        break;
    }
        // Other decoding methods could be added here,
    }

    return 0;
}

int main(void)
{

    char input[64] = "com/teradata/fnc/data/st_geometry.java";
    unsigned int nl = 0;
    printf("Start....\n");
    printf("%s,%u\n", input, strlen(input));
    nl = enCodeUDFParameterSignature(input, 0);
    printf("%s, %d\n", input, nl);
    nl = deCodeUDFParameterSignature(input, 64, 0);
    printf("%s, %d\n", input, nl);
    return EXIT_SUCCESS;
}
