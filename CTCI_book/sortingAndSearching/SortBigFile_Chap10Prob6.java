SortBigFile_Chap10Prob6
Question: given 20 GB file, Each line contains a string. Explain how will you sort?

Answer: We load part of file into memory and then sort. When all chunks are sorted, we merge them one by one
At the end we have fully sorted file
This algorithm is called external sort