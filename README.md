# COP4520Assignment1
Programming Assignment 1

Here is a multi-threaded program that finds the count of prime numbers from 0 to 10^8 as well as the sum of all the prime numbers found and the top 10 biggest prime numbers ordered from lowest to highest. 

In this repository there are 2 programs: hw1COP4520Sieve.java and hw1COP4520NonSieve.java. The former uses the Sieve of Eratosthenes with multi-threading to efficiently arrive at a solution. The latter uses a naive approach of checking each number for primality by trying to divide it by all numbers of to the square root of that number, with multi-threading as well. The sieve approach is significanltly faster than that of the naive approach. However, the space complexity of the sieve approach is 10^8 while the naive approach has constant space complexity. 

Naive approach summary:
In this approach we make 8 threads and then compute isPrime(int n) a function that check if the number that was passed is prime. This function checks by trying to divide the number passed by all numbers smaller than that of its square root. By having 8 threads we are able to have 8 different isPrime()'s running at the same time and whenever a thread finishes computing isPrime() they go ahead and compute isPrime() for the next available number which we find by having an atomic integer that keeps track of what number we're currently at. 

Sieve approach summary:
In this approach we make 8 threads and then compute a prime sieve on the next available prime number by looking at an atomic integer which keeps the index we're at. Once we find a prime number, we multiply that number by 2 and mark all the numbers we find as not prime, since they are multiples of the original number. By doing this approach, we are filling out an array of booleans which contains all the locations of prime and composite numbers. Then all there is to do is loop through the array and count how many are "false" meaning "not composite", and we add them to the total prime count and prime sum.

To compile and run the programs:
1. Download the file onto your computer.
2. In the command prompt, CD into the directory where that file is.
3. Type "javac hw1COP4520Sieve.java" for the Sieve program OR "javac hw1COP4520NonSieve.java" for the Non-Sieve program.
4. Type "java hw1COP4520Sieve > primes.txt" for the Sieve program OR  "java hw1COP4520NonSieve > primes.txt" for the Non-Sieve program.
5. You will find the output of the program you choose to run in a file called primes.txt.
