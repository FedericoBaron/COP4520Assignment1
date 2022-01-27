# COP4520Assignment1
Programming Assignment 1

Here is a multi-threaded program that finds the count of prime numbers from 0 to 10^8 as well as the sum of all the prime numbers found and the top 10 biggest prime numbers ordered from lowest to highest. 

In this repository there are 2 programs: hw1COP4520Sieve.java and hw1COP4520NonSieve.java. The former uses the Sieve of Eratosthenes with multi-threading to efficiently arrive at a solution. The latter uses a naive approach of checking each number for primality by trying to divide it by all numbers of to the square root of that number. The sieve approach is significanltly faster than that of the naive approach. However, the space complexity of the sieve approach is 10^8 while the naive approach has constant space complexity. 
