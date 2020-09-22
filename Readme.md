# Geektrust Lengaburu and Felicornia War

Solution for the Geektrust`s *Lengaburu and Felicornia War* problem.

## Problem Statement

Please refer to the pdf document with problem statement. Same has been copied below.
[Geektrust_in_war_java.pdf](/docs/Geektrust_in_war_java.pdf)


## Sample input & output
Your program should take the location to the test file as parameter. Input needs to be read from a text file,
and output should be printed to the console.

**Input Format**
```
"FALICORNIA_ATTACK" "FELICORNIA DEPLOYMENT"
```
<sub><sup>*"FELICORNIA DEPLOYMENT" Ex: <NO. OF HORSES>H" "<NO. OF ELEPHANTS>E" "<NO. OF ARMOURED TANKS>AT" "<NO. OF SLING GUNS>SG"*<sup/><sub/>

**Output Format**
```
"WINS/LOSES" "LENGABURU DEPLOYMENT FACING FELICORNIA"
```
<sub><sup>*"LENGABURU DEPLOYMENT FACING FELICORNIA" Ex: <NO. OF HORSES>H" "<NO. OF ELEPHANTS>E" "<NO. OF ARMOURED TANKS>AT" "<NO. OF SLING GUNS>SG"*<sup/><sub/>

**Sample Input**
```
FALICORNIA_ATTACK 100H 101E 20AT 5SG
FALICORNIA_ATTACK 150H 96E 26AT 8SG
FALICORNIA_ATTACK 250H 50E 20AT 15SG
```

**Sample Output**
```
WINS 52H 50E 10AT 3SG
WINS 75H 50E 10AT 5SG
LOSES 100H 38E 10AT 5SG
```

## How to run

### With a file from repo
```console
$ gradle run --args="src/test/resources/inputs/sample-01.txt"
```

### With a file of your own
```console
$ gradle run --args="home/Downloads/sample.txt" --info
```

### With commands inline
```console
$ gradle run --args="FALICORNIA_ATTACK 100H 101E 20AT 5SG" --info
```