Discrete Event Simulation

Requirements:
Java 1.7 or newer.

Run:
1) Run DiscreteEventSimulation.jar in the console by command
   java -jar DiscreteEventSimulation.jar

2) Enter the 3 parameters
   - number of jobs (64 according to the description, but you can change this value)
   - number of tasks (20 -100)
   - number of iterations

3) The program will start the generation of DAG, calculation of parameters and processing of all jobs by 4 processors.
4) After the execution of the program console displays basic information.
   All calculations are stored in files. Each iteration is stored in a file named "IterationN.txt", 
   where N - number of iteration. Files are created in the same directory where the DiscreteEventSimulation.jar.
