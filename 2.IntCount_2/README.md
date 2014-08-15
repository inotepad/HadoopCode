#IntCount

This project aims to learn the TextOutputFormat class.

##1.IntCount

Read 4 bytes integer from a big endian binary file.

Count the numbers of each integer.

The output is a binary file.

In the output file , key meaning the integer and value meaning the count is separated by 0xFFFFFFFF.

##2.BinWriter

This project creates the big-endian-binary file for IntCount test.

##3.BufBinWriter

This project decrease the time when creating the big file comparing to BinWriter.

##4.BinReader

This project aims to read the output file of IntCount.
