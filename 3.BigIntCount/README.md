#BigIntCount
##1.BigIntCount
Read 92 bytes integer (BigInt) from a big endian binary file.

Count the numbers of each integer.

The output is a binary file.

In the output file , key meaning the integer and value meaning the count is separated by 0xFFFFFFFF.

This project aims to learn how to deal with the boundary inconformity between splits and BigInt.

Therefore,the last 4 bytes of the 92 bytes integer is used to focus on the core of the problem.

##2.BigBinWriter

This project creates the big-endian-binary file for IntCount test.

##3.BigBinReader

This project read file created by BigBinWriter to enable to correct of BigBinWriter..

##4.BinReader

This project aims to read the output file of IntCount.
